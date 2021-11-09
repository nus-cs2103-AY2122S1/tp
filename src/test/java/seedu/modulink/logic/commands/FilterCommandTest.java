package seedu.modulink.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modulink.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.modulink.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modulink.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.modulink.commons.core.Messages;
import seedu.modulink.logic.parser.exceptions.ParseException;
import seedu.modulink.model.Model;
import seedu.modulink.model.ModelManager;
import seedu.modulink.model.UserPrefs;
import seedu.modulink.model.person.ModuleContainsKeywordsPredicate;
import seedu.modulink.model.tag.Mod;

public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() throws ParseException {
        ModuleContainsKeywordsPredicate firstPredicate =
                new ModuleContainsKeywordsPredicate(Set.of(new Mod("CS2100")));
        ModuleContainsKeywordsPredicate secondPredicate =
                new ModuleContainsKeywordsPredicate(Set.of(new Mod("CS2103T need group")));

        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate);

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noProfilesFound() throws ParseException {
        String expectedMessage = String.format(Messages.MESSAGE_NO_PERSON_LISTED, "with this module.");
        ModuleContainsKeywordsPredicate predicate = preparePredicate("CS1441");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }


    @Test
    public void execute_multipleKeywords_multiplePersonsFound() throws ParseException {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        ModuleContainsKeywordsPredicate predicate = preparePredicate("CS2100");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }


    /**
     * Parses {@code userInput} into a {@code ModuleContainsKeywordsPredicate}.
     */
    private ModuleContainsKeywordsPredicate preparePredicate(String userInput) throws ParseException {
        Set<Mod> modList = Set.of(new Mod(userInput.split("\\s+")[0]));
        return new ModuleContainsKeywordsPredicate(modList);
    }

}
