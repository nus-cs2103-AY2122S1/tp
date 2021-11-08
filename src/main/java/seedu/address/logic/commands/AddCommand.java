package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NATIONALITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL_HANDLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "\n" + COMMAND_WORD + ": Adds a person to the address book.\n\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_NATIONALITY + "NATIONALITY] "
            + "[" + PREFIX_TUTORIAL_GROUP + "TUTORIAL GROUP] "
            + "[" + PREFIX_REMARK + "REMARK] "
            + "[" + PREFIX_SOCIAL_HANDLE + "SOCIAL HANDLE]... "
            + "[" + PREFIX_TAG + "TAG]...\n\n"
            + "Note:\n"
            + " - Parameters in square brackets are optional.\n"
            + " - Parameters followed by ... can be used more than once.\n\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Alex Lee Xiao Ming "
            + PREFIX_GENDER + "M "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "e1234567@u.nus.edu "
            + PREFIX_NATIONALITY + "Singaporean "
            + PREFIX_TUTORIAL_GROUP + "T09 "
            + PREFIX_REMARK + "likes to code "
            + PREFIX_SOCIAL_HANDLE + "tg:alexx432 "
            + PREFIX_SOCIAL_HANDLE + "ig:alexx765 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "members";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT_NAME_ABSENT = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, "No name was entered. \n%1$s");
    public static final String MESSAGE_INVALID_COMMAND_FORMAT_PREAMBLE_PRESENT = String.format(
            MESSAGE_INVALID_COMMAND_FORMAT, "An input was entered without prefix. \n%1$s");

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
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
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
