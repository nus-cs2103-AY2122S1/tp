package seedu.academydirectory.logic.commands;

import static seedu.academydirectory.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.academydirectory.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.ArrayList;
import java.util.List;

import seedu.academydirectory.commons.core.Messages;
import seedu.academydirectory.commons.core.index.Index;
import seedu.academydirectory.logic.commands.exceptions.CommandException;
import seedu.academydirectory.model.Model;
import seedu.academydirectory.model.student.Attendance;
import seedu.academydirectory.model.student.Student;

public class AttendanceCommand extends Command {

    public static final String COMMAND_WORD = "attendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the attendance status of the student(s) identified "
            + "by the index number used in the last person listing as well "
            + "as the index of the Studio session. Existing attendance will "
            + "be overwritten by the input.\n"
            + "Parameters: INDEX(ES) (must be a positive integer(s))"
            + "ses/ STUDIO_SESSION_INDEX (must be a positive integer within range)"
            + "att/ ATTENDANCE_STATUS (0 or 1)\n"
            + "Example: " + COMMAND_WORD + " * s/ 7 a/ 1";

    public static final String MESSAGE_UPDATE_ATTENDANCE_SUCCESS = "Attendance updated!";

    private final ArrayList<Index> indexArrayList;
    private final boolean attendanceStatus;
    private final Integer studioSession;

    /**
     * @param attendanceStatus true if attended, false otherwise
     * @param studioSession The studio session number
     * @param indexArrayList The ArrayList of students that are involved in the AttendanceCommand
     */
    public AttendanceCommand(boolean attendanceStatus, Integer studioSession, ArrayList<Index> indexArrayList) {
        requireAllNonNull(indexArrayList, attendanceStatus, studioSession);
        this.indexArrayList = indexArrayList;
        this.attendanceStatus = attendanceStatus;
        this.studioSession = studioSession;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();

        for (Index index : indexArrayList) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }
        }

        for (Index index : indexArrayList) {
            Student studentToEdit = lastShownList.get(index.getZeroBased());
            Attendance attendanceToEdit = studentToEdit.getAttendance();
            attendanceToEdit = attendanceToEdit.update(studioSession, attendanceStatus);
            Student editedPerson = new Student(
                    studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
                    studentToEdit.getAddress(), studentToEdit.getStudioRecord(), studentToEdit.getAssessment(),
                    studentToEdit.getTags());
            editedPerson.setAttendance(attendanceToEdit);
            model.setStudent(studentToEdit, editedPerson);
        }
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_UPDATE_ATTENDANCE_SUCCESS);

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AttendanceCommand)) {
            return false;
        }

        AttendanceCommand e = (AttendanceCommand) other;
        return indexArrayList.equals(e.indexArrayList)
                && attendanceStatus == e.attendanceStatus
                && studioSession.equals(e.studioSession);
    }



}
