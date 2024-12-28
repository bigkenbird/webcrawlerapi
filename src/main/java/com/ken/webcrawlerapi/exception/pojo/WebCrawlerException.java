package com.ken.webcrawlerapi.exception.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ken.chen
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WebCrawlerException extends RuntimeException {
    public WebCrawlerException(String message){
        super(message);
    }
}
