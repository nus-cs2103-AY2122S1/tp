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
    public static final String FULL_USER_GUIDE = "For the full user guide and the Beginner's Tutorial, please "
            + "refer to: https://ay2122s1-cs2103t-w16-3.github.io/tp/UserGuide.html   ";

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
            + "5. '...' signals that multiple parameters of this type can be accepted (separated by a space), "
            + "but there must be at least one parameter present.\n"
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
    public static final String ADD_L_COMMAND = "add -l n/LESSON_NAME [c/LESSON_CAPACITY] [p/LESSON_PRICE] "
            + "[t/LESSON_TIMING]";
    public static final String ADD_L_EXAMPLE = "add -l n/Maths 1 c/50 p/100 t/Mon 1000-1200";

    public static final String LIST_TITLE = "Listing all students and lessons";
    public static final String LIST_DESC = "Shows a list of all students and lessons in TutorAid in the order that "
            + "they were added.";
    public static final String LIST_GENERAL_COMMAND = "list [-a]";
    public static final String LIST_EXAMPLE = "list";
    public static final String LIST_EXAMPLE_DESC = " displays all students and lessons in TutorAid while showing only "
            + "their names and list indexes. ";
    public static final String LIST_A_EXAMPLE = "list -a";
    public static final String LIST_A_EXAMPLE_DESC = " displays all of the details of the students and lessons in "
            + "TutorAid in full.";

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
    public static final String EDIT_S_COMMAND = "edit -s STUDENT_INDEX [sn/STUDENT_NAME] [sp/STUDENT_PHONE] "
            + "[pn/PARENT_NAME] [pp/PARENT_PHONE]";
    public static final String EDIT_S_EXAMPLE = "edit -s 2 pp/91112222";
    public static final String EDIT_S_EXAMPLE_DESC = " changes the 2nd student’s parent contact number in "
            + "TutorAid to 91112222.";

    public static final String EDIT_L_TITLE = "Editing a lesson";
    public static final String EDIT_L_DESC = "Edits the specified lesson with the given lesson index in TutorAid.";
    public static final String EDIT_L_COMMAND = "edit -l LESSON_INDEX [n/LESSON_NAME] [c/LESSON_CAPACITY] "
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
    public static final String ADD_P_DESC = "Adds a progress entry to the student at the specified student index.";
    public static final String ADD_P_COMMAND = "add -p STUDENT_INDEX PROGRESS";
    public static final String ADD_P_EXAMPLE = "add -p 2 completed homework";
    public static final String ADD_P_EXAMPLE_DESC = " adds 'completed homework' to the 2nd student in TutorAid.";

    public static final String DEL_P_TITLE = "Deleting progress from a student";
    public static final String DEL_P_DESC = "Removes the latest progress entry from the student at the "
            + "specified student index.";
    public static final String DEL_P_COMMAND = "del -p STUDENT_INDEX";
    public static final String DEL_P_EXAMPLE = "del -p 2";
    public static final String DEL_P_EXAMPLE_DESC = " deletes the latest progress of the 2nd student in TutorAid.";

    public static final String FIND_S_TITLE = "Finding students by name";
    public static final String FIND_S_DESC = "Finds students whose names contain any of the given keywords by doing "
            + "a case-insensitive search.";
    public static final String FIND_S_COMMAND = "find -s KEYWORD...";
    public static final String FIND_S_EXAMPLE = "find -s John";
    public static final String FIND_S_EXAMPLE_DESC = " returns students who have the word 'john' in their names. ";

    public static final String FIND_L_TITLE = "Finding lessons by name";
    public static final String FIND_L_DESC = "Finds lessons whose names contain any of the given keywords by doing "
            + "a case-insensitive search.";
    public static final String FIND_L_COMMAND = "find -l KEYWORD...";
    public static final String FIND_L_EXAMPLE = "find -l Maths";
    public static final String FIND_L_EXAMPLE_DESC = " returns lessons that have the word 'maths' in their names.";

    public static final String ADD_SL_TITLE = "Adding student(s) to lesson(s)";
    public static final String ADD_SL_DESC = "Adds student(s) into the lesson(s) they are enrolled in.";
    public static final String ADD_SL_COMMAND = "add -sl s/STUDENT_INDEX... l/LESSON_INDEX...";
    public static final String ADD_SL_EXAMPLE = "add -sl s/1 2 3 l/1 2";
    public static final String ADD_SL_EXAMPLE_DESC = " adds students with indexes 1, 2, 3 into lessons "
            + "with indexes 1, 2.";

    public static final String DEL_SL_TITLE = "Deleting student(s) from lesson(s)";
    public static final String DEL_SL_DESC = "Removes student(s) from the lesson(s) they are no longer enrolled in.";
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
    public static final String EDIT_DATA_DESC2 = " while TutorAid lesson data are saved as a JSON file ";
    public static final String EDIT_LESSONS_FILEPATH = "[JAR file location]/data/tutorAidLessons.json";
    public static final String EDIT_DATA_DESC3 = "\nAdvanced users are welcome to update data directly by editing "
            + "any of the two data files.";
    public static final String EDIT_CAUTION = "\nCaution: If your changes to any of the two data files make its "
            + "format or data invalid, TutorAid will discard both data files and replace them with "
            + " empty data files in the next run.";

    public static final String GLOSSARY_TITLE = "Glossary";
    public static final String CLI = "CLI: CLI (also known as a Command Line Interface) refers to a form of "
            + "interface that processes a user's input in the form of text.";
    public static final String GUI1 = "\nGUI: GUI (also known as a Graphical User Interface) refers to a form of "
            + "user interface that allows user to interact with the";
    public static final String GUI2 = " application through graphical icons such as buttons.";
    public static final String PROGRESS1 = "\nProgress: Refers to any remark you may want to add for a specific "
            + "student, such as their performance in class and ";
    public static final String PROGRESS2 = "their homework progression.";

    public static final String FAQ_TITLE = "FAQ";
    public static final String FAQ1 = "Q: How do I transfer my data to another Computer?\n"
            + "A: Install the app in the other computer and overwrite the empty data file "
            + "it creates with the file that contains the data of your previous TutorAid home folder.\n\n";
    public static final String FAQ2_Q = "Q: I am currently only able to view my students' latest progress entry, "
            + "how can I view all the entries?\n";
    public static final String FAQ2_ANS_PART1 = "A: To view all the progress entries of a student, you can key ";
    public static final String FAQ2_ANS_PART2 = " into the Command Box and hit ENTER. You should be able to see you 10 "
            + "most recently added progress entries.";
    public static final String FAQ2_ANS_PART3 = "Please note that you will not be able to view all the progress "
            + "entries of multiple students at any one time.\n\n";
    public static final String FAQ3_Q = "Q: I just added a new progress entry for one of my students, and all "
            + "my other students and lessons have disappeared. How do I view them again?\n";
    public static final String FAQ3_ANS_PART1 = "A: To revert back to the original view of all students "
            + "and all lessons, simply key in ";
    public static final String FAQ3_ANS_PART2 = "into the Command Box and hit ENTER.\n\n";
    public static final String FAQ4 = "Q: I have added more than 10 progress entries for a student, but why "
            + "am I only able to see up to the 10 most recent entries?\n"
            + "A: TutorAid only support storing up to 10 progress entries so that it is easier for tutors to manage "
            + "and view all of their entries for each student.";
}
