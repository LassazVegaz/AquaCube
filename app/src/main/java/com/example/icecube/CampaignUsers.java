package com.example.icecube;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class CampaignUsers implements Serializable
{


    @Exclude
    private String key;
    private String name;
    private String age;
    private String area;
    public CampaignUsers(){}
    public CampaignUsers(String name, String age, String area)
    {
        this.name = name;
        this.age = age;
        this.area=area;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAge()
    {
        return age;
    }

    public void setAge(String age)
    {
        this.age = age;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea(String area)
    {
        this.area = area;
    }


    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }
}
