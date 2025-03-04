package com.example.server.tcpserver.hendler;

import com.example.server.Storage.IStorage;
import com.example.server.command.CreateTopicCommand;
import com.example.server.command.CreateVoteCommand;
import com.example.server.command.DeleteVoteComand;
import com.example.server.command.RequestVariantsCommand;
import com.example.server.command.ViewCommand;
import com.example.server.command.ViewTopicCommand;
import com.example.server.command.ViewTopicVoteCommand;
import com.example.server.command.VoteCommand;
import com.example.server.entity.User;

public class CommandHandler {

    private final  User U;
    private final IStorage S;

    public CommandHandler(IStorage s,User u){
        this.S = s;
        U = u;
    }

    public String HandleCommand(String[] args){
        String userName = "";
        String commandName = "";
        String topicName = "";
        String voteName  = "";
        String variants = "";

        for (String arg : args) {
            if (arg.startsWith("-t=")) {
                topicName = arg.substring(3); 
            } else if (arg.startsWith("-v=")) {
                voteName = arg.substring(3); 
            } else if (arg.startsWith("-n=")) {
                topicName = arg.substring(3); 
            } else if (arg.startsWith("-vs=")) {
                variants = arg.substring(4); 
            } else if (arg.startsWith("-u=")) {
                userName = arg.substring(3); 
            } else {
                commandName += arg;
            }
        }
        
        switch (commandName) {
            case "view" -> {
                if(!"".equals(topicName) && !"".equals(voteName)){
                    U.SetCommand(new ViewTopicVoteCommand(S,topicName,voteName));
                } else if(!"".equals(topicName)){
                    U.SetCommand(new ViewTopicCommand(S, topicName));
                } else {
                    U.SetCommand(new ViewCommand(S));
                }
            }
            
            case "login" -> {
                U.SetName(userName);
                return "Susessfully logined";
            }

            case "createtopic" -> {
                U.SetCommand(new CreateTopicCommand(S,U.GetName(),topicName));
            }

            case "delete" -> {
                U.SetCommand(new DeleteVoteComand(S,U,topicName,voteName));
            }

            case "createvoteparams" -> {
                U.SetCommand(new CreateVoteCommand(S,topicName,voteName,variants.split("\\|")));
            }

            case "createvote" -> {
                return "request vote params "+ "-t=" + topicName;
            }

            case "vote" -> {
                U.SetCommand(new RequestVariantsCommand(S, topicName, voteName));
            }

            case "voteparamsresponse" -> {
                U.SetCommand(new VoteCommand(S, topicName, voteName, variants));
            }
        
            default -> {
                return "Unknown command";
            }
        }

        return U.Run();
    }

}
