package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.member.Id;
import seedu.address.model.member.Member;

/**
 * Adds a member to the ezFoodie.
 */
public class AddMemberCommand extends AddCommand {

    /**
     * Stands for command word for add member.
     */
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a member to the ezFoodie.\n"
            + "Parameters:\n"
            + PREFIX_MEMBER + " "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS\n"
            + "Example:\n"
            + COMMAND_WORD + " "
            + PREFIX_MEMBER + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25";

    public static final String MESSAGE_SUCCESS = "New member added: %1$s";
    public static final String MESSAGE_FULL = "Member ID has reached " + Id.MAX + ".";
    public static final String MESSAGE_DUPLICATE_MEMBER =
            "This member (phone or email) already exists in the ezFoodie.";

    private final Member toAdd;

    /**
     * Constructs an {@codeAddMemberCommand} to add the specified {@code Member}.
     */
    public AddMemberCommand(Member member) {
        requireNonNull(member);
        toAdd = member;
    }

    /**
     * Executes model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult with toAdd member.
     * @throws CommandException if the user input does not conform the expected format.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasMember(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEMBER);
        }

        model.addMember(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMemberCommand // instanceof handles nulls
                && toAdd.equals(((AddMemberCommand) other).toAdd));
    }

}
