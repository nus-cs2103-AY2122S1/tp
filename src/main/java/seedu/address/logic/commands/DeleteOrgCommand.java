package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.organisation.Organisation;

/**
 * Deletes an organisation identified using it's displayed index from the address book.
 */
public class DeleteOrgCommand extends Command {

    public static final String COMMAND_WORD = "deleteorg";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the organisation identified by the index number used in the displayed organisation list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ORGANISATION_SUCCESS = "Deleted Organisation: %1$s";

    private final Index targetIndex;

    public DeleteOrgCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Organisation> lastShownList = model.getFilteredOrganisationList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORGANISATION_DISPLAYED_INDEX);
        }

        Organisation organisationToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteOrganisation(organisationToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ORGANISATION_SUCCESS, organisationToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteOrgCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteOrgCommand) other).targetIndex)); // state check
    }
}
