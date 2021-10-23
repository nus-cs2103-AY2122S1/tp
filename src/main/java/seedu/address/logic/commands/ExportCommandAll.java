package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.storage.ExportStorage;

/**
 * Exports all contacts from the address book.
 */
public class ExportCommandAll extends ExportCommand {

    public ExportCommandAll() { }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        ExportStorage.clearStorage();
        // loop through all contacts and export
        for (Person personToExport : lastShownList) {
            model.exportPerson(personToExport);
        }
        return new CommandResult(MESSAGE_EXPORT_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || other instanceof ExportCommandAll; // instanceof handles nulls
    }

}
