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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

@Service
@RequiredArgsConstructor
public class StorageService implements StorageInter {
    private final Storage storage;
    @Value("gs://spring-bucket-shrawandadarwal/my-file.txt")
    private Resource gcsFile;

    @Override
    public ResponseEntity<?> creteFileInGCPStorage(String content) {
        String status = "";
        try {
            storage.create(
                    BlobInfo.newBuilder("test-storage-service-poc", "test.json").build(),
                    content.getBytes());
            status = "File successfully created!!";
        } catch (Exception e) {
            status = e.getMessage();
        }
        return ResponseEntity.ok(status);
    }

    @Override
    public ResponseEntity<?> createBucketAndNewFiles(com.gcp.gcpdemo.pojo.BucketInfo content) {
//        gcp-storage-bucket-poc
        String status = "";
        try {
            Bucket bucket = storage.create(BucketInfo.of("test-storage-service-poc"));

            storage.create(
                    BlobInfo.newBuilder("test-storage-service-poc", "test.txt").build(),
                    content.getContent().getBytes());
            status = "New Bucket and File created successfully ";
        } catch (Exception e) {
            status = e.getMessage();
        }
        return ResponseEntity.ok("The Data successfully created!! in the storage ");
    }

    @Override
    public String readGcsFile() throws IOException {
        return StreamUtils.copyToString(
                gcsFile.getInputStream(),
                Charset.defaultCharset());

    }

    @Override
    public String writeFileInGcs(String data) throws IOException {
        try (OutputStream os = ((WritableResource) gcsFile).getOutputStream()) {
            os.write(data.getBytes());
        }
        return "file was updated\n";
    }

    @Override
    public ResponseEntity<?> downloadFileFromGCPStorage() throws IOException {
        String status = "";
        File tempFile = File.createTempFile("abc", "cbd");
        try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
            String writingContent = StreamUtils.copyToString(
                    gcsFile.getInputStream(),
                    Charset.defaultCharset());


            outputStream.write(writingContent.getBytes());

            status = "Files has been created at location : " + tempFile.getAbsolutePath();
        } catch (IOException exception) {

            status = exception.getMessage();
        }
        return ResponseEntity.ok(status);
    }


}
