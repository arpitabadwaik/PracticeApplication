package com.example.user.practiceapp.model;

import com.google.gson.annotations.SerializedName;

public class JsonGSonModel {

    @SerializedName("village_code")
    public String villageCode;

    @SerializedName("village_name")
    public String villageName;

    @SerializedName("village_id")
    public String villageId;

    @SerializedName("block_name")
    public String blockName;

    @SerializedName("panchayat_name")
    public String panchayatName;

    public String getVillageCode() {
        return villageCode;
    }

    public String getVillageName() {
        return villageName;
    }

    public String getVillageId() {
        return villageId;
    }

    public String getBlockName() {
        return blockName;
    }

    public String getPanchayatName() {
        return panchayatName;
    }
}