package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Exports the current view as a mailing list in the csv file format.
 */
public class MailingListCommand extends Command {

    public static final String COMMAND_WORD = "contact";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports the current view as a mailing list. "
            + "Parameters: "
            + "[" + PREFIX_PHONE + "] "
            + "[" + PREFIX_EMAIL + "] "
            + "[" + PREFIX_ADDRESS + "] "
            + "[" + PREFIX_TAG + "]"
            + "[" + PREFIX_BIRTHDAY + "] \n"
            + "Default export is Phone numbers and Email addresses.\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_PHONE + " "
            + PREFIX_EMAIL;

    public static final String MESSAGE_EMPTY_PERSON_LIST = "Trying to export an empty mailing list does not make sense";

    public static final String MESSAGE_SUCCESS = "Exporting current view as a CSV file";

    private final Set<Prefix> prefixToWrite;

    /**
     * @param prefixToWrite Set of Prefixes to write into the csv file
     */
    public MailingListCommand(Set<Prefix> prefixToWrite) {
        this.prefixToWrite = Collections.unmodifiableSet(prefixToWrite);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException{
        requireNonNull(model);
        List<Person> personList = model.getFilteredPersonList().stream().collect(Collectors.toList());

        if (personList.isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_PERSON_LIST);
        }

        return new CommandResultExport(MESSAGE_SUCCESS,personList,prefixToWrite);
    }

}
