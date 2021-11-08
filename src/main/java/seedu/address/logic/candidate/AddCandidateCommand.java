package seedu.address.logic.candidate;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.position.Position.MESSAGE_POSITION_CLOSED;
import static seedu.address.model.position.Position.MESSAGE_POSITION_DOES_NOT_EXIST;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.Command;
import seedu.address.logic.CommandResult;
import seedu.address.logic.candidate.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;

/**
 * Adds a candidate to the HR Manager.
 */
public class AddCandidateCommand extends Command {

    public static final String COMMAND_WORD = "add_c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a candidate to the HR Manager.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_POSITION + "POSITION... "
            + "[" + PREFIX_STATUS + "STATUS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_POSITION + "Accountant "
            + PREFIX_STATUS + "Scheduled "
            + PREFIX_TAG + "pending "
            + PREFIX_TAG + "reviewRequired";

    public static final String MESSAGE_SUCCESS = "New candidate added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "Candidate with Email:"
            + " [ %1$s ] already exists in the HR Manager";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCandidateCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON, toAdd.getEmail()));
        }

        Set<Position> positions = toAdd.getPositions();
        Set<Position> positionReferences = new HashSet<>();
        for (Position p : positions) {
            if (!model.hasPosition(p)) {
                throw new CommandException(String.format(MESSAGE_POSITION_DOES_NOT_EXIST, p.getTitle()));
            }
            if (model.isPositionClosed(p)) {
                throw new CommandException(String.format(MESSAGE_POSITION_CLOSED,
                        model.getPositionReference(p).getTitle()));
            }

            positionReferences.add(model.getPositionReference(p));
        }

        toAdd.setPositions(positionReferences);
        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), CommandResult.CommandType.CANDIDATE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCandidateCommand // instanceof handles nulls
                && toAdd.equals(((AddCandidateCommand) other).toAdd));
    }
}
