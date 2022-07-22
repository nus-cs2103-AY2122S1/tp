package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIMIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuition.StudentList;
import seedu.address.model.tuition.Timeslot;
import seedu.address.model.tuition.TuitionClass;

public class AddClassCommand extends Command {
    public static final String COMMAND_WORD = "addclass";
    public static final String SHORTCUT = "ac";

    public static final String MESSAGE_SUCCESS = "New tuition class added: %1$s";
    public static final String MESSAGE_TIMESLOT_CONFLICT = "This time slot has already been taken";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "The following students are not found: ";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds tuition class given name, limit, timeslot, and student \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_LIMIT + "LIMIT "
            + PREFIX_TIMESLOT + "TIMESLOT "
            + "[" + PREFIX_STUDENT + "NAME,...] "
            + "[" + PREFIX_REMARK + "REMARK]\n"
            + "Example: " + COMMAND_WORD + " n/Physics l/10 ts/Mon 11:00-14:00 s/Alex Yeoh,Bernice Yu";

    private static final String MESSAGE_CLASS_LIMIT_EXCEEDED = "The following students are not "
            + "added due to class limit: ";

    private TuitionClass toAdd;

    /**
     * Creates an AddClassCommand to add the specified {@code TuitionClass}
     */
    public AddClassCommand(TuitionClass tuitionClass) {
        requireNonNull(tuitionClass);
        toAdd = tuitionClass;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Timeslot timeslot = toAdd.getTimeslot();
        List<TuitionClass> classList = model.getFilteredTuitionList();
        if (classList != null && timeslot.checkTimetableConflicts(classList)) {
            throw new CommandException(MESSAGE_TIMESLOT_CONFLICT);
        }

        //Set students in tuition class
        ArrayList[] students = getStudents(model, toAdd.getStudentList().getStudents());
        toAdd.changeStudents(students[0]);

        //Update model with new tuition class
        model.addTuition(toAdd);
        model.updateFilteredTuitionList(Model.PREDICATE_SHOW_ALL_TUITIONS);
        addClassToStudents(toAdd, students[2], model);
        String message = this.getMessage(students[1], students[3]);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd) + "\n" + message);
    }


    private void addClassToStudents(TuitionClass tuitionClass, ArrayList<Student> validStudentsAsPerson, Model model) {
        for (Student person: validStudentsAsPerson) {
            Student studentToChange = person;
            person.addClass(tuitionClass);
            person.addTag(new Tag(String.format("%s | %s", tuitionClass.getName().getName(),
                    tuitionClass.getTimeslot())));
            model.setStudent(studentToChange, person);
        }
    }


    private ArrayList[] getStudents(Model model, ArrayList<String> nowStudents) {
        toAdd.changeStudents(new ArrayList<>());
        ArrayList[] students = AddToClassCommand.categorizeStudents(new StudentList(nowStudents), model, toAdd);
        ArrayList[] returnValue = new ArrayList[]{students[2], students[1], students[0], students[3]};
        return returnValue;
    }
    private String getMessage(ArrayList<String> invalidStudents, ArrayList<String> notAdded) {
        String message = "";
        boolean hasInvalidStudent = invalidStudents.size() >= 1;
        boolean hasNotAddedStudent = notAdded.size() >= 1;
        if (hasInvalidStudent) {
            message += MESSAGE_STUDENT_NOT_FOUND + invalidStudents;
        }
        if (hasNotAddedStudent) {
            message += "\n" + MESSAGE_CLASS_LIMIT_EXCEEDED + notAdded;
        }
        return message;
    }
}

