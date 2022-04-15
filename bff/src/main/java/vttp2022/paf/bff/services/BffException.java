package vttp2022.paf.bff.services;

public class BffException extends Exception {

    String reason;
    
    public String getReason() {
        return reason;
    }

    public BffException(String reason) {
        this.reason = reason;
    }
}
