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

    @ExceptionHandler(Exception.class)
    public void handleInternalServerError(Exception ex) {
        String className = ex.getStackTrace()[0].getClassName();
        int lineNumber = ex.getStackTrace()[0].getLineNumber();

        log.error("系統發生錯誤 - 來源: {} (行數: {})", className, lineNumber);
        log.error("錯誤訊息: {}", ex.getMessage(), ex);
    }
}
