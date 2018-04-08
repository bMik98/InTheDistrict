package com.akurus.telegram.entity;

public class Advert {
    private String Name;
    private String DateStart;
    private String Nomer;
    private String Adres;
    private String Text;
    private String Url;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDateStart() {
        return DateStart;
    }

    public void setDateStart(String dateStart) {
        DateStart = dateStart;
    }

    public String getNomer() {
        return Nomer;
    }

    public void setNomer(String nomer) {
        Nomer = nomer;
    }

    public String getAdres() {
        return Adres;
    }

    public void setAdres(String adres) {
        Adres = adres;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}