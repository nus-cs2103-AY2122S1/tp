package seedu.address.ui;

public class CommandFormat {

    public static final String ADD_FORMAT = "Add a student\n"
            + "\tadd n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/REMARK] [t/TAG]…\n\n"
            + "Add a tuition class\n"
            + "\taddclass n/NAME l/LIMIT ts/TIMESLOT [s/NAME,NAME,NAME...] [r/REMARK]\n\n";

    public static final String DELETE_FORMAT = "Delete a student\n"
            + "\tdelete INDEX\n\n"
            + "Delete a tuition class\n"
            + "\tdeleteclass INDEX\n\n";

    public static final String MOVE_FORMAT = "Add a student to a class\n"
            + "\taddtoclass si/INDEX_STUDENT INDEX_STUDENT INDEX_STUDENT... tc/INDEX_CLASS\n"
            + "\taddtoclass s/NAME,NAME,NAME... tc/INDEX_CLASS\n\n"
            + "Remove a student from a class\n"
            + "\tremove si/INDEX_STUDENT... tc/INDEX_CLASS\n\n";

    public static final String EDIT_FORMAT = "Edit a student\n"
            + "\teditstudent INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]\n\n"
            + "Edit a class\n"
            + "\teditclass INDEX l/limit t/time n/NAME,NAME,NAME... [t/TAG]...\n\n";

    public static final String VIEW_FORFMAT = "View a student\n"
            + "\tstudent INDEX\n\n"
            + "View a class\n"
            + "\tclass INDEX\n\n";

    public static final String REMARK_FORMAT = "Add remarks to student\n"
            + "\tremark INDEX_STUDENT r/REMARK\n\n"
            + "Add remarks to class\n"
            + "\tremarkclass INDEX_CLASS r/REMARK\n\n";

    public static final String SORT_FORMAT = "Sort classes\n"
            + "\tsort [o/ORDER]\n\n";

    public static final String EXIT_FORMAT = "Exit the app\n" + "\texit\n\n";

}
