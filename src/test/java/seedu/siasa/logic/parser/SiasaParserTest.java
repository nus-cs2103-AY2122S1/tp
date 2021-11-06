package seedu.siasa.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.siasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.siasa.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.siasa.model.contact.PersonComparator.CONTACT_SORT_BY_ALPHA_ASC;
import static seedu.siasa.model.policy.PolicyComparator.POLICY_SORT_BY_PAYMENT_DSC;
import static seedu.siasa.testutil.Assert.assertThrows;
import static seedu.siasa.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.siasa.testutil.TypicalIndexes.INDEX_FIRST_POLICY;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.siasa.logic.commands.ClearCommand;
import seedu.siasa.logic.commands.DownloadCommand;
import seedu.siasa.logic.commands.ExitCommand;
import seedu.siasa.logic.commands.HelpCommand;
import seedu.siasa.logic.commands.contact.AddContactCommand;
import seedu.siasa.logic.commands.contact.ClearContactPolicyCommand;
import seedu.siasa.logic.commands.contact.DeleteContactCommand;
import seedu.siasa.logic.commands.contact.EditContactCommand;
import seedu.siasa.logic.commands.contact.EditContactCommand.EditContactDescriptor;
import seedu.siasa.logic.commands.contact.FindContactCommand;
import seedu.siasa.logic.commands.contact.ListContactCommand;
import seedu.siasa.logic.commands.contact.ListContactPolicyCommand;
import seedu.siasa.logic.commands.contact.SortContactCommand;
import seedu.siasa.logic.commands.policy.DeletePolicyCommand;
import seedu.siasa.logic.commands.policy.ListPolicyCommand;
import seedu.siasa.logic.commands.policy.ShowExpiringPolicyCommand;
import seedu.siasa.logic.commands.policy.ShowExpiringPolicySummaryCommand;
import seedu.siasa.logic.commands.policy.SortPolicyCommand;
import seedu.siasa.logic.parser.exceptions.ParseException;
import seedu.siasa.model.contact.Contact;
import seedu.siasa.model.contact.NameContainsKeywordsPredicate;
import seedu.siasa.testutil.ContactBuilder;
import seedu.siasa.testutil.ContactUtil;
import seedu.siasa.testutil.EditContactDescriptorBuilder;

public class SiasaParserTest {

    private final SiasaParser parser = new SiasaParser();

    @Test
    public void parseCommand_addContact() throws Exception {
        Contact contact = new ContactBuilder().build();
        AddContactCommand command = (AddContactCommand) parser.parseCommand(ContactUtil.getAddCommand(contact));
        assertEquals(new AddContactCommand(contact), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteContact() throws Exception {
        DeleteContactCommand command = (DeleteContactCommand) parser.parseCommand(
                DeleteContactCommand.COMMAND_WORD + " " + INDEX_FIRST_CONTACT.getOneBased());
        assertEquals(new DeleteContactCommand(INDEX_FIRST_CONTACT), command);
    }

    @Test
    public void parseCommand_deletePolicy() throws Exception {
        DeletePolicyCommand command = (DeletePolicyCommand) parser.parseCommand(
                DeletePolicyCommand.COMMAND_WORD + " " + INDEX_FIRST_POLICY.getOneBased());
        assertEquals(new DeletePolicyCommand(INDEX_FIRST_POLICY), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Contact contact = new ContactBuilder().build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(contact).build();
        EditContactCommand command = (EditContactCommand) parser.parseCommand(EditContactCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CONTACT.getOneBased() + " " + ContactUtil.getEditContactDescriptorDetails(descriptor));
        assertEquals(new EditContactCommand(INDEX_FIRST_CONTACT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_download() throws Exception {
        assertTrue(parser.parseCommand(DownloadCommand.COMMAND_WORD) instanceof DownloadCommand);
        assertTrue(parser.parseCommand(DownloadCommand.COMMAND_WORD + " 3") instanceof DownloadCommand);
    }

    @Test
    public void parseCommand_findContact() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindContactCommand command = (FindContactCommand) parser.parseCommand(
                FindContactCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindContactCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_contactPolicy() throws Exception {
        ListContactPolicyCommand command = (ListContactPolicyCommand) parser.parseCommand(
                ListContactPolicyCommand.COMMAND_WORD + " " + INDEX_FIRST_CONTACT.getOneBased());
        assertEquals(new ListContactPolicyCommand(INDEX_FIRST_CONTACT), command);
    }

    @Test
    public void parseCommand_clearPolicy() throws Exception {
        ClearContactPolicyCommand command = (ClearContactPolicyCommand) parser.parseCommand(
                ClearContactPolicyCommand.COMMAND_WORD + " " + INDEX_FIRST_CONTACT.getOneBased());
        assertEquals(new ClearContactPolicyCommand(INDEX_FIRST_POLICY), command);
    }

    @Test
    public void parseCommand_sortContact() throws Exception {
        SortContactCommand command = (SortContactCommand) parser.parseCommand(
                SortContactCommand.COMMAND_WORD + " asc");
        assertEquals(new SortContactCommand(CONTACT_SORT_BY_ALPHA_ASC), command);
    }

    @Test
    public void parseCommand_sortPolicy() throws Exception {
        SortPolicyCommand command = (SortPolicyCommand) parser.parseCommand(
                SortPolicyCommand.COMMAND_WORD + " paymentdsc");
        assertEquals(new SortPolicyCommand(POLICY_SORT_BY_PAYMENT_DSC), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listContact() throws Exception {
        assertTrue(parser.parseCommand(ListContactCommand.COMMAND_WORD) instanceof ListContactCommand);
        assertTrue(parser.parseCommand(ListContactCommand.COMMAND_WORD + " 3") instanceof ListContactCommand);
    }

    @Test
    public void parseCommand_listPolicy() throws Exception {
        assertTrue(parser.parseCommand(ListPolicyCommand.COMMAND_WORD) instanceof ListPolicyCommand);
        assertTrue(parser.parseCommand(ListPolicyCommand.COMMAND_WORD + " 3") instanceof ListPolicyCommand);
    }

    @Test
    public void parseCommand_expiringPolicy() throws Exception {
        assertTrue(parser.parseCommand(ShowExpiringPolicyCommand.COMMAND_WORD) instanceof ShowExpiringPolicyCommand);
        assertTrue(parser.parseCommand(
                ShowExpiringPolicyCommand.COMMAND_WORD + " 3") instanceof ShowExpiringPolicyCommand);
    }

    @Test
    public void parseCommand_expiringPolicySummary() throws Exception {
        assertTrue(parser.parseCommand(
                ShowExpiringPolicySummaryCommand.COMMAND_WORD) instanceof ShowExpiringPolicySummaryCommand);
        assertTrue(parser.parseCommand(
                ShowExpiringPolicySummaryCommand.COMMAND_WORD + " 3") instanceof ShowExpiringPolicySummaryCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
