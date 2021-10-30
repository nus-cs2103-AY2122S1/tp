package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Displays details of a person identified using it's displayed name on the side panel.
 */
public class ViewCommandName extends ViewCommand {

    private final Name targetName;

    public ViewCommandName(Name targetName) {
        this.targetName = targetName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        for (Person person : lastShownList) {
            String fullName = person.getName().fullName;
            if (fullName.equals(targetName.fullName.trim())) {
                Person selectedPerson = person;
                return new CommandResult(String.format(MESSAGE_VIEW_PERSON_SUCCESS, selectedPerson), selectedPerson);
            }
        }
        throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommandName// instanceof handles nulls
                && targetName.equals(((ViewCommandName) other).targetName)); // state check
    }
}
