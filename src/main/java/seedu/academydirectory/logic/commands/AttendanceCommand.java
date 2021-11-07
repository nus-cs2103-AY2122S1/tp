package seedu.academydirectory.logic.commands;

import static seedu.academydirectory.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.academydirectory.commons.core.Messages;
import seedu.academydirectory.commons.core.index.Index;
import seedu.academydirectory.logic.commands.exceptions.CommandException;
import seedu.academydirectory.model.VersionedModel;
import seedu.academydirectory.model.student.Attendance;
import seedu.academydirectory.model.student.Student;
import seedu.academydirectory.model.student.StudioRecord;

public class AttendanceCommand extends Command {

    public static final String COMMAND_WORD = "attendance";

    public static final String HELP_MESSAGE = "#### Editing a student's Studio attendance: `attendance`\n"
            + "Edits the attendance status of their students.\n\n"
            + "Format: `attendance INDEX ses/STUDIO_SESSION att/ATTENDANCE_STATUS`\n\n"
            + "* Edits the attendance of a student or multiple students based on their `INDEX`.\n"
            + "* Modifies the student(s) at the specified `INDEX`. The index refers to the index number shown in the "
            + "displayed student list. The index **must be a positive integer** 1, 2, 3, …\u200B\n"
            + "* Multiple `INDEX` can be parsed in at once as long as they are all valid.\n"
            + "* The `STUDIO_SESSION` field is a positive integer from 1 to 12 inclusive which"
            +  " refers to the Studio Session to be modified.\n"
            + "* The `ATTENDANCE_STATUS` field can only be a 1 or 0 to indicate whether the student attended "
            + "the session or not where 1 marks a student as having attended while 0 marks a student as unattended.\n"
            + "* Existing values will be updated to the input values.\n\nExamples:\n\n"
            + "* `attendance 1 ses/1 att/1` Marks the student with index number `1` as present for studio session 1\n"
            + "* `attendance 1, 2, 3 ses/12 att/0` Marks students with "
            + "index numbers `1`, `2`, and `3` as absent for studio session 12\n"
            +  "* `attendance 1, 2, 3 ses/7 att/1` Marks students with index numbers "
            + "`1`, `2`, and `3` as present for studio session 7\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the attendance status of the student(s)\n"
            + "Parameters: INDEX(ES) (positive integer) "
            + "ses/ STUDIO_SESSION_INDEX (positive integer within range) "
            + "att/ ATTENDANCE_STATUS (0 or 1)\n"
            + "Type in `help attendance` for more details\n";

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
    public CommandResult execute(VersionedModel model) throws CommandException {
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
            StudioRecord newStudioRecord = new StudioRecord(attendanceToEdit, studentToEdit.getParticipation());
            Student editedStudent = new Student(studentToEdit.getName(), studentToEdit.getPhone(),
                    studentToEdit.getEmail(), studentToEdit.getTelegram(), newStudioRecord,
                    studentToEdit.getAssessment(), studentToEdit.getTags());
            model.setStudent(studentToEdit, editedStudent);
        }

        return new CommandResult(MESSAGE_UPDATE_ATTENDANCE_SUCCESS, Optional.of(MESSAGE_UPDATE_ATTENDANCE_SUCCESS));

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
