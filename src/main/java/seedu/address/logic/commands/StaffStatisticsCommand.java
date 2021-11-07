package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.person.Period.getPeriodFromDateOverMonth;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Period;
import seedu.address.model.person.Person;

/**
 * Class representing a command for obtaining information on
 * staff salaries.
 */
public class StaffStatisticsCommand extends Command {

    public static final String COMMAND_WORD = "stats";
    public static final String MESSAGE_SUCCESS = "Stats for %4$s:\n"
            + "Total salary: %1$s\n"
            + "Total work time: %2$s\n"
            + "Average work time: %3$s\n";
    public static final String EMPTY_MODEL = "There is no one to get the statistics of.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //empty case
        ObservableList<Person> staffList = model.getUnFilteredPersonList();
        if (staffList.isEmpty()) {
            throw new CommandException(EMPTY_MODEL);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                totalSalary(staffList), totalWorkTime(staffList),
                totalWorkTime(staffList) / staffList.size(), getCurrentPeriod()));
    }

    /**
     * Obtain the total salary of every staff in the input model.
     *
     * @param staffs The staffs to get the total salary from.
     * @return The total salary.
     */
    private double totalSalary(List<? extends Person> staffs) {
        final Period period = getCurrentPeriod();
        return staffs.stream()
                .mapToDouble(person -> person.getSalaryToBePaid(period))
                .reduce(0, (x, y) -> x + y);

    }

    /**
     * Obtain the total worktime of every staff in the input model.
     *
     * @param staffs The staffs to get the work time from.
     * @return the total work time in hours.
     */
    private long totalWorkTime(List<? extends Person> staffs) {
        final Period period = getCurrentPeriod();
        long result = staffs.stream()
                .mapToLong(person -> person.getTotalWorkingHour(period))
                .sum();
        return result;
    }

    private Period getCurrentPeriod() {
        return getPeriodFromDateOverMonth(LocalDate.now());
    }

}
