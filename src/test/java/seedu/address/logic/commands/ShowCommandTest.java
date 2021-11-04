package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSESSMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Assessment;
import seedu.address.model.student.Group;
import seedu.address.model.student.ID;
import seedu.address.model.student.IdContainsKeywordsPredicate;
import seedu.address.model.student.Name;
import seedu.address.model.student.NameEqualsPredicate;
import seedu.address.model.student.Student;
import seedu.address.testutil.AssessmentBuilder;
import seedu.address.testutil.GroupBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ShowCommand}.
 */
public class ShowCommandTest {

    @TempDir
    public Path testFolder;

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void constructor_nullFirstArg_throwsNullPointerException() {
        Path path = getTempFilePath("error.csv");
        assertThrows(NullPointerException.class, () ->
            new ShowCommand((Name) null, path));

        assertThrows(NullPointerException.class, () ->
            new ShowCommand((Index) null, path));

        assertThrows(NullPointerException.class, () ->
            new ShowCommand((ID) null, path));

        assertThrows(NullPointerException.class, () ->
            new ShowCommand((Assessment) null, path));

        assertThrows(NullPointerException.class, () ->
            new ShowCommand((Group) null, path));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        ShowCommand showCommand = new ShowCommand(outOfBoundIndex, null);

        assertCommandFailure(showCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidName_throwsCommandException() {
        //AMY does not exist in typicalAddressBook
        Name name = AMY.getName();
        String studentName = name.toString();

        ShowCommand showCommand = new ShowCommand(name, null);

        Predicate<Student> predicate = new NameEqualsPredicate(studentName);
        model.updateFilteredStudentList(predicate);

        assertCommandFailure(showCommand, model, ShowCommand.MESSAGE_NONEXISTENT_STUDENT);
    }

    @Test
    public void execute_invalidId_throwsCommandException() {
        //AMY does not exist in typicalAddressBook
        ID id = AMY.getId();
        String studentId = id.value;

        ShowCommand showCommand = new ShowCommand(id, null);

        Predicate<Student> predicate = new IdContainsKeywordsPredicate(List.of(studentId));
        model.updateFilteredStudentList(predicate);

        assertCommandFailure(showCommand, model, ShowCommand.MESSAGE_NONEXISTENT_STUDENT);
    }

    @Test
    public void execute_invalidGroup_throwsCommandException() {
        Group group = new GroupBuilder().withName(VALID_GROUP_TUTORIAL).build();

        ShowCommand showCommand = new ShowCommand(group, null);

        assertCommandFailure(showCommand, model, ShowCommand.MESSAGE_NONEXISTENT_GROUP);
    }

    @Test
    public void execute_invalidAssessment_throwsCommandException() {
        Assessment assessment = new AssessmentBuilder().withValue(VALID_ASSESSMENT_AMY).build();

        ShowCommand showCommand = new ShowCommand(assessment, null);

        assertCommandFailure(showCommand, model, ShowCommand.MESSAGE_NONEXISTENT_ASSESSMENT);
    }

    @Test
    public void equals() {
        ShowCommand showFirstCommand = new ShowCommand(INDEX_FIRST_PERSON, Path.of("abc.csv"));
        ShowCommand showSecondCommand = new ShowCommand(INDEX_FIRST_PERSON, Path.of("abc.csv"));

        // same object -> returns true
        assertTrue(showFirstCommand.equals(showFirstCommand));

        // same values -> returns true
        assertTrue(showFirstCommand.equals(showSecondCommand));

        // different types -> returns false
        assertFalse(showFirstCommand.equals(1));

        // null -> returns false
        assertFalse(showFirstCommand.equals(null));

        // different ID -> returns false
        showSecondCommand = new ShowCommand(INDEX_SECOND_PERSON, Path.of("abc.csv"));
        assertFalse(showFirstCommand.equals(showSecondCommand));

        //diff types of arguments -> returns false
        showSecondCommand = new ShowCommand(new Name(VALID_NAME_AMY), Path.of("abc.csv"));
        assertFalse(showFirstCommand.equals(showSecondCommand));
    }
}
