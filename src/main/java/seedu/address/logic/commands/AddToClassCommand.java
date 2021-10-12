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
    public static final String MESSAGE_SUCCESS = "New student %1$s added to class.";
    private static final String MESSAGE_STUDENT_EXISTS = "Student %1$s is already in the class";
    private static final String MESSAGE_CLASS_IS_FULL = "The following students are not "
            + "added due to class limit: ";
    private static final String MESSAGE_STUDENT_NOT_FOUND = "The following students are not found in the address book: ";
    private static final String MESSAGE_NO_STUDENT_ADDED = "No student has been added.";
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

    private ArrayList[] getStudent(StudentList studentList, Model model, TuitionClass tuitionClass) {
        ArrayList<String> invalidStudentNames = new ArrayList<>();
        ArrayList<Person> newStudents = new ArrayList<>();
        ArrayList<String> notAdded = new ArrayList<>();
        ArrayList<String> validStudentNames = new ArrayList<>();
        ArrayList<String> existingStudent = new ArrayList<>();
        validStudentNames.addAll(tuitionClass.getStudentList().getStudents());
        int limit = tuitionClass.getLimit().getLimit();
        for (String studentName: studentList.getStudents()) {
            Person person = new Person(new Name(studentName));
            if (!model.hasPerson(person)) {
                if (!invalidStudentNames.contains(studentName)) {
                    invalidStudentNames.add(studentName);
                }
                continue;
            }
            if (tuitionClass.getStudentList().getStudents().contains(studentName)) {
                if (!existingStudent.contains(studentName)) {
                    existingStudent.add(studentName);
                }
                continue;
            }
            if (limit <= validStudentNames.size()) {
                if (!notAdded.contains(studentName)) {
                    notAdded.add(studentName);
                }
                continue;
            }
            if (!newStudents.contains(model.getSameNamePerson(person))) {
                newStudents.add(model.getSameNamePerson(person));
                validStudentNames.add(studentName);
            }
        }
        ArrayList[] returnValue = new ArrayList[]{newStudents, invalidStudentNames,
            validStudentNames, notAdded, existingStudent};
        return returnValue;
    }

    private CommandResult executeStudentName(Model model, TuitionClass tuitionClass) throws CommandException {
        ArrayList[] students = this.getStudent(studentList, model, tuitionClass);
        ArrayList<Person> newStudents = students[0];
        if (newStudents.size() == 0) {
            return new CommandResult(getMessage(students));
        }
        TuitionClass modifiedClass = null;
        for (Person student: newStudents) {
            modifiedClass = model.addToClass(tuitionClass, student);
        }
        if (modifiedClass == null) {
            return new CommandResult(getMessage(students));
        }
        for (Person person: newStudents) {
            Person studentToAdd = person;
            Person studentToChange = person;
            studentToAdd.addClass(modifiedClass);
            studentToAdd.addTag(new Tag(modifiedClass.getName().getName() + " "
                    + modifiedClass.getTimeslot().time));
            updateModel(model, tuitionClass, modifiedClass, studentToAdd, studentToChange);
        }

        return new CommandResult(getMessage(students));
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
        studentToAdd.addTag(new Tag(modifiedClass.getName().getName()
                + " " + modifiedClass.getTimeslot().time));
        updateModel(model, tuitionClass, modifiedClass, studentToAdd, studentToChange);
        return new CommandResult(String.format(MESSAGE_SUCCESS, studentToAdd.getName().fullName, modifiedClass));
    }

    private String getMessage(ArrayList[] students) {
        ArrayList<String> invalidStudentNames = students[1];
        ArrayList<Person> newStudents = students[0];
        ArrayList<String> notAdded = students[3];
        boolean limitExceeded = notAdded.size() > 0;
        boolean hasInvalidNames = invalidStudentNames.size() > 0;
        boolean noStudentAdded = newStudents.size() == 0;
        boolean studentExists = students[4].size() > 0;
        String message = "";
        String studentAdded = "";
        for (Person person: newStudents) {
            studentAdded += person.getName().toString() + " ";
        }
        if (noStudentAdded) {
            message += MESSAGE_NO_STUDENT_ADDED + "\n";
        } else {
            message += String.format(MESSAGE_SUCCESS, studentAdded) + "\n";
        }
        if (studentExists) {
            message += String.format(MESSAGE_STUDENT_EXISTS, students[4]) + "\n";
        }
        if (limitExceeded) {
            message += MESSAGE_CLASS_IS_FULL + notAdded + "\n";
        }
        if (hasInvalidNames) {
            message += MESSAGE_STUDENT_NOT_FOUND + invalidStudentNames;
        }
        return message;
    }
}
