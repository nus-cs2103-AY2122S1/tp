package seedu.tracker.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.tracker.model.Model;
import seedu.tracker.model.module.ModuleInSpecificSemesterPredicate;

import static seedu.tracker.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_SEMESTER;

public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View modules selected in specific semester"
            + "Parameters: "
            + PREFIX_ACADEMIC_YEAR + "YEAR "
            + PREFIX_SEMESTER + "SEMESTER ";

    public static final String MESSAGE_SUCCESS = "New module added: %1$s";

    private final ModuleInSpecificSemesterPredicate predicate;

    public ViewCommand(ModuleInSpecificSemesterPredicate predicate){
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleList(predicate);
        return new CommandResult(
                String.format(Messages.)
        )
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ViewCommand
                && predicate.equals(((ViewCommand) other).predicate));
    }

}
