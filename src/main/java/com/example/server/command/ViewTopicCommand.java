package com.example.server.command;

import com.example.server.Storage.IStorage;

public class ViewTopicCommand implements ICommand{
    private final IStorage Storage;
    private final String TopicName;

    public ViewTopicCommand(IStorage s, String topicName){
        Storage = s;
        this.TopicName = topicName;
    }

    @Override
    public String Exequte() {
        if (Storage.GetTopicbyName(TopicName) == null){
            return "Topic not found";
        }
        return Storage.GetTopicbyName(TopicName).GetVotes();
    }
}
