package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERACTION;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interaction.Interaction;
import seedu.address.model.person.Person;

public class InteractionCommand extends Command {
    public static final String COMMAND_WORD = "interaction";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a interaction to the person identified "
            + "by the index number used in the displayed person list. \n"
            + "Parameters: INDEX (must be a positive integer) " + "[" + PREFIX_INTERACTION + "INTERACTION]" + "["
            + PREFIX_DATE + "DATE]\n" + "Example: " + COMMAND_WORD + " 1 " + PREFIX_INTERACTION + "We talked. "
            + PREFIX_DATE + "1990-01-20";

    public static final String MESSAGE_SUCCESS = "Interaction has been recorded";

    private final Interaction toAdd;
    private final Index index;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public InteractionCommand(Index index, Interaction interaction) {
        requireNonNull(interaction);
        this.index = index;
        toAdd = interaction;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToAppendTo = lastShownList.get(index.getZeroBased());
        Person appendedPerson = personToAppendTo.appendInteraction(toAdd);
        model.setPerson(personToAppendTo, appendedPerson);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InteractionCommand // instanceof handles nulls
                        && toAdd.equals(((InteractionCommand) other).toAdd));
    }
}
