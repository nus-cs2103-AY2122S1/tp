package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_VENUE;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String ADD_PERSON = "Adds a person to the address book.";

    public static final String ADD_TASK = "Adds tasks to a person in the address book.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": \n"
            + "Usage 1: " + ADD_PERSON
            + "\n" + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "LABEL]... "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_IMPORTANCE + "IMPORTANCE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney "
            + PREFIX_DESCRIPTION + "Really likes melons "
            + PREFIX_IMPORTANCE + "true"
            + "\n\n" + "Usage 2: " + ADD_TASK + "\n"
            + "Parameters: " + "INDEX "
            + PREFIX_TASK_INDEX + " TASK_INDEX (must be a positive integer less than or equal to "
            + Integer.MAX_VALUE + ") "
            + PREFIX_TASK_DESCRIPTION + "TASK_NAME "
            + "[" + PREFIX_TASK_DATE + "TASK_DATE] "
            + "[" + PREFIX_TASK_TIME + "TASK_TIME] "
            + "[" + PREFIX_TASK_VENUE + "TASK_ADDRESS]... \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TASK_INDEX + "2 "
            + PREFIX_TASK_DESCRIPTION + "Assignment Discussion"
            + PREFIX_TASK_INDEX + "1 "
            + PREFIX_TASK_DESCRIPTION + "Run"
            + PREFIX_TASK_TIME + "18:00";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s with %2$d %3$s attached";

    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}.
     *
     * @param person The person to be added to the address book.
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        CommandResult commandResult = new CommandResult(generateSuccessMessage(toAdd));
        commandResult.setWriteCommand();
        return commandResult;
    }

    /**
     * Generates a command execution success message based on person detail
     * and the number of tasks given
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToAdd) {
        int size = personToAdd.getTasks().size();
        String taskOrTasks = StringUtil.singularOrPlural("task", size);
        return String.format(MESSAGE_SUCCESS, personToAdd, size, taskOrTasks);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }


    public String getCommand() {
        return COMMAND_WORD;
    }

    public String getDescription() {
        return ADD_PERSON;
    }
}
