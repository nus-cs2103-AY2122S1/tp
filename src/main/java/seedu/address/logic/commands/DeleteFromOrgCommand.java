package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.organisation.Organisation;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Deletes a person from an organisation.
 */
public class DeleteFromOrgCommand extends Command {

    public static final String COMMAND_WORD = "deletefromorg";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a person from an organisation. "
            + "Parameters: "
            + "INDEX (must be a positive integer, refers to person in list) "
            + PREFIX_NAME + "NAME \n"
            + "Example: " + COMMAND_WORD + " 1 n/Facebook";

    public static final String MESSAGE_SUCCESS = "%1$s deleted from %2$s";

    private final Name organisationName;
    private final Index targetIndex;

    /**
     * Creates an DeleteFromOrgCommand to delete the specified {@code Person}
     */
    public DeleteFromOrgCommand(Index targetIndex, Name name) {
        this.targetIndex = targetIndex;
        organisationName = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Organisation organisation = model.getOrganisationByName(organisationName);
        if (organisation == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORGANISATION_DISPLAYED_NAME);
        }
        UniquePersonList persons = organisation.getPersons();
        List<Person> personList = persons.asUnmodifiableObservableList();

        if (targetIndex.getZeroBased() >= personList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = personList.get(targetIndex.getZeroBased());
        persons.remove(person);
        personList = persons.asUnmodifiableObservableList();

        Organisation newOrganisation = new Organisation(organisation.getName(), organisation.getEmail());
        newOrganisation.setPersons(personList);
        model.setOrganisation(organisation, newOrganisation);

        return new CommandResult(String.format(MESSAGE_SUCCESS, person, organisationName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteFromOrgCommand // instanceof handles nulls
                && organisationName.equals(((DeleteFromOrgCommand) other).organisationName));
    }
}
