package com.example.dfrank.java_developer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dfrank on 6/13/17.
 */

public class ItemResponse {
    @SerializedName("items")
    @Expose
    private List<Item> items;
    public List<Item> getItem(){
        return items;
    }
    public void setItem(List<Item> items){
        this.items = items;
    }
}
