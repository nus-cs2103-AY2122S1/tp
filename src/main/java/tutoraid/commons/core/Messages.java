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
    public static final String MESSAGE_JSON_INTEGRITY_LESSON_OVER_CAPACITY = "The save data is corrupted: One or more "
            + "lessons has too many students. The student data will be discarded.";
    public static final String MESSAGE_JSON_INTEGRITY_LESSON_ERROR = "The lesson save data is corrupted and will be "
            + "discarded.";
    public static final String MESSAGE_JSON_INTEGRITY_STUDENT_ERROR = "The student save data is corrupted and will be "
            + "discarded.";
    public static final String MESSAGE_NO_STUDENT_DATA = "No student data found. Starting with sample students.";
    public static final String MESSAGE_NO_LESSON_DATA = "No lesson data found. Starting with sample lessons.";
    public static final String WELCOME_MESSAGE = "Welcome to TutorAid! Type help if you need a quick start guide.";
}
