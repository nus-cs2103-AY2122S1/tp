package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.event.exceptions.InvalidScheduleInputException;
import seedu.address.model.event.exceptions.InvalidTimeException;
import seedu.address.model.tag.Tag;

public class Schedule extends Event<Schedule> {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final String ERROR_MSG_LETTERS_IN_TIME =
            "Please provide a proper time formatting between 0000 - 2359";
    public static final String ERROR_MSG_INVALID_TIME_RANGE = "Please provide a proper time range between 0000 - 2359.";
    public static final String ERROR_MSG_INVALID_TIME_SQEUENCE = "Time <from> cannot be greater than time <to>.";

    private final LocalDateTime taskDateTimeFrom;
    private final LocalDateTime taskDateTimeTo;
    private final int timeFrom;
    private final int timeTo;
    private final Set<Tag> tags = new HashSet<>();
    private final String recurrType;
    private final String recurrDate;
    private final LocalDateTime recurrDateTo;

    /**
     * Priamry Constructor
     *
     * @param description description for this {@code Schedule}.
     * @param date        date for this {@code Schedule}.
     * @param timeFrom    start time for this {@code Schedule}.
     * @param timeTo      end time for this {@code Schedule}.
     * @param isDone      if this {@code Schedule} is completed.
     */
    public Schedule(String description, String date, String timeFrom, String timeTo, boolean isDone, Set<Tag> tags,
            String recurrType, String recurDate) throws InvalidScheduleInputException {
        super(description, date, isDone);
        this.checkTimeRangeAndFormatting(timeFrom, timeTo);
        String dateCopy = date;
        if (dateCopy.contains("/")) {
            dateCopy = dateCopy.replace("/", "-");
        }
        this.checkDateFormatting(dateCopy);
        this.taskDateTimeFrom = this.getLocalDateTime(dateCopy, timeFrom);
        this.taskDateTimeTo = this.getLocalDateTime(dateCopy, timeTo);
        this.timeFrom = Integer.parseInt(timeFrom);
        this.timeTo = Integer.parseInt(timeTo);
        this.tags.addAll(tags);
        this.recurrType = recurrType;

        if (!recurrType.equals("N")) {
            String recurrDateCopy = recurDate;
            recurrDateCopy = recurrDateCopy.replace("/", "-");
            this.checkDateFormatting(recurrDateCopy);
            this.recurrDate = recurrDateCopy;
            recurrDateTo = this.getLocalDateTime(recurrDateCopy, timeTo);
        } else {
            recurrDateTo = null;
            this.recurrDate = recurDate;
        }
    }

    /**
     * Constructor which helps to create the same Object as {@code this}.
     *
     * @param schedule is the {@code Schedule} to be created.
     */
    public Schedule(Schedule schedule) {
        super(schedule.getDescription(), schedule.getDate(), false);
        this.taskDateTimeFrom = schedule.getTaskDateTimeFrom();
        this.taskDateTimeTo = schedule.getRecurrDateTo();
        this.timeFrom = schedule.getTimeFrom();
        this.timeTo = schedule.getTimeTo();
        this.tags.addAll(schedule.getTags());
        this.recurrType = schedule.getRecurrType();
        this.recurrDate = schedule.getRecurrDate();
        this.recurrDateTo = schedule.getRecurrDateTo();
    }

    /**
     * Constructor which helps in the changing of the date, keeping the remaining
     * information the same.
     *
     * @param schedule         is the {@code Schedule} to be created.
     * @param taskDateTimeFrom the new date with a specific timing from.
     * @param taskDateTimeTo   the new date with a specific timing to.
     */
    public Schedule(Schedule schedule, LocalDateTime taskDateTimeFrom, LocalDateTime taskDateTimeTo) {
        super(schedule.getDescription(), taskDateTimeFrom.format(Schedule.FORMATTER), false);
        this.taskDateTimeFrom = taskDateTimeFrom;
        this.taskDateTimeTo = taskDateTimeTo;
        this.timeFrom = schedule.getTimeFrom();
        this.timeTo = schedule.getTimeTo();
        this.tags.addAll(schedule.getTags());
        this.recurrType = schedule.getRecurrType();
        this.recurrDate = schedule.getRecurrDate();
        this.recurrDateTo = schedule.getRecurrDateTo();
    }

    public LocalDateTime getTaskDateTimeFrom() {
        return this.taskDateTimeFrom;
    }

    public LocalDateTime getTaskDateTimeTo() {
        return this.taskDateTimeTo;
    }

    public int getTimeFrom() {
        return this.timeFrom;
    }

    public int getTimeTo() {
        return this.timeTo;
    }

    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public String getRecurrType() {
        return this.recurrType;
    }

    public LocalDateTime getRecurrDateTo() {
        return this.recurrDateTo;
    }

