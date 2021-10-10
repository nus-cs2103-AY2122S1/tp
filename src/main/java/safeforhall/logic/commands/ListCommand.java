package safeforhall.logic.commands;

import static java.util.Objects.requireNonNull;

import safeforhall.logic.parser.CliSyntax;
import safeforhall.model.Model;
import safeforhall.model.person.LastDate;
import safeforhall.model.person.NameNearLastDatePredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists residents whose ART collection or"
            + "FET tests are due within the range of the current date and the optional given date. "
            + "Parameters: "
            + CliSyntax.PREFIX_KEYWORD + "KEYWORD "
            + CliSyntax.PREFIX_DATE + "DATE\n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_KEYWORD + "f "
            + CliSyntax.PREFIX_DATE + "30-9-2021 ";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    private final NameNearLastDatePredicate predicate;

    /**
     * Creates an ListCommand to add the specified {@code String}
     */
    public ListCommand(String keyword) {
        requireNonNull(keyword);
        this.predicate = new NameNearLastDatePredicate(keyword);
    }

    /**
     * Creates an ListCommand to add the specified {@code String, LastDate}
     */
    public ListCommand(String keyword, LastDate date) {
        requireNonNull(keyword);
        requireNonNull(date);
        this.predicate = new NameNearLastDatePredicate(keyword, date);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
