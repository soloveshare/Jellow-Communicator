package com.dsource.idc.jellowintl.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ekalpa on 6/23/2017.
 */
public class LevelTwoVerbiageModel {
    @SerializedName("arrays")
    private ArrayList<ArrayList<ArrayList<String>>> mVerbiageModel;

    public ArrayList<ArrayList<String>> getVerbiageModel(int itemPosition) {
        return mVerbiageModel.size() != 0 ? mVerbiageModel.get(itemPosition) : null;
    }
}