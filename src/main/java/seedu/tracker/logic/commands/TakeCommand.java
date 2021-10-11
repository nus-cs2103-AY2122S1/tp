package seedu.tracker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_SEMESTER;
import static seedu.tracker.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.List;
import java.util.Set;

import seedu.tracker.commons.core.Messages;
import seedu.tracker.commons.core.index.Index;
import seedu.tracker.logic.commands.exceptions.CommandException;
import seedu.tracker.model.Model;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.module.Code;
import seedu.tracker.model.module.Description;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.Module;
import seedu.tracker.model.module.Title;
import seedu.tracker.model.tag.Tag;

public class TakeCommand extends Command {
    public static final String COMMAND_WORD = "take";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Schedules a module for a particular semester. "
            + "If a module is already scheduled, it will be overwritten by the input value. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_ACADEMIC_YEAR + "YEAR "
            + PREFIX_SEMESTER + "SEMESTER "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ACADEMIC_YEAR + "2 "
            + PREFIX_SEMESTER + "1 ";

    public static final String MESSAGE_SUCCESS = "Module scheduled: %1$s";
    public static final String MESSAGE_NOT_SCHEDULED = "The year and semester fields must be provided";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module has already been scheduled in the same semester";

    private final Index index;
    private final AcademicCalendar academicCalendar;

    /**
     * @param index of the module in the filtered module list to schedule
     * @param academicCalendar the specific semester in which the module is scheduled
     */
    public TakeCommand(Index index, AcademicCalendar academicCalendar) {
        requireNonNull(index);
        requireNonNull(academicCalendar);

        this.index = index;
        this.academicCalendar = academicCalendar;
    }

    /**
     * Creates and returns a {@code Module} with the details of {@code moduleToSchedule}
     * scheduled for {@code academicCalendar}.
     */
    public Module createScheduledModule(Module moduleToSchedule, AcademicCalendar academicCalendar) {
        assert moduleToSchedule != null;

        Code currCode = moduleToSchedule.getCode();
        Title currTitle = moduleToSchedule.getTitle();
        Description currDescription = moduleToSchedule.getDescription();
        Mc currMc = moduleToSchedule.getMc();
        Set<Tag> currTags = moduleToSchedule.getTags();

        return new Module(currCode, currTitle, currDescription, currMc, currTags, academicCalendar);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToSchedule = lastShownList.get(index.getZeroBased());
        Module scheduledModule = createScheduledModule(moduleToSchedule, academicCalendar);

        model.setModule(moduleToSchedule, scheduledModule);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(String.format(MESSAGE_SUCCESS, scheduledModule));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TakeCommand)) {
            return false;
        }

        // state check
        TakeCommand t = (TakeCommand) other;
        return index.equals(t.index)
                && academicCalendar.equals(t.academicCalendar);
    }
}
