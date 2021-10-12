package seedu.unify.model.task;

public class WeeklyTracker {

    private static Integer week = 1;

    public static void setWeek(Integer weekNumber) {
        week = weekNumber;
    }

    public static Integer getWeek() {
        return week;
    }
}
