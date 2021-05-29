package com.example.digibill;

public class item
{
    String name;
    String unit;
    String price;
    String quantiy;
    public item() {

    }
    public item(String name, String unit, String price, String quantiy) {
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.quantiy = quantiy;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantiy() {
        return quantiy;
    }

    public void setQuantiy(String quantiy) {
        this.quantiy = quantiy;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
