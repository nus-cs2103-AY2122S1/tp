package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.model.Model;
import seedu.address.model.person.ContactHasFacultyPredicate;
import seedu.address.model.person.ContactHasMajorPredicate;
import seedu.address.model.person.ContactHasRolePredicate;
import seedu.address.model.person.ContactHasTagPredicate;


/**
 * Finds and lists all persons in address book which have the specified tags attached to them.
 * tag matching is case insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts which have "
            + "the specified tag (case-insensitive) attached to them and displays them as a list with index numbers.\n"
            + "Parameters: TAGNAME [MORE_TAGNAMES]...\n"
            + "Example: " + COMMAND_WORD + " r/staff f/computing";

    private ContactHasTagPredicate tagPredicate;
    private ContactHasRolePredicate rolePredicate;
    private ContactHasFacultyPredicate facultyPredicate;
    private ContactHasMajorPredicate majorPredicate;
    private final ArgumentMultimap argMultimap;

    public FilterCommand(ArgumentMultimap argMultimap) {
        this.argMultimap = argMultimap;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            rolePredicate = new ContactHasRolePredicate(argMultimap.getAllValues(PREFIX_ROLE));
            model.updateFilteredPersonList(rolePredicate);
        }
        //System.out.println(model.getFilteredPersonList());
        if (argMultimap.getValue(PREFIX_FACULTY).isPresent()) {
            facultyPredicate = new ContactHasFacultyPredicate(argMultimap.getAllValues(PREFIX_FACULTY));
            model.updateFilteredPersonList(facultyPredicate);
        }
        //System.out.println(model.getFilteredPersonList());
        if (argMultimap.getValue(PREFIX_MAJOR).isPresent()) {
            majorPredicate = new ContactHasMajorPredicate(argMultimap.getAllValues(PREFIX_MAJOR));
            model.updateFilteredPersonList(majorPredicate);
        }
        System.out.println(model.getFilteredPersonList());
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            tagPredicate = new ContactHasTagPredicate(argMultimap.getAllValues(PREFIX_TAG));
            model.updateFilteredPersonList(tagPredicate);
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && tagPredicate.equals(((FilterCommand) other).tagPredicate)
                && facultyPredicate.equals(((FilterCommand) other).facultyPredicate)
                && majorPredicate.equals(((FilterCommand) other).majorPredicate)
                && rolePredicate.equals(((FilterCommand) other).rolePredicate)); // state check
    }

}
