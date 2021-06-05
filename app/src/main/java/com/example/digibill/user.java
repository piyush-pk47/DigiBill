package com.example.digibill;

public class user
{
    String name,mobile_number,invoice;

    public user(String name, String mobile_number, String invoice) {
        this.name = name;
        this.mobile_number = mobile_number;
        this.invoice = invoice;
    }

    public user() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }
}
