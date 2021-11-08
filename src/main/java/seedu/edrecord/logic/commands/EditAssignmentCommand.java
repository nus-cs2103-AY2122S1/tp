package seedu.edrecord.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.core.Messages.MESSAGE_NO_MODULE_SELECTED;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;

import java.util.Optional;

import seedu.edrecord.commons.core.Messages;
import seedu.edrecord.commons.core.index.Index;
import seedu.edrecord.commons.util.CollectionUtil;
import seedu.edrecord.logic.commands.exceptions.CommandException;
import seedu.edrecord.model.Model;
import seedu.edrecord.model.assignment.Assignment;
import seedu.edrecord.model.assignment.Score;
import seedu.edrecord.model.assignment.Weightage;
import seedu.edrecord.model.name.Name;

/**
 * Edits the details of an Assignment under the currently selected Module.
 */
public class EditAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "edasg";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the assignment using "
            + "its ID number as shown in the displayed assignment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: ID (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_WEIGHTAGE + "WEIGHTAGE] "
            + "[" + PREFIX_SCORE + "MAX_SCORE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Midterm "
            + PREFIX_WEIGHTAGE + "2.5";

    public static final String MESSAGE_EDIT_ASSIGNMENT_SUCCESS = "Edited Assignment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This assignment already exists in this module.";
    public static final String MESSAGE_INVALID_ASSIGNMENT_MAX_SCORE = "The new maximum score is lower than "
            + "some existing grades.";
    public static final String MESSAGE_TOTAL_WEIGHTAGE_EXCEEDS_100 =
            "The edited assignment brings the total module weightage above 100%";

    private final Index id;
    private final EditAssignmentDescriptor editDescriptor;

    /**
     * @param id The index of the assignment in the assignment list to edit
     * @param editDescriptor Details to edit the assignment with
     */
    public EditAssignmentCommand(Index id, EditAssignmentDescriptor editDescriptor) {
        requireNonNull(id);
        requireNonNull(editDescriptor);

        this.id = id;
        this.editDescriptor = new EditAssignmentDescriptor(editDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasSelectedModule()) {
            throw new CommandException(MESSAGE_NO_MODULE_SELECTED);
        }

        if (id.getOneBased() >= model.getAssignmentCounter()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
        }

        Assignment asgToEdit = model.getAssignment(id.getOneBased())
                .orElseThrow(() -> new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX));

        Assignment editedAsg = createEditedAssignment(asgToEdit, editDescriptor);

        // if name edited, check if other assignments have the same name
        if (!(asgToEdit.getName().equals(editedAsg.getName())) && model.hasSameNameInCurrentModule(editedAsg)) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }

        if (model.hasHigherGradeInCurrentModule(asgToEdit, editedAsg)) {
            throw new CommandException(MESSAGE_INVALID_ASSIGNMENT_MAX_SCORE);
        }

        if (editedAsg.hasHigherWeightage(asgToEdit)) {
            Assignment delta = createDeltaAssignment(editedAsg, asgToEdit);
            if (model.isTotalWeightageExceeded(delta)) {
                throw new CommandException(MESSAGE_TOTAL_WEIGHTAGE_EXCEEDS_100);
            }
        }

        model.setAssignment(asgToEdit, editedAsg);
        return new CommandResult(String.format(MESSAGE_EDIT_ASSIGNMENT_SUCCESS, editedAsg));
    }

    /**
     * Creates and returns an {@code Assignment} with the details of {@code toEdit}
     * edited with {@code editDescriptor}.
     */
    private static Assignment createEditedAssignment(Assignment toEdit,
                                                     EditAssignmentDescriptor editDescriptor) {
        requireNonNull(toEdit);

        Name updatedName = editDescriptor.getName().orElse(toEdit.getName());
        Weightage updatedWeightage = editDescriptor.getWeightage().orElse(toEdit.getWeightage());
        Score updatedMaxScore = editDescriptor.getMaxScore().orElse(toEdit.getMaxScore());
        int id = toEdit.getId();

        return new Assignment(updatedName, updatedWeightage, updatedMaxScore, id);
    }

    /**
     * Creates an assignment whose weightage is the weightage of assignment {@code edited} minus
     * the weightage of assignment {@code current}.
     */
    private static Assignment createDeltaAssignment(Assignment edited, Assignment current) {
        Weightage deltaWeightage = new Weightage(
                String.valueOf(edited.getWeightage().weightage - current.getWeightage().weightage));
        return new Assignment(edited.getName(), deltaWeightage, edited.getMaxScore(), current.getId());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAssignmentCommand)) {
            return false;
        }

        // state check
        EditAssignmentCommand e = (EditAssignmentCommand) other;
        return id.equals(e.id)
                && editDescriptor.equals(e.editDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditAssignmentDescriptor {
        private Name name;
        private Weightage weightage;
        private Score maxScore;

        public EditAssignmentDescriptor() {
        }

        /**
         * Constructor that copies data from another {@code EditAssignmentDescriptor} object.
         */
        public EditAssignmentDescriptor(EditAssignmentDescriptor toCopy) {
            setName(toCopy.name);
            setWeightage(toCopy.weightage);
            setMaxScore(toCopy.maxScore);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, weightage, maxScore);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setWeightage(Weightage weightage) {
            this.weightage = weightage;
        }

        public Optional<Weightage> getWeightage() {
            return Optional.ofNullable(weightage);
        }

        public void setMaxScore(Score maxScore) {
            this.maxScore = maxScore;
        }

        public Optional<Score> getMaxScore() {
            return Optional.ofNullable(maxScore);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAssignmentDescriptor)) {
                return false;
            }

            // state check
            EditAssignmentDescriptor e = (EditAssignmentDescriptor) other;

            return getName().equals(e.getName())
                    && getWeightage().equals(e.getWeightage())
                    && getMaxScore().equals(e.getMaxScore());
        }
    }
}
