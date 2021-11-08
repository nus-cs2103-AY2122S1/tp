package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicate.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicate.MultiplePredicates;
import seedu.address.model.person.predicate.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicate.NationalityContainsKeywordsPredicate;
import seedu.address.model.person.predicate.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicate.TagContainsKeywordsPredicate;
import seedu.address.model.person.predicate.TutorialGroupContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        String arguments = PersonUtil.getEditPersonDescriptorDetails(descriptor);
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + arguments);
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findNamePredicate() throws Exception {
        List<String> keywords = Arrays.asList("n/Mario");
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(List.of("Mario"));
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(predicate), command);
    }

    @Test
    public void parseCommand_findPhonePredicate() throws Exception {
        List<String> keywords = Arrays.asList("p/995999");
        PhoneContainsKeywordsPredicate predicate =
                new PhoneContainsKeywordsPredicate(List.of("995999"));
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(predicate), command);
    }

    @Test
    public void parseCommand_findEmailPredicate() throws Exception {
        List<String> keywords = Arrays.asList("e/acbar@rabca.com");
        EmailContainsKeywordsPredicate predicate =
                new EmailContainsKeywordsPredicate(List.of("acbar@rabca.com"));
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(predicate), command);
    }

    @Test
    public void parseCommand_findNationalityPredicate() throws Exception {
        List<String> keywords = Arrays.asList("nat/North Korean");
        NationalityContainsKeywordsPredicate predicate =
                new NationalityContainsKeywordsPredicate(List.of("North Korean"));
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(predicate), command);
    }

    @Test
    public void parseCommand_findTutorialGroupPredicate() throws Exception {
        List<String> keywords = Arrays.asList("tg/123456789");
        TutorialGroupContainsKeywordsPredicate predicate =
                new TutorialGroupContainsKeywordsPredicate(List.of("123456789"));
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(predicate), command);
    }

    @Test
    public void parseCommand_findTagPredicate() throws Exception {
        List<String> keywords = Arrays.asList("t/Wowz");
        TagContainsKeywordsPredicate predicate =
                new TagContainsKeywordsPredicate(List.of("Wowz"));
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(predicate), command);
    }

    @Test
    public void parseCommand_findMultiplePredicates() throws Exception {
        List<String> keywords = Arrays.asList("n/foo", "p/999", "e/bar@a.com", "nat/DPRK", "tg/420", "t/baz");
        MultiplePredicates predicate = new MultiplePredicates(new ArrayList<Predicate<Person>>(List.of(
                new NameContainsKeywordsPredicate(List.of("foo")),
                new PhoneContainsKeywordsPredicate(List.of("999")),
                new EmailContainsKeywordsPredicate(List.of("bar@a.com")),
                new NationalityContainsKeywordsPredicate(List.of("DPRK")),
                new TutorialGroupContainsKeywordsPredicate(List.of("420")),
                new TagContainsKeywordsPredicate(List.of("baz"))
        )));
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(predicate), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, String.format("%s: %s.\n%s",
                MESSAGE_UNKNOWN_COMMAND, "unknownCommand", ""), () -> parser.parseCommand("unknownCommand"));
    }
}
