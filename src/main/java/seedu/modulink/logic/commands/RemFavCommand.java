package seedu.modulink.logic.commands;

import javafx.collections.ObservableList;
import seedu.modulink.commons.core.Messages;
import seedu.modulink.model.Model;
import seedu.modulink.model.person.Person;


/**
 * Removes person whose student ID matches the user input from favourites.
 * ID matching is case insensitive.
 */
public class RemFavCommand extends Command {
    public static final String COMMAND_WORD = "remFav";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a person as a favourite.\n "
            + "Parameters: Student_ID\n"
            + "Example: " + COMMAND_WORD + " A1234567X";

    public static final String MULTIPLE_ID_ERROR =
            "You can only remove one person from your Favourites list at a time.";

    private final String studentId;

    public RemFavCommand(String studentId) {
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
                if (!person.getIsFavourite()) {
                    return new CommandResult(Messages.MESSAGE_PERSON_IS_NOT_FAVOURITE);
                } else {
                    person.setFavouriteFalse();
                    noPersonFound = false;
                }
            }
        }

        if (noPersonFound) {
            return new CommandResult(Messages.MESSAGE_NO_SUCH_ID_FOUND);
        } else {
            // included this so the list will be properly updated
            model.refreshFilteredPersonList();
            return new CommandResult(
                    String.format(Messages.MESSAGE_FAVOURITE_REMOVED, studentId));
        }

    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemFavCommand // instanceof handles nulls
                && this.studentId.equals(((RemFavCommand) other).studentId));
    }
}
