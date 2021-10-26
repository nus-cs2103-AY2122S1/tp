package tutoraid.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_ADD_COMMAND = "Invalid add command format! "
            + "Must start with 'add -s' or 'add -p' or 'add -sl'";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_DELETE_COMMAND = "Invalid delete command format! "
            + "Must start with 'del -s' or 'del -p' or 'add -sl'";;
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = "The student index provided is invalid";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_MULTIPLE_INDEXES =
            "The student indexes provided are invalid";
    public static final String MESSAGE_INVALID_LESSON_DISPLAYED_MULTIPLE_INDEXES =
            "The lesson indexes provided are invalid";
    public static final String MESSAGE_INVALID_STUDENT_ALREADY_ATTEND_LESSON = "The students provided"
            + " must not be attending any of the lessons provided";
    public static final String MESSAGE_INVALID_STUDENT_NOT_IN_LESSON = "The students provided"
            + " must be attending all of the lessons provided";
    public static final String MESSAGE_INVALID_LESSON_FULL = "At least one of the lessons cannot accommodate"
            + " all the students provided as it will exceed capacity";
    public static final String MESSAGE_STUDENTS_LISTED_OVERVIEW = "%1$d students listed!";
}
