package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENGLISH_PHRASE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOREIGN_PHRASE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.flashcard.Flashcard;

/**
 * Adds a flashcard to the flashcard app.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_DESCRIPTION = "Adds a flashcard";
    public static final String COMMAND_USAGE = "add e/ENGLISH_PHRASE f/FOREIGN_PHRASE";
    public static final String COMMAND_EXAMPLES = "add e/Hello f/你好";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a flashcard to the flashcard app. "
            + "Parameters: "
            + PREFIX_ENGLISH_PHRASE + "ENGLISH_PHRASE "
            + PREFIX_FOREIGN_PHRASE + "FOREIGN_PHRASE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ENGLISH_PHRASE + "Good Morning "
            + PREFIX_FOREIGN_PHRASE + "早安";

    public static final String MESSAGE_SUCCESS = "New flashcard added: %1$s";
    public static final String MESSAGE_DUPLICATE_FLASHCARD = "This flashcard already exists in the flashcard app";

    private final Flashcard toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Flashcard}
     */
    public AddCommand(Flashcard flashcard) {
        requireNonNull(flashcard);
        toAdd = flashcard;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFlashcard(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_FLASHCARD);
        }

        model.addFlashcard(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
