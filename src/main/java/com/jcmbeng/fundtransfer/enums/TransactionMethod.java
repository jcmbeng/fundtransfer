package com.jcmbeng.fundtransfer.enums;

public enum TransactionMethod {
    DESK("DES"),
    ATM("ATM"),
    FEES("FEE"),
    TRANSFER("TRA");



    private String methodeCode;
    
    TransactionMethod (String methodeCode){
        this.methodeCode = methodeCode;
    }
    public String getMethodeCode () {
        return methodeCode;
    }
    
    
}
