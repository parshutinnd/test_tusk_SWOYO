package com.example.client.entity;

import com.example.client.command.ICommand;

public class User {
    private final String Name;
    ICommand command;

    public User(String name)
    {
        this.Name = name;
    }

    public String GetName(){
        return Name;
    }

    public void SetCommand(ICommand com)
    {
        command = com;
    }

    public void Run(){
        command.Exequte();
    }
}
