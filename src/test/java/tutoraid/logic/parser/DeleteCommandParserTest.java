package tutoraid.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tutoraid.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import org.junit.jupiter.api.Test;

import tutoraid.logic.commands.DeleteStudentCommand;

public class DeleteCommandParserTest {
    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parseCommand_deleteStudent_correctCommandReturned() throws Exception {
        DeleteStudentCommand command = (DeleteStudentCommand) parser.parse(
                DeleteStudentCommand.COMMAND_FLAG + " " + INDEX_FIRST_ITEM.getOneBased());
        assertEquals(new DeleteStudentCommand(INDEX_FIRST_ITEM), command);
    }
}
