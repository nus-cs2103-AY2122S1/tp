package seedu.tracker.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;
import seedu.tracker.commons.core.Messages;
import seedu.tracker.commons.core.index.Index;
import seedu.tracker.logic.commands.exceptions.CommandException;
import seedu.tracker.model.Model;
import seedu.tracker.model.module.Code;
import seedu.tracker.model.module.Description;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.Module;
import seedu.tracker.model.module.Title;
import seedu.tracker.model.tag.Tag;

public class UntakeCommand extends Command {
    public static final String COMMAND_WORD = "untake";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Remove the schedule from the module identified by the index number used in the displayed module list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNTAKE_MODULE_SUCCESS = "Unscheduled Module: %1$s";
    public static final String MESSAGE_MODULE_ALREADY_UNSCHEDULED = "This module is already unscheduled: %1$s";

    private final Index targetIndex;

    /**
     * @param targetIndex of the module in the filtered module list to unschedule
     */
    public UntakeCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    private Module createUnscheduledModule(Module moduleToUnschedule) {
        assert moduleToUnschedule != null;

        Code currCode = moduleToUnschedule.getCode();
        Title currTitle = moduleToUnschedule.getTitle();
        Description currDescription = moduleToUnschedule.getDescription();
        Mc currMc = moduleToUnschedule.getMc();
        Set<Tag> currTags = moduleToUnschedule.getTags();

        return new Module(currCode, currTitle, currDescription, currMc, currTags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToUntake = lastShownList.get(targetIndex.getZeroBased());
        Module unscheduledModule = createUnscheduledModule(moduleToUntake);

        if (moduleToUntake.equals(unscheduledModule)) {
            throw new CommandException(String.format(MESSAGE_MODULE_ALREADY_UNSCHEDULED, moduleToUntake));
        }

        model.setModule(moduleToUntake, unscheduledModule);
        return new CommandResult(String.format(MESSAGE_UNTAKE_MODULE_SUCCESS, moduleToUntake));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UntakeCommand // instanceof handles nulls
                && targetIndex.equals(((UntakeCommand) other).targetIndex)); // state check
    }
}
