package tutoraid.logic.commands;

import static tutoraid.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import tutoraid.model.LessonBook;
import tutoraid.model.Model;
import tutoraid.model.ModelManager;
import tutoraid.model.StudentBook;
import tutoraid.model.UserPrefs;
import tutoraid.testutil.TypicalLessons;
import tutoraid.testutil.TypicalStudents;

public class ClearCommandTest {

    @Test
    public void execute_emptyStudentBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyStudentBook_success() {
        Model model = new ModelManager(TypicalStudents.getTypicalStudentBook(),
                TypicalLessons.getTypicalLessonBook(), new UserPrefs());
        Model expectedModel = new ModelManager(TypicalStudents.getTypicalStudentBook(),
                TypicalLessons.getTypicalLessonBook(), new UserPrefs());
        expectedModel.setStudentBook(new StudentBook());
        expectedModel.setLessonBook(new LessonBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
