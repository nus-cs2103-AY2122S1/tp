package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_DAY_SHIFT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DASH_TIME;

import java.time.DayOfWeek;
import java.time.LocalTime;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.PersonIsWorkingPredicate;


/**
 * Class representing the view schedule command
 * which views the schedule by Person.
 */
public class ViewShiftCommand extends Command {
    public static final String DEFAULT_MESSAGE = "Staff working at that shift: \n";
    public static final String COMMAND_WORD = "viewShift";
    public static final String HELP_MESSAGE = COMMAND_WORD + ": find the staff working at the specified shift\n\n"
            + "Parameters:\n"
            + PREFIX_DASH_DAY_SHIFT + " day-slot_number\n"
            + PREFIX_DASH_TIME + " day-time" + " (time is in format HH:mm)\n\n"
            + "Examples:\n"
            + COMMAND_WORD + " " + PREFIX_DASH_DAY_SHIFT + " monday-0\n"
            + COMMAND_WORD + " " + PREFIX_DASH_DAY_SHIFT + " TUESDAY-1\n"
            + COMMAND_WORD + " " + PREFIX_DASH_TIME + " wednesday-11:00\n\n";

    public static final int INVALID_SLOT_NUMBER = -1;
    public static final int INVALID_SLOT_NUMBER_INDICATING_EMPTY_PREFIXES = -2;
    private static final String NO_STAFF_WORKING = "There is currently no staff working at the specified shift.";
    private static final String STAFF_LIST_EMPTY = "There are no staffs in the staff list, please add some first!";

    private final DayOfWeek dayOfWeek;
    private final int slotNum;
    private final LocalTime time;
    private final PersonIsWorkingPredicate isWorkingPredicate;

    /**
     * Constructs a ViewShiftCommand object.
     *
     * @param dayOfWeek The dayOfWeek that will be checked
     * @param slotNum The slot number that will be checked
     * @param time The time that will be checked
     */
    public ViewShiftCommand(DayOfWeek dayOfWeek, int slotNum, LocalTime time) {
        this.dayOfWeek = dayOfWeek;
        this.slotNum = slotNum;
        this.time = time;
        this.isWorkingPredicate = new PersonIsWorkingPredicate(dayOfWeek, slotNum, time);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ObservableList<Person> staffs = model.getUnFilteredPersonList();
        if (staffs.size() == 0) {
            throw new CommandException(STAFF_LIST_EMPTY);
        }

        model.updateFilteredPersonList(isWorkingPredicate);

        // Assumes either time is null, or dayOfWeek and slotNum is null (as passed in by ViewShiftCommandParser)
        if (time != null && dayOfWeek != null) {
            return executeViewShiftByTime(staffs);
        } else if (slotNum != INVALID_SLOT_NUMBER && dayOfWeek != null) {
            return executeViewShiftBySlot(staffs);
        } else {
            throw new CommandException(HELP_MESSAGE);
        }
    }

    /**
     * Executes ViewShift by slot number, and finds the staff working at that day of the week and slot number
     *
     * @param staffs The staff list that will be checked
     * @return The staff working at that day of the week and slot number
     */
    public CommandResult executeViewShiftBySlot(ObservableList<Person> staffs) {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (Person p : staffs) {
            boolean hasShift = p.isWorking(dayOfWeek, slotNum);
            if (hasShift) {
                result.append(counter).append(". ").append(p.getName()).append("\n");
                counter++;
            }
        }

        if (result.toString().equals("")) {
            return new CommandResult(NO_STAFF_WORKING);
        } else {
            return new CommandResult(DEFAULT_MESSAGE + result.toString());
        }
    }

    /**
     * Executes ViewShift by slot number, and finds the staff working at that day of the week and time
     *
     * @param staffs The staff list that will be checked
     * @return The staff working at that day of the week and time
     */
    public CommandResult executeViewShiftByTime(ObservableList<Person> staffs) {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (Person p : staffs) {
            boolean hasShift = p.isWorking(dayOfWeek, time);
            if (hasShift) {
                result.append(counter).append(". ").append(p.getName()).append("\n");
                counter++;
            }
        }
        if (result.equals("")) {
            return new CommandResult(NO_STAFF_WORKING);
        } else if (slotNum == INVALID_SLOT_NUMBER_INDICATING_EMPTY_PREFIXES) {
            return new CommandResult(HELP_MESSAGE + getWorkingStaffByTime(staffs));
        } else {
            return new CommandResult(DEFAULT_MESSAGE + result.toString());
        }
    }

    public String getWorkingStaffByTime(ObservableList<Person> staffs) {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (Person p : staffs) {
            boolean hasShift = p.isWorking(dayOfWeek, time);
            if (hasShift) {
                result.append(counter).append(". ").append(p.getName()).append("\n");
                counter++;
            }
        }
        return "Currently working:\n" + result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ViewShiftCommand
                && (this.dayOfWeek == null || this.dayOfWeek.equals(((ViewShiftCommand) other).dayOfWeek))
                && (this.slotNum != INVALID_SLOT_NUMBER || this.slotNum == ((ViewShiftCommand) other).slotNum)
                && (this.time == null || this.time.equals(((ViewShiftCommand) other).time)));
    }
}
