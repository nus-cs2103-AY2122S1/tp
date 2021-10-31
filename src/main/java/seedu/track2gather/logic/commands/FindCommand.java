package seedu.track2gather.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_SHN_PERIOD_END;
import static seedu.track2gather.logic.parser.CliSyntax.PREFIX_SHN_PERIOD_START;

import java.util.function.Predicate;

import seedu.track2gather.commons.core.Messages;
import seedu.track2gather.model.Model;
import seedu.track2gather.model.person.Person;

/**
 * Finds and lists all persons in contacts list who fulfills the criteria specified by the
 * user. Only one parameter can be specified at a time, but multiple arguments can be specified.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons who fulfills the "
            + "criteria specified by the user. " + "User should specify only one parameter from the "
            + "following: "
            + "1. Name: \"" + PREFIX_NAME + "\" KEYWORD [MORE_KEYWORDS] "
            + "2. Phone: \"" + PREFIX_PHONE + "\" KEY_NUMBERS [MORE_KEY_NUMBERS] "
            + "3. Case Number: \"" + PREFIX_CASE_NUMBER + "\" KEY_NUMBERS [MORE_KEY_NUMBERS] "
            + "4. Shn Period (start): \"" + PREFIX_SHN_PERIOD_START + "\" KEY_DATE [MORE_KEY_DATES] "
            + "5. Shn Period (end): \"" + PREFIX_SHN_PERIOD_END + "\" KEY_DATE [MORE_KEY_DATES] \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + " alice bob charlie";

    private final Predicate<Person> predicate;

    /**
     * Creates a FindCommand to search by the specified {@code Predicate}
     */
    public FindCommand(Predicate<Person> predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
