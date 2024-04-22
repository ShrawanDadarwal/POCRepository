package com.gcp.gcpdemo.controller;

import com.gcp.gcpdemo.service.inter.StorageInter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/storage")
@RequiredArgsConstructor
public class StorageController {
    private final StorageInter storageInter;

    @PostMapping("/storageFile")
    public ResponseEntity<?> createFileIntoStorage(@RequestBody String content)  {

        return storageInter.creteFileInGCPStorage(content);
    }

    @GetMapping("/downloadFile")
    public ResponseEntity<?> downloadFile()throws IOException{

        return storageInter.downloadFileFromGCPStorage();
    }
}
