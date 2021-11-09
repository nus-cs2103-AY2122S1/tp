package seedu.modulink.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_GITHUB_USERNAME;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;

import seedu.modulink.logic.commands.exceptions.CommandException;
import seedu.modulink.model.Model;
import seedu.modulink.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class CreateCommand extends Command {

    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates the user's profile in ModuLink. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_ID + "STUDENT ID "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_GITHUB_USERNAME + "GITHUB USERNAME] "
            + "[" + PREFIX_TELEGRAM_HANDLE + "TELEGRAM HANDLE] "
            + "[" + PREFIX_MOD + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_ID + "A1234567Z "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_GITHUB_USERNAME + "johnd "
            + PREFIX_TELEGRAM_HANDLE + "@johndoe "
            + PREFIX_MOD + "CS2100 "
            + PREFIX_MOD + "CS2101";

    public static final String MESSAGE_SUCCESS = "Profile created: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";
    public static final String MESSAGE_DUPLICATE_STUDENT_ID = "There is already a person with this Student ID.";

    private final Person myProfile;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public CreateCommand(Person person) {
        requireNonNull(person);
        myProfile = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(myProfile)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        if (model.hasStudentIdNotProfile(myProfile)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT_ID);
        }

        // deletes the placeholder and adds the user profile
        if (!model.getFilteredPersonList().isEmpty()) {
            Person placeholder = model.getFilteredPersonList().get(0);
            model.deletePerson(placeholder);
        }
        model.addProfile(myProfile);
        return new CommandResult(String.format(MESSAGE_SUCCESS, myProfile));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateCommand // instanceof handles nulls
                && myProfile.equals(((CreateCommand) other).myProfile));
    }
}
