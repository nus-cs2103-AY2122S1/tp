package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.MailingListCommandParser;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


class MailingListCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_defaultPrefix_success() throws CommandException {
        Set<Prefix> prefixes = MailingListCommandParser.DEFAULT_PREFIXES;

        assertEquals(
                new MailingListCommand(prefixes).execute(model),
                new CommandResult(MailingListCommand.MESSAGE_SUCCESS, CommandResult.ResultType.EXPORT_CSV));
    }

    @Test
    public void execute_somePrefix_success() throws CommandException {
        Set<Prefix> prefixes = Set.of(PREFIX_PHONE);
        assertEquals(
                new MailingListCommand(prefixes).execute(model),
                new CommandResult(MailingListCommand.MESSAGE_SUCCESS, CommandResult.ResultType.EXPORT_CSV));
    }

    @Test
    public void execute_emptyMailingList_failure() {
        model.updateFilteredPersonList(person -> false);
        assertCommandFailure(new MailingListCommand(MailingListCommandParser.DEFAULT_PREFIXES), model,
                MailingListCommand.MESSAGE_EMPTY_PERSON_LIST);
    }
}
