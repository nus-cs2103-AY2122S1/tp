package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;
import static tutoraid.ui.DetailLevel.LOW;

import tutoraid.commons.core.Messages;
import tutoraid.model.Model;
import tutoraid.ui.DetailLevel;

/**
 * Lists all students in TutorAid to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    private final DetailLevel detailLevel;

    /**
     * Default constructor that lists students without showing fields.
     */
    public ListCommand() {
        this.detailLevel = LOW;
    }

    /**
     * Constructor for a list command that lists students. Fields are shown if {@code viewAll} is true.
     *
     * @param detailLevel Level of detail for fields to be displayed
     */
    public ListCommand(DetailLevel detailLevel) {
        this.detailLevel = detailLevel;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredLessonList(Model.PREDICATE_SHOW_ALL_LESSONS);
        model.viewList(detailLevel);
        return new CommandResult(Messages.MESSAGE_LIST_SUCCESS);
    }
}
