package tutoraid.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import tutoraid.logic.commands.FindLessonCommand;
import tutoraid.logic.commands.FindStudentCommand;
import tutoraid.model.lesson.LessonNameContainsSubstringsPredicate;
import tutoraid.model.student.NameContainsSubstringsPredicate;

public class FindCommandParserTest {
    private FindCommandParser parser = new FindCommandParser();
    /**
     * Parses {@code userInput} into a {@code NameContainsSubstringsPredicate}.
     */
    private NameContainsSubstringsPredicate prepareFindStudentPredicate(String userInput) {
        return new NameContainsSubstringsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code LessonNameContainsSubstringsPredicate}.
     */
    private LessonNameContainsSubstringsPredicate prepareFindLessonPredicate(String userInput) {
        return new LessonNameContainsSubstringsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    @Test
    public void parseCommand_find_student() throws Exception {
        FindStudentCommand command = (FindStudentCommand) parser.parse(" -s 1");
        assertEquals(new FindStudentCommand(prepareFindStudentPredicate("1")), command);
    }

    @Test
    public void parseCommand_find_lesson() throws Exception {
        FindLessonCommand command = (FindLessonCommand) parser.parse(" -l 1");
        assertEquals(new FindLessonCommand(prepareFindLessonPredicate("1")), command);
    }
}