    public String getRecurrDate() {
        return this.recurrDate;
    }

    public Schedule addOneDay() {
        return new Schedule(this, this.taskDateTimeFrom.plusDays(1), this.taskDateTimeTo.plusDays(1));
    }

    public Schedule addOneWeek() {
        return new Schedule(this, this.taskDateTimeFrom.plusWeeks(1), this.taskDateTimeTo.plusWeeks(1));
    }

    public Schedule addOneYear() {
        return new Schedule(this, this.taskDateTimeFrom.plusYears(1), this.taskDateTimeTo.plusYears(1));
    }

    /**
     * Gets the formatted time and date in a proper {@code String}.
     *
     * @return formatteed time and date.
     */
    public String getDateTime() {
        return String.format("at %s from %04d to %04d", this.getDate(), this.getTimeFrom(), this.getTimeTo());
    }

    /**
     * Checks if the respective {@code Schedule} is the same as this
     * {@code Schedule}.
     *
     * @param schedule the {@code Schedule} to check if it is the same.
     * @return a {@code boolean} if it is the same.
     */
    public boolean isSameSchedule(Schedule schedule) {
        return this.getTaskDateTimeFrom().equals(schedule.getTaskDateTimeFrom())
                && this.getTaskDateTimeTo().equals(schedule.getTaskDateTimeTo())
                && this.getDescription().equals(schedule.getDescription()) && this.getDate().equals(schedule.getDate())
                && this.getTimeFrom() == schedule.getTimeFrom() && this.getTimeTo() == schedule.getTimeTo();
    }

    @Override
    public String getDate() {
        int day = Integer.parseInt(this.date.split("-")[0]);
        int month = Integer.parseInt(this.date.split("-")[1]);
        int year = Integer.parseInt(this.date.split("-")[2]);
        return String.format("%02d-%02d-%d", day, month, year);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Schedule)) {
            return false;
        }

        Schedule otherSchedule = (Schedule) other;
        return (otherSchedule.getDescription().equals(this.getDescription())
                && otherSchedule.getTaskDateTimeFrom().equals(this.getTaskDateTimeFrom())
                && otherSchedule.getTaskDateTimeTo().equals(this.getTaskDateTimeTo())
                && otherSchedule.getTags().equals(getTags()));
    }

    @Override
    public String toString() {
        return String.format("%s on %s from %04d to %04d", this.getDescription(), this.getDate(), this.timeFrom,
                this.timeTo);
    }

    private int getHour(String time) {
        return Integer.parseInt(time) / 100;
    }

    private int getMinute(String time) {
        return Integer.parseInt(time) % 100;
    }

    private void checkTimeFormatting(String time) {
        try {
            int hour = Integer.parseInt(time) / 100;
            int minute = Integer.parseInt(time) % 100;
            if (hour > 23 || hour < 0) {
                throw new InvalidTimeException(ERROR_MSG_INVALID_TIME_RANGE);
            }
            if (minute > 59 || minute < 0) {
                throw new InvalidTimeException(ERROR_MSG_INVALID_TIME_RANGE);
            }
        } catch (NumberFormatException e) {
            throw new InvalidTimeException(ERROR_MSG_LETTERS_IN_TIME);
        }
    }

    private void checkTimeRange(String timeFrom, String timeTo) {
        if (Integer.parseInt(timeFrom) > Integer.parseInt(timeTo)) {
            throw new InvalidTimeException(ERROR_MSG_INVALID_TIME_SQEUENCE);
        }
    }

    private void checkDateFormatting(String str) {
        String copy = str;
        if (!copy.contains("-")) {
            throw new InvalidScheduleInputException();
        }
        String[] date = copy.split("-");
        if (date.length != 3) {
            throw new InvalidScheduleInputException();
        }
        if (Integer.parseInt(date[0]) > 31 || Integer.parseInt(date[0]) < 0) {
            throw new InvalidScheduleInputException();
        }
        if (Integer.parseInt(date[1]) > 12 || Integer.parseInt(date[1]) < 0) {
            throw new InvalidScheduleInputException();
        }
        if (Integer.parseInt(date[2]) < 0) {
            throw new InvalidScheduleInputException();
        }
    }

    private void checkTimeRangeAndFormatting(String timeFrom, String timeTo) {
        this.checkTimeFormatting(timeFrom);
        this.checkTimeFormatting(timeTo);
        this.checkTimeRange(timeFrom, timeTo);
    }

    private LocalDateTime getLocalDateTime(String dateCopy, String time) {
        return LocalDateTime.of(Integer.parseInt(dateCopy.split("-")[2]), Integer.parseInt(dateCopy.split("-")[1]),
                Integer.parseInt(dateCopy.split("-")[0]), getHour(time), getMinute(time));
    }
}
