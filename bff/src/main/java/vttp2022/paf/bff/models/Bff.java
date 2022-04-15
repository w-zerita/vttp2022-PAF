package vttp2022.paf.bff.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Bff {

    private String email;
    private String name;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private String status;
    private String passphrase;
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getPassphrase() {
        return passphrase;
    }
    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }
    
    @Override
    public String toString() {
        return "Bff [dob=" + dob + ", email=" + email + ", name=" + name + ", passphrase=" + passphrase + ", phone="
                + phone + ", status=" + status + "]";
    }
    
    public Bff() {
    }

    public Bff(String email, String name, String phone, Date dob, String status, String passphrase) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.dob = dob;
        this.status = status;
        this.passphrase = passphrase;
    }
}
