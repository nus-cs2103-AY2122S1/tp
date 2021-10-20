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
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.claim.Claim;
import seedu.address.model.claim.Description;
import seedu.address.model.claim.Status;
import seedu.address.model.claim.Title;
import seedu.address.model.person.Person;

public class ClaimCommand extends Command {

    public static final String COMMAND_WORD = "claim";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a claim to the person identified by the index number used in the displayed person list. "
            + "Claims with the same name will be overwritten. Statuses can only be 'pending' or 'completed' \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "TITLE] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_STATUS + "STATUS]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Heart surgery "
            + PREFIX_DESCRIPTION + "Pre-existing condition "
            + PREFIX_STATUS + "Pending";

    public static final String MESSAGE_CLAIM_ADDED_SUCCESS = "Claim added: %1$s";
    public static final String MESSAGE_CLAIM_EDITED_SUCCESS = "Claim edited: %1$s";
    public static final String MESSAGE_CLAIM_REMOVED_SUCCESS = "Claim removed: %1$s";

    private enum ExecutionType {
        ADD (MESSAGE_CLAIM_ADDED_SUCCESS),
        EDIT (MESSAGE_CLAIM_EDITED_SUCCESS),
        REMOVE (MESSAGE_CLAIM_REMOVED_SUCCESS);

        private final String successMessage;

        ExecutionType(String successMessage) {
            this.successMessage = successMessage;
        }

        String getSuccessMessage() {
            return this.successMessage;
        }
    }

    public static final String MESSAGE_CLAIM_EDIT_FAILURE =
            "You are trying to edit a claim that does not exist. " + "Claim title: %1$s";

    private final Index index;
    private final EditClaimDescriptor editClaimDescriptor;

    /**
     * Constructs an {@Code ClaimCommand}
     *
     * @param index A valid index
     * @param editClaimDescriptor A valid claim
     */
    public ClaimCommand(Index index, EditClaimDescriptor editClaimDescriptor) {
        requireAllNonNull(index, editClaimDescriptor);
        this.index = index;
        this.editClaimDescriptor = editClaimDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Claim editedClaim;
        ExecutionType executionType;
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAddClaim = lastShownList.get(index.getZeroBased());
        HashSet<Claim> claims = new HashSet<>(personToAddClaim.getClaims());

        Optional<Claim> claimToEdit = getClaimWithTitle(claims, editClaimDescriptor.getTitle());

        if (claimToEdit.isEmpty()) {
            editedClaim = editClaimDescriptor.build();
            claims.add(editedClaim);
            executionType = ExecutionType.ADD;
        } else if (editClaimDescriptor.isEmpty()) {
            editedClaim = claimToEdit.get();
            claims.remove(claimToEdit.get());
            executionType = ExecutionType.REMOVE;
        } else {
            editedClaim = createEditedClaim(claimToEdit.get(), editClaimDescriptor);
            claims.remove(claimToEdit.get());
            claims.add(editedClaim);
            executionType = ExecutionType.EDIT;
        }

        Person newPerson = new Person(personToAddClaim, claims);

        model.setPerson(personToAddClaim, newPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(executionType.getSuccessMessage(), editedClaim));
    }

    /**
     * Creates and returns a {@code Claim} with the details of {@code claimToEdit}
     * edited with {@code editClaimDescriptor}.
     *
     */
    public Claim createEditedClaim(Claim claimToEdit, EditClaimDescriptor editClaimDescriptor) {
        requireAllNonNull(claimToEdit, editClaimDescriptor);
        assert(claimToEdit.getTitle().equals(editClaimDescriptor.getTitle()));

        Description updatedDescription = editClaimDescriptor.getDescription().orElse(claimToEdit.getDescription());
        Status updatedStatus = editClaimDescriptor.getStatus().orElse(claimToEdit.getStatus());

        return new Claim(claimToEdit.getTitle(), updatedDescription, updatedStatus);
    }

    public Optional<Claim> getClaimWithTitle(HashSet<Claim> claimHashSet, Title title) {
        return claimHashSet.stream().filter(claim -> claim.getTitle().equals(title)).findFirst();
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
        return index.equals(e.index) && editClaimDescriptor.equals(e.editClaimDescriptor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, editClaimDescriptor);
    }

    public static class EditClaimDescriptor {
        private final Title title;
        private Description description;
        private Status status;

        /**
         * Constructs an {@Code EditClaimDescriptor} with a valid title.
         *
         * @param title A valid title
         */
        public EditClaimDescriptor(Title title) {
            requireNonNull(title);
            this.title = title;
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Title getTitle() {
            return this.title;
        }

        public Optional<Description> getDescription() {
            return this.description != null ? Optional.of(description) : Optional.empty();
        }

        public Optional<Status> getStatus() {
            return this.status != null ? Optional.of(status) : Optional.empty();
        }

        public boolean isComplete() {
            return this.title != null && this.description != null && this.status != null;
        }

        public boolean isEmpty() {
            return this.description == null && this.status == null;
        }

        /**
         * Returns the claim with the corresponding attributes
         */
        public Claim build() throws CommandException {
            if (!isComplete()) {
                throw new CommandException(String.format(MESSAGE_CLAIM_EDIT_FAILURE, getTitle()));
            }
            return new Claim(title, description, status);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditClaimDescriptor)) {
                return false;
            }

            // state check
            EditClaimDescriptor e = (EditClaimDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getDescription().equals(e.getDescription())
                    && getStatus().equals(e.getStatus());
        }
    }
}
