package com.docstorage.app.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import org.springframework.web.bind.annotation.RestController;

import com.docstorage.app.entity.Document;
import com.docstorage.app.service.DocumentJPARepository;
import com.docstorage.app.util.RandomId;



@RestController
public class DocController {
	
	@Autowired
	private DocumentJPARepository docService;
	
@GetMapping(value="/storage/welcome")
	public String sayHi(){
		return "Hello World";
	}
	
@GetMapping(value="/storage/documents")
public ResponseEntity<List> getAllDocument(){
	List<Document> docList = new ArrayList<>();
	docList = docService.findAll();
	if (!docList.isEmpty())
		return new ResponseEntity<List>(docList,HttpStatus.OK);
	else
		return new ResponseEntity<List>(docList,HttpStatus.NOT_FOUND);	
}

	@GetMapping(value="/storage/documents/{docId}",
			produces= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> getDocumentById(@PathVariable String docId){
		Document doc = docService.findById(docId).get();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Content-Length", ""+doc.getContentLength());
		return new ResponseEntity<String>(doc.getContent(),httpHeaders,HttpStatus.OK);
	}

	@PostMapping(value="/storage/documents", 
			consumes= {MediaType.TEXT_PLAIN_VALUE},
			produces= {MediaType.TEXT_PLAIN_VALUE}
				)
	public ResponseEntity<String> addNewDocument(
					@RequestHeader("Content-Length") int length,
					@RequestBody String content) {
		String id = RandomId.generateDocumentId();
		Document document = new Document(id, length,content);
		docService.save(document);
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.add("Content-Type", "text/html; charset=us-ascii");
		httpHeader.add("Content-Length", "20");
		return new ResponseEntity<String>(id, httpHeader, HttpStatus.CREATED);
	}
	
	
	@PutMapping(value="/storage/documents/{docId}",
				consumes= {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<Void> updateDocumentById(@PathVariable String docId,
								@RequestHeader("Content-Length") int length,
								@RequestBody String content){
		
		Document document = docService.findById(docId).get();
		document.setContent(content);
		document.setContentLength(length);
		docService.save(document);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/storage/documents/{docId}")
	public ResponseEntity<Void> deleteDocumentById(@PathVariable String docId) {
		Document document = docService.findById(docId).get();
		docService.deleteById(docId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}

