package com.example.server.command;

import com.example.server.Storage.IStorage;
import com.example.server.entity.Topic;

public class CreateTopicCommand implements  ICommand{

    private final IStorage Storage;
    private final String Creator;
    private final String Name;

    public CreateTopicCommand(IStorage s, String creatorName, String name) {
        this.Storage = s;
        this.Creator = creatorName;
        this.Name = name;
    }


    @Override
    public String Exequte() {
        if (Storage.GetTopicbyName(Name) != null){
            return "Topic already exist";
        }
        Storage.SaveTopic(new Topic(Creator, Name));
        return "Sucssecfully created topic";
    }

}
