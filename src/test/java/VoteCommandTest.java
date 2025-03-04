import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

import com.example.server.Storage.FileStorage;
import com.example.server.Storage.IStorage;
import com.example.server.command.CreateTopicCommand;
import com.example.server.command.CreateVoteCommand;
import com.example.server.command.ViewTopicVoteCommand;
import com.example.server.command.VoteCommand;
import com.example.server.entity.User;

public class VoteCommandTest {
    private final String PATH = ".\\src\\main\\resources";
    private final String FILENAME = "TestVote.json"; 

    private final User U = new User("username");
    private final IStorage S = new FileStorage(PATH, FILENAME);

    @Test
    public void CreateVoteCommand_Test() {

        String topicName = "CreateVoteTopic";
        String voteName = "ForTestCommand";
        String[] options = {"Work","NotWork"};
        
        U.SetCommand(new CreateTopicCommand(S, U.GetName(), topicName));
        U.Run();
        U.SetCommand(new CreateVoteCommand(S, topicName, voteName, options));
        U.Run();

        U.SetCommand(new ViewTopicVoteCommand(S, topicName, voteName));
        String before = U.Run();

        U.SetCommand(new VoteCommand(S, topicName, voteName, "Work"));
        U.Run();

        U.SetCommand(new ViewTopicVoteCommand(S, topicName, voteName));
        String after = U.Run();
        assertNotEquals(before,after);
    }
}
