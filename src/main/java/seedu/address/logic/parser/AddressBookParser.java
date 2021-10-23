package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.*;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCustomerCommand.COMMAND_WORD:
            return new AddCustomerCommandParser().parse(arguments);

        case AddEmployeeCommand.COMMAND_WORD:
            return new AddEmployeeCommandParser().parse(arguments);

        case AddSupplierCommand.COMMAND_WORD:
            return new AddSupplierCommandParser().parse(arguments);

        case EditCustomerCommand.COMMAND_WORD:
            return new EditCustomerCommandParser().parse(arguments);

        case EditEmployeeCommand.COMMAND_WORD:
            return new EditEmployeeCommandParser().parse(arguments);

        case EditSupplierCommand.COMMAND_WORD:
            return new EditSupplierCommandParser().parse(arguments);

        case DeleteCustomerCommand.COMMAND_WORD:
            return new DeleteCustomerCommandParser().parse(arguments);

        case DeleteEmployeeCommand.COMMAND_WORD:
            return new DeleteEmployeeCommandParser().parse(arguments);

        case DeleteSupplierCommand.COMMAND_WORD:
            return new DeleteSupplierCommandParser().parse(arguments);

        case DeleteReservationCommand.COMMAND_WORD:
            return new DeleteReservationCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCustomerCommand.COMMAND_WORD:
            return new FindCustomerCommandParser().parse(arguments);

        case FindEmployeeCommand.COMMAND_WORD:
            return new FindEmployeeCommandParser().parse(arguments);

        case FindSupplierCommand.COMMAND_WORD:
            return new FindSupplierCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case GetCustomerReservingCommand.COMMAND_WORD:
            return new GetCustomerReservingCommandParser().parse(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case SetTablesCommand.COMMAND_WORD:
            return new SetTablesCommandParser().parse(arguments);

        case CustomerCommand.COMMAND_WORD:
            return new CustomerCommand();

        case EmployeeCommand.COMMAND_WORD:
            return new EmployeeCommand();

        case SupplierCommand.COMMAND_WORD:
            return new SupplierCommand();

        case ReservationCommand.COMMAND_WORD:
            return new ReservationCommand();

        case ReserveCommand.COMMAND_WORD:
            return new ReserveCommandParser().parse(arguments);

        case CheckCommand.COMMAND_WORD:
            return new CheckCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
