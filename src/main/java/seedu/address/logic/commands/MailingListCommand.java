package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;

/**
 * Exports the current view as a mailing list in the csv file format.
 */
public class MailingListCommand extends Command {

    public static final String COMMAND_WORD = "mailingList";
    public static final String COMMAND_DESCRIPTION = "Exports the current view as a mailing list.\n";
    public static final String COMMAND_EXAMPLE = "Parameters: "
            + "[" + PREFIX_PHONE + "] "
            + "[" + PREFIX_EMAIL + "] "
            + "[" + PREFIX_ADDRESS + "] "
            + "[" + PREFIX_BIRTHDAY + "] "
            + "[" + PREFIX_TAG + "] \n"
            + "Default export is Phone numbers and Email addresses.\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_PHONE + " " + PREFIX_EMAIL;
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": " + COMMAND_DESCRIPTION + COMMAND_EXAMPLE;
    public static final String MESSAGE_EXTRA_ARGUMENTS_FAILURE = "Invalid fields selected.\n" + COMMAND_EXAMPLE;
    public static final String MESSAGE_EMPTY_PERSON_LIST = "Trying to export an empty mailing list does not make sense";
    public static final String MESSAGE_SUCCESS = "Exporting current view as a CSV file";
    private final Set<Prefix> prefixToWrite;


    /**
     * Creates a {@code MailingListCommand} which generates a csv file of contacts.
     *
     * @param prefixToWrite Set of Prefixes to write into the csv file.
     */
    public MailingListCommand(Set<Prefix> prefixToWrite) {
        this.prefixToWrite = Collections.unmodifiableSet(prefixToWrite);
    }

    /**
     * Executes the {@code MailingListCommand} which generates a csv file of contacts.
     *
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} regarding the status of the {@code MailingListCommand}.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setPrefixes(prefixToWrite);
        if (model.getFilteredPersonList().isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_PERSON_LIST);
        }
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }

    /**
     * Checks if {@code other} is equal to {@code this}.
     *
     * @param other the object to check if it is equal to {@code this}.
     * @return {@code boolean} indicating if it is equal.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MailingListCommand)) {
            return false;
        }

        // state check
        MailingListCommand e = (MailingListCommand) other;
        return prefixToWrite.equals(e.prefixToWrite);
    }
}
