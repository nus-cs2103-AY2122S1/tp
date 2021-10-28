package seedu.academydirectory.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = "The student index provided is invalid";
    public static final String MESSAGE_STUDENTS_LISTED_OVERVIEW = "%1$d students listed!";
    public static final String MESSAGE_HELP_NOT_EXIST = "Error: No instruction exist for the command %1$s";

    public static final String GENERAL_HELP_MESSAGE = "### Command Summary\n"
            + "This subsection provides a summary for all the commands used in Academy Directory\n"
            + "\nAction | Format | Examples\n"
            + "--------|--------|----------\n"
            + "[**Add Student**](#adding-a-student-add) | `add n/NAME e/EMAIL te/TELE_HANDLE "
            + "[p/PHONE_NUMBER] [t/TAG]` | `add n/James Ho te/@TeleHandle p/22224444 e/jamesho@example.com t/Streams`\n"
            + "[**Delete Student**](#deleting-a-student-delete) | `delete INDEX` | `delete 3`\n"
            + "[**Tag Student**](#tagging-a-student--tag)| `tag INDEX t/TAG [t/TAG]...` | `tag 1 t/mission t/streams`\n"
            + "[**Get Personal Detail**](#getting-personal-detail-of-students-get) | `get [p/] [e/] [te/] [n/NAME]` |"
            + " `get p/ e/ te/ n/Alex Yeoh`\n"
            + "[**Edit Personal Detail**](#editing-a-students-detail--edit) | `edit INDEX [n/NAME] "
            + "[p/PHONE_NUMBER] [e/EMAIL] [te/TELE_HANDLE]` | `edit 2 n/James Lee e/jameslee@example.com`\n"
            + "[**Add Grade**](#adding-grades-for-an-assessment--grade) | `grade INDEX as/ASSESSMENT g/GRADE` | "
            + "`grade INDEX as/RA1 g/15`\n"
            + "[**Edit Attendance**](#editing-a-students-studio-attendance-attendance) | `attendance INDEX "
            + "ses/STUDIO_SESSION att/ATTENDANCE_STATUS` | `attendance 1, 2 ses/1 att/1`\n"
            + "[**Edit Participation**](#editing-a-students-studio-participation-participation) | "
            + "`participation INDEX ses/STUDIO_SESSION add/PARTICIPATION_TO_ADD` | `participation 1 ses/12 att/0`\n"
            + "[**Show Grades**](#displaying-the-grades-for-an-assessment--show) | `show ASSESSMENT` | `show RA1`\n"
            + "[**Visualize all grades**](#visualizing-assessments-grades-of-the-entire-class-visualize) | `visualize` "
            + "| `visualize`\n"
            + "[**Filter Academy Directory**](#filtering-academy-directory-by-name-or-tag-filter) | `filter KEYWORD "
            + "[MORE_KEYWORDS]` | `filter James Jake`\n"
            + "[**Sort Student List**](#sorting-student-list--sort) | `sort attr/ATTRIBUTE ord/ORDER` "
            + "| `sort attr/average ord/asc`\n"
            + "[**List All Students**](#listing-all-students--list) | `list` | `list`\n"
            + "[**Clear Student List**](#clearing-all-students--clear) | `clear` | `clear`\n"
            + "[**Undo**](#undo-changes-to-academy-directory-undo) | `undo` | `undo`\n"
            + "[**Redo**](#redo-changes-to-academy-directory-redo) | `redo` | `redo`\n"
            + "[**Help**](#seeking-help--help) | `help [COMMAND_NAME]` | `help add`\n"
            + "[**Exit**](#exiting-the-program--exit) | `exit` | `exit`\n"
            + "[**View Commit History**](#for-advanced-users-viewing-commit-history-history) | `history` | `history`\n"
            + "[**Revert Commit**](#for-advanced-users-reverting-commit-revert) | `revert HASH` | `revert df7x3`";
}
