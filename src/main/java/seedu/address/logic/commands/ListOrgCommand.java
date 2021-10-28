package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.organisation.Organisation;

/**
 * Lists all persons in the address book to the user.
 */
public class ListOrgCommand extends Command {

    public static final String COMMAND_WORD = "listorg";

    public static final String MESSAGE_SUCCESS = "Listed all organisations";
    public static final Predicate<Organisation> PREDICATE_SHOW_ALL_ORGANISATIONS = unused -> true;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOrganisationList(PREDICATE_SHOW_ALL_ORGANISATIONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
