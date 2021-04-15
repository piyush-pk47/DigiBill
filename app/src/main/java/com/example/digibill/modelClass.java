package com.example.digibill;

public class modelClass
{
    private String textview;
    private String textview1;
    private String textview2;

    modelClass(String textview,String textview1,String textview2)
    {
        this.textview=textview;
        this.textview1=textview1;
        this.textview2=textview2;
    }

    public String getTextview() {
        return textview;
    }

    public String getTextview1() {
        return textview1;
    }

    public String getTextview2() {
        return textview2;
    }
}
