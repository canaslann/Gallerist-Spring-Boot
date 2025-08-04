package com.canaslan.enums;

import lombok.Getter;

@Getter
public enum MessageType {
    NO_RECORD_EXIST("1004", "Record is not found"),
    GENERAL_EXCEPTION("9999", "General exception"),
    TOKEN_IS_EXPIRED("1005", "Token is expired"),
    USERNAME_OR_PASSWORD_INVALID("1007", "Username or password invalid"),
    REFRESH_TOKEN_IS_EXPIRED("1008", "Refresh token was expire"),
    CURRENCY_RATES_IS_OCCURED("1009", "Döviz kuru alınamadı"),
    AMOUNT_IS_NOT_ENOUGH("1010", "Amount is not enough"),
    CAR_IS_SALED("1011", "Car is saled"),
    USERNAME_NOT_FOUND("1006", "Username not found");
	

    private String code;

    private String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
