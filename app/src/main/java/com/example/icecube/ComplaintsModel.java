package com.example.icecube;

public class ComplaintsModel {

    String Area,Date,Dis,Issue,Name,Phone;

 ComplaintsModel(){
 }

    public ComplaintsModel(String area, String date,String dis, String issue, String name, String phone) {
        Area = area;
        Date = date;
        Dis = dis;
        Issue = issue;
        Name = name;
        Phone = phone;

    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDis() {
        return Dis;
    }

    public void setDis(String dis) {
        Dis = dis;
    }

    public String getIssue() {
        return Issue;
    }

    public void setIssue(String issue) {
        Issue = issue;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }





}
