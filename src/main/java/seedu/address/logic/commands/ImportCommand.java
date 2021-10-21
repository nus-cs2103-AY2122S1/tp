package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_USAGE = ": Imports the member data from a CSV file into SportsPA's data file.\n"
            + "Each line of the CSV file must be as such: NAME, PHONE, [AVAILABILITY]\n"
            + "Parameters: KEYWORD CSV_FILE_PATH"
            + "Example: " + COMMAND_WORD + " myFilePath.csv";
    public static final String MESSAGE_SUCCESS = "Successfully imported from CSV file!";

    private final ArrayList<Person> personList;

    public ImportCommand(ArrayList<Person> personList) {
        this.personList = personList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        for (Person person: personList) {
            if (model.hasPerson(person)) {
                continue;
            }
            model.addPerson(person);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ImportCommand
                && personList.equals(((ImportCommand) other).personList));
    }
}
