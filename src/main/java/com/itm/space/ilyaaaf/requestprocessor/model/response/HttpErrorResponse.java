package com.itm.space.ilyaaaf.requestprocessor.model.response;

public record HttpErrorResponse(int code, String type, String message) {
}
