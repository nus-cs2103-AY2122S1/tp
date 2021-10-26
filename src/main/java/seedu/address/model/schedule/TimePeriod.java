package seedu.address.model.schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import seedu.address.model.schedule.exceptions.EndTimeBeforeStartTimeException;

public class TimePeriod implements Comparable<TimePeriod> {
    /** The start date and time of this {@code TimePeriod}. */
    private LocalDateTime startDateTime;

    /** The end date and time of this {@code TimePeriod}. */
    private LocalDateTime endDateTime;

    /**
     * A public constructor to initialize the {@code TimePeriod} with startDateTime and endDateTime.
     * @param startDateTime The given startDateTime.
     * @param endDateTime The given endDateTIme.
     * @throws EndTimeBeforeStartTimeException
     */
    public TimePeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) throws EndTimeBeforeStartTimeException {
        if (endDateTime.isAfter(startDateTime)) {
            this.startDateTime = startDateTime;
            this.endDateTime = endDateTime;
        } else {
            this.startDateTime = startDateTime;
            this.endDateTime = startDateTime;
            throw new EndTimeBeforeStartTimeException("EndDateTime has been set to startDateTime by default.");
        }
    }

    /**
     * A public constructor to initialize the {@code TimePeriod} with startDate, startTime, endDate, endTime.
     * @param startDate The given startDate.
     * @param startTime The given startTime.
     * @param endDate The given endDate.
     * @param endTime The given endTime.
     * @throws EndTimeBeforeStartTimeException
     */
    public TimePeriod(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime)
            throws EndTimeBeforeStartTimeException {
        LocalDateTime startDateTime = LocalDateTime.of(
                startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(),
                startTime.getHour(), startTime.getMinute()
        );
        LocalDateTime endDateTime = LocalDateTime.of(
                endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth(),
                endTime.getHour(), endTime.getMinute()
        );
        new TimePeriod(startDateTime, endDateTime);
    }

    /**
     * A boolean method to check whether a moment is included in this {@code TimePeriod}.
     * @param moment A LocalDateTime, the given moment.
     * @return A boolean value indicating whether the moment is included.
     */
    public boolean containsMoment(LocalDateTime moment) {
        return this.startDateTime.isBefore(moment) && this.endDateTime.isAfter(moment);
    }

    /**
     * A boolean method to check whether this {@code TimePeriod} has conflict with another {@code TimePeriod}.
     * @param tp Another {@code TimePeriod}.
     * @return A boolean value indicating whether the two {@TimePeriod} has conflicts.
     */
    public boolean hasConflictWith(TimePeriod tp) {
        return this.containsMoment(tp.startDateTime) || this.containsMoment(tp.endDateTime)
                || tp.containsMoment(this.startDateTime) || tp.containsMoment(this.endDateTime);
    }

    public void setStartDateTime(LocalDateTime newStartDateTime) throws EndTimeBeforeStartTimeException {
        if (this.endDateTime.isAfter(newStartDateTime)) {
            this.startDateTime = newStartDateTime;
        } else {
            throw new EndTimeBeforeStartTimeException("The operation to set startDateTime was not executed.");
        }
    }

    public void setEndDateTime(LocalDateTime newEndDateTime) throws EndTimeBeforeStartTimeException {
        if (this.startDateTime.isBefore(newEndDateTime)) {
            this.endDateTime = newEndDateTime;
        } else {
            throw new EndTimeBeforeStartTimeException("The operation to set endDateTime was not executed.");
        }
    }

    public LocalDateTime getStartDateTime() {
        return LocalDateTime.of(this.startDateTime.toLocalDate(), this.startDateTime.toLocalTime());
    }

    public LocalDateTime getEndDateTime() {
        return LocalDateTime.of(this.endDateTime.toLocalDate(), this.endDateTime.toLocalTime());
    }

    @Override
    public int compareTo(TimePeriod o) {
        if (this.startDateTime.compareTo(o.startDateTime) == 0) {
            return this.endDateTime.compareTo(o.endDateTime);
        } else {
            return this.startDateTime.compareTo(o.startDateTime);
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Start Time: ")
               .append(getStartDateTime())
               .append("End Time: ")
               .append(getEndDateTime());

        return builder.toString();
    }
}
