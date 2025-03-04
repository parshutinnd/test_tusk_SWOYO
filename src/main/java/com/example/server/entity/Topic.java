package com.example.server.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Topic {

    @JsonProperty("name")
    private final String Name;

    @JsonProperty("creatorName")
    private final String CreatorName;

    @JsonProperty("votes")
    private final List<Vote> Votes;
    
    public Topic() {
        this.Name = "";
        this.Votes = new ArrayList<>();
        this.CreatorName = "";
    }

    public Topic(String userName,String Name) {
        this.Name = Name;
        this.Votes = new ArrayList<>();
        this.CreatorName = userName;
    }

    public Topic(String userName,String Name,List<Vote> votes) {
        this.Name = Name;
        this.Votes = votes;
        this.CreatorName = userName;
    }


    public String GetName(){
        return Name;
    }

    public String GetCreatorName(){
        return CreatorName;
    }

    public void AddVote(Vote v)
    {
        if(Votes.contains(v)){
            Votes.remove(v);
        }
        this.Votes.add(v);
    }

    public int GetVotesCount(){
        return Votes.size();
    }

    public Vote GetVotebyName(String name) {
        for (Vote v: Votes) {
            if (v.GetName() == null ? name == null : v.GetName().equals(name))
                return v;
        }
        return null;
    }

    public String GetVotes(){
        String result = "";
        for (Vote vote : Votes) {
            result += vote.GetName();
            result += "\n";
        }
        return result;
    }

    public String GetVotesWithResults(){
        String result = "";
        for (Vote vote : Votes) {
            result += vote.GetName();
            result += "\n";
            result += vote.GetResults();
        }
        return result;
    }

    public void DeleteVote(String voteName){
        Votes.remove(GetVotebyName(voteName));
    }
}
