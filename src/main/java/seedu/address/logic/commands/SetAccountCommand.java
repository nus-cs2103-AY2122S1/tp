package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASS;

import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.Account;
import seedu.address.model.Model;
import seedu.address.model.account.Password;

/**
 * Edits the details of an existing account in the ezFoodie.
 */
public class SetAccountCommand extends Command {

    public static final String COMMAND_WORD = "set";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets login password. "
            + "Existing password will be overwritten by the new password.\n"
            + "Parameters: "
            + PREFIX_PASS + "PASSWORD\n"
            + "Example: "
            + COMMAND_WORD + " " + PREFIX_PASS + "123456";

    public static final String MESSAGE_SUCCESS = "Password updated.";

    private final EditAccountDescriptor editAccountDescriptor;

    /**
     * Constructs a {@code SetAccountCommand} with {@code editAccountDescriptor}.
     *
     * @param editAccountDescriptor details to edit the account with.
     */
    public SetAccountCommand(EditAccountDescriptor editAccountDescriptor) {
        requireNonNull(editAccountDescriptor);
        this.editAccountDescriptor = editAccountDescriptor;
    }

    /**
     * Executes the model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult related to set account command.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        Password passwordToEdit = model.getAccount().getPassword();
        Account editedAccount = createEditedAccount(passwordToEdit, editAccountDescriptor);
        model.setAccount(editedAccount);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Creates and returns a {@code Account} with the details of {@code passwordToEdit}
     * edited with {@code editAccountDescriptor}.
     */
    private static Account createEditedAccount(Password passwordToEdit, EditAccountDescriptor editAccountDescriptor) {
        assert passwordToEdit != null;

        Password updatedPassword = editAccountDescriptor.getPassword().orElse(passwordToEdit);
        return new Account(updatedPassword);
    }

    /**
     * Overrides the equals method.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SetAccountCommand // instanceof handles nulls
                && editAccountDescriptor.equals(((SetAccountCommand) other).editAccountDescriptor)); // state check
    }

    /**
     * Stores the details to edit the account with. Each non-empty field value will replace the
     * corresponding field value of the account.
     */
    public static class EditAccountDescriptor {
        private Password password;

        /**
         * Constructs a {@code EditAccountDescriptor}.
         */
        public EditAccountDescriptor() {}

        /**
         * Copies constructor.
         * A defensive copy of {@code toCopy} is used internally.
         */
        public EditAccountDescriptor(SetAccountCommand.EditAccountDescriptor toCopy) {
            setPassword(toCopy.password);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(password);
        }

        public void setPassword(Password password) {
            this.password = password;
        }

        public Optional<Password> getPassword() {
            return Optional.ofNullable(password);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof SetAccountCommand.EditAccountDescriptor)) {
                return false;
            }

            // state check
            SetAccountCommand.EditAccountDescriptor e = (SetAccountCommand.EditAccountDescriptor) other;

            return getPassword().equals(e.getPassword());
        }
    }
}
