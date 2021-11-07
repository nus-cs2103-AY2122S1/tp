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
import seedu.academydirectory.model.student.Participation;
import seedu.academydirectory.model.student.Student;

public class ParticipationCommand extends Command {

    public static final String COMMAND_WORD = "participation";

    public static final String HELP_MESSAGE = "#### Editing a student’s Studio participation: `participation`\n"
            + "Avengers will be able to track the participation of their students during the relevant studios. "
            + "\n"
            + "We will be keeping track of Studio participation with a counter system which starts at 0 by default.\n"
            + "\n"
            + "Format: `participation INDEX... ses/STUDIO_SESSION add/PARTICIPATION_TO_ADD`\n"
            + "\n"
            + "* Edits the Studio participation of a student or multiple students based on their `INDEX`.\n"
            + "* Modifies the student(s) at the specified `INDEX`. The index refers to the index number shown "
            + "in the displayed student list. The index **must be a positive integer** 1, 2, 3, …\u200B\n"
            + "* The `STUDIO_SESSION` field is a positive integer from 1 to 12 inclusive which refers to the "
            + "Studio Session to be modified.\n"
            + "* The `PARTICIPATION_TO_ADD` field is an integer from -500 to 500 inclusive which indicates "
            + "the Participation score of the student.\n"
            + "* `PARTICIPATION_TO_ADD` will be added to the student's current Participation score\n"
            + "* A student’s Studio Participation score cannot be reduced below 0.\n"
            + "* If a student's `Attendance` is `false` and the Participation score to be added is greater than 0, "
            + "the student will also be marked as having attended the Studio.\n"
            + "\n"
            + "Examples:\n"
            + "* `participation 1, 2, 3 ses/12 add/500`\n"
            + "* `participation 4, 6 ses/2 add/-300`\n"
            + "* `participation 4 ses/9 add/1`";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the participation count of the student(s)\n"
            + "Parameters: INDEX(ES) (positive integer) "
            + "ses/ STUDIO_SESSION_INDEX (positive integer within range) "
            + "add/ PARTICIPATION_TO_ADD\n"
            + "Type in `help participation` for more details";

    public static final String MESSAGE_UPDATE_PARTICIPATION_SUCCESS = "Participation updated!";
    public static final String COMMIT_MESSAGE = "Participation updated!";

    private final ArrayList<Index> indexArrayList;
    private final Integer studioSession;
    private final Integer participationUpdate;

    /**
     * @param participationUpdate The new updated participation count
     * @param studioSession The studio session number
     * @param indexArrayList The ArrayList of students that are involved in the ParticipationCommand
     */
    public ParticipationCommand(Integer participationUpdate, Integer studioSession,
                                ArrayList<Index> indexArrayList) {
        requireAllNonNull(indexArrayList, participationUpdate, studioSession);
        this.indexArrayList = indexArrayList;
        this.participationUpdate = participationUpdate;
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

        if (participationUpdate > 0) {
            int sessionCount = model.getFilteredStudentList().get(0).getParticipation().getSessionCount();
            for (Index index : indexArrayList) {
                Student studentToEdit = lastShownList.get(index.getZeroBased());
                boolean[] newAttArr = studentToEdit.getAttendance().getAttendanceArray();
                newAttArr[studioSession - 1] = true;
                Attendance newAttendance = new Attendance(sessionCount);
                newAttendance.setAttendance(newAttArr);
                studentToEdit.setAttendance(newAttendance);
            }
        }

        for (Index index : indexArrayList) {
            Student studentToEdit = lastShownList.get(index.getZeroBased());
            Participation participationToEdit = studentToEdit.getParticipation();
            participationToEdit = participationToEdit.add(studioSession, participationUpdate);

            Student editedStudent = new Student(
                    studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
                    studentToEdit.getTelegram(), studentToEdit.getStudioRecord(),
                    studentToEdit.getAssessment(), studentToEdit.getTags());
            editedStudent.setParticipation(participationToEdit);
            model.setStudent(studentToEdit, editedStudent);
        }

        return new CommandResult(MESSAGE_UPDATE_PARTICIPATION_SUCCESS,
                Optional.of(COMMIT_MESSAGE));

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ParticipationCommand)) {
            return false;
        }
        ParticipationCommand e = (ParticipationCommand) other;
        return indexArrayList.equals(e.indexArrayList)
                && participationUpdate == e.participationUpdate
                && studioSession.equals(e.studioSession);
    }



}
