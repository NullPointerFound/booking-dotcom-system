package com.booking.system.commons.application.dto.impl;


import com.booking.system.commons.application.dto.Response;

public class ApiResponse<T> implements Response<T> {

    private final T data;
    private final boolean success;

    public ApiResponse(T data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public static <T> Response<T> of(final T data) {
        return new ApiResponse<>(data, true);
    }

    @Override
    public T getData() {
        return this.data;
    }

    @Override
    public boolean getSuccess() {
        return this.success;
    }

}
