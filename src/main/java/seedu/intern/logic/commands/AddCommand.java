package seedu.intern.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_GRADUATIONYEARMONTH;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_INSTITUTION;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_JOB;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.intern.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.intern.logic.commands.exceptions.CommandException;
import seedu.intern.model.Model;
import seedu.intern.model.applicant.Applicant;

/**
 * Adds an applicant to Intern Watcher.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a applicant to the intern book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_GRADE + "GRADE "
            + PREFIX_INSTITUTION + "INSTITUTION "
            + PREFIX_COURSE + "COURSE "
            + PREFIX_GRADUATIONYEARMONTH + "GRADUATION_YEAR_MONTH "
            + PREFIX_JOB + "APPLIED_JOB "
            + "[" + PREFIX_STATUS + "APPLICATION_STATUS] "
            + "[" + PREFIX_SKILL + "SKILL]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_GRADE + "4.50 "
            + PREFIX_INSTITUTION + "NTU "
            + PREFIX_COURSE + "Computer Science "
            + PREFIX_GRADUATIONYEARMONTH + "06/2025 "
            + PREFIX_JOB + "Software Engineer "
            + PREFIX_STATUS + "INTERVIEWED "
            + PREFIX_SKILL + "Java "
            + PREFIX_SKILL + "Python";

    public static final String MESSAGE_SUCCESS = "New applicant added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPLICANT = "This applicant already exists in Intern Watcher";
    public static final String MESSAGE_COMMIT_ADD = "Add applicant: %1$s";

    private final Applicant toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Applicant}
     */
    public AddCommand(Applicant applicant) {
        requireNonNull(applicant);
        toAdd = applicant;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasApplicant(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICANT);
        }

        model.addApplicant(toAdd);
        model.commitInternWatcher(String.format(MESSAGE_COMMIT_ADD, toAdd));
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
