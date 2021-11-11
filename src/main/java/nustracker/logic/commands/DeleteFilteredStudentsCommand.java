package nustracker.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustracker.commons.core.Messages.MESSAGE_STUDENT_LIST_NOT_SHOWN;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import nustracker.logic.commands.exceptions.CommandException;
import nustracker.model.Model;
import nustracker.model.student.Student;
import nustracker.model.student.StudentId;
import nustracker.ui.MainWindow.CurrentlyShownList;


/**
 * Deletes all students that are currently in the filtered list of AddressBook.
 */
public class DeleteFilteredStudentsCommand extends Command {

    public static final String COMMAND_WORD = "delfiltered";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes all the students that are currently shown in the student list.\n"
            + "It is recommended to change which students are shown using the filter command before using this.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_DELETE_ALL_FILTERED_SUCCESS =
            "Successfully deleted all shown students in the student list.";

    public static final String MESSAGE_AT_LEAST_ONE_STUDENT_REQ_FOR_DELFILTER =
            COMMAND_WORD + " requires at least one student to be shown in the list in order for it to work.";



    @Override
    public CommandResult execute(Model model, CurrentlyShownList currentlyShownList)
            throws CommandException {
        requireNonNull(model);

        if (currentlyShownList != CurrentlyShownList.STUDENTS_LIST) {
            throw new CommandException(MESSAGE_STUDENT_LIST_NOT_SHOWN);
        }

        ObservableList<Student> filteredStudents = model.getFilteredStudentList();

        if (filteredStudents.size() == 0) {
            throw new CommandException(MESSAGE_AT_LEAST_ONE_STUDENT_REQ_FOR_DELFILTER);
        }

        // Make a copy since the Observable List contents are modified while this is running.
        ArrayList<Student> copyOfFilteredStudents = new ArrayList<>();

        for (Student currStudent : filteredStudents) {
            copyOfFilteredStudents.add(currStudent);
        }

        for (Student currStudent : copyOfFilteredStudents) {
            StudentId currStudentId = currStudent.getStudentId();
            DeleteStudentCommand delThisStudent = new DeleteStudentCommand(currStudentId);

            delThisStudent.execute(model, CurrentlyShownList.STUDENTS_LIST);

        }

        model.updateFilteredStudentList(currStudent -> true);

        return new CommandResult(MESSAGE_DELETE_ALL_FILTERED_SUCCESS);
    }



    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteFilteredStudentsCommand)) {
            return false;
        } else {
            // All DeleteFilteredCommands are the same.
            return true;
        }

    }
}
