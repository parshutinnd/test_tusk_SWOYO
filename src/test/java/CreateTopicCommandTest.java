import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

import com.example.server.Storage.FileStorage;
import com.example.server.Storage.IStorage;
import com.example.server.command.CreateTopicCommand;
import com.example.server.entity.User;

public class CreateTopicCommandTest {

    private final String PATH = ".\\src\\main\\resources";
    private final String FILENAME = "TestVote.json"; 

    private final User U = new User("username");
    private final IStorage S = new FileStorage(PATH, FILENAME);


    @Test
    public void CreateTopicCommand_Test(){

        String topicName = "TestTopic" + Math.random();

        U.SetCommand(new CreateTopicCommand(S, U.GetName(), topicName));
        U.Run();

        assertNotEquals(null,S.GetTopicbyName(topicName));
    }

    @Test
    public void IsTopicUniq_Test(){

        String topicName = "TestTopic1";

        U.SetCommand(new CreateTopicCommand(S, U.GetName(), topicName));
        U.Run();

        assertEquals("Topic already exist",U.Run());
    }

}
