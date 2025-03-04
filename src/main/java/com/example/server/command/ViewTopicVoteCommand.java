package com.example.server.command;

import com.example.server.Storage.IStorage;

public class ViewTopicVoteCommand implements ICommand{
    private final IStorage Storage;
    private final String TopicName;
    private final String VoteName;

    public ViewTopicVoteCommand(IStorage s, String topicName, String voteName){
        this.Storage = s;
        this.TopicName = topicName;
        this.VoteName = voteName;
    }

    @Override
    public String Exequte() {
        if( Storage.GetTopicbyName(TopicName) == null){
            return "Topic not found";
        } else if(Storage.GetTopicbyName(TopicName).GetVotebyName(VoteName) == null){
            return "Vote not found";
        } else {
            return Storage.GetTopicbyName(TopicName).GetVotebyName(VoteName).GetResults();
        }
    }
}
