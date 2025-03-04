package com.example.server.command;

import com.example.server.Storage.IStorage;
import com.example.server.entity.Vote;

public class CreateVoteCommand implements ICommand{

    private final IStorage Storage;
    private final String TopicName;
    private final String VoteName;
    private final String[] Variants;

    public CreateVoteCommand(IStorage s,String topicName, String voteName, String[] variants) {
        this.Storage = s;
        this.TopicName = topicName;
        this.VoteName = voteName;
        this.Variants = variants;
    }

    @Override
    public String Exequte() {
        Storage.GetTopicbyName(TopicName).AddVote(new Vote(VoteName,Variants));
        Storage.Save();
        return "Sucssecfully created vote";
    }

}
