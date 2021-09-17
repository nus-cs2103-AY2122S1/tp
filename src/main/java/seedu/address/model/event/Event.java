package seedu.address.model.event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.model.particpant.Participant;

/**
 * This is an Event class.
 */
public class Event {

    private boolean isDone = false;
    private ArrayList<Participant> participants = new ArrayList<>();
    private String name;
    private LocalDate date;
    private LocalTime time;

    /**
     * This is the constructors of an Event.
     *
     * @param name of the Event.
     * @param date at which the Event occurs.
     * @param time of the Event.
     */
    public Event(String name, LocalDate date, LocalTime time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    /**
     * This is an overloaded constructor of Event.
     *
     * @param name of the Event.
     * @param date at which the Event occurs.
     */
    public Event(String name, LocalDate date) {
        this(name, date, LocalTime.MIN);
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(this.participants);
    }
    /**
     * Marks the Event as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "]" + this.name + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " "
                + (this.time.equals(LocalTime.MIN) ? "" : this.time.format(DateTimeFormatter.ofPattern("h:mma")))
                + ") " + "Total Participants: " + this.participants.size();
    }
}
