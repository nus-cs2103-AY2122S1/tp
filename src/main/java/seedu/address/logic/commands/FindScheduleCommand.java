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
public class FindScheduleCommand extends Command {
    public static final String DEFAULT_MESSAGE = "Staff working at that shift: \n";
    public static final String COMMAND_WORD = "findSchedule";
    public static final String HELP_MESSAGE = COMMAND_WORD + ": find the staff working at the specified shift\n"
            + "Method to use:\n"
            + COMMAND_WORD + " [TAG] [DAY]-[SHIFT NUMBER]\n\n"
            + "Examples:\n"
            + COMMAND_WORD + " " + PREFIX_DASH_DAY_SHIFT + " monday-0\n"
            + COMMAND_WORD + " " + PREFIX_DASH_DAY_SHIFT + " TUESDAY-1\n"
            + COMMAND_WORD + " " + PREFIX_DASH_TIME + " wednesday-11:00";

    public static final int INVALID_SLOT_NUMBER = -1;
    private static final String NO_STAFF_WORKING = "There is currently no staff working at the specified shift.";
    private static final String STAFF_LIST_EMPTY = "There are no staffs in the staff list, please add some first!";

    private final DayOfWeek dayOfWeek;
    private final int slotNum;
    private final LocalTime time;
    private final PersonIsWorkingPredicate isWorkingPredicate;

    /**
     * Constructs a FindScheduleCommand object.
     *
     * @param dayOfWeek The dayOfWeek that will be checked
     * @param slotNum The slot number that will be checked
     * @param time The time that will be checked
     */
    public FindScheduleCommand(DayOfWeek dayOfWeek, int slotNum, LocalTime time) {
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

        // Assumes either time is null, or dayOfWeek and slotNum is null (as passed in by FindScheduleCommandParser)
        if (time != null && dayOfWeek != null) {
            return executeFindScheduleByTime(staffs);
        } else if (slotNum != INVALID_SLOT_NUMBER && dayOfWeek != null) {
            return executeFindScheduleBySlot(staffs);
        } else {
            throw new CommandException(HELP_MESSAGE);
        }
    }

    /**
     * Executes FindSchedule by slot number, and finds the staff working at that day of the week and slot number
     *
     * @param staffs The staff list that will be checked
     * @return The staff working at that day of the week and slot number
     */
    public CommandResult executeFindScheduleBySlot(ObservableList<Person> staffs) {
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
     * Executes FindSchedule by slot number, and finds the staff working at that day of the week and time
     *
     * @param staffs The staff list that will be checked
     * @return The staff working at that day of the week and time
     */
    public CommandResult executeFindScheduleByTime(ObservableList<Person> staffs) {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        for (Person p : staffs) {
            boolean hasShift = p.isWorking(dayOfWeek, time);
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
}
