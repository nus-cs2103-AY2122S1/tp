package seedu.academydirectory.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.academydirectory.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.academydirectory.testutil.TypicalStudents.getTypicalAcademyDirectory;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.academydirectory.commons.core.Messages;
import seedu.academydirectory.model.Model;
import seedu.academydirectory.model.ModelManager;
import seedu.academydirectory.model.UserPrefs;
import seedu.academydirectory.model.student.Information;
import seedu.academydirectory.model.student.InformationWantedFunction;
import seedu.academydirectory.model.student.Student;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class RetrieveCommandTest {
    private final Model model = new ModelManager(getTypicalAcademyDirectory(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAcademyDirectory(), new UserPrefs());

    @Test
    public void equals() {
        InformationWantedFunction firstFunction = new InformationWantedFunction(PREFIX_EMAIL);
        InformationWantedFunction secondFunction = new InformationWantedFunction(PREFIX_TELEGRAM);

        RetrieveCommand retrieveFirstCommand = new RetrieveCommand(firstFunction);
        RetrieveCommand retrieveSecondCommand = new RetrieveCommand(secondFunction);

        // same object -> returns true
        assertEquals(retrieveFirstCommand, retrieveFirstCommand);

        // same values -> returns true
        RetrieveCommand retrieveFirstCommandCopy = new RetrieveCommand(firstFunction);
        assertEquals(retrieveFirstCommand, retrieveFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, retrieveFirstCommand);
        assertNotEquals(0, retrieveSecondCommand);

        // null -> returns false
        assertFalse(retrieveFirstCommand.equals(null));
        assertFalse(retrieveSecondCommand.equals(null));

        // different student -> returns false
        assertNotEquals(retrieveFirstCommand, retrieveSecondCommand);
    }

    @Test
    public void execute_singlePrefixNonEmptyModel() {
        InformationWantedFunction function = new InformationWantedFunction(PREFIX_TELEGRAM);

        ObservableList<Information> expectedResponse = model.getAcademyDirectory()
                .getStudentList().stream().map(Student::getTelegram)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        String expectedMessage = expectedResponse.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));

        RetrieveCommand command = new RetrieveCommand(function);
        ObservableList<Information> actualResponse = model.getFilteredStudentListView(function);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void execute_singlePrefixEmptyModel() {
        Model emptyModel = new ModelManager();

        InformationWantedFunction function = new InformationWantedFunction(PREFIX_TELEGRAM);
        String expectedMessage = String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);

        RetrieveCommand command = new RetrieveCommand(function);
        ObservableList<Information> actualResponse = emptyModel.getFilteredStudentListView(function);
        assertCommandSuccess(command, emptyModel, expectedMessage, emptyModel);
        assertEquals(FXCollections.observableArrayList(), actualResponse);
    }
}
