package seedu.tracker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_SEMESTER;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.tracker.model.Model;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.module.Code;
import seedu.tracker.model.module.Description;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.Module;
import seedu.tracker.model.module.ModuleInSpecificSemesterPredicate;
import seedu.tracker.model.module.Title;
import seedu.tracker.model.tag.Tag;

/**
 * Clears the module Tracker.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Untake modules in a specific semester"
            + "\nParameters: "
            + PREFIX_ACADEMIC_YEAR + "YEAR "
            + PREFIX_SEMESTER + "SEMESTER "
            + "Example: " + COMMAND_WORD
            + " " + PREFIX_ACADEMIC_YEAR + "1 "
            + PREFIX_SEMESTER + "1";

    public static final String MESSAGE_SUCCESS = "All modules in %1$s has been unscheduled!";
    private final ModuleInSpecificSemesterPredicate predicate;
    private final AcademicCalendar academicCalendar;

    /**
     * Creates a ClearCommand.
     */
    public ClearCommand(AcademicCalendar academicCalendar) {
        requireNonNull(academicCalendar);
        this.predicate = new ModuleInSpecificSemesterPredicate(academicCalendar);
        this.academicCalendar = academicCalendar;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleList(predicate);
        ObservableList<Module> filteredList = model.getFilteredModuleList();
        List<Module> list = new ArrayList<>();
        for (int i = 0; i < filteredList.size(); i++) {
            list.add(filteredList.get(i));
        }
        for (Module module : list) {
            assert module != null;
            Code currCode = module.getCode();
            Title currTitle = module.getTitle();
            Description currDescription = module.getDescription();
            Mc currMc = module.getMc();
            Set<Tag> currTags = module.getTags();
            model.setModule(module, new Module(currCode, currTitle, currDescription, currMc, currTags));
        }
        model.updateFilteredModuleList(x->true);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, academicCalendar));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClearCommand // instanceof handles nulls
                && predicate.equals(((ClearCommand) other).predicate)); // state check
    }
}
