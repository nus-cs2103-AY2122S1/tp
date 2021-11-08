package manageme.logic.commands.calendar;

import manageme.logic.commands.CommandResult;

public class CalendarCommandResult extends CommandResult {
    private final String feedbackToSystem;

    /**
     * Constructs a {@code CalendarCommandResult} with the specified fields.
     */
    public CalendarCommandResult(String feedbackToUser, String feedbackToSystem) {
        super(feedbackToUser);
        this.feedbackToSystem = feedbackToSystem;
    }

    @Override
    public boolean isCalendarCommand() {
        return true;
    }

    @Override
    public String getFeedbackToSystem() {
        return feedbackToSystem;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CalendarCommandResult)) {
            return false;
        }

        CalendarCommandResult otherCalendarCommandResult = (CalendarCommandResult) other;
        return feedbackToSystem.equals(otherCalendarCommandResult.feedbackToSystem)
                && super.equals(otherCalendarCommandResult);
    }

    @Override
    public int hashCode() {
        return feedbackToSystem.hashCode();
    }
}
