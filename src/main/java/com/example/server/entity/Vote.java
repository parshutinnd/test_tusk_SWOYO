package com.example.server.entity;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vote {
    
    @JsonProperty("name")
    private final String Name;

    @JsonProperty("variants")
    private final Map<String,Integer> Variants;

    public Vote() {
        this.Name = "";
        this.Variants = new HashMap<>();
    }

    public Vote(String name,String[] V) {
        this.Name = name;
        this.Variants = new HashMap<>();

        for (String variant : V) {
            Variants.put(variant, 0);
        }
    }

    public String AddVoice(String name){
        if (Variants.containsKey(name)){
            Variants.put(name, Variants.get(name)+1);
            return "Added voice to " + name; 
        }
        return "Variant not found";
    }
    
    public String GetName(){
        return Name;
    }


    public String GetVariants(){

        String res = "";
        for (Map.Entry<String, Integer> v : Variants.entrySet()) {
            Object key = v.getKey();
            res += key + "\n";
        }
        return res;
    }

    public String GetResults(){

        String res = "";
        res += GetName() + ": " + Variants.size() + "\n";
        for (Map.Entry<String, Integer> v : Variants.entrySet()) {
            Object key = v.getKey();
            Object val = v.getValue();
            res += key + ": " + val + "\n"; 
        }
        return res;
    }
}
