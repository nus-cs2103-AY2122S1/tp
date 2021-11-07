package seedu.academydirectory.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = "The student index provided is invalid";
    public static final String MESSAGE_STUDENTS_LISTED_OVERVIEW = "%1$d students listed!";
    public static final String MESSAGE_HELP_NOT_EXIST = "Error: No instruction exist for the command %1$s\n"
            + "Please type in `help` for more assistance.";

    public static final String GENERAL_HELP_MESSAGE = "### Command Summary\n"
            + "This subsection provides a summary for all the commands used in Academy Directory\n"
            + "\nAction | Format\n"
            + "--------|--------\n"
            + "[**Add Student**](#adding-a-student-add) | `add n/NAME e/EMAIL te/TELE_HANDLE "
            + "[p/PHONE_NUMBER] [t/TAG]`\n"
            + "[**Delete Student**](#deleting-a-student-delete) | `delete INDEX`\n"
            + "[**Tag Student**](#tagging-a-student--tag)| `tag INDEX t/TAG [t/TAG]...`\n"
            + "[**Get Personal Detail**](#getting-personal-detail-of-students-get) | `get [p/] [e/] [te/] [n/NAME]`\n"
            + "[**Edit Personal Detail**](#editing-a-students-detail--edit) | `edit INDEX [n/NAME] "
            + "[p/PHONE_NUMBER] [e/EMAIL] [te/TELE_HANDLE]`\n"
            + "[**Add Grade**](#adding-grades-for-an-assessment--grade) | `grade INDEX as/ASSESSMENT g/GRADE`\n"
            + "[**Edit Attendance**](#editing-a-students-studio-attendance-attendance) | `attendance INDEX "
            + "ses/STUDIO_SESSION att/ATTENDANCE_STATUS`\n"
            + "[**Edit Participation**](#editing-a-students-studio-participation-participation) | "
            + "`participation INDEX ses/STUDIO_SESSION add/PARTICIPATION_TO_ADD`\n"
            + "[**Show Grades**](#displaying-the-grades-for-an-assessment--show) | `show ASSESSMENT`\n"
            + "[**View student full details**](#view-student-details) | `view INDEX`\n"
            + "[**Visualize all grades**](#visualizing-assessments-grades-of-the-entire-class-visualize) "
            + "| `visualize`\n"
            + "[**Filter Academy Directory**](#filtering-academy-directory-by-name-or-tag-filter) | `filter KEYWORD "
            + "[MORE_KEYWORDS]`\n"
            + "[**Sort Student List**](#sorting-student-list--sort) | `sort attr/ATTRIBUTE ord/ORDER`\n"
            + "[**List All Students**](#listing-all-students--list) | `list`\n"
            + "[**Clear Student List**](#clearing-all-students--clear) | `clear`\n"
            + "[**Undo**](#undo-changes-to-academy-directory-undo) | `undo`\n"
            + "[**Redo**](#redo-changes-to-academy-directory-redo) | `redo`\n"
            + "[**Help**](#seeking-help--help) | `help [COMMAND_NAME]`\n"
            + "[**Exit**](#exiting-the-program--exit) | `exit`\n"
            + "[**View Commit History**](#for-advanced-users-viewing-commit-history-history) | `history`\n"
            + "[**Revert Commit**](#for-advanced-users-reverting-commit-revert) | `revert HASH`";
}
