package seedu.awe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.awe.commons.core.Messages.MESSAGE_EDITCONTACTCOMMAND_CANNOT_BE_EDITED;
import static seedu.awe.commons.core.Messages.MESSAGE_EDITCONTACTCOMMAND_DUPLICATE_PERSON;
import static seedu.awe.commons.core.Messages.MESSAGE_EDITCONTACTCOMMAND_EDIT_PERSON_SUCCESS;
import static seedu.awe.commons.core.Messages.MESSAGE_EDITCONTACTCOMMAND_SAME_NAME;
import static seedu.awe.commons.core.Messages.MESSAGE_EDITCONTACTCOMMAND_SAME_NUMBER;
import static seedu.awe.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.awe.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.awe.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.awe.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.awe.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.awe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.awe.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.awe.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.awe.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.awe.testutil.TypicalPersons.getTypicalAwe;

import org.junit.jupiter.api.Test;

import seedu.awe.commons.core.Messages;
import seedu.awe.commons.core.index.Index;
import seedu.awe.logic.commands.EditContactCommand.EditPersonDescriptor;
import seedu.awe.model.Awe;
import seedu.awe.model.Model;
import seedu.awe.model.ModelManager;
import seedu.awe.model.UserPrefs;
import seedu.awe.model.person.Person;
import seedu.awe.testutil.EditPersonDescriptorBuilder;
import seedu.awe.testutil.PersonBuilder;
import seedu.awe.ui.MainWindow;
import seedu.awe.ui.UiView;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditContactCommandTest {

    private Model model = new ModelManager(getTypicalAwe(), new UserPrefs());

    @Test
    public void execute_notOnContactsPage_failure() {
        MainWindow.setViewEnum(UiView.EXPENSE_PAGE);
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditContactCommand editContactCommand = new EditContactCommand(INDEX_FIRST, descriptor);

        String expectedMessage = MESSAGE_EDITCONTACTCOMMAND_CANNOT_BE_EDITED;

        assertCommandFailure(editContactCommand, model, expectedMessage);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        MainWindow.setViewEnum(UiView.CONTACT_PAGE);
        Person editedPerson = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditContactCommand editContactCommand = new EditContactCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(MESSAGE_EDITCONTACTCOMMAND_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new Awe(model.getAwe()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        MainWindow.setViewEnum(UiView.CONTACT_PAGE);
        Index indexLastPerson = Index.fromOneBased(model.getFilteredPersonList().size());
        Person lastPerson = model.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        PersonBuilder personInList = new PersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditContactCommand editContactCommand = new EditContactCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(MESSAGE_EDITCONTACTCOMMAND_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new Awe(model.getAwe()), new UserPrefs());
        expectedModel.setPerson(lastPerson, editedPerson);

        assertCommandSuccess(editContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        MainWindow.setViewEnum(UiView.CONTACT_PAGE);
        EditContactCommand editContactCommand = new EditContactCommand(INDEX_FIRST, new EditPersonDescriptor());
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(MESSAGE_EDITCONTACTCOMMAND_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new Awe(model.getAwe()), new UserPrefs());

        assertCommandSuccess(editContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        MainWindow.setViewEnum(UiView.CONTACT_PAGE);
        showPersonAtIndex(model, INDEX_FIRST);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditContactCommand editContactCommand = new EditContactCommand(INDEX_FIRST,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(MESSAGE_EDITCONTACTCOMMAND_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new Awe(model.getAwe()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(editContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sameName_failure() {
        MainWindow.setViewEnum(UiView.CONTACT_PAGE);
        showPersonAtIndex(model, INDEX_FIRST);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        EditContactCommand editContactCommand = new EditContactCommand(INDEX_FIRST,
                new EditPersonDescriptorBuilder().withName(personInFilteredList.getName().getFullName()).build());

        assertCommandFailure(editContactCommand, model, MESSAGE_EDITCONTACTCOMMAND_SAME_NAME);
    }

    @Test
    public void execute_sameNumber_failure() {
        MainWindow.setViewEnum(UiView.CONTACT_PAGE);
        showPersonAtIndex(model, INDEX_FIRST);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        EditContactCommand editContactCommand = new EditContactCommand(INDEX_FIRST,
                new EditPersonDescriptorBuilder().withPhone(personInFilteredList.getPhone().toString()).build());

        assertCommandFailure(editContactCommand, model, MESSAGE_EDITCONTACTCOMMAND_SAME_NUMBER);
    }

    @Test
    public void execute_differentNameSameNumber_failure() {
        MainWindow.setViewEnum(UiView.CONTACT_PAGE);
        showPersonAtIndex(model, INDEX_FIRST);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        EditContactCommand editContactCommand = new EditContactCommand(INDEX_FIRST,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                        .withPhone(personInFilteredList.getPhone().toString()).build());

        assertCommandFailure(editContactCommand, model, MESSAGE_EDITCONTACTCOMMAND_SAME_NUMBER);
    }


    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        MainWindow.setViewEnum(UiView.CONTACT_PAGE);
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST.getZeroBased());
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstPerson).build();
        EditContactCommand editContactCommand = new EditContactCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editContactCommand, model, MESSAGE_EDITCONTACTCOMMAND_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        MainWindow.setViewEnum(UiView.CONTACT_PAGE);
        showPersonAtIndex(model, INDEX_FIRST);

        // edit person in filtered list into a duplicate in awe book
        Person personInList = model.getAwe().getPersonList().get(INDEX_SECOND.getZeroBased());
        EditContactCommand editContactCommand = new EditContactCommand(INDEX_FIRST,
                new EditPersonDescriptorBuilder(personInList).build());

        assertCommandFailure(editContactCommand, model, MESSAGE_EDITCONTACTCOMMAND_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        MainWindow.setViewEnum(UiView.CONTACT_PAGE);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditContactCommand editContactCommand = new EditContactCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editContactCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of awe book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        MainWindow.setViewEnum(UiView.CONTACT_PAGE);
        showPersonAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of awe book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAwe().getPersonList().size());

        EditContactCommand editContactCommand = new EditContactCommand(outOfBoundIndex,
                new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editContactCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void createEditedPerson_nullPersonToEdit_failure() {

    }

    @Test
    public void equals() {
        MainWindow.setViewEnum(UiView.CONTACT_PAGE);
        final EditContactCommand standardCommand = new EditContactCommand(INDEX_FIRST, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditContactCommand commandWithSameValues = new EditContactCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearAllDataCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditContactCommand(INDEX_SECOND, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditContactCommand(INDEX_FIRST, DESC_BOB)));
    }

}
