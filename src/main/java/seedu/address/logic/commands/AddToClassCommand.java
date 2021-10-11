package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUITION_CLASS;

import java.util.ArrayList;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuition.StudentList;
import seedu.address.model.tuition.TuitionClass;

public class AddToClassCommand extends Command {
    public static final String COMMAND_WORD = "addtoclass";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add students to existing class. "
            + "\n"
            + "Parameters: "
            + PREFIX_STUDENT_INDEX + "STUDENT_INDEX "
            + "[OR " + PREFIX_STUDENT + "STUDENT_NAME" + "] "
            + PREFIX_TUITION_CLASS + "CLASS_INDEX"
            + "\n" + "Example1: " + COMMAND_WORD + " si/3 tc/3"
            + "\n" + "Example2: " + COMMAND_WORD + " s/Felicia,James tc/3";
    public static final String MESSAGE_SUCCESS = "New student %1$s is added to class: %2$s";
    private static final String MESSAGE_STUDENT_EXISTS = "Student %1$s is already in the class";
    private static final String MESSAGE_CLASS_IS_FULL = "Cannot add student as the class limit has been exceeded.";
    private Index studentIndex;
    private Index classIndex;
    private StudentList studentList;
    private boolean isUsingIndex;

    /**
     * Constructor for AddToClass command using student index.
     * @param studentIndex index of student to be added.
     * @param classIndex index of class to be added to.
     */
    public AddToClassCommand(Index studentIndex, Index classIndex) {
        this.classIndex = classIndex;
        this.studentIndex = studentIndex;
        this.isUsingIndex = true;
    }

    /**
     * Constructor for AddToClass command using student name.
     * @param students index of student to be added.
     * @param classIndex index of class to be added to.
     */
    public AddToClassCommand(StudentList students, Index classIndex) {
        this.classIndex = classIndex;
        this.studentList = students;
        this.isUsingIndex = false;
    }

    /**
     * Adds an existing student to an existing class.
     * @param model {@code Model} which the command should operate on.
     * @return a CommandResult to be shown to users.
     * @throws CommandException if the student or class is not found, or the student is in the class already.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //checks that the tuition class exists and is not full
        TuitionClass tuitionClass = model.getTuitionClass(classIndex);
        if (tuitionClass == null) {
            throw new CommandException(Messages.MESSAGE_CLASS_NOT_FOUND);
        }
        boolean isClassFull = tuitionClass.isFull();
        if (isClassFull) {
            throw new CommandException(MESSAGE_CLASS_IS_FULL);
        }
        //checks whether the command is using index or names
        if (!isUsingIndex) {
            return this.executeStudentName(model, tuitionClass);
        }
        return this.executeStudentIndex(model, tuitionClass);
    }

    private void updateModel(Model model, TuitionClass tuitionClass,
                             TuitionClass modifiedClass, Person studentToAdd, Person studentToChange) {
        model.setPerson(studentToChange, studentToAdd);
        model.setTuition(tuitionClass, modifiedClass);
    }

    private ArrayList[] getStudent(StudentList studentList, Model model, TuitionClass tuitionClass)
            throws CommandException {
        ArrayList<String> invalidStudentNames = new ArrayList<>();
        ArrayList<Person> newStudents = new ArrayList<>();
        ArrayList<String> validStudentNames = new ArrayList<>();
        validStudentNames.addAll(tuitionClass.getStudentList().getStudents());
        for (String studentName: studentList.getStudents()) {
            int limit = tuitionClass.getLimit().getLimit();
            if (limit == tuitionClass.getStudentCount()) {
                throw new CommandException(MESSAGE_CLASS_IS_FULL);
            }
            Person person = new Person(new Name(studentName));
            if (model.hasPerson(person)) {
                newStudents.add(model.getSameNamePerson(person));
                validStudentNames.add(studentName);
            } else {
                invalidStudentNames.add(studentName);
            }
        }
        ArrayList[] returnValue = new ArrayList[3];
        returnValue[0] = newStudents;
        returnValue[1] = invalidStudentNames;
        returnValue[2] = validStudentNames;
        return returnValue;
    }

    private CommandResult executeStudentName(Model model, TuitionClass tuitionClass) throws CommandException {
        ArrayList[] students = this.getStudent(studentList, model, tuitionClass);
        ArrayList<String> invalidStudentNames = students[1];
        ArrayList<Person> newStudents = students[0];
        ArrayList<String> validStudentNames = students[2];
        String studentAdded = "";
        if (newStudents.size() == 0) {
            throw new CommandException(Messages.MESSAGE_STUDENT_NOT_FOUND);
        }
        TuitionClass modifiedClass = null;
        for (Person student: newStudents) {
            modifiedClass = model.addToClass(tuitionClass, student);
        }
        if (modifiedClass == null) {
            throw new CommandException(String.format(MESSAGE_STUDENT_EXISTS, invalidStudentNames));
        }
        for (Person person: newStudents) {
            studentAdded += person.getName().toString();
            Person studentToAdd = person;
            Person studentToChange = person;
            studentToAdd.addClass(modifiedClass);
            studentToAdd.addTag(new Tag(modifiedClass.getName().getName()));
            updateModel(model, tuitionClass, modifiedClass, studentToAdd, studentToChange);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, studentAdded, modifiedClass)
                + "\n" + Messages.MESSAGE_STUDENT_NOT_FOUND + invalidStudentNames);
    }

    private CommandResult executeStudentIndex(Model model, TuitionClass tuitionClass) throws CommandException {
        Person studentToAdd = model.getStudent(studentIndex);
        Person studentToChange = model.getStudent(studentIndex);

        if (studentToAdd == null) {
            throw new CommandException(Messages.MESSAGE_STUDENT_NOT_FOUND);
        }

        TuitionClass modifiedClass = model.addToClass(tuitionClass, studentToAdd);
        if (modifiedClass == null) {
            throw new CommandException(String.format(MESSAGE_STUDENT_EXISTS, studentToAdd.getName().toString()));
        }
        studentToAdd.addClass(modifiedClass);
        studentToAdd.addTag(new Tag(modifiedClass.getName().getName()));
        updateModel(model, tuitionClass, modifiedClass, studentToAdd, studentToChange);
        return new CommandResult(String.format(MESSAGE_SUCCESS, studentToAdd.getName().fullName, modifiedClass));
    }
}
