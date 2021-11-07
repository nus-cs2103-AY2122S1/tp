package tutoraid.commons.core;

public class HelpGuide {
    public static final String EXAMPLE = "Example(s): ";
    public static final String FORMAT = "Format: ";
    public static final String S_FLAG = "-s";
    public static final String L_FLAG = "-l";

    public static final String USER_GUIDE_TITLE = "TutorAid Quick User Guide";
    public static final String OVERVIEW = "TutorAid is a desktop app for private tutors to match their students’ "
            + "contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a "
            + "Graphical User Interface (GUI). If you can type fast, TutorAid can get your student management tasks "
            + "done faster than traditional GUI apps.";
    public static final String FULL_USER_GUIDE = "For the full user guide, please refer to: "
            + "https://ay2122s1-cs2103t-w16-3.github.io/tp/UserGuide.html   ";

    public static final String QUICK_START_TITLE = "Quick Start";
    public static final String QUICK_START = "1. Ensure you have Java 11 or above installed in your Computer.\n"
            + "2. Once you have opened tutoraid.jar, type the command in the command box and press Enter to execute.\n"
            + "3. Refer to the Features below for details of each command.";

    public static final String FEATURES_TITLE = "Features";
    public static final String FEATURES_NOTES = "1. Words in UPPER_CASE are the parameters to be supplied by the "
            + "user and can contain spaces.\n"
            + "2. Items in square brackets are optional.\n"
            + "3. Parameters can be in any order.\n"
            + "4. If a parameter is expected only once in the command but if you specify it multiple times, only the "
            + "last occurrence of the parameter will be taken.\n"
            + "5. ... signals that multiple parameters of this type can be accepted (separated by a space), but there "
            + "must be at least one parameter present.\n"
            + "6. Extraneous parameters for commands that do not take in parameters will be ignored.";

    public static final String HELP_TITLE = "Viewing help";
    public static final String HELP_COMMAND = "help";
    public static final String HELP_DESC = "Shows a message explaining how to access the help page.";

    public static final String ADD_S_TITLE = "Adding a student";
    public static final String ADD_S_DESC = "Adds a new student to TutorAid.";
    public static final String ADD_S_COMMAND = "add -s sn/STUDENT_NAME [sp/STUDENT_PHONE] [pn/PARENT_NAME] "
            + "[pp/PARENT_PHONE]";
    public static final String ADD_S_EXAMPLE = "add -s sn/John Does sp/81234567 pn/Mrs Doe pp/91234567";

    public static final String ADD_L_TITLE = "Adding a lesson";
    public static final String ADD_L_DESC = "Adds a new lesson to TutorAid.";
    public static final String ADD_L_COMMAND = "add -l s/LESSON_NAME [c/LESSON_PHONE] [p/LESSON_PRICE] "
            + "[t/LESSON_TIMING]";
    public static final String ADD_L_EXAMPLE = "add -l s/Maths 1 c/50 p/100 t/Mon 1000-1200";

    public static final String LIST_TITLE = "Listing all students and lessons";
    public static final String LIST_DESC = "Shows a list of all students and lessons in TutorAid in the order that "
            + "they were added.";
    public static final String LIST_GENERAL_COMMAND = "list [-a]";
    public static final String LIST_EXAMPLE = "list";
    public static final String LIST_EXAMPLE_DESC = " displays all students and lessons in TutorAid while showing only "
            + "their names and list indexes. ";
    public static final String LIST_A_EXAMPLE = "list -a";
    public static final String LIST_A_EXAMPLE_DESC = " displays all of the details of the students and lessons in "
            + "TutorAid in full";

    public static final String DEL_S_TITLE = "Deleting a student";
    public static final String DEL_S_DESC = "Deletes the specified student with the given student index from TutorAid.";
    public static final String DEL_S_COMMAND = "del -s STUDENT_INDEX";
    public static final String DEL_S_EXAMPLE = "del -s 2";
    public static final String DEL_S_EXAMPLE_DESC = " deletes the 2nd student from TutorAid.";

    public static final String DEL_L_TITLE = "Deleting a lesson";
    public static final String DEL_L_DESC = "Deletes the specified lesson with the given lesson index from TutorAid.";
    public static final String DEL_L_COMMAND = "del -l LESSON_INDEX";
    public static final String DEL_L_EXAMPLE = "del -l 3";
    public static final String DEL_L_EXAMPLE_DESC = " deletes the 3rd lesson from TutorAid.";

    public static final String EDIT_S_TITLE = "Editing a student";
    public static final String EDIT_S_DESC = "Edits the details of the specified student with the given student "
            + "index in TutorAid.";
    public static final String EDIT_S_COMMAND = "edit STUDENT_INDEX [sn/STUDENT_NAME] [sp/STUDENT_PHONE] "
            + "[pn/PARENT_NAME] [pp/PARENT_PHONE]";
    public static final String EDIT_S_EXAMPLE = "edit -s 2 pp/91112222";
    public static final String EDIT_S_EXAMPLE_DESC = " changes the 2nd student’s parent contact number in "
            + "TutorAid to 91112222.";

    public static final String EDIT_L_TITLE = "Editing a lesson";
    public static final String EDIT_L_DESC = "Edits the specified lesson with the given lesson index in TutorAid.";
    public static final String EDIT_L_COMMAND = "edit LESSON_INDEX [n/LESSON_NAME] [c/LESSON_CAPACITY] "
            + "[p/LESSON_TIMING] [t/LESSON_TIMING]";
    public static final String EDIT_L_EXAMPLE = "edit -l 2 c/17";
    public static final String EDIT_L_EXAMPLE_DESC = " changes the 2nd lesson's capacity in "
            + "TutorAid to 17.";

