package donnafin.logic.parser;

import static donnafin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static donnafin.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static donnafin.testutil.Assert.assertThrows;
import static donnafin.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import donnafin.logic.commands.AddCommand;
import donnafin.logic.commands.ClearCommand;
import donnafin.logic.commands.DeleteCommand;
import donnafin.logic.commands.ExitCommand;
import donnafin.logic.commands.FindCommand;
import donnafin.logic.commands.HelpCommand;
import donnafin.logic.commands.HomeCommand;
import donnafin.logic.commands.ListCommand;
import donnafin.logic.parser.exceptions.ParseException;
import donnafin.model.person.NameContainsKeywordsPredicate;
import donnafin.model.person.Person;
import donnafin.testutil.PersonBuilder;
import donnafin.testutil.PersonUtil;

/**
This class should ensure that the commands for AddressBookParser pass AND
the commands for the rest of the parser, in this case on client view parser fails.
 */
public class ContextAddressBookParserTest {

    private final AddressBookParser addressBookParser = new AddressBookParser();
    private Context context = new Context(addressBookParser);

    @BeforeEach
    public void reset() {
        context = new Context(addressBookParser);
    }

    @Test
    public void test_executeParserStrategyCommand_addressBookParserAdd() throws ParseException {
        //Test add
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) context.executeParserStrategyCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }


    @Test
    public void executeParserStrategyCommand_addressBookParserClear() throws Exception {
        assertTrue(context.executeParserStrategyCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(context.executeParserStrategyCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void executeParserStrategyCommand_addressBookParserDelete() throws Exception {
        DeleteCommand command = (DeleteCommand) context.executeParserStrategyCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void executeParserStrategyCommand_addressBookParserExit() throws Exception {
        assertTrue(context.executeParserStrategyCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(context.executeParserStrategyCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void executeParserStrategyCommand_addressBookParserFind() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) context.executeParserStrategyCommand(
                FindCommand.COMMAND_WORD + " " + String.join(" ", keywords));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void executeParserStrategyCommand_addressBookParserHelp() throws Exception {
        assertTrue(context.executeParserStrategyCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(context.executeParserStrategyCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void executeParserStrategyCommand_addressBookParserList() throws Exception {
        assertTrue(context.executeParserStrategyCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(context.executeParserStrategyCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void executeParserStrategyCommand_addressBookParserUnrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> context.executeParserStrategyCommand(""));
    }

    @Test
    public void executeParserStrategyCommand_addressBookParserUnknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> context.executeParserStrategyCommand("unknownCommand"));
    }

    //From here on out it should we will clientParser fails
    @Test
    public void executeParserStrategyCommand_clientParserHome() throws Exception {
        assertTrue(strategyIsAddressBookParser());
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> context.executeParserStrategyCommand(HomeCommand.COMMAND_WORD));
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> context.executeParserStrategyCommand(
               HomeCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void executeParserStrategyCommand_clientParserUnrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> context.executeParserStrategyCommand(""));
    }

    @Test
    public void executeParserStrategyCommand_clientParserUnknownCommand_throwsParseException() {
        assertThrows(ParseException.class,
                MESSAGE_UNKNOWN_COMMAND, ()
                -> context.executeParserStrategyCommand("unknownCommand"));
    }

    private boolean strategyIsAddressBookParser() {
        return context.getCurrentStrategyParser().equals(addressBookParser);
    }
}
