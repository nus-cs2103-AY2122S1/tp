package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.claim.Claim;
import seedu.address.model.person.Person;

public class ClaimCommand extends Command {

    public static final String COMMAND_WORD = "claim";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a claim to the person identified by the index number used in the displayed person list."
            + "Claims with the same name will be overwritten. Statuses can only be 'pending' or 'completed' \n"
            + "Parameters: INDEX (must be a positive integer)"
            + "[" + PREFIX_NAME + "TITLE]"
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]"
            + "[" + PREFIX_STATUS + "STATUS]\n"
            + "Example: " + COMMAND_WORD + "1"
            + PREFIX_NAME + "Heart surgery"
            + PREFIX_DESCRIPTION + "Pre-existing condition"
            + PREFIX_STATUS + "Pending";

    public static final String MESSAGE_CLAIM_ADDED_SUCCESS = "Claim added: %1$s";

    private final Index index;
    private final Claim claim;

    /**
     * Constructs an {@Code ClaimCommand}
     *
     * @param index A valid index
     * @param claim A valid claim
     */
    public ClaimCommand(Index index, Claim claim) {
        requireAllNonNull(index, claim);
        this.index = index;
        this.claim = claim;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddClaim = lastShownList.get(index.getZeroBased());
        HashSet<Claim> claims = new HashSet<>(personToAddClaim.getClaims());

        claims.remove(this.claim);
        claims.add(this.claim);

        Person newPerson = new Person(personToAddClaim, claims);

        model.setPerson(personToAddClaim, newPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_CLAIM_ADDED_SUCCESS, claim));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ClaimCommand)) {
            return false;
        }
        ClaimCommand e = (ClaimCommand) other;
        return index.equals(e.index) && claim.equals(e.claim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, claim);
    }
}
