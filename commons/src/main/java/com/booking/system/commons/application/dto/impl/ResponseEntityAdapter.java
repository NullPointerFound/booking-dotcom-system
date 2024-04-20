package com.booking.system.commons.application.dto.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.booking.system.commons.application.dto.Response;

public class ResponseEntityAdapter<T> extends ResponseEntity<T> {

    private ResponseEntityAdapter(
            final T body,
            final HttpStatus status
    ) {
        super(body, status);
    }

    public static <T> ResponseEntity<Response<T>> of(final T data) {
        return new ResponseEntityAdapter<>(ApiResponse.of(data), HttpStatus.OK);
    }

    public static <T> ResponseEntity<Response<T>> of(
            final T data,
            final HttpStatus status
    ) {
        return new ResponseEntityAdapter<>(ApiResponse.of(data), status);
    }


    public static ResponseEntity<Response<Void>> empty() {
        return new ResponseEntityAdapter<>(ApiResponse.of(null), HttpStatus.OK);
    }

}