package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLASS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Student;
import seedu.address.model.tuitionclass.StudentNameList;
import seedu.address.model.tuitionclass.TuitionClass;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts students/classes by specified"
            + " parameter and order\n"
            + "Parameters: PARAMETER_TO_SORT_BY DIRECTION_OF_SORT\n"
            + "Example: " + COMMAND_WORD + " name asc";
    public static final String INVALID_SORTBY = "The parameter to sort by can only be: name or timing";
    public static final String INVALID_DIRECTIONOFSORT = "The direction of sort can only be asc or desc";

    private final String sortBy;

    private final String directionOfSort;

    /**
     * Constructs a new SortCommand.
     *
     * @param sortBy sortBy keyword.
     * @param directionOfSort directionOfSort keyword.
     */
    public SortCommand(String sortBy, String directionOfSort) {
        this.sortBy = sortBy;
        this.directionOfSort = directionOfSort;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        // sort the StudentNameList;
        if (sortBy.equals("name")) {
            model.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);

            ArrayList<Student> studentsToSort = new ArrayList<>(model.getFilteredStudentList());

            if (directionOfSort.equals("asc")) {
                studentsToSort.sort((student1, student2) ->
                        student1.getName().toString().compareTo(student2.getName().toString()));
            } else {
                studentsToSort.sort((student1, student2) ->
                        student2.getName().toString().compareTo(student1.getName().toString()));
            }

            model.setStudents(studentsToSort);
            
        } else if (sortBy.equals("timing")) {
            model.updateFilteredClassList(PREDICATE_SHOW_ALL_CLASS);

            ArrayList<TuitionClass> classesToSort = new ArrayList<>(model.getFilteredTuitionClassList());

            if (directionOfSort.equals("asc")) {
                classesToSort.sort((class1, class2) ->
                        class1.getClassTiming().compareTo(class2.getClassTiming()));
            } else {
                classesToSort.sort((class1, class2) ->
                        class2.getClassTiming().compareTo(class1.getClassTiming()));
            }


            model.setClasses(classesToSort);

            model.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
            ArrayList<Student> unsortedStudentList = new ArrayList<>(model.getFilteredStudentList());
            ArrayList<Student> sortedStudentList = new ArrayList<>();

            // sort the student list so that it is sorted based in order of classes
            filterStudentBasedOnClasses(sortedStudentList, unsortedStudentList, classesToSort);

            addStudentsWithNoClasses(sortedStudentList, unsortedStudentList, classesToSort);

            model.setStudents(sortedStudentList);

            hideTuitionClassStudentList();

        } else {
            return new CommandResult("sort by" + sortBy
                    + " has not been implemented by the developers");
        }

        return new CommandResult("Sorted students based on " + sortBy
                + " in " + directionOfSort + " direction");
    }

    /**
     * Orders the studentList in the order of them appearing in the tuitionClassList. If they appear in multiple
     * tuition classes in the tuitionClassList, their first occurrence would be taken as their oder in the resulting
     * order.
     *
     * @param listToAddTo ArrayList to be addedTo.
     * @param studentList ArrayList of students in original order.
     * @param tuitionClassList ArrayList of tuitionClasses which we are going to match the order.
     */
    private void filterStudentBasedOnClasses(
            ArrayList<Student> listToAddTo, ArrayList<Student> studentList, ArrayList<TuitionClass> tuitionClassList) {
        for (int i = 0; i < tuitionClassList.size(); i++) {
            TuitionClass tuitionClass = tuitionClassList.get(i);
            StudentNameList studentNameList = tuitionClass.getStudentList();
            //check if student in the name list, then add to a new array if its not inside
            for (int j = 0; j < studentNameList.size(); j++) {
                Student toAdd = getStudentWithName(studentList, studentNameList.get(j));
                if (toAdd != null && !listToAddTo.contains(toAdd)) {
                    listToAddTo.add(toAdd);
                }
            }
        }
    }

    /**
     * Adds students from the studentList with no classes in the tuitionClassList into the listToAddTo.
     *
     * @param listToAddTo ArrayList to be added to.
     * @param studentList Original arrayList of students.
     * @param tuitionClassList ArrayList of tuitionClass to check if student is inside.
     */
    private void addStudentsWithNoClasses(
            ArrayList<Student> listToAddTo, ArrayList<Student> studentList, ArrayList<TuitionClass> tuitionClassList) {
        for (int i = 0; i < studentList.size(); i++) {
            boolean hasClass = false;
            Student studentToCheck = studentList.get(i);
            for (TuitionClass tuitionClass : tuitionClassList) {
                if (tuitionClass.containsStudent(studentToCheck.getName())) {
                    hasClass = true;
                }
            }
            if (!hasClass) {
                listToAddTo.add(studentToCheck);
            }
        }
    }

    /**
     * Gets the student with given name from the ArrayList of students.
     *
     * @param unsortedStudentList ArrayList of students to get student with given name from.
     * @param name Name of student to be returned from the list.
     * @return Student with the given name.
     */
    private Student getStudentWithName(ArrayList<Student> unsortedStudentList, Name name) {
        for (Student student : unsortedStudentList) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (this == obj) {
            return true;
        } else if (obj instanceof SortCommand) {
            SortCommand sortCommand = (SortCommand) obj;
            return this.directionOfSort.equals(sortCommand.directionOfSort) && this.sortBy.equals(sortCommand.sortBy);
        } else {
            return false;
        }
    }
}
