package com.example.icecube.activities.sathinka;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class CampaignOrg {
    private String campaignId;
    private String location;
    private String programType;

    public CampaignOrg(){

    }

    public CampaignOrg(String campaignId, String location, String programType) {
        this.campaignId = campaignId;
        this.location = location;
        this.programType = programType;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public String getLocation() {
        return location;
    }

    public String getProgramType() {
        return programType;
    }
}
