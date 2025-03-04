package com.example.server.command;

import com.example.server.Storage.IStorage;
import com.example.server.entity.User;

public class DeleteVoteComand implements ICommand{
 
    private final IStorage storage;
    private final User User;
    private final String TopicName;
    private final String VoteName;

    public DeleteVoteComand(IStorage s,User user,String topicName,String voteName){
        this.storage = s;
        this.User = user;
        this.VoteName = voteName;
        this.TopicName = topicName;
    }

    @Override
    public String Exequte() {
        var t = storage.GetTopicbyName(TopicName);
        if (t == null){
            return "Topic not found";
        }
        if(t.GetVotebyName(VoteName) == null) {
            return "Vote not found";
        }
        if (t.GetCreatorName() == null ? User.GetName() == null : t.GetCreatorName().equals(User.GetName())){
            t.DeleteVote(VoteName);
            storage.Save();
        } else {
            return "Only creator can delete vote";
        }
        return "Sucssecfully deleted topic";
    }

}
