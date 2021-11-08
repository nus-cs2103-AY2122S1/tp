package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COUNT_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BAGEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAGEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddToOrderCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EndAndTransactOrderCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListInventoryCommand;
import seedu.address.logic.commands.ListTransactionCommand;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.commands.RemoveFromOrderCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.StartOrderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemDescriptor;
import seedu.address.model.item.NameContainsKeywordsPredicate;
import seedu.address.testutil.ItemBuilder;
import seedu.address.testutil.ItemDescriptorBuilder;
import seedu.address.testutil.ItemUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        ItemDescriptor descriptor = new ItemDescriptorBuilder().withName(VALID_NAME_BAGEL).build();
        AddCommand command = (AddCommand) parser.parseCommand(ItemUtil.getAddCommand(descriptor));

        descriptor.setCount(1); // Parser should set descriptor count to 1
        assertEquals(new AddCommand(descriptor), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        ItemDescriptor descriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_BAGEL).build();

        Item item = new ItemBuilder().withName(VALID_NAME_BAGEL).withId(VALID_ID_BAGEL).build();
        DeleteCommand command = (DeleteCommand) parser.parseCommand(ItemUtil.getDeleteCommand(item));
        assertEquals(new DeleteCommand(descriptor), command);
    }

    @Test
    public void parseCommand_remove() throws Exception {
        ItemDescriptor descriptor = new ItemDescriptorBuilder()
                .withName(VALID_NAME_BAGEL).withId(VALID_ID_BAGEL)
                .withCount(VALID_COUNT_BAGEL).build();

        Item item = new ItemBuilder().withName(VALID_NAME_BAGEL).withId(VALID_ID_BAGEL).build();
        RemoveCommand command = (RemoveCommand) parser.parseCommand(ItemUtil.getRemoveCommand(item));
        assertEquals(new RemoveCommand(descriptor), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Item item = new ItemBuilder().build();
        ItemDescriptor descriptor = new ItemDescriptorBuilder(item).build();

        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_ITEM.getOneBased() + " " + ItemUtil.getItemDescriptorDetails(descriptor));

        assertEquals(new EditCommand(INDEX_FIRST_ITEM, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_NAME + "foo");
        assertEquals(new FindCommand(List.of(new NameContainsKeywordsPredicate(keywords))), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListInventoryCommand);

        String listTransactionInput = ListCommand.COMMAND_WORD + " " + ListTransactionCommand.TRANSACTIONS_KEYWORD;
        assertTrue(parser.parseCommand(listTransactionInput) instanceof ListTransactionCommand);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " " + PREFIX_NAME) instanceof SortCommand);
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " " + PREFIX_COUNT) instanceof SortCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        final String userGuide = "https://se-education.org/addressbook-level3/UserGuide.html";
        final String helpMessage = "Refer to the user guide: " + userGuide;
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, helpMessage), (
            ) -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_startOrder() throws Exception {
        assertTrue(parser.parseCommand(StartOrderCommand.COMMAND_WORD) instanceof StartOrderCommand);
    }

    @Test
    public void parseCommand_addToOrder() throws Exception {
        ItemDescriptor descriptor = new ItemDescriptorBuilder().withName(VALID_NAME_BAGEL).build();
        AddToOrderCommand command = (AddToOrderCommand) parser.parseCommand(
                AddToOrderCommand.COMMAND_WORD + " " + VALID_NAME_BAGEL);

        descriptor.setCount(1); // Parser should set descriptor count to 1
        assertEquals(new AddToOrderCommand(descriptor), command);
    }

    @Test
    public void parseCommand_removeFromOrder() throws Exception {
        ItemDescriptor descriptor = new ItemDescriptorBuilder().withName(VALID_NAME_BAGEL).build();
        RemoveFromOrderCommand command = (RemoveFromOrderCommand) parser.parseCommand(
                RemoveFromOrderCommand.COMMAND_WORD + " " + VALID_NAME_BAGEL);

        descriptor.setCount(1); // Parser should set descriptor count to 1
        assertEquals(new RemoveFromOrderCommand(descriptor), command);
    }

    @Test
    public void parseCommand_endAndTransactOrder() throws Exception {
        assertTrue(parser.parseCommand(EndAndTransactOrderCommand.COMMAND_WORD) instanceof EndAndTransactOrderCommand);
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
