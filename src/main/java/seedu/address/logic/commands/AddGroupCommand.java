package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.EmptyClassCode;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class AddGroupCommand extends Command {
    public static final String COMMAND_WORD = "addcg";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a group to a class to Classmate. "
            + "Parameters: "
            + PREFIX_GROUPNUMBER + "GROUPNAME "
            + PREFIX_CLASSCODE + "CLASSCODE "
            + PREFIX_TYPE + "TYPE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUPNUMBER + "1 "
            + PREFIX_CLASSCODE + "G06 "
            + PREFIX_TYPE + "OP1 ";

    public static final String MESSAGE_SUCCESS = "New group added: %1$s";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in Classmate";
    public static final String MESSAGE_CLASS_NOT_EXIST = "The class does not exist in Classmate";

    private final TutorialGroup toAdd;
    private final TutorialClass toAddTutorialClass;

    /**
     * Creates an AddClassCommand to add the specified {@code TutorialGroup}
     */
    public AddGroupCommand(TutorialGroup tutorialGroup) {
        requireNonNull(tutorialGroup);
        toAdd = tutorialGroup;
        // new class with the same class code created to check whether it already exists in ClassMATE
        toAddTutorialClass = TutorialClass.createTestTutorialClass(toAdd.getClassCode());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // check if tutorial class already exists in ClassMATE
        if (toAddTutorialClass.equals(new EmptyClassCode()) || !model.hasTutorialClass(toAddTutorialClass)) {
            throw new CommandException(MESSAGE_CLASS_NOT_EXIST);
        }

        if (model.hasTutorialGroup(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        model.addTutorialGroup(toAdd);

        // rearrange tutorial groups in order after adding
        model.sortTutorialGroups();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddGroupCommand // instanceof handles nulls
                && toAdd.equals(((AddGroupCommand) other).toAdd));
    }
}
