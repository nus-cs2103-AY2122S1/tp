package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.ClassCode;
import seedu.address.model.student.ClassMemberPredicate;
import seedu.address.model.tutorialclass.TutorialClass;





/**
 * Views a TutorialClass and its students, identified using it's displayed index from the ClassMATE.
 */
public class ViewClassCommand extends Command {

    public static final String COMMAND_WORD = "viewc";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the tutorial class identified by the index number in the displayed class list.\n"
            + "Displays Students in tutorial class in Student List. \n"
            + "Parmeters: INDEX (must be a positive integer that is a valid index of the list)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_CLASS_SUCCESS = "Viewed class: %1$s";

    private final Index targetIndex;

    /**
     * Constructor for ViewClassCommand
     *
     * @param targetIndex Index to be deleted.
     */
    public ViewClassCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<TutorialClass> lastShownList = model.getFilteredTutorialClassList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        }

        // filter List of tutorial classes to show selected class
        TutorialClass classToShow = lastShownList.get(targetIndex.getZeroBased());
        model.updateFilteredTutorialClassList(tc -> tc.isSameTutorialClass(classToShow));

        // show only students part of the current class
        ClassCode targetClassCode = classToShow.getClassCode();
        model.updateFilteredStudentList(new ClassMemberPredicate(targetClassCode));
        return new CommandResult(String.format(MESSAGE_VIEW_CLASS_SUCCESS, classToShow));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewClassCommand // instanceof handles nulls
                && targetIndex.equals(((ViewClassCommand) other).targetIndex)); // state check
    }
}
