package com.gcp.gcpdemo.service.inter;

import com.gcp.gcpdemo.pojo.BucketInfo;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface StorageInter {
    ResponseEntity<?> creteFileInGCPStorage(String content);
    ResponseEntity<?> createBucketAndNewFiles(BucketInfo content);
    ResponseEntity<?> downloadFileFromGCPStorage()throws IOException;
    String readGcsFile()throws IOException;
    String writeFileInGcs(String content)throws IOException;
}
