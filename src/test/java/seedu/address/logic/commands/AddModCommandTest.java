package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Mod;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

class AddModCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    void execute_addMod_success() throws CommandException {
        Person editedPerson = new PersonBuilder().buildProfile();
        model.addPerson(editedPerson);
        EditCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withTags("CS2103T").build();
        AddModCommand amc = new AddModCommand(descriptor);
        amc.execute(model);
        assertTrue(model.getProfile().getMods().contains(new Mod("CS2103T")));
    }

    @Test
    public void execute_addDuplicateMod_failure() throws CommandException {
        Person editedPerson = new PersonBuilder().buildProfile();
        model.addPerson(editedPerson);
        EditCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withTags("CS2103T").build();
        AddModCommand amc = new AddModCommand(descriptor);
        amc.execute(model);
        assertTrue(model.getProfile().getMods().contains(new Mod("CS2103T")));
        assertCommandFailure(amc, model, AddModCommand.MESSAGE_DUPLICATE_MODULE);
    }
}
