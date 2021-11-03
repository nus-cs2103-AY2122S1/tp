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
 * Adds a person to an organisation.
 */
public class AddToOrgCommand extends Command {

    public static final String COMMAND_WORD = "addtoorg";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to an organisation. "
            + "Parameters: "
            + "INDEX (must be a positive integer, refers to person in list) "
            + PREFIX_NAME + "NAME \n"
            + "Example: " + COMMAND_WORD + " 1 n/Facebook";

    public static final String MESSAGE_SUCCESS = "New person added to %1$s: %2$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the organisation";

    private final Name organisationName;
    private final Index targetIndex;

    /**
     * Creates an AddToOrgCommand to add the specified {@code Person}
     */
    public AddToOrgCommand(Index targetIndex, Name name) {
        this.targetIndex = targetIndex;
        organisationName = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> personList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= personList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAdd = personList.get(targetIndex.getZeroBased());

        Organisation organisation = model.getOrganisationByName(organisationName);
        if (organisation == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_ORGANISATION_DISPLAYED_NAME);
        }
        UniquePersonList persons = organisation.getPersons();
        if (persons.contains(personToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        persons.add(personToAdd);
        List<Person> organisationPersonList = persons.asUnmodifiableObservableList();

        Organisation newOrganisation = new Organisation(organisation.getName(), organisation.getEmail());
        newOrganisation.setPersons(organisationPersonList);
        model.setOrganisation(organisation, newOrganisation);

        return new CommandResult(String.format(MESSAGE_SUCCESS, organisationName, personToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddToOrgCommand // instanceof handles nulls
                && organisationName.equals(((AddToOrgCommand) other).organisationName));
    }
}