    public static final String VIEW_S_TITLE = "Viewing a student";
    public static final String VIEW_S_DESC = "Displays the specified student’s name, phone number and progress, "
            + "along with their parent’s name and phone number.";
    public static final String VIEW_S_COMMAND = "view -s STUDENT_INDEX";
    public static final String VIEW_S_EXAMPLE = "view -s 2";
    public static final String VIEW_S_EXAMPLE_DESC = " shows the details associated with the 2nd student.";

    public static final String VIEW_L_TITLE = "Viewing a lesson";
    public static final String VIEW_L_DESC = "Displays the specified lesson’s name, capacity, price and "
            + "timing.";
    public static final String VIEW_L_COMMAND = "view -l LESSON_INDEX";
    public static final String VIEW_L_EXAMPLE = "view -l 4";
    public static final String VIEW_L_EXAMPLE_DESC = " shows the details associated with the 4th lesson.";

    public static final String ADD_P_TITLE = "Adding progress for a student";
    public static final String ADD_P_DESC = "Adds a given string representing progress to a student with a given "
            + "student index.";
    public static final String ADD_P_COMMAND = "add -p STUDENT_INDEX PROGRESS";
    public static final String ADD_P_EXAMPLE = "add -p 2 completed homework";
    public static final String ADD_P_EXAMPLE_DESC = " adds 'completed homework' to the 2nd student in TutorAid.";

    public static final String DEL_P_TITLE = "Deleting progress from a student";
    public static final String DEL_P_DESC = "Removes the string representing progress from the student with a given "
            + "student index.";
    public static final String DEL_P_COMMAND = "del -p STUDENT_INDEX";
    public static final String DEL_P_EXAMPLE = "del -p 2";
    public static final String DEL_P_EXAMPLE_DESC = " deletes the progress of the 2nd student in TutorAid.";

    public static final String FIND_TITLE = "Locate students or lessons by name";
    public static final String FIND_DESC1 = "A case-insensitive search is done to find student or lesson names "
            + "which contain any of the given keywords. Use ";
    public static final String FIND_DESC2 = " flag to search for students and ";
    public static final String FIND_DESC3 = " to search for lessons.";
    public static final String FIND_COMMAND = "find FLAG KEYWORD [MORE_KEYWORDS]";
    public static final String FIND_S_EXAMPLE = "find -s John";
    public static final String FIND_S_EXAMPLE_DESC = " returns students who have the word 'john' in their names. ";
    public static final String FIND_L_EXAMPLE = "find -l Maths";
    public static final String FIND_L_EXAMPLE_DESC = " returns lessons that have the word 'maths' in their names.";

    public static final String ADD_SL_TITLE = "Adding student(s) to lesson(s)";
    public static final String ADD_SL_DESC = "Tells TutorAid that the specified student(s) attend "
            + "the specified lesson(s).";
    public static final String ADD_SL_COMMAND = "add -sl s/STUDENT_INDEX... l/LESSON_INDEX...";
    public static final String ADD_SL_EXAMPLE = "add -sl s/1 2 3 l/1 2";
    public static final String ADD_SL_EXAMPLE_DESC = " adds students with indexes 1, 2, 3 into lessons "
            + "with indexes 1, 2.";

    public static final String DEL_SL_TITLE = "Deleting student(s) from lesson(s)";
    public static final String DEL_SL_DESC = "Tells TutorAid that the specified student(s) no longer attend "
            + "the specified lesson(s).";
    public static final String DEL_SL_COMMAND = "del -sl s/STUDENT_INDEX... l/LESSON_INDEX...";
    public static final String DEL_SL_EXAMPLE = "del -sl s/1 2 3 l/1 2";
    public static final String DEL_SL_EXAMPLE_DESC = " deletes students with indexes 1, 2, 3 from lessons "
            + "with indexes 1, 2.";

    public static final String CLEAR_TITLE = "Clearing all entries";
    public static final String CLEAR_DESC = "Clears all student and lesson entries from TutorAid.";
    public static final String CLEAR_COMMAND = "clear";

    public static final String EXIT_TITLE = "Exiting TutorAid";
    public static final String EXIT_DESC = "Exits the program.";
    public static final String EXIT_COMMAND = "exit";

    public static final String SAVING_DATA_TITLE = "Saving the data";
    public static final String SAVING_DATA_DESC = "TutorAid data is saved in the hard disk automatically after any "
            + "command that changes the data. There is no need to save manually.";

    public static final String EDIT_DATA_TITLE = "Editing the data file";
    public static final String EDIT_DATA_DESC1 = "TutorAid student data are saved as a JSON file ";
    public static final String EDIT_STUDENTS_FILEPATH = "[JAR file location]/data/tutorAidStudents.json";
    public static final String EDIT_DATA_DESC2 = ", while TutorAid lesson data are saved as a JSON file ";
    public static final String EDIT_LESSONS_FILEPATH = "[JAR file location]/data/tutorAidLessons.json";
    public static final String EDIT_DATA_DESC3 = "Advanced users are welcome to update data directly by editing "
            + "any of the two data files.";
    public static final String EDIT_CAUTION = "Caution: If your changes to the data file makes its format invalid, "
            + "TutorAid will discard all data and start with an empty data file at the next run.";

    public static final String FAQ_TITLE = "FAQ";
    public static final String FAQ = "Q: How do I transfer my data to another Computer?\n"
            + "A: Install the app in the other computer and overwrite the empty data file "
            + "it creates with the file that contains the data of your previous TutorAid home folder.";
}
