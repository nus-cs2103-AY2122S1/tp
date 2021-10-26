package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.MailingListCommandParser;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;


class MailingListCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

//    @Test
//    public void execute_defaultPrefix_success() throws CommandException {
//        Set<Prefix> prefixes = MailingListCommandParser.DEFAULT_PREFIXES;
//
//        List<Person> personList = model.getFilteredPersonList().stream().collect(Collectors.toList());
//
//        assertEquals(
//                new MailingListCommand(prefixes).execute(model),
//                new CommandResult(MailingListCommand.MESSAGE_SUCCESS, personList, prefixes));
//    }
//
//    @Test
//    public void execute_somePrefix_success() throws CommandException {
//        Set<Prefix> prefixes = Set.of(PREFIX_PHONE);
//        List<Person> personList = model.getFilteredPersonList().stream().collect(Collectors.toList());
//
//        assertEquals(
//                new MailingListCommand(prefixes).execute(model),
//                new CommandResult(MailingListCommand.MESSAGE_SUCCESS, personList, prefixes));
    }
}
