package com.gcp.gcpdemo.pojo;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@Builder
public class ResponsePojo implements Serializable {
    private HttpStatus status;
    private String responseMessage;
    private Object error;
    private Object data;
}
