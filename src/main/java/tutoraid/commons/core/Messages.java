package tutoraid.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.";
    public static final String MESSAGE_INVALID_ADD_COMMAND = "Invalid add command format! "
            + "Must start with 'add -s', 'add -l', 'add -p', or 'add -sl'.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_DELETE_COMMAND = "Invalid delete command format! "
            + "Must start with 'del -s', 'del -l', 'del -p', or 'del -sl'.";
    public static final String MESSAGE_INVALID_FIND_COMMAND = "Invalid find command format! "
            + "Must start with 'find -s' or 'find -l'.";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = "The student index provided is invalid.";
    public static final String MESSAGE_LIST_SUCCESS = "Listed all students and lessons.";
    public static final String MESSAGE_INVALID_LESSON_DISPLAYED_INDEX = "The lesson index provided is invalid.";
    public static final String MESSAGE_INVALID_EDIT_COMMAND = "Invalid edit command format! "
            + "Must start with 'edit -s' or 'edit -l'.";
    public static final String MESSAGE_INVALID_STUDENT_ALREADY_ATTEND_LESSON = "The student provided"
            + " is already attending the lesson provided.";
    public static final String MESSAGE_INVALID_STUDENT_NOT_IN_LESSON = "The student provided"
            + " is not attending the lesson provided.";
    public static final String MESSAGE_INVALID_LESSON_FULL = "The lesson provided is full.";
    public static final String MESSAGE_INVALID_JSON_CAPACITY = "One or more lessons is over capacity.";
    public static final String MESSAGE_INVALID_VIEW_COMMAND = "Invalid view command format! "
            + "Must start with 'view -s' or 'view -l'.";
    public static final String MESSAGE_STUDENTS_LISTED_OVERVIEW = "%1$d student(s) listed!";
    public static final String MESSAGE_LESSONS_LISTED_OVERVIEW = "%1$d lesson(s) listed!";
    public static final String MESSAGES_SHOWING_HELP_MESSAGE = "Opened help window.";
    public static final String MESSAGE_INVALID_NO_PROGRESS_TO_DELETE = "This student has no progress"
            + " entries to delete from.";
    public static final String MESSAGE_CAPACITY_LESS_THAN_STUDENTS = "The given capacity is lower "
            + "than the number of students currently enrolled in the lesson!";
}
