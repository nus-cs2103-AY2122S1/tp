package tutoraid.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tutoraid.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import tutoraid.logic.commands.DeleteStudentCommand;

public class DeleteCommandParserTest {
    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parseCommand_delete_student() throws Exception {
        DeleteStudentCommand command = (DeleteStudentCommand) parser.parse(
                DeleteStudentCommand.COMMAND_FLAG + " " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new DeleteStudentCommand(INDEX_FIRST_STUDENT), command);
    }
}
