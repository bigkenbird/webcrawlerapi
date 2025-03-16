package com.ken.webcrawlerapi.exception;

import com.ken.webcrawlerapi.exception.pojo.WebCrawlerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author ken.chen
 */
@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(WebCrawlerException.class)
    void handleWebCrawlerException(WebCrawlerException exception) {
        log.error(exception.getMessage());
    }
}
