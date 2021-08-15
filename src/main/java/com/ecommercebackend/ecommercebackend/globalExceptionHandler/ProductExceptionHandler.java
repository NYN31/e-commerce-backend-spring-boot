package com.ecommercebackend.ecommercebackend.globalExceptionHandler;

import com.ecommercebackend.ecommercebackend.pojo.exception.ProductNotFoundException;
import com.ecommercebackend.ecommercebackend.pojo.response.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {
    // product not found exception
    @ExceptionHandler
    public ResponseEntity<ProductResponse> handleException(ProductNotFoundException exc){
        ProductResponse response = new ProductResponse();

        response.statusCode = HttpStatus.NOT_FOUND.value();
        response.message = exc.getMessage();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND) ;
    }


    // pass a bad url / bad exception (catch all exception)
    @ExceptionHandler
    public ResponseEntity<ProductResponse> handleException(Exception exc){
        ProductResponse response = new ProductResponse();

        response.statusCode = HttpStatus.BAD_REQUEST.value();
        response.message = exc.getMessage();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST) ;
    }
}
