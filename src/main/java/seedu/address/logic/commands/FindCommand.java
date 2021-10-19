package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CASE_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.function.Predicate;

/**
 * Finds and lists all persons in address book who fulfills the criteria specified by the
 * user. Only one parameter can be specified at a time, but multiple arguments can be specified.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final int REQUIRED_NUMBER_OF_PREFIX = 1;

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons who fulfills the "
            + "criteria specified by the user. " + "User should specify only one parameter from the "
            + "following: "
            + "1. Name: \"" + PREFIX_NAME + "\" KEYWORD [MORE_KEYWORDS] "
            + "2. Case Number: Use \"" + PREFIX_CASE_NUMBER  + "\" CASE_NUMBER [MORE_CASE_NUMBER]. "
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + " alice bob charlie";

    private final Predicate<Person> predicate;

    public FindCommand(Predicate<Person> predicate) {
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
