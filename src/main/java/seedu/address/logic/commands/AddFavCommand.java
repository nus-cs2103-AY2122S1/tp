package seedu.address.logic.commands;


import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


/**
 * Adds person whose student ID matches the user input to favourites..
 * ID matching is case insensitive.
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

        // Look for the Person with the student ID, and if he is
        // not a favourite, make him a favourite. If he already is,
        // return a message saying he already is.
            for (Person person : personList) {
                if (person.getStudentId().toString().equalsIgnoreCase(studentId)) {
                    if (person.getIsFavourite()) {
                        return new CommandResult(String.format(Messages.MESSAGE_PERSON_ALREADY_FAVOURITE));
                    } else {
                        person.setFavourite();
                        noPersonFound = false;
                    }
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
