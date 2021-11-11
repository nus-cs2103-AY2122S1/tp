package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE_AND_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.application.Application;

/**
 * Adds an application to InternSHIP.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD.toUpperCase() + " command: Adds an application to InternSHIP.\n"
            + "Parameters:\n"
            + PREFIX_COMPANY_NAME + "COMPANY_NAME "
            + PREFIX_INTERNSHIP_POSITION + "INTERNSHIP_POSITION "
            + PREFIX_DEADLINE_OF_APPLICATION + "APPLICATION_DEADLINE "
            + "[" + PREFIX_REQUIREMENT + "APPLICATION_REQUIREMENTS" + "]"
            + "[" + PREFIX_INTERVIEW_DATE_AND_TIME + "INTERVIEW_DATE_AND_TIME" + "]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COMPANY_NAME + "Shopee "
            + PREFIX_INTERNSHIP_POSITION + "software engineer "
            + PREFIX_DEADLINE_OF_APPLICATION + "2021-12-12 "
            + PREFIX_REQUIREMENT + "resume "
            + PREFIX_INTERVIEW_DATE_AND_TIME + "2021-12-20 0830";

    public static final String MESSAGE_SUCCESS = "New application added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPLICATION =
            "Warning: This application already exists in InternSHIP!";

    private final Application toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Application}
     */
    public AddCommand(Application application) {
        requireNonNull(application);
        toAdd = application;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasApplication(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICATION);
        }

        model.addApplication(toAdd);
        model.commitInternship(model.getInternship());
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
