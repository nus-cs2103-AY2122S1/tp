package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuition.Timeslot;
import seedu.address.model.tuition.TuitionClass;

public class AddClassCommand extends Command {
    public static final String COMMAND_WORD = "addclass";

    public static final String MESSAGE_SUCCESS = "New tuition class added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLASS = "This time slot has already been taken in the address book";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "The following students are not found in the address book: ";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add tuition class given name, limit, sessions, timeslot, and student \n"
            + "Parameters: NAME LIMIT COUNTER TIMESLOT STUDENT\n"
            + "Example: " + COMMAND_WORD + " n/Physics l/10 c/4 ts/Mon 11:00-14:00 s/Alex Yeoh";

    private static final String MESSAGE_CLASS_LIMIT_EXCEEDED = "The following students are not "
            + "added due to class limit: ";

    private static final String MESSAGE_TIMESLOT_FORMAT = "The format for time slot should be WWW HH:MM-HH:MM \n"
            + "Example: Mon 11:00-14:00";
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
        if (!timeslot.isFormatCorrect()) {
            throw new CommandException(MESSAGE_TIMESLOT_FORMAT);
        }
        if (model.hasTuition(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLASS);
        }
        int limit = toAdd.getLimit().getLimit();
        ArrayList[] students = getStudents(model, toAdd.getStudentList().getStudents(), limit);
        toAdd.changeStudents(students[0]);
        model.addTuition(toAdd);
        addClassToStudent(toAdd, students[2], model);
        String message = this.getMessage(students[1], students[3]);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd) + "\n" + message);
    }
    private void addClassToStudent(TuitionClass tuitionClass, ArrayList<Person> validStudentsAsPerson, Model model) {
        for (Person person: validStudentsAsPerson) {
            Person studentToChange = person;
            person.addClass(tuitionClass);
            person.addTag(new Tag(tuitionClass.getName().getName()));
            model.setPerson(studentToChange, person);
        }
    }
    private ArrayList[] getStudents(Model model, ArrayList<String> nowStudents, int limit) {
        ArrayList<String> newStudents = new ArrayList<>();
        ArrayList<String> invalidStudents = new ArrayList<>();
        ArrayList<Person> validStudentsAsPerson = new ArrayList<>();
        ArrayList<String> notAddedStudent = new ArrayList<>();
        for (String s: nowStudents) {
            Person person = new Person(new Name(s));
            if (!model.hasPerson(person)) {
                invalidStudents.add(s);
                continue;
            }
            if (newStudents.size() >= limit) {
                //valid students not added due to limit exceeded.
                notAddedStudent.add(s);
                continue;
            }
            newStudents.add(s);
            validStudentsAsPerson.add(model.getSameNamePerson(person));
        }
        ArrayList[] returnValue = new ArrayList[4];
        returnValue[0] = newStudents;
        returnValue[1] = invalidStudents;
        returnValue[2] = validStudentsAsPerson;
        returnValue[3] = notAddedStudent;
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

