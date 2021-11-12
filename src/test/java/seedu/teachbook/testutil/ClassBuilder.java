package seedu.teachbook.testutil;


import java.util.Arrays;
import java.util.stream.Collectors;

import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.classobject.ClassName;
import seedu.teachbook.model.student.Student;
import seedu.teachbook.model.student.UniqueStudentList;

public class ClassBuilder {

    public static final String DEFAULT_CLASSNAME = "Default";

    private ClassName className;
    private UniqueStudentList studentList;

    /**
     * Creates a {@code ClassBuilder} with the default details.
     */
    public ClassBuilder() {
        this.className = new ClassName(DEFAULT_CLASSNAME);
        this.studentList = new UniqueStudentList();
    }

    /**
     * Initializes the {@code ClassBuilder} with the data of {@code classToCopy}.
     */
    public ClassBuilder(Class classToCopy) {
        this.className = classToCopy.getClassName();
        this.studentList = classToCopy.getUniqueStudentListOfThisClass();
    }

    /**
     * Sets the {@code ClassName} of the {@code Class} that we are building.
     */
    public ClassBuilder withName(String className) {
        this.className = new ClassName(className);
        return this;
    }

    /**
     * Sets the student list of the {@code Class} that we are building.
     */
    public ClassBuilder withStudentList(Student... studentList) {
        this.studentList.setStudents(Arrays.stream(studentList).collect(Collectors.toList()));
        return this;
    }

    /**
     * Builds a valid {@code Class} object as per resulted after the execution of {@code AddClassCommand}.
     */
    public Class build() {
        Class classObj = new Class(className);
        classObj.setStudentsOfThisClass(studentList);
        return classObj;
    }

}
