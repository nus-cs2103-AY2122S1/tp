package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_FILTER_PREFIX;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRAMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.organisation.UniqueOrganisationList;
import seedu.address.model.person.Person;

public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new UniqueOrganisationList());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            new UniqueOrganisationList());
    private Predicate<Person> predicate = unused -> true;

    @Test
    public void equals() {
        ArgumentMultimap argMultimap1 = ArgumentTokenizer.tokenize("first");
        ArgumentMultimap argMultimap2 = ArgumentTokenizer.tokenize("second");

        FilterCommand filterFirstCommand = new FilterCommand(argMultimap1);
        FilterCommand filterSecondCommand = new FilterCommand(argMultimap2);
        //same object -> returns true;
        assertTrue(filterFirstCommand.equals(filterFirstCommand));
        //same values -> return true;
        FilterCommand filterFirstCopy = new FilterCommand(argMultimap1);
        assertTrue(filterFirstCommand.equals(filterFirstCopy));
        //different types -> returns false
        assertFalse(filterFirstCommand.equals(1));
        //null -> returns false
        assertFalse(filterFirstCommand.equals(null));
        // different person -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }
    @Test
    public void execute_zeroFilterFields_showAllPersons() {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(" ", PREFIX_FACULTY, PREFIX_FRAMEWORK,
                PREFIX_LANGUAGE, PREFIX_MAJOR, PREFIX_SKILL, PREFIX_TAG);
        FilterCommand command = new FilterCommand(argumentMultimap);
        expectedModel.updateFilteredPersonList(predicate);
        String expectedMessage = String.format(MESSAGE_INVALID_FILTER_PREFIX,
                argumentMultimap.getPreamble());
        command.execute(model);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }
}
