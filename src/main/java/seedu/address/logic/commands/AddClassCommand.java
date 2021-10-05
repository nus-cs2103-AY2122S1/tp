package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tuition.Student;
import seedu.address.model.tuition.TuitionClass;

public class AddClassCommand extends Command {
    public static final String COMMAND_WORD = "addclass";

    public static final String MESSAGE_SUCCESS = "New tuition class added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLASS = "This time slot has already been taken in the address book";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "The following student are not found in the address book: ";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add tuition class given name, limit, sessions, timeslot, and student \n"
            + "Parameters: NAME LIMIT COUNTER TIMESLOT STUDENT\n"
            + "Example: " + COMMAND_WORD + " n/Physics l/10 c/4 ts/Mon 4pm, s/";


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
        Student student = toAdd.getStudent();
        ArrayList<String> nowStudents = student.getStudents();
        ArrayList<String> newStudents = new ArrayList<>();
        ArrayList<String> invalidStudents = new ArrayList<>();
        ArrayList<Person> validStudentsAsPerson = new ArrayList<>();
        requireNonNull(model);
        if (model.hasTuition(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLASS);
        }
        for (String s: nowStudents) {
            Person person = new Person(new Name(s));
            if (model.hasPerson(person)) {
                newStudents.add(s);
                validStudentsAsPerson.add(model.getSameNamePerson(person));
            } else {
                invalidStudents.add(s);
            }
        }
        toAdd.changeStudents(newStudents);
        model.addTuition(toAdd);
        for (Person person: validStudentsAsPerson) {
            person.addClass(toAdd);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd
                + "\n" + MESSAGE_STUDENT_NOT_FOUND + invalidStudents));
    }
}
