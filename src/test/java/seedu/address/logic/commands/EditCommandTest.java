package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClientId.CLIENTID_FIRST_PERSON;
import static seedu.address.testutil.TypicalClientId.CLIENTID_OUTOFBOUND;
import static seedu.address.testutil.TypicalClientId.CLIENTID_SECOND_PERSON;
import static seedu.address.testutil.TypicalClientId.CLIENTID_THIRD_PERSON;
import static seedu.address.testutil.TypicalClientId.CLIENTID_ZERO_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ClientId;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        ClientId clientId = CLIENTID_ZERO_PERSON;
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(List.of(clientId), descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        expectedModel.setPersonByClientIds(List.of(clientId), descriptor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        ClientId clientId = CLIENTID_THIRD_PERSON;
        Person thirdPerson = model.getAddressBook().getPerson(clientId);

        PersonBuilder personInList = new PersonBuilder(thirdPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(List.of(clientId), descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPersonByClientIds(List.of(clientId), descriptor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        ClientId clientId = CLIENTID_FIRST_PERSON;
        EditCommand editCommand = new EditCommand(List.of(clientId), new EditPersonDescriptor());
        Person editedPerson = model.getAddressBook().getPerson(clientId);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_batchEdit_success() {
        ClientId clientId1 = CLIENTID_FIRST_PERSON;
        ClientId clientId2 = CLIENTID_THIRD_PERSON;
        ClientId clientId3 = CLIENTID_SECOND_PERSON;
        List<ClientId> clientIdList = List.of(clientId1, clientId2, clientId3);
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptorBuilder()
                .withAddress(VALID_ADDRESS_AMY).withPhone(VALID_PHONE_BOB).build();

        EditCommand editCommand = new EditCommand(clientIdList, editPersonDescriptor);
        Person person1 = model.getAddressBook().getPerson(clientId1);
        Person person2 = model.getAddressBook().getPerson(clientId2);
        Person person3 = model.getAddressBook().getPerson(clientId3);

        Person editedPerson1 = new PersonBuilder(person1).withAddress(VALID_ADDRESS_AMY)
                .withPhone(VALID_PHONE_BOB).build();
        Person editedPerson2 = new PersonBuilder(person2).withAddress(VALID_ADDRESS_AMY)
                .withPhone(VALID_PHONE_BOB).build();
        Person editedPerson3 = new PersonBuilder(person3).withAddress(VALID_ADDRESS_AMY)
                .withPhone(VALID_PHONE_BOB).build();

        List<ClientId> clientIdlist = List.of(clientId1, clientId2, clientId3);
        List<Person> editPersonList = List.of(editedPerson1, editedPerson2, editedPerson3);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                StringUtil.joinListToString(editPersonList, "\n"));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPersonByClientIds(clientIdlist, editPersonDescriptor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePerson_failure() {
        ClientId clientId = CLIENTID_FIRST_PERSON;
        Person firstPerson = model.getAddressBook().getPerson(clientId);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(List.of(CLIENTID_THIRD_PERSON), descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }


    @Test
    public void execute_invalidPerson_failure() {
        ClientId outOfBound = CLIENTID_OUTOFBOUND;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(List.of(outOfBound), descriptor);

        assertCommandFailure(editCommand, model,
            String.format(Messages.MESSAGE_NONEXISTENT_CLIENT_ID, CLIENTID_OUTOFBOUND)
        );
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(List.of(CLIENTID_THIRD_PERSON), DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(List.of(CLIENTID_THIRD_PERSON), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(List.of(CLIENTID_SECOND_PERSON), DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(List.of(CLIENTID_FIRST_PERSON), DESC_BOB)));
    }

}
