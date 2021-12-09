package seedu.times.logic.commands.classcommands;

import static java.util.Objects.requireNonNull;
import static seedu.times.logic.parser.CliSyntax.PREFIX_CLASSTIMING;
import static seedu.times.logic.parser.CliSyntax.PREFIX_CLASS_NAME;
import static seedu.times.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.times.logic.parser.CliSyntax.PREFIX_RATE;

import seedu.times.commons.core.Messages;
import seedu.times.logic.commands.Command;
import seedu.times.logic.commands.CommandResult;
import seedu.times.logic.commands.exceptions.CommandException;
import seedu.times.model.Model;
import seedu.times.model.tuitionclass.TuitionClass;
import seedu.times.model.tuitionclass.exceptions.OverlappingClassException;
import seedu.times.ui.TabName;

/**
 * Adds a tuition class to Timestable.
 */
public class AddClassCommand extends Command {

    public static final String COMMAND_WORD = "addclass";

    public static final String MESSAGE_SUCCESS = "New class added: %1$s";
    public static final Object MESSAGE_USAGE = COMMAND_WORD + ": Adds a class to the address book.\n"
            + "Parameters: "
            + PREFIX_CLASS_NAME + "CLASS NAME "
            + PREFIX_CLASSTIMING + "CLASS TIMING "
            + PREFIX_RATE + "RATE "
            + PREFIX_LOCATION + "LOCATION\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CLASS_NAME + "Sec 4 A Maths "
            + PREFIX_CLASSTIMING + "MON 11:30-13:30 "
            + PREFIX_RATE + "70 "
            + PREFIX_LOCATION + "Nex Tuition Center";

    private final TuitionClass tuitionClass;

    /**
     * Creates an AddToClassCommand to add the specified tuitionClass.
     *
     * @param tuitionClass TuitionClass to be added.
     */
    public AddClassCommand(TuitionClass tuitionClass) {
        requireNonNull(tuitionClass);
        this.tuitionClass = tuitionClass;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        try {
            model.addTuitionClass(tuitionClass);
        } catch (OverlappingClassException ice) {
            throw new CommandException(Messages.MESSAGE_CLASHING_CLASS_TIMING);
        }

        updateView(TabName.CLASSES);
        return new CommandResult(String.format(MESSAGE_SUCCESS, tuitionClass));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddClassCommand // instanceof handles nulls
                && tuitionClass.equals(((AddClassCommand) other).tuitionClass));
    }
}
