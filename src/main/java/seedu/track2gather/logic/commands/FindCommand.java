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
 * Finds person(s) based on the field specified by the user.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds person(s) based on the field "
            + "specified by the user.\n"
            + "Parameters (indicate only 1): "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE_NUMBER] "
            + "[" + PREFIX_CASE_NUMBER + "CASE_NUMBER] "
            + "[" + PREFIX_SHN_PERIOD_START + "SHN_START_DATE] "
            + "[" + PREFIX_SHN_PERIOD_END + "SHN_END_DATE]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_PHONE + "91";

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
                String.format(Messages.MESSAGE_PERSONS_FOUND_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
