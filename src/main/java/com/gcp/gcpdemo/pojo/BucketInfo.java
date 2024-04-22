package com.gcp.gcpdemo.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BucketInfo implements Serializable {
    private String bucketName;
    private String subBucketName;
    private String content;
}
