package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.FindTagCaseSensitiveCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class FindTagCaseSensitiveCommandIntegrationTest {
    private FindTagCaseSensitiveCommandParser parser = new FindTagCaseSensitiveCommandParser();

    //Integration test with command result, FindTagCaseInsensitiveCommand, model and FindTagCaseSensitiveCommandParser
    @Test
    public void parse_oneTag_returnsCorrectCommandResult() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        try {
            CommandResult parserCommandResult = parser.parse("friends").execute(model);
            CommandResult expectCommandResult = new CommandResult(generateMessage(3));
            assertEquals(expectCommandResult, parserCommandResult);
        } catch (ParseException e) {
            //Not supposed to get a parse error
            assert false;
        }
    }

    @Test
    public void parse_multipleTags_returnsCorrectCommandResultWithMultiplePeople() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        try {
            CommandResult parserCommandResult = parser.parse("friends owesMoney").execute(model);
            CommandResult expectCommandResult = new CommandResult(generateMessage(2));
            assertEquals(expectCommandResult, parserCommandResult);
        } catch (ParseException e) {
            //Not supposed to get a parse error
            assert false;
        }
    }

    @Test
    public void parse_multipleTags_returnsCorrectCommandResultWithNoPeople() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        try {
            CommandResult parserCommandResult = parser.parse("friends owesMoney husband").execute(model);
            CommandResult expectCommandResult = new CommandResult(generateMessage(0));
            assertEquals(expectCommandResult, parserCommandResult);
        } catch (ParseException e) {
            //Not supposed to get a parse error
            assert false;
        }
    }

    @Test
    public void parse_multipleTagsCaseInsensitive_returnsCorrectCommandResultWithNoPeople() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        try {
            CommandResult parserCommandResult = parser.parse("friEnDs OwesMoney").execute(model);
            System.out.println(parserCommandResult.getFeedbackToUser());
            CommandResult expectCommandResult = new CommandResult(generateMessage(0));
            assertEquals(expectCommandResult, parserCommandResult);
        } catch (ParseException e) {
            //Not supposed to get a parse error
            assert false;
        }
    }

    private String generateMessage(int numPeople) {
        return String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, numPeople);
    }
}
