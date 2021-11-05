package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_CLASS_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_GROUP_DOES_NOT_EXIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class DeleteGroupCommand extends Command {

    public static final String COMMAND_WORD = "deletecg";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the group identified by class code, group number and type used in the displayed class list.\n"
            + "Parameters: "
            + PREFIX_CLASSCODE + "CLASS_CODE "
            + PREFIX_TYPE + "GROUP_TYPE "
            + PREFIX_GROUPNUMBER + "GROUP_NUMBER "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUPNUMBER + "1 "
            + PREFIX_CLASSCODE + "G06 "
            + PREFIX_TYPE + "OP1 ";

    public static final String MESSAGE_DELETE_CLASS_SUCCESS = "Deleted group: %1$s";

    private final TutorialGroup toDelete;
    private final TutorialClass toDeleteTutorialClass;

    /**
     * Constructor for DeleteGroupCommand
     *
     * @param tutorialGroup TutorialGroup to be deleted.
     */
    public DeleteGroupCommand(TutorialGroup tutorialGroup) {
        requireNonNull(tutorialGroup);
        toDelete = tutorialGroup;
        // new class with the same class code created to check whether it already exists in ClassMATE
        toDeleteTutorialClass = TutorialClass.createTestTutorialClass(toDelete.getClassCode());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // check if tutorial class already exists in ClassMATE
        if (!model.hasTutorialClass(toDeleteTutorialClass)) {
            throw new CommandException(MESSAGE_CLASS_DOES_NOT_EXIST);
        }

        if (!model.hasTutorialGroup(toDelete)) {
            throw new CommandException(MESSAGE_GROUP_DOES_NOT_EXIST);
        }

        model.deleteTutorialGroup(toDelete);

        // rearrange tutorial groups in order after adding
        model.sortTutorialGroups();
        return new CommandResult(String.format(MESSAGE_DELETE_CLASS_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteGroupCommand
                && toDelete.equals(((DeleteGroupCommand) other).toDelete));
    }
}
