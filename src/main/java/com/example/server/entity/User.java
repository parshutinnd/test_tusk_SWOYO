package com.example.server.entity;

import com.example.server.command.ICommand;

public class User {

    private String Name;
    ICommand command;

    public User(String name)
    {
        this.Name = name;
    }

    public String GetName(){
        return Name;
    }

    public void SetName(String newName)
    {
        Name = newName;
    }

    public void SetCommand(ICommand com)
    {
        command = com;
    }

    public String Run(){
        return command.Exequte();
    }

}
