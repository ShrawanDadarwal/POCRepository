package com.gcp.gcpdemo.service;

import com.gcp.gcpdemo.service.inter.StorageInter;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class StorageService implements StorageInter {
    private final Storage storage;
    @Value("gs://spring-bucket-shrawandadarwal/my-file.txt")
    private Resource gcsFile;

    @Override
    public ResponseEntity<?> creteFileInGCPStorage(String content) {
//        gcp-storage-bucket-poc

        storage.create(
                BlobInfo.newBuilder("test-storage-service-poc", "test.json").build(),
                content.getBytes());
        return ResponseEntity.ok("The Data successfully created!! in the storage ");
    }
    public ResponseEntity<?> creteNewFileInNewBucket(String content) {
//        gcp-storage-bucket-poc
        Bucket bucket = storage.create(BucketInfo.of("test-storage-service-poc"));

        storage.create(
                BlobInfo.newBuilder("test-storage-service-poc", "test.txt").build(),
                content.getBytes());
        return ResponseEntity.ok("The Data successfully created!! in the storage ");
    }
    public String readGcsFile() throws IOException {
        return StreamUtils.copyToString(
                gcsFile.getInputStream(),
                Charset.defaultCharset());

    }
    String writeGcs(@RequestBody String data) throws IOException {
        try (OutputStream os = ((WritableResource) gcsFile).getOutputStream()) {
            os.write(data.getBytes());
        }
        return "file was updated\n";
    }

    @Override
    public ResponseEntity<?> downloadFileFromGCPStorage() throws IOException {
       String status="";
        File tempFile = File.createTempFile("abc", "cbd");
        try (FileOutputStream outputStream = new FileOutputStream(tempFile)){
            String writingContent = StreamUtils.copyToString(
                    gcsFile.getInputStream(),
                    Charset.defaultCharset());


                outputStream.write(writingContent.getBytes());

            status="Files has been created at location : "+tempFile.getAbsolutePath();
        }catch (IOException exception){

            status =exception.getMessage();
        }
        return ResponseEntity.ok(status);
    }
}
