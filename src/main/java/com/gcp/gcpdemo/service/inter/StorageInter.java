package com.gcp.gcpdemo.service.inter;

import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface StorageInter {
    ResponseEntity<?> creteFileInGCPStorage(String content);
    ResponseEntity<?> downloadFileFromGCPStorage()throws IOException;
}
