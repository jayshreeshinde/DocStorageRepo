package com.docstorage.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Document {
	@Id
	private String id;
	private int contentLength;
	private String content;
	
	public Document() {
	}
	
	public Document(String id, int contentLength, String content) {
		super();
		this.id = id;
		this.contentLength = contentLength;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getContentLength() {
		return contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Document [id=" + id + ", contentLength=" + contentLength + ", content=" + content + "]";
	}
	
	
}

