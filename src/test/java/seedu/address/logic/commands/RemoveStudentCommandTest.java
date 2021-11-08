package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.tuition.TuitionClass;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailureWithoutException;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClasses.addTypicalClassesToAddressBook;
import static seedu.address.testutil.TypicalClasses.getAddressBookWithTypicalClasses;
import static seedu.address.testutil.TypicalClasses.getTypicalClasses;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_TENTH;
import static seedu.address.testutil.TypicalStudents.getAddressBookWithTypicalStudents;

public class RemoveStudentCommandTest {

    private Model model = new ModelManager(addTypicalClassesToAddressBook(getAddressBookWithTypicalStudents()),
            new UserPrefs());

    @Test
    public void execute_classDoesNotExist_failure() {
        RemoveStudentCommand removeStudentCommand = new RemoveStudentCommand(List.of(INDEX_FIRST), INDEX_TENTH);
        assertCommandFailure(removeStudentCommand, model, Messages.MESSAGE_CLASS_NOT_FOUND);
    }

    @Test
    public void execute_removeInvalidIndexes_failure() {
        RemoveStudentCommand removeStudentCommand = new RemoveStudentCommand(List.of(INDEX_TENTH), INDEX_FIRST);
        String message = "This student is not found.";
        assertCommandFailure(removeStudentCommand, model, message);
    }

    private static Model resetModel() {
        AddressBook students = getAddressBookWithTypicalStudents();
        AddressBook classes = addTypicalClassesToAddressBook(students);
        return new ModelManager(classes, new UserPrefs());
    }
}
