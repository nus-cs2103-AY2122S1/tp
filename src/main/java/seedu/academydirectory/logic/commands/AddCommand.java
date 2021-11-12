package seedu.academydirectory.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.academydirectory.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Optional;

import seedu.academydirectory.logic.commands.exceptions.CommandException;
import seedu.academydirectory.model.VersionedModel;
import seedu.academydirectory.model.student.Student;

/**
 * Adds a student to the academy directory.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String HELP_MESSAGE = "#### Adding a student: `add`\n"
            + "\n"
            + "Adds a student to Academy Directory\n\n"
            + "Format: `add n/NAME e/EMAIL te/TELE_HANDLE [p/PHONE_NUMBER]`\n"
            + "\n"
            + "<div markdown=\"span\" class=\"alert alert-primary\">:bulb: **Tip:**\n"
            + "A student can have no phone number due to privacy concern.\n"
            + "</div>\n"
            + "\n"
            + "* `PHONE_NUMBER` is an optional field. The default value is `NA` which stands "
            + "for \"Not Applicable\".\n"
            + "* If Avengers do not wish to enter a student's phone number, \n"
            + "  * `p/` prefix can be omitted from the command.\n"
            + "  * Otherwise, Avengers can supply the value `NA` to `p/` prefix. "
            + "Note that it must be `NA` not `N.A` or `na`\n"
            + "* If newly inputted students have `NAME` matching exactly (case-sensitive) with "
            + "an already existing entry, the program will output a warning message and"
            + " show the existing entry.\\\n"
            + "`This student already exists in the Academy Directory.`\n"
            + "\n"
            + "Examples:\n"
            + "* `add n/Aaron Tan te/@sausage e/e0123456@u.nus.edu p/90312311`\n"
            + "* `add n/Charles Ng te/@charles e/e0123434@u.nus.edu p/NA`\n"
            + "* `add n/Betsy Lim te/@unislave e/e0123456@u.nus.edu`";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to Academy Directory.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_TELEGRAM + "TELEGRAM "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Type in `help add` for more details.\n";

    public static final String COMMIT_MESSAGE = "New student added: %1$s";
    public static final String MESSAGE_SUCCESS = "New student added: %1$s\nPlease use `view` for more details";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the Academy Directory";

    private final Student toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public AddCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
    }

    @Override
    public CommandResult execute(VersionedModel model) throws CommandException {
        requireNonNull(model);

        if (model.hasStudent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.addStudent(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd),
                Optional.of(String.format(COMMIT_MESSAGE, toAdd.getName())));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
