package com.docstorage.app.service;
import com.docstorage.app.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DocumentJPARepository extends JpaRepository<Document, String>{

}
