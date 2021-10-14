package tutoraid.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tutoraid.logic.commands.AddStudentCommand;
import tutoraid.model.student.Student;
import tutoraid.testutil.StudentBuilder;
import tutoraid.testutil.StudentUtil;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parseCommand_add_student() throws Exception {
        Student student = new StudentBuilder().build();
        AddStudentCommand command = (AddStudentCommand) parser.parse(StudentUtil.getAddCommand(student));
        assertEquals(new AddStudentCommand(student), command);
    }
}
