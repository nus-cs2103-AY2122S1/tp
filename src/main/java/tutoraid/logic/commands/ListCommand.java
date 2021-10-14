package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;

import tutoraid.model.Model;

/**
 * Lists all students in TutorAid to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all students";

    private final boolean viewAll;

    /**
     * Default constructor that lists students without showing fields.
     */
    public ListCommand() {
        this.viewAll = false;
    }

    /**
     * Constructor for a list command that lists students. Fields are shown if {@code viewAll} is true.
     *
     * @param viewAll Whether to show fields
     */
    public ListCommand(boolean viewAll) {
        this.viewAll = viewAll;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        model.viewList(viewAll);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
