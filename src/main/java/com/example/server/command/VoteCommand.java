package com.example.server.command;

import com.example.server.Storage.IStorage;

public class VoteCommand implements ICommand{

    private final IStorage Storage;
    private final String TopicName;
    private final String VoteName;
    private final String Variant;

    public VoteCommand(IStorage s,String topicName, String voteName, String variant) {
        this.Storage = s;
        this.TopicName = topicName;
        this.VoteName = voteName;
        this.Variant = variant;
    }

    @Override
    public String Exequte() {
        if( Storage.GetTopicbyName(TopicName) == null){
            return "Topic not found";
        } else if(Storage.GetTopicbyName(TopicName).GetVotebyName(VoteName) == null){
            return "Vote not found";
        } else {
            String res = Storage.GetTopicbyName(TopicName).GetVotebyName(VoteName).AddVoice(Variant);
            Storage.Save();
            return res;
        }
    }

}
