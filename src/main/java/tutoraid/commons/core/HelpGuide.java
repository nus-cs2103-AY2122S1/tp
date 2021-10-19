package tutoraid.commons.core;

public class HelpGuide {
    public static final String EXAMPLE = "Example(s): ";
    public static final String FORMAT = "Format: ";

    public static final String USER_GUIDE_TITLE = "TutorAid Quick User Guide";
    public static final String OVERVIEW = "TutorAid is a desktop app for private tutors to match their students’ "
            + "contacts, optimized for use via a Command Line Interface (CLI) while still having the benefits of a "
            + "Graphical User Interface (GUI). If you can type fast, TutorAid can get your student management tasks "
            + "done faster than traditional GUI apps.";
    public static final String FULL_USER_GUIDE = "For the full user guide, please refer to: "
            + "https://ay2122s1-cs2103t-w16-3.github.io/tp/UserGuide.html   ";

    public static final String QUICK_START_TITLE = "Quick Start";
    public static final String QUICK_START_STEP1 = "1. Ensure you have Java 11 or above installed in your Computer.";
    public static final String QUICK_START_STEP2 = "2. Once you have opened tutoraid.jar, type the command in the "
            + "command box and press Enter to execute.";
    public static final String QUICK_START_STEP3 = "3. Refer to the Features below for details of each command.";

    public static final String FEATURES_TITLE = "Features";
    public static final String FEATURES_POINT1 = "1. Words in UPPER_CASE are the parameters to be supplied by the "
            + "user and can contain spaces.";
    public static final String FEATURES_POINT2 = "2. Items in square brackets are optional.";
    public static final String FEATURES_POINT3 = "3. Parameters can be in any order.";
    public static final String FEATURES_POINT4 = "4. If a parameter is expected only once in the command but if you "
            + "specify it multiple times, only the last occurrence of the parameter will be taken.";
    public static final String FEATURES_POINT5 = "5. Extraneous parameters for commands that do not take in "
            + "parameters will be ignored.";

    public static final String HELP_TITLE = "Viewing help";
    public static final String HELP_COMMAND = "help";
    public static final String HELP_DESC = "Shows a message explaining how to access the help page.";

    public static final String ADD_S_TITLE = "Adding a student";
    public static final String ADD_S_DESC = "Adds a new student to TutorAid.";
    public static final String ADD_S_COMMAND = "add -s sn/STUDENT_NAME [sp/STUDENT_PHONE] [pn/PARENT_NAME] "
            + "[pp/PARENT_PHONE]";
    public static final String ADD_S_EXAMPLE = "add -s sn/John Does sp/81234567 pn/Mrs Doe pp/91234567";

    public static final String LIST_TITLE = "Listing all students";
    public static final String LIST_DESC = "Shows a list of all students in TutorAid in the order that they were "
            + "added.";
    public static final String LIST_GENERAL_COMMAND = "list [-a]";
    public static final String LIST_EXAMPLE = "list";
    public static final String LIST_EXAMPLE_DESC = " displays all students in TutorAid while showing only their "
            + "name and list index. ";
    public static final String LIST_A_EXAMPLE = "list -a";
    public static final String LIST_A_EXAMPLE_DESC = " displays all students in TutorAid while showing all of their "
            + "data such as their contact number, payment status and so on.";

    public static final String DEL_S_TITLE = "Deleting a student";
    public static final String DEL_S_DESC = "Deletes the specified student with the given student index from TutorAid.";
    public static final String DEL_S_COMMAND = "del -s STUDENT_INDEX";
    public static final String DEL_S_EXAMPLE = "del -s 2";
    public static final String DEL_S_EXAMPLE_DESC = " deletes the 2nd student in TutorAid.";

    public static final String EDIT_S_TITLE = "Editing a student";
    public static final String EDIT_S_DESC = "Edits the specified student with the given student index from TutorAid.";
    public static final String EDIT_S_COMMAND = "edit STUDENT_INDEX [sn/STUDENT_NAME] [sp/STUDENT_PHONE] "
            + "[pn/PARENT_NAME] [pp/PARENT_PHONE]";
    public static final String EDIT_S_EXAMPLE = "edit 2 pp/91112222";
    public static final String EDIT_S_EXAMPLE_DESC = " changes the 2nd student’s parent contact number in "
            + "TutorAid to 91112222.";

    public static final String VIEW_S_TITLE = "Viewing a student";
    public static final String VIEW_S_DESC = "Displays the specified student’s name, phone number, progress and "
            + "payment status, along with their parent’s name and phone number.";
    public static final String VIEW_S_COMMAND = "view STUDENT_INDEX";
    public static final String VIEW_S_EXAMPLE = "view 2";
    public static final String VIEW_S_EXAMPLE_DESC = " shows the details associated with the 2nd student.";

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

    public static final String PAID_TITLE = "Set payment made";
    public static final String PAID_DESC = "Sets the payment status of the specified student to paid for the "
            + "current month.";
    public static final String PAID_COMMAND = "paid STUDENT_INDEX";
    public static final String PAID_EXAMPLE = "paid 3";
    public static final String PAID_EXAMPLE_DESC = " updates the 3rd student’s payment status to 'paid'.";

    public static final String UNPAID_TITLE = "Unset payment made";
    public static final String UNPAID_DESC = "Sets the payment status of the specified student to ‘unpaid’ for the "
            + "current month.";
    public static final String UNPAID_COMMAND = "unpaid STUDENT_INDEX";
    public static final String UNPAID_EXAMPLE = "unpaid 3";
    public static final String UNPAID_EXAMPLE_DESC = " updates the 3rd student’s payment status to 'unpaid'.";

    public static final String CLEAR_TITLE = "Clearing all entries";
    public static final String CLEAR_DESC = "Clears all entries from TutorAid.";
    public static final String CLEAR_COMMAND = "clear";

    public static final String EXIT_TITLE = "Exiting TutorAid";
    public static final String EXIT_DESC = "Exits the program.";
    public static final String EXIT_COMMAND = "exit";

    public static final String SAVING_DATA_TITLE = "Saving the data";
    public static final String SAVING_DATA_DESC = "TutorAid data is saved in the hard disk automatically after any "
            + "command that changes the data. There is no need to save manually.";

    public static final String EDIT_DATA_TITLE = "Editing the data file";
    public static final String EDIT_DATA_DESC1 = "TutorAid data are saved as a JSON file ";
    public static final String EDIT_FILEPATH = "[JAR file location]/data/tutoraid.json";
    public static final String EDIT_DATA_DESC2 = "Advanced users are welcome to update data directly by editing "
            + "that data file.";
    public static final String EDIT_CAUTION = "Caution: If your changes to the data file makes its format invalid, "
            + "TutorAid will discard all data and start with an empty data file at the next run.";

    public static final String FAQ_TITLE = "FAQ";
    public static final String FAQ_Q = "Q: How do I transfer my data to another Computer?";
    public static final String FAQ_A = "A: Install the app in the other computer and overwrite the empty data file "
            + "it creates with the file that contains the data of your previous TutorAid home folder.";

}
