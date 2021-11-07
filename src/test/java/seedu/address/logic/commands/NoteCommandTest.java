package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.TYPICAL_GROUP_CS2103T;
import static seedu.address.testutil.TypicalNote.TYPICAL_STUDENT_NOTE;
import static seedu.address.testutil.TypicalNote.TYPICAL_STUDENT_NOTE_ALT;
import static seedu.address.testutil.TypicalStudents.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.student.Name;
import seedu.address.model.student.Note;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class NoteCommandTest {
    private Model model = new ModelManager();

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NoteCommand(null, new Note("Note")));
    }

    @Test
    public void constructor_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NoteCommand(new Name("Alex"), null));
    }

    @Test
    public void execute_addValidNote_successful() {
        model.addGroup(TYPICAL_GROUP_CS2103T);
        model.addStudent(ALICE);

        NoteCommand noteCommand = new NoteCommand(ALICE.getName(), TYPICAL_STUDENT_NOTE);

        String expectedMessage = String.format(NoteCommand.MESSAGE_SUCCESS, ALICE.getName(), TYPICAL_STUDENT_NOTE);

        Model expectedModel = new ModelManager();
        expectedModel.addGroup(TYPICAL_GROUP_CS2103T);

        Student editedStudent = new StudentBuilder(ALICE).withNote(TYPICAL_STUDENT_NOTE.getValue()).build();
        expectedModel.addStudent(editedStudent);

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_updateValidNote_successful() {
        model.addGroup(TYPICAL_GROUP_CS2103T);
        Student newStudent = new StudentBuilder(ALICE).withNote(TYPICAL_STUDENT_NOTE.getValue()).build();
        model.addStudent(newStudent);

        NoteCommand noteCommand = new NoteCommand(newStudent.getName(), TYPICAL_STUDENT_NOTE_ALT);

        String expectedMessage = String.format(NoteCommand.MESSAGE_SUCCESS, ALICE.getName(), TYPICAL_STUDENT_NOTE_ALT);

        Model expectedModel = new ModelManager();
        expectedModel.addGroup(TYPICAL_GROUP_CS2103T);

        Student editedStudent = new StudentBuilder(newStudent).withNote(TYPICAL_STUDENT_NOTE_ALT.getValue()).build();
        expectedModel.addStudent(editedStudent);

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }
}
