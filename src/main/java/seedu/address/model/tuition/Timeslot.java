package seedu.address.model.tuition;
import static java.util.Objects.requireNonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Represents the time slot the tuition class takes
 */
public class Timeslot {
    public static final String TIME_FORMAT_INCORRECT = "The time format is not correct";

    //used to sorting tuition classes by comparing date
    private static HashMap<String, Integer> days = new HashMap<>() {{
            put("Mon", 1);
            put("Tue", 2);
            put("Wed", 3);
            put("Thu", 4);
            put("Fri", 5);
            put("Sat", 6);
            put("Sun", 7);
        }};
    public final Date start;
    public final Date end;

    /**
     * Constructor for timeslot.
     *
     * @param start
     * @param end
     */
    public Timeslot(Date start, Date end) {
        requireNonNull(start);
        requireNonNull(end);
        this.start = start;
        this.end = end;
    }

    public String getDayString() {
        return (this.getTime().split(" "))[0];
    }

    public Date getStart() {
        return this.start;
    }

    public Date getEnd() {
        return this.end;
    }

    public int getDay() {
        return start.getDay();
    }

    /**
     * compare two time slot to detect any conflict
     * @return true if conflict exist, false if no conflict
     */
    public boolean checkClassConflict(Timeslot otherSlot) {
        Date otherStart = otherSlot.getStart();
        Date otherEnd = otherSlot.getEnd();

        if (otherStart.getDay() != start.getDay()) {
            return false;
        }
        return Math.max(otherStart.getTime(), start.getTime())
                < Math.min(otherEnd.getTime(), end.getTime());
    }

    /**
     * Checks if current list of classes have this timselot already.
     *
     * @param classList
     * @return
     */
    public boolean checkTimetableConflicts(List<TuitionClass> classList) {
        for (TuitionClass tc: classList) {
            if (this.checkClassConflict(tc.getTimeslot())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof Timeslot) {
            Timeslot otherSlot = ((Timeslot) other);
            return getDay() == otherSlot.getDay()
                    && otherSlot.getEnd().getTime() == end.getTime()
                    && otherSlot.getStart().getTime() == start.getTime();
        }
        return false;
    }

    /**
     * Returns String representation of timeslot.
     *
     */

    public String getTime() {
        DateFormat timeFormat = new SimpleDateFormat("EEE HH:mm");
        DateFormat timeOnly = new SimpleDateFormat("HH:mm");
        return String.format("%s-%s", timeFormat.format(start), timeOnly.format(end));
    }


    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return getTime();
    }

    /**
     * Parses a time range in the form of string into LocalTime format.
     * @return the starting time and end time in an array.
     */
    public LocalTime[] parseTime() {
        String[] time = this.getTime().split(" ");
        String start = time[1].substring(0, 5);
        String end = time[1].substring(6, 11);
        if (start.substring(0, 2).equals("24")) {
            start = "00" + start.substring(2);
        }
        if (end.substring(0, 2).equals("24")) {
            end = "00" + end.substring(2);
        }
        LocalTime localStart = LocalTime.parse(start);
        LocalTime localEnd = LocalTime.parse(end);
        return new LocalTime[]{localStart, localEnd};
    }

    public static HashMap<String, Integer> getDays() {
        return days;
    }

    /**
     * Returns timeslot given timeslot as String in the correct format.
     * @param slot
     * @return
     */
    public static Timeslot parseString(String slot) {
        String[] arr = slot.trim().split(" ", 2); //Splits day from timings
        String startTime = String.format("%s %s", arr[0], arr[1].split("-")[0]); //Mon 10:00
        String endTime = String.format("%s %s", arr[0], arr[1].split("-")[1]); //Mon 11:00
        DateFormat sdf = new SimpleDateFormat("EEE HH:mm");
        try {
            Date start = sdf.parse(startTime);
            Date end = sdf.parse(endTime);
            return new Timeslot(start, end);
        } catch (java.text.ParseException e) {
            return null;
        }
    }
}

