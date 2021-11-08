package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.exam.Exam;

public class AddExamCommand extends AddCommand {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an exam to the Mod book. "
            + "\nParameters: "
            + PREFIX_NAME + "EXAM_NAME "
            + PREFIX_DATE + "DATE "
            + PREFIX_START + "START_TIME "
            + PREFIX_END + "END_TIME "
            + PREFIX_LINK + "LINK "
            + PREFIX_VENUE + "VENUE "
            + "\nExample: " + COMMAND_WORD + " exam "
            + PREFIX_NAME + "Final "
            + PREFIX_DATE + "02/02/1999 "
            + PREFIX_START + "10:00 "
            + PREFIX_END + "11:00 "
            + PREFIX_LINK + "https://www.youtube.com/watch?v=8mL3L9hN2l4 "
            + PREFIX_VENUE + "Field";

    public static final String MESSAGE_SUCCESS = "New exam added: %1$s";
    public static final String MESSAGE_DUPLICATE_EXAM = "This exam already exists in the mod book";

    private final Exam toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Exam}
     */
    public AddExamCommand(Exam exam) {
        requireNonNull(exam);
        this.toAdd = exam;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Module module = model.getFilteredModuleList().get(0);

        if (model.moduleHasExam(module, toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXAM);
        }
        model.addExamToModule(module, toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddExamCommand // instanceof handles nulls
                && toAdd.equals(((AddExamCommand) other).toAdd));
    }
}

