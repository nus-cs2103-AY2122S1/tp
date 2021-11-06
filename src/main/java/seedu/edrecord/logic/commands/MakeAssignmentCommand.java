package seedu.edrecord.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.core.Messages.MESSAGE_NO_MODULE_SELECTED;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;

import seedu.edrecord.logic.commands.exceptions.CommandException;
import seedu.edrecord.model.Model;
import seedu.edrecord.model.assignment.Assignment;

public class MakeAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "mkasg";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an assignment to the current module. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_WEIGHTAGE + "WEIGHTAGE "
            + PREFIX_SCORE + "MAX_SCORE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Side quest 10 "
            + PREFIX_WEIGHTAGE + "20 "
            + PREFIX_SCORE + "50";

    public static final String MESSAGE_SUCCESS = "New assignment added: %1$s";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This assignment already exists in this module";
    public static final String MESSAGE_TOTAL_WEIGHTAGE_EXCEEDS_100 =
            "Adding this assignment brings the total module weightage above 100%";

    private final Assignment toAdd;

    /**
     * Creates an MakeAssignmentCommand to add the specified {@code Assignment}
     */
    public MakeAssignmentCommand(Assignment assignment) {
        requireNonNull(assignment);
        toAdd = assignment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasSelectedModule()) {
            throw new CommandException(MESSAGE_NO_MODULE_SELECTED);
        }
        if (model.hasAssignmentInCurrentModule(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }
        if (model.isTotalWeightageExceeded(toAdd)) {
            throw new CommandException(MESSAGE_TOTAL_WEIGHTAGE_EXCEEDS_100);
        }
        model.addAssignment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MakeAssignmentCommand // instanceof handles nulls
                && toAdd.equals(((MakeAssignmentCommand) other).toAdd));
    }
}
