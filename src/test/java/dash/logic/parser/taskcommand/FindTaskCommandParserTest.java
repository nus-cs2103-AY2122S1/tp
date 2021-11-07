package dash.logic.parser.taskcommand;

import static dash.logic.parser.CliSyntax.PREFIX_COMPLETION_STATUS;
import static dash.logic.parser.CliSyntax.PREFIX_PERSON;
import static dash.logic.parser.CliSyntax.PREFIX_TAG;
import static dash.logic.parser.CliSyntax.PREFIX_TASK_DATE;
import static dash.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import dash.commons.core.Messages;
import dash.logic.commands.taskcommand.FindTaskCommand;
import dash.logic.commands.taskcommand.FindTaskCommand.FindTaskDescriptor;
import dash.logic.parser.CommandParserTestUtil;
import dash.model.person.Person;
import dash.model.task.TaskDate;
import dash.testutil.TypicalPersons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FindTaskCommandParserTest {

    private FindTaskCommandParser parser = new FindTaskCommandParser();
    private ObservableList<Person> people = FXCollections.observableList(TypicalPersons.getTypicalPersons());

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailureWithPersonList(parser, "     ", people,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        FindTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces for description
        FindTaskDescriptor findTaskDescriptor = new FindTaskDescriptor();
        findTaskDescriptor.setDesc(Arrays.asList("Desc1", "Desc2"));
        FindTaskCommand expectedFindTaskCommand =
                new FindTaskCommand(findTaskDescriptor);
        CommandParserTestUtil.assertParseSuccessWithPersonList(parser, "Desc1 Desc2", people,
                expectedFindTaskCommand);

        // multiple whitespaces between keywords
        CommandParserTestUtil.assertParseSuccessWithPersonList(parser, " \n Desc1 \n \t Desc2  \t", people,
                expectedFindTaskCommand);

        //description with prefix
        FindTaskDescriptor findTaskDescriptorA = new FindTaskDescriptor();
        findTaskDescriptorA.setDesc(Arrays.asList("CS2103T"));
        FindTaskCommand expectedFindTaskCommandA =
                new FindTaskCommand(findTaskDescriptorA);
        CommandParserTestUtil.assertParseSuccessWithPersonList(parser, " " + PREFIX_TASK_DESCRIPTION
                + "CS2103T", people, expectedFindTaskCommandA);

        //datetime
        FindTaskDescriptor findTaskDescriptorB = new FindTaskDescriptor();
        findTaskDescriptorB.setDate(new TaskDate("21/10/2021", true));
        FindTaskCommand expectedFindTaskCommandB =
                new FindTaskCommand(findTaskDescriptorB);
        CommandParserTestUtil.assertParseSuccessWithPersonList(parser, " " + PREFIX_TASK_DATE
                        + "21/10/2021", people, expectedFindTaskCommandB);

        //completionstatus
        FindTaskDescriptor findTaskDescriptorC = new FindTaskDescriptor();
        findTaskDescriptorC.setCompletionStatus(true);
        FindTaskCommand expectedFindTaskCommandC =
                new FindTaskCommand(findTaskDescriptorC);
        CommandParserTestUtil.assertParseSuccessWithPersonList(parser, " " + PREFIX_COMPLETION_STATUS
                + "true", people, expectedFindTaskCommandC);

        //tags
        FindTaskDescriptor findTaskDescriptorD = new FindTaskDescriptor();
        findTaskDescriptorD.setTags(Arrays.asList("Homework", "Groupwork"));
        FindTaskCommand expectedFindTaskCommandD =
                new FindTaskCommand(findTaskDescriptorD);
        String userInput = " " + PREFIX_TAG + "Homework " + PREFIX_TAG + "Groupwork";
        CommandParserTestUtil.assertParseSuccessWithPersonList(parser, userInput, people,
                expectedFindTaskCommandD);

        //person
        FindTaskDescriptor findTaskDescriptorE = new FindTaskDescriptor();
        findTaskDescriptorE.setPerson(Arrays.asList("Alice", "Benson"));
        FindTaskCommand expectedFindTaskCommandE =
                new FindTaskCommand(findTaskDescriptorE);
        userInput = " " + PREFIX_PERSON + "1 " + PREFIX_PERSON + "2";
        CommandParserTestUtil.assertParseSuccessWithPersonList(parser, userInput, people,
                expectedFindTaskCommandE);
    }

}
