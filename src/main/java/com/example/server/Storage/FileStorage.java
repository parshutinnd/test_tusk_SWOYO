package com.example.server.Storage;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.example.server.entity.Topic;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileStorage implements IStorage{

    ObjectMapper mapper = new ObjectMapper();

    private final String Path;
    private final String FileName;
    private Set<Topic> Topics;
    
    public FileStorage(String filePath,String fileName) {
        this.Path = filePath;
        this.FileName = fileName;
        this.Topics = new HashSet<>();
        Load();
    }

    @Override
    public void SaveTopic(Topic t) {
        Topics.add(t);
        this.Save();
    }

    @Override
    public String GetAlltopicsName() {

        String res = "";
        for (Topic t: Topics) {
           res += t.GetName() + ": "+ t.GetVotesCount() + "\n";
        }
        return res;
    }

    @Override
    public Topic GetTopicbyName(String name) {
        for (Topic t: Topics) {
            if (t.GetName() == null ? name == null : t.GetName().equals(name))
                return t;
        }
        return null;
    }

    @Override
    public void Save() {

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(Path + "\\" + FileName), Topics);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Load(){
        try {
            this.Topics = mapper.readValue(new File(Path + "\\" + FileName), 
                          mapper.getTypeFactory()
                                .constructCollectionType(Set.class, Topic.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
