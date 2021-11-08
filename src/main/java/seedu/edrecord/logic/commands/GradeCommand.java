package seedu.edrecord.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.commons.core.Messages.MESSAGE_NO_MODULE_SELECTED;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.edrecord.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.edrecord.commons.core.Messages;
import seedu.edrecord.commons.core.index.Index;
import seedu.edrecord.logic.commands.exceptions.CommandException;
import seedu.edrecord.model.Model;
import seedu.edrecord.model.assignment.Assignment;
import seedu.edrecord.model.assignment.Grade;
import seedu.edrecord.model.assignment.Grade.GradeStatus;
import seedu.edrecord.model.assignment.Score;
import seedu.edrecord.model.module.ModuleSet;
import seedu.edrecord.model.name.Name;
import seedu.edrecord.model.person.AssignmentGradeMap;
import seedu.edrecord.model.person.Email;
import seedu.edrecord.model.person.Info;
import seedu.edrecord.model.person.Person;
import seedu.edrecord.model.person.Phone;
import seedu.edrecord.model.tag.Tag;

/**
 * Assigns a grade to a student for an assignment.
 */
public class GradeCommand extends Command {

    public static final String COMMAND_WORD = "grade";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns a grade to the student "
            + "identified by the index number used in the displayed student list. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NAME + "ASSIGNMENT NAME "
            + PREFIX_STATUS + "STATUS "
            + "[" + PREFIX_SCORE + "SCORE] \n"
            + "Status has 3 possible inputs: Not submitted, Submitted or Graded. \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Assignment 2 "
            + PREFIX_STATUS + "Graded "
            + PREFIX_SCORE + "73 ";

    public static final String MESSAGE_SUCCESS = "Graded student: %s \nfor assignment: %s \nwith grade: %s";
    public static final String MESSAGE_NO_SUCH_ASSIGNMENT = "There is no assignment with this name";
    public static final String MESSAGE_SCORE_GREATER_THAN_MAX = "Student's score is greater than the "
            + "maximum score for this assignment";
    public static final String MESSAGE_STATUS_SCORE_MISMATCH = "Assignment has not been graded and should not "
            + "have a score.";

    private final Index index;
    private final Name name;
    private final Grade grade;

    /**
     * @param index Index of the student to grade.
     * @param name  Name of the assignment to grade.
     * @param grade Grade of the assignment.
     */
    public GradeCommand(Index index, Name name, Grade grade) {
        requireNonNull(index);
        requireNonNull(name);
        requireNonNull(grade);

        this.index = index;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        // Check for valid index
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        // Check whether a module has been selected
        if (!model.hasSelectedModule()) {
            throw new CommandException(MESSAGE_NO_MODULE_SELECTED);
        }

        // Get assignment
        Assignment assignment = model.searchAssignment(name)
                .orElseThrow(() -> new CommandException(MESSAGE_NO_SUCH_ASSIGNMENT));

        // Check if ungraded assignment has score or score is more than the assignment's maximum score.
        if (grade.getScore().isPresent()) {
            if (grade.getStatus() == GradeStatus.GRADED) {
                Score thisScore = grade.getScore().get();
                Score maxScore = assignment.getMaxScore();
                if (thisScore.compareTo(maxScore) > 0) {
                    throw new CommandException(MESSAGE_SCORE_GREATER_THAN_MAX);
                }
            } else {
                throw new CommandException(MESSAGE_STATUS_SCORE_MISMATCH);
            }
        }


        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, assignment, grade);

        model.setPerson(personToEdit, editedPerson);
        model.setSearchFilter(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedPerson, assignment, grade));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with the given {@code Assignment} and corresponding {@code Grade}.
     */
    private static Person createEditedPerson(Person personToEdit, Assignment assignment, Grade grade) {
        requireNonNull(personToEdit);

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Info updatedInfo = personToEdit.getInfo();
        ModuleSet updatedModule = personToEdit.getModules();
        Set<Tag> updatedTags = personToEdit.getTags();
        AssignmentGradeMap updatedGrades = personToEdit.getGrades();
        updatedGrades.add(assignment, grade);

        return new Person(updatedName, updatedPhone, updatedEmail, updatedInfo, updatedModule, updatedTags,
                updatedGrades);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GradeCommand)) {
            return false;
        }

        // state check
        GradeCommand e = (GradeCommand) other;
        return index.equals(e.index)
                && name.equals(e.name)
                && grade.equals(e.grade);
    }
}
