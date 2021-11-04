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
    public static final String MESSAGE_DUPLICATE = "There were some duplicate contacts "
            + "that already existed in your database";

    private List<Person> personsToAdd;

    /**
     * Creates an ImportCommand to add the specified list of {@code Person}.
     */
    public ImportCommand(List<Person> personsToAdd) {
        requireNonNull(personsToAdd);
        this.personsToAdd = personsToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int count = 0;

        for (int i = 0; i < personsToAdd.size(); i++) {
            Person person = personsToAdd.get(i);
            if (model.hasPerson(person)) {
                continue;
            }
            model.addPerson(person);
            count++;
        }

        if (count < personsToAdd.size()) {
            return new CommandResult(count + " " + MESSAGE_SUCCESS + ". " + MESSAGE_DUPLICATE + ".");
        }
        return new CommandResult(count + " " + MESSAGE_SUCCESS + ".");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImportCommand // instanceof handles nulls
                && personsToAdd.equals(((ImportCommand) other).personsToAdd));
    }

}
