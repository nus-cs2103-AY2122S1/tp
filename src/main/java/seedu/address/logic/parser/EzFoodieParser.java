package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.PermissionException;
import seedu.address.commons.status.ExecutionStatus;
import seedu.address.commons.status.LoginStatus;
import seedu.address.commons.util.CommandUtil;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.LoginCommand;
import seedu.address.logic.commands.LogoutCommand;
import seedu.address.logic.commands.RedeemCommand;
import seedu.address.logic.commands.SetAccountCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SummaryCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Parses user input.
 */
public class EzFoodieParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private final Model model;
    private final ExecutionStatus executionStatus;

    /**
     * Constructs a {@code EzFoodieParser} with the given {@code Model}.
     */
    public EzFoodieParser(Model model) {
        this.model = model;
        this.executionStatus = ExecutionStatus.NORMAL;
    }

    /**
     * Constructs a {@code EzFoodieParser} with the given {@code Model} and {@code ExecutionStatus}.
     */
    public EzFoodieParser(Model model, ExecutionStatus executionStatus) {
        this.model = model;
        this.executionStatus = executionStatus;
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string.
     * @return the command based on the user input.
     * @throws ParseException if the user input does not conform the expected format.
     * @throws PermissionException if the user does not have insufficient permission.
     */
    public Command parseCommand(String userInput) throws ParseException, PermissionException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        CommandUtil.addCommand(userInput);

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandPrefixParser(model, executionStatus).parse(arguments).parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandPrefixParser(executionStatus).parse(arguments).parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case RedeemCommand.COMMAND_WORD:
            return new RedeemCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            if (LoginStatus.getLoginStatus() == LoginStatus.MANAGER) {
                return new DeleteCommandPrefixParser().parse(arguments).parse(arguments);
            }
            throw new PermissionException(Messages.MESSAGE_PERMISSION_DENIED);

        case ClearCommand.COMMAND_WORD:
            if (LoginStatus.getLoginStatus() == LoginStatus.MANAGER) {
                return new ClearCommand();
            }
            throw new PermissionException(Messages.MESSAGE_PERMISSION_DENIED);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            if (LoginStatus.getLoginStatus() == LoginStatus.MANAGER) {
                return new SortCommandParser().parse(arguments);
            }
            throw new PermissionException(Messages.MESSAGE_PERMISSION_DENIED);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case LoginCommand.COMMAND_WORD:
            return new LoginCommandParser().parse(arguments);

        case LogoutCommand.COMMAND_WORD:
            return new LogoutCommand();

        case SetAccountCommand.COMMAND_WORD:
            if (LoginStatus.getLoginStatus() == LoginStatus.MANAGER) {
                return new SetAccountCommandParser().parse(arguments);
            }
            throw new PermissionException(Messages.MESSAGE_PERMISSION_DENIED);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case SummaryCommand.COMMAND_WORD:
            if (LoginStatus.getLoginStatus() == LoginStatus.MANAGER) {
                return new SummaryCommand();
            }
            throw new PermissionException(Messages.MESSAGE_PERMISSION_DENIED);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
