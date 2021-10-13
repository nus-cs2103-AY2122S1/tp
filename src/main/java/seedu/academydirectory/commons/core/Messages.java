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

    public static final String GENERAL_HELP_MESSAGE = "## Command summary\n"
            + "\n"
            + "Command name | Format, Examples\n"
            + "--------|------------------\n"
            + "**Add** | `add n/NAME [p/PHONE_NUMBER] e/EMAIL [t/TAG]…\u200B` <br> e.g., "
            + "`add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`\n"
            + "**Clear** | `clear`\n"
            + "**Delete** | `delete INDEX`<br> e.g., `delete 3`\n"
            + "**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [t/TAG]…\u200B`"
            + "<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`\n"
            + "**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`\n"
            + "**List** | `list`\n"
            + "**Attendance** | `attendance s/STUDIO_GROUP k/KEYWORD a/ATTENDANCE_STATUS [i/INDEX]`<br> "
            + "e.g., `attendance s/1 k/Aaron a/0`\n"
            + "**Studio Participation** | `grade n/STUDENT_NAME a/ASSESSMENT g/GRADE`<br> e.g., "
            + "`studiopart s/12 k/Benny a/3`\n"
            + "**Grade** | `grade n/STUDENT_NAME a/ASSESSMENT g/GRADE` <br> e.g., `grade n/Aaron a/RA1 g/15`\n"
            + "**Show Grades** | `showgrade a/ASSESSMENT [s/STUDIO_GROUP]` <br> e.g., `showgrade a/RA1`\n"
            + "**Help** | `help`\n";
}
