package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Batch imports contacts to the address book.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Contacts added successfully";

    private List<Person> personsToAdd;

    public ImportCommand(List<Person> personsToAdd) {
        this.personsToAdd = personsToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        for (int i = 0; i < personsToAdd.size(); i++) {
            Person person = personsToAdd.get(i);

            if (model.hasPerson(person)) {
                continue;
            }

            model.addPerson(person);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

}
