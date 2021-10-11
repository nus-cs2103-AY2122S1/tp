package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTID;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ClientId;
import seedu.address.model.person.Email;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the client id used in the displayed person list.\n"
            + "Parameters: CLIENTID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_CLIENTID +" 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_PERSON_FAILURE = "Person not found in list";

    private final ArrayList<Predicate> predicates;

    public DeleteCommand(ArrayList<Predicate> predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person personToDelete;
        try {
            personToDelete = model.deletePersonByFields(predicates);
        } catch (PersonNotFoundException e) {
            throw new CommandException(MESSAGE_DELETE_PERSON_FAILURE);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && predicates.equals(((DeleteCommand) other).predicates)); // state check
    }
}
