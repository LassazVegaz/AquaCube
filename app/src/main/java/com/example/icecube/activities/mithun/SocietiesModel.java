package com.example.icecube.activities.mithun;

public class SocietiesModel {

    String Name,Area,Issue,Dis,Phone,Date;

    SocietiesModel(){
    }

    public SocietiesModel(String name, String area,String issue, String dis, String phone, String date) {
        Name = name;
        Area = area;
        Issue = issue;
        Dis = dis;
        Phone = phone;
        Date = date;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getIssue() {
        return Issue;
    }

    public void setIssue(String issue) {
        Issue = issue;
    }

    public String getDis() {
        return Dis;
    }

    public void setDis(String dis) {
        Dis = dis;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
