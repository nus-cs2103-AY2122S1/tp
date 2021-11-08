package dash.logic.commands.personcommand;

import static dash.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import dash.logic.commands.exceptions.CommandException;
import dash.model.AddressBook;
import dash.model.Model;
import dash.model.ModelManager;
import dash.model.UserInputList;
import dash.model.UserPrefs;
import dash.model.person.Person;
import dash.model.task.TaskList;
import dash.testutil.Assert;
import dash.testutil.EditPersonDescriptorBuilder;
import dash.testutil.PersonBuilder;
import dash.testutil.TypicalIndexes;
import dash.testutil.TypicalPersons;

class TagPersonCommandTest {

    private AddressBook addressBook = new AddressBook();
    private Model model = new ModelManager(addressBook, new UserPrefs(), new TaskList(),
            new UserInputList());

    @Test
    public void constructor_nullTagPerson_throwsNullPointerException() {
        EditPersonCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder(TypicalPersons.ALICE).build();
        Assert.assertThrows(NullPointerException.class, () -> new TagPersonCommand(null, descriptor));
        Assert.assertThrows(NullPointerException.class, ()-> new TagPersonCommand(TypicalIndexes.INDEX_FIRST,
                null));
    }

    @Test
    public void execute_oneTagSpecifiedUnfilteredList_success() {
        Person personToAdd = new PersonBuilder().withName("Bob").build();
        model.addPerson(personToAdd);

        Person person = model.getFilteredPersonList().get(0);
        Person editedPerson = new PersonBuilder(person).withTags("tag1").build();
        EditPersonCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        TagPersonCommand tagPersonCommand = new TagPersonCommand(TypicalIndexes.INDEX_FIRST, descriptor);

        String expectedMessage = String.format(TagPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                new TaskList(), new UserInputList());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(tagPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_moreThanOneTagSpecifiedUnfilteredList_success() {
        Person personToAdd = new PersonBuilder().withName("Bob").build();
        model.addPerson(personToAdd);

        Person person = model.getFilteredPersonList().get(0);
        Person editedPerson = new PersonBuilder(person).withTags("tag1", "tag2", "tag3").build();
        EditPersonCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        TagPersonCommand tagPersonCommand = new TagPersonCommand(TypicalIndexes.INDEX_FIRST, descriptor);

        String expectedMessage = String.format(TagPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                new TaskList(), new UserInputList());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(tagPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_tagSpecifiedUnfilteredList_throwsCommandException() {
        Person personToAdd = new PersonBuilder().withName("Bob").build();
        model.addPerson(personToAdd);

        Person person = model.getFilteredPersonList().get(0);
        Person editedPerson = new PersonBuilder(person).withTags("tag1", "tag2", "tag3").build();
        EditPersonCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        TagPersonCommand tagPersonCommand = new TagPersonCommand(TypicalIndexes.INDEX_THIRD, descriptor);

        Assert.assertThrows(CommandException.class, () -> tagPersonCommand.execute(model));
    }

    @Test
    public void equals() {
        EditPersonCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder(TypicalPersons.ALICE).build();
        TagPersonCommand tagFirstCommand = new TagPersonCommand(TypicalIndexes.INDEX_FIRST, descriptor);
        TagPersonCommand tagSecondCommand = new TagPersonCommand(TypicalIndexes.INDEX_SECOND, descriptor);

        // same object -> returns true
        assertTrue(tagFirstCommand.equals(tagFirstCommand));

        // same values -> returns true
        TagPersonCommand tagFirstCommandCopy = new TagPersonCommand(TypicalIndexes.INDEX_FIRST, descriptor);
        assertTrue(tagFirstCommand.equals(tagFirstCommandCopy));

        // different types -> returns false
        assertFalse(tagSecondCommand.equals(1));

        // null -> returns false
        assertFalse(tagFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(tagFirstCommand.equals(tagSecondCommand));
    }

}
