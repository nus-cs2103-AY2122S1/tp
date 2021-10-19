package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
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

class RemoveModCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    void execute_removeMod_success() throws CommandException {
        Person editedPerson = new PersonBuilder().withTags("CS2103T").buildProfile();
        model.addPerson(editedPerson);
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags("CS2103T").build();
        RemoveModCommand rmc = new RemoveModCommand(descriptor);
        rmc.execute(model);
        assertFalse(model.getProfile().getMods().contains(new Mod("CS2103T")));
    }

    @Test
    void execute_removeNonExistingMod_failure() {
        Person editedPerson = new PersonBuilder().buildProfile();
        model.addPerson(editedPerson);
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags("CS2103T").build();
        RemoveModCommand rmc = new RemoveModCommand(descriptor);
        assertCommandFailure(rmc, model, RemoveModCommand.MESSAGE_MODULE_DOES_NOT_EXIST);
    }
}
