package seedu.modulink.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modulink.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.modulink.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.modulink.logic.commands.exceptions.CommandException;
import seedu.modulink.logic.parser.exceptions.ParseException;
import seedu.modulink.model.Model;
import seedu.modulink.model.ModelManager;
import seedu.modulink.model.UserPrefs;
import seedu.modulink.model.person.Person;
import seedu.modulink.model.tag.Mod;
import seedu.modulink.testutil.EditPersonDescriptorBuilder;
import seedu.modulink.testutil.PersonBuilder;

class AddModCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    void execute_addMod_success() throws CommandException, ParseException {
        Person editedPerson = new PersonBuilder().buildProfile();
        model.addPerson(editedPerson);
        EditCommand.EditPersonDescriptor descriptor =
                new EditPersonDescriptorBuilder().withTags("CS2103T").build();
        AddModCommand amc = new AddModCommand(descriptor);
        amc.execute(model);
        assertTrue(model.getProfile().getMods().contains(new Mod("CS2103T")));
    }

    @Test
    public void execute_addDuplicateMod_failure() throws CommandException, ParseException {
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
