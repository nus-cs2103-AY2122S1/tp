package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ORDER;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteOrderCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditTaskCommand;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindOrderCommand;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListOrderCommand;
import seedu.address.logic.commands.ListTaskCommand;
import seedu.address.logic.commands.MarkOrderCommand;
import seedu.address.logic.commands.MarkTaskCommand;
import seedu.address.logic.commands.ShowCompletedOrders;
import seedu.address.logic.commands.ShowCompletedTasks;
import seedu.address.logic.commands.ShowIncompleteOrders;
import seedu.address.logic.commands.ShowIncompleteTasks;
import seedu.address.logic.commands.SortOrdersCommand;
import seedu.address.logic.commands.TotalOrdersCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.sort.SortDescriptor;
import seedu.address.model.sort.SortField;
import seedu.address.model.sort.SortOrdering;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;
import seedu.address.testutil.OrderBuilder;
import seedu.address.testutil.OrderUtil;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TaskUtil;

public class SalesNoteParserTest {

    private final SalesNoteParser parser = new SalesNoteParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    //======================================== TASK COMMANDS ========================================

    @Test
    public void parseCommand_addTask() throws Exception {
        Task task = new TaskBuilder().build();
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(TaskUtil.getAddTaskCommand(task));
        assertEquals(new AddTaskCommand(task), command);
    }

    @Test
    public void parseCommand_editTask() throws Exception {
        Task task = new TaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(task).build();
        EditTaskCommand command = (EditTaskCommand) parser.parseCommand(EditTaskCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TASK.getOneBased() + " " + TaskUtil.getEditTaskDescriptorDetails(descriptor));
        assertEquals(new EditTaskCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_deleteTask() throws Exception {
        DeleteTaskCommand command = (DeleteTaskCommand) parser.parseCommand(
                DeleteTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new DeleteTaskCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_markTask() throws Exception {
        MarkTaskCommand command = (MarkTaskCommand) parser.parseCommand(
                MarkTaskCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new MarkTaskCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_listTask() throws Exception {
        assertTrue(parser.parseCommand(ListTaskCommand.COMMAND_WORD) instanceof ListTaskCommand);
        assertTrue(parser.parseCommand(ListTaskCommand.COMMAND_WORD + " 3") instanceof ListTaskCommand);
    }

    @Test
    public void parseCommand_showCompletedTasks() throws Exception {
        assertTrue(parser.parseCommand(ShowCompletedTasks.COMMAND_WORD) instanceof ShowCompletedTasks);
        assertTrue(parser.parseCommand(ShowCompletedTasks.COMMAND_WORD + " 5") instanceof ShowCompletedTasks);
    }

    @Test
    public void parseCommand_showIncompleteTasks() throws Exception {
        assertTrue(parser.parseCommand(ShowIncompleteTasks.COMMAND_WORD) instanceof ShowIncompleteTasks);
        assertTrue(parser.parseCommand(ShowIncompleteTasks.COMMAND_WORD + " 5") instanceof ShowIncompleteTasks);
    }

    @Test
    public void parseCommand_findTask() throws Exception {
        List<String> keywords = Arrays.asList("buttons", "sew", "SO1");
        FindTaskCommand command = (FindTaskCommand) parser.parseCommand(
                FindTaskCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindTaskCommand(new TaskContainsKeywordsPredicate(keywords)), command);
    }

    //======================================== ORDER COMMANDS ========================================

    @Test
    public void parseCommand_markOrder() throws Exception {
        MarkOrderCommand command = (MarkOrderCommand) parser.parseCommand(
                MarkOrderCommand.COMMAND_WORD + " " + INDEX_FIRST_ORDER.getOneBased());
        assertEquals(new MarkOrderCommand(INDEX_FIRST_ORDER), command);
    }

    @Test
    public void parseCommand_addOrder() throws Exception {
        Order order = new OrderBuilder().build();
        AddOrderCommand command = (AddOrderCommand) parser.parseCommand(OrderUtil.getAddOrderCommand(order));
        assertEquals(new AddOrderCommand(order), command);
    }

    @Test
    public void parseCommand_showCompletedOrders() throws Exception {
        assertTrue(parser.parseCommand(ShowCompletedOrders.COMMAND_WORD) instanceof ShowCompletedOrders);
        assertTrue(parser.parseCommand(ShowCompletedOrders.COMMAND_WORD + " 5") instanceof ShowCompletedOrders);
    }

    @Test
    public void parseCommand_showIncompleteOrders() throws Exception {
        assertTrue(parser.parseCommand(ShowIncompleteOrders.COMMAND_WORD) instanceof ShowIncompleteOrders);
        assertTrue(parser.parseCommand(ShowIncompleteOrders.COMMAND_WORD + " 5") instanceof ShowIncompleteOrders);
    }

    @Test
    public void parseCommand_deleteOrder() throws Exception {
        DeleteOrderCommand command = (DeleteOrderCommand) parser.parseCommand(
                DeleteOrderCommand.COMMAND_WORD + " " + INDEX_FIRST_ORDER.getOneBased());
        assertEquals(new DeleteOrderCommand(INDEX_FIRST_ORDER), command);
    }

    @Test
    public void parseCommand_totalOrders() throws Exception {
        assertTrue(parser.parseCommand(TotalOrdersCommand.COMMAND_WORD) instanceof TotalOrdersCommand);
        assertTrue(parser.parseCommand(TotalOrdersCommand.COMMAND_WORD + " 5") instanceof TotalOrdersCommand);
    }

    @Test
    public void parseCommand_sortOrders() throws Exception {
        String sortField = "amount";
        String sortOrdering = "desc";
        SortDescriptor descriptor =
                new SortDescriptor(new SortField(sortField), new SortOrdering(sortOrdering));
        SortOrdersCommand expectedSortOrdersCommand = new SortOrdersCommand(descriptor);

        SortOrdersCommand command = (SortOrdersCommand) parser.parseCommand(
                SortOrdersCommand.COMMAND_WORD + " " + OrderUtil.getSortOrdersDetails(sortField, sortOrdering));
        assertEquals(expectedSortOrdersCommand, command);
    }

    @Test
    public void parseCommand_findOrder() throws Exception {
        List<String> keywords = Arrays.asList("August", "SO2", "Alice");
        FindOrderCommand command = (FindOrderCommand) parser.parseCommand(
                FindOrderCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindOrderCommand(new OrderContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_listOrder() throws Exception {
        assertTrue(parser.parseCommand(ListOrderCommand.COMMAND_WORD) instanceof ListOrderCommand);
        assertTrue(parser.parseCommand(ListOrderCommand.COMMAND_WORD + " 3") instanceof ListOrderCommand);
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
