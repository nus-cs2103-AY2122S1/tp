package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDate.getTypicalCountdown;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Shortcut;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code PriceCommand}.
 */
public class PriceCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalCountdown(),
            new UserPrefs(), new Shortcut());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalCountdown(),
            new UserPrefs(), new Shortcut());

    @Test
    public void execute_zeroKeywords_totalWeddingCostCalculated() {
        String expectedMessage = "Your wedding overall cost is $1200.00";
        PriceCommand command = new PriceCommand();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_singleKeywords_costByCategoryCalculated() {
        String expectedMessage = "Total price for [Florist] is $300.00";
        PriceCommand command = new PriceCommand("Florist");
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleKeywords_costByCategoryCalculated() {
        String expectedMessage = "Total price for [Florist, Photographer] is $550.00";
        List<String> keywordList = new ArrayList<>();
        keywordList.add("Florist");
        keywordList.add("Photographer");
        PriceCommand command = new PriceCommand(keywordList);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_keywordNotFound_throwsCommandException() {
        List<String> keywordList = new ArrayList<>();
        keywordList.add("Singer");
        assertThrows(CommandException.class, () -> new PriceCommand(keywordList).execute(model));
    }

    @Test
    public void equals() {

        List<String> floristCategoryKeyword = new ArrayList<>();
        floristCategoryKeyword.add("Florist");

        List<String> photographerCategoryKeyword = new ArrayList<>();
        floristCategoryKeyword.add("Photographer");

        PriceCommand checkFloristPriceCommand = new PriceCommand(floristCategoryKeyword);
        PriceCommand checkPhotographerPriceCommand = new PriceCommand(photographerCategoryKeyword);

        // same object -> returns true
        assertTrue(checkFloristPriceCommand.equals(checkFloristPriceCommand));

        // same values -> returns true
        PriceCommand checkFloristPriceCommandCopy = new PriceCommand(floristCategoryKeyword);
        assertTrue(checkFloristPriceCommand.equals(checkFloristPriceCommandCopy));

        // different types -> returns false
        assertFalse(checkFloristPriceCommand.equals(1));

        // null -> returns false
        assertFalse(checkFloristPriceCommand.equals(null));

        // different person -> returns false
        assertFalse(checkFloristPriceCommand.equals(checkPhotographerPriceCommand));
    }

}
