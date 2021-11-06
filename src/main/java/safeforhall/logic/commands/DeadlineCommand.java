package safeforhall.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

import safeforhall.logic.commands.exceptions.CommandException;
import safeforhall.logic.parser.CliSyntax;
import safeforhall.model.Model;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.NameMissedDeadlinePredicate;
import safeforhall.model.person.NameNearLastDatePredicate;
import safeforhall.model.person.Person;

/**
 * Lists all persons whose ART Collection or FET tests are due on the given date if one day is given or due within a
 * range of 2 dates if 2 dates are given.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String PARAMETERS = "k/KEYWORD d1/DATE d2/DATE";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists residents whose ART collection or "
            + "FET tests are due within the range of the given date or the range of the 2 dates given.\n"
            + "Parameters: "
            + CliSyntax.PREFIX_KEYWORD + "KEYWORD "
            + CliSyntax.PREFIX_DATE1 + "DATE "
            + CliSyntax.PREFIX_DATE2 + "DATE \n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_KEYWORD + "f "
            + CliSyntax.PREFIX_DATE1 + "30-09-2021 "
            + CliSyntax.PREFIX_DATE2 + "05-10-2021";

    public static final String MESSAGE_USAGE_LATE = COMMAND_WORD + ": Lists residents whose ART collection or "
            + "FET tests are due before the given date.\n"
            + "Parameters: "
            + CliSyntax.PREFIX_KEYWORD + "KEYWORD "
            + CliSyntax.PREFIX_DATE1 + "DATE \n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_KEYWORD + "lf "
            + CliSyntax.PREFIX_DATE1 + "30-09-2021 ";

    public static final String MESSAGE_SUCCESS_ART = "Listed all residents whose ART collections are due on the "
            + "given range of dates";
    public static final String MESSAGE_SUCCESS_FET = "Listed all residents whose FET are due on the given range of "
            + "dates";
    public static final String MESSAGE_SUCCESS_MISSED_ART = "Listed all residents whose ART collections are due "
            + "before the given date";
    public static final String MESSAGE_SUCCESS_MISSED_FET = "Listed all residents whose FET are due before the "
            + "given date";
    public static final String MESSAGE_SECOND_DATE_EARLIER_THAN_FIRST = "The second date inputted is earlier than "
            + "the first";
    public static final String MESSAGE_WRONG_KEYWORD = "Wrong keyword parsed";
    public static final String ART_KEYWORD = "c";
    public static final String FET_KEYWORD = "f";
    public static final String LATE_ART_KEYWORD = "lc";
    public static final String LATE_FET_KEYWORD = "lf";

    private final String keyword;
    private final LastDate date1;
    private final LastDate date2;
    private final Predicate<Person> predicate;

    /**
     * Creates an DeadlineCommand to add the specified {@code String}
     */
    public DeadlineCommand(String keyword, LastDate date1) {
        requireNonNull(keyword);
        this.keyword = keyword;
        this.date1 = date1;
        this.date2 = date1;
        if (keyword.equals("lf")) {
            this.predicate = new NameMissedDeadlinePredicate(FET_KEYWORD, date1);
        } else if (keyword.equals("lc")) {
            this.predicate = new NameMissedDeadlinePredicate(ART_KEYWORD, date1);
        } else {
            this.predicate = new NameNearLastDatePredicate(keyword, date1);
        }
    }

    /**
     * Creates an ListCommand to add the specified {@code String, LastDate}
     */
    public DeadlineCommand(String keyword, LastDate date1, LastDate date2) {
        requireNonNull(keyword);
        requireNonNull(date1);
        requireNonNull(date2);
        this.date1 = date1;
        this.date2 = date2;
        this.keyword = keyword;
        this.predicate = new NameNearLastDatePredicate(keyword, date1, date2);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        long period = ChronoUnit.DAYS.between(date1.toLocalDate(), date2.toLocalDate());
        if (period < 0) {
            throw new CommandException(MESSAGE_SECOND_DATE_EARLIER_THAN_FIRST);
        }
        model.updateFilteredPersonList(predicate);
        switch (keyword) {
        case ART_KEYWORD:
            return new CommandResult(MESSAGE_SUCCESS_ART);
        case FET_KEYWORD:
            return new CommandResult(MESSAGE_SUCCESS_FET);
        case LATE_ART_KEYWORD:
            return new CommandResult(MESSAGE_SUCCESS_MISSED_ART);
        case LATE_FET_KEYWORD:
            return new CommandResult(MESSAGE_SUCCESS_MISSED_FET);
        default:
            throw new CommandException(MESSAGE_WRONG_KEYWORD);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineCommand // instanceof handles nulls
                && date1.equals(((DeadlineCommand) other).date1)
                && date2.equals(((DeadlineCommand) other).date2)); // state check
    }
}
