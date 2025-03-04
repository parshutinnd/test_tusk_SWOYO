package com.example.server.command;

import com.example.server.Storage.IStorage;

public class ViewCommand implements ICommand{

    private final IStorage storage;

    public ViewCommand(IStorage s){
        storage = s;
    }

    @Override
    public String Exequte() {
        return storage.GetAlltopicsName();
    }

}
