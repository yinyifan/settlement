package com.yifan.settlement.Exception;


public enum ErrorCodeAndMsg {
    SETTLEMENT_MSG_CREATE_ALREADY_EXIST("1000","the settlement message already exist in DB! Please check the trade id again."),
    SETTLEMENT_MSG_GET_DOES_NOT_EXIST("1001", "No settlement message available for this trade id!"),
    SETTLEMENT_MSG_SSI_CODE_DOES_NOT_EXIST("1002", "there is no matching record for the ssi code provided");
    private String code;
    private String errorMsg;

    ErrorCodeAndMsg(String code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }


    public String getCode() {
        return code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }


    @Override
    public String toString() {
        return "errorCode='" + code + '\'' +
                ", errorMsg='" + errorMsg + '\'';
    }
}
