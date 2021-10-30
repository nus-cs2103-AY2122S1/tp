package tutoraid.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tutoraid.logic.commands.AddLessonCommand;
import tutoraid.logic.commands.AddStudentCommand;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.student.Student;
import tutoraid.testutil.LessonBuilder;
import tutoraid.testutil.LessonUtil;
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

    @Test
    public void parseCommand_add_lesson() throws Exception {
        Lesson lesson = new LessonBuilder().build();
        AddLessonCommand command = (AddLessonCommand) parser.parse(LessonUtil.getAddCommand(lesson));
        assertEquals(new AddLessonCommand(lesson), command);
    }
}
