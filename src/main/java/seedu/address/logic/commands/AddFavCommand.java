package seedu.address.logic.commands;


import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.Arrays;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class AddFavCommand extends Command {

    public static final String COMMAND_WORD = "addfav";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person as a favourite. \n "
            + "Parameters: Student_ID \n"
            + "Example: " + COMMAND_WORD + " A1234567X";

    private final String studentId;

    public AddFavCommand(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public CommandResult execute(Model model) {
        boolean noPersonFound = true;
        ObservableList<Person> personList = model.getPersonList();
            for (Person person : personList) {
                if (person.getStudentId().toString().equalsIgnoreCase(studentId)) {
                    person.setFavourite();
                    noPersonFound = false;
            }
        }
        if (noPersonFound) {
            return new CommandResult(
                    String.format(Messages.MESSAGE_NO_SUCH_ID_FOUND));
        } else {
            return new CommandResult(
                    String.format(Messages.MESSAGE_FAVOURITE_ADDED, studentId));
        }
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddFavCommand // instanceof handles nulls
                && this.studentId.equals(((AddFavCommand) other).studentId));
    }


}
