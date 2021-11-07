package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_CLASS_DOES_NOT_EXIST;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutorialclass.ClassCodeContainsKeywordsPredicate;

/**
 * Finds and lists all classes in ClassMATE whose class code contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindClassCommand extends Command {

    public static final String COMMAND_WORD = "findc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all classes whose class code contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " G15 G16 G17";

    private final ClassCodeContainsKeywordsPredicate predicate;

    public FindClassCommand(ClassCodeContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredTutorialClassList(predicate);

        // check if tutorial class exists in ClassMATE
        if (model.getFilteredTutorialClassList().size() == 0 ) {
            throw new CommandException(MESSAGE_CLASS_DOES_NOT_EXIST);
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_TUTORIAL_CLASSES_LISTED_OVERVIEW,
                        model.getFilteredTutorialClassList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindClassCommand // instanceof handles nulls
                && predicate.equals(((FindClassCommand) other).predicate)); // state check
    }
}
