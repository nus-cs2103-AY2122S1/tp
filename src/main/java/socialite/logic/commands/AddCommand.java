package socialite.logic.commands;

import static java.util.Objects.requireNonNull;
import static socialite.logic.parser.CliSyntax.PREFIX_DATE;
import static socialite.logic.parser.CliSyntax.PREFIX_FACEBOOK;
import static socialite.logic.parser.CliSyntax.PREFIX_INSTAGRAM;
import static socialite.logic.parser.CliSyntax.PREFIX_NAME;
import static socialite.logic.parser.CliSyntax.PREFIX_PHONE;
import static socialite.logic.parser.CliSyntax.PREFIX_TAG;
import static socialite.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static socialite.logic.parser.CliSyntax.PREFIX_TIKTOK;
import static socialite.logic.parser.CliSyntax.PREFIX_TWITTER;

import socialite.logic.commands.exceptions.CommandException;
import socialite.model.Model;
import socialite.model.person.Person;

/**
 * Adds a person to the contact list.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the contact list. \n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_DATE + "NAME:YYYY-MM-DD[:monthly|:yearly]]... "
            + "[" + PREFIX_FACEBOOK + "FACEBOOK] "
            + "[" + PREFIX_INSTAGRAM + "INSTAGRAM] "
            + "[" + PREFIX_TELEGRAM + "TELEGRAM] "
            + "[" + PREFIX_TIKTOK + "TIKTOK] "
            + "[" + PREFIX_TWITTER + "TWITTER]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney "
            + PREFIX_DATE + "birthday:2000-01-01:yearly "
            + PREFIX_DATE + "ord:2020-01-01 "
            + PREFIX_FACEBOOK + "john.doe "
            + PREFIX_INSTAGRAM + "john.doe "
            + PREFIX_TELEGRAM + "johndoe "
            + PREFIX_TIKTOK + "johndoe "
            + PREFIX_TWITTER + "johndoe";

    public static final String MESSAGE_ADD_PERSON_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON =
            "A contact with the same phone number already exists in the contact list!";
    public static final String MESSAGE_HELP_GUIDE = "Enter 'help add' for in-app guidance.";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_ADD_PERSON_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
