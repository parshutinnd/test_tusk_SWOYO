package com.example.server.Storage;

import com.example.server.entity.Topic;

public interface IStorage {
    void Save();
    void SaveTopic(Topic t);
    String GetAlltopicsName();
    Topic GetTopicbyName(String name);
}
