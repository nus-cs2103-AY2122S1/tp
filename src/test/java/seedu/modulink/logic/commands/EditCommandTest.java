package seedu.modulink.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modulink.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.modulink.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.modulink.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.modulink.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.modulink.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modulink.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.modulink.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.modulink.model.AddressBook;
import seedu.modulink.model.Model;
import seedu.modulink.model.ModelManager;
import seedu.modulink.model.UserPrefs;
import seedu.modulink.model.person.Person;
import seedu.modulink.model.person.StudentId;
import seedu.modulink.testutil.EditPersonDescriptorBuilder;
import seedu.modulink.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        model.addPerson(new PersonBuilder().buildProfile());
        Person editedPerson = new PersonBuilder().buildProfile();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getProfile(), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        model.addPerson(new PersonBuilder().buildProfile());
        Person lastPerson = model.getProfile();

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).build();
        EditCommand editCommand = new EditCommand(descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentId_failure() {
        StudentId id = model.getFilteredPersonList().get(0).getStudentId();
        model.addPerson(new PersonBuilder().buildProfile());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName(VALID_NAME_BOB).withStudentId(id.toString()).build();
        EditCommand editCommand = new EditCommand(descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STUDENT_ID);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ListCommand()));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(DESC_BOB)));
    }

}
