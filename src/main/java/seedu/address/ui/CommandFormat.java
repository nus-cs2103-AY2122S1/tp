package seedu.address.ui;

public class CommandFormat {

    public static final String ADD_FORMAT = "Add student/class\n"
            + "\tadd n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [r/REMARK]\n"
            + "\taddclass n/NAME l/LIMIT ts/TIMESLOT [s/NAME,NAME,NAME...] [r/REMARK]\n\n";

    public static final String DELETE_FORMAT = "Delete student/class\n"
            + "\tdelete INDEX\n"
            + "\tdeleteclass INDEX\n\n";

    public static final String MOVE_FORMAT = "Add student to class\n"
            + "\taddtoclass si/INDEX_STUDENT INDEX_STUDENT INDEX_STUDENT... tc/INDEX_CLASS\n"
            + "\taddtoclass s/NAME,NAME,NAME... tc/INDEX_CLASS\n\n"
            + "Remove a student from a class\n"
            + "\tremove si/INDEX_STUDENT... tc/INDEX_CLASS\n\n";

    public static final String EDIT_FORMAT = "Edit a student/class\n"
            + "\teditstudent INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS]\n"
            + "\teditclass INDEX l/limit t/time n/NAME,NAME,NAME... [t/TAG]...\n\n";

    public static final String VIEW_FORFMAT = "View student/class\n"
            + "\tstudent INDEX\n"
            + "\tclass INDEX\n\n";

    public static final String REMARK_FORMAT = "Add remarks to student/class\n"
            + "\tremark INDEX_STUDENT r/REMARK\n"
            + "\tremarkclass INDEX_CLASS r/REMARK\n\n";

    public static final String SORT_FORMAT = "Sort classes\n"
            + "\tsort [o/ORDER]\n\n";

    public static final String FIND_FORMAT = "Find student/class by name\n"
            + "\tfind NAME\n"
            + "\tfindclass NAME\n\n";

    public static final String LIST_FORMAT = "List all students/classes\n"
            + "\tlist\n"
            + "\tlistclass\n\n";

    public static final String TIMETABLE_FORMAT = "View timetable\n"
            + "\ttimetable\n\n";

    public static final String TODAY_FORMAT = "View today's classes\n"
            + "\ttoday\n\n";

    public static final String EXIT_FORMAT = "Exit the app\n" + "\texit\n\n";

}
