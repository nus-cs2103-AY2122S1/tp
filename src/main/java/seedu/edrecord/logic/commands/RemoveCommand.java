package seedu.edrecord.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.edrecord.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.edrecord.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.edrecord.commons.core.Messages;
import seedu.edrecord.commons.core.index.Index;
import seedu.edrecord.commons.util.CollectionUtil;
import seedu.edrecord.logic.commands.exceptions.CommandException;
import seedu.edrecord.model.Model;
import seedu.edrecord.model.group.Group;
import seedu.edrecord.model.module.Module;
import seedu.edrecord.model.name.Name;
import seedu.edrecord.model.person.Email;
import seedu.edrecord.model.person.Info;
import seedu.edrecord.model.person.Person;
import seedu.edrecord.model.person.Phone;
import seedu.edrecord.model.tag.Tag;

/**
 * Removes an existing person in edrecord from an existing mod and/or class.
 */
public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "rm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes the person identified by the index number used"
            + "in the displayed person list from the specified module and/or class.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_MODULE + "MODULE] "
            + "[" + PREFIX_GROUP + "GROUP]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MODULE + "CS2103 "
            + PREFIX_GROUP + "T01";

    public static final String MESSAGE_MOVE_PERSON_SUCCESS = "Removed %1$s from %2$s/%3$s";

    private final Index index;
    private final RemovePersonDescriptor movePersonDescriptor;

    /**
     * @param index of the person in the filtered person list to move
     * @param movePersonDescriptor module and/or class to remove the person from
     */
    public RemoveCommand(Index index, RemovePersonDescriptor movePersonDescriptor) {
        requireNonNull(index);
        requireNonNull(movePersonDescriptor);

        this.index = index;
        this.movePersonDescriptor = new RemovePersonDescriptor(movePersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToMove = lastShownList.get(index.getZeroBased());
        Person movedPerson = createMovedPerson(personToMove, movePersonDescriptor);

        // TODO: Check if mods and classes of movedPerson are valid
        Module mod = movedPerson.getModule();
        if (!model.hasModule(mod)) {
            throw new CommandException(Module.MESSAGE_DOES_NOT_EXIST);
        }
        Module savedMod = model.getModule(mod);
        Group grp = movedPerson.getGroup();
        if (!savedMod.hasGroup(grp)) {
            throw new CommandException(Group.MESSAGE_DOES_NOT_EXIST);
        }

        model.setPerson(personToMove, movedPerson);
        model.setSearchFilter(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(
                MESSAGE_MOVE_PERSON_SUCCESS, movedPerson, movedPerson.getModule(), movedPerson.getGroup()));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToMove}
     * edited with {@code movePersonDescriptor}.
     */
    private static Person createMovedPerson(Person personToMove, RemovePersonDescriptor movePersonDescriptor) {
        assert personToMove != null;

        Name updatedName = personToMove.getName();
        Phone updatedPhone = personToMove.getPhone();
        Email updatedEmail = personToMove.getEmail();
        Info updatedInfo = personToMove.getInfo();
        // TODO: Remove module and/or group from movePersonDescriptor to new person created
        Module updatedModule = movePersonDescriptor.getModule().orElse(personToMove.getModule());
        Group updatedGroup = movePersonDescriptor.getGroup().orElse(personToMove.getGroup());
        Set<Tag> updatedTags = personToMove.getTags();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedInfo, updatedModule, updatedGroup,
                updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemoveCommand)) {
            return false;
        }

        // state check
        RemoveCommand e = (RemoveCommand) other;
        return index.equals(e.index)
                && movePersonDescriptor.equals(e.movePersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class RemovePersonDescriptor {
        private Module module;
        private Group group;

        public RemovePersonDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public RemovePersonDescriptor(RemovePersonDescriptor toCopy) {
            setModule(toCopy.module);
            setGroup(toCopy.group);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(module, group);
        }

        public void setModule(Module mod) {
            this.module = mod;
        }

        public Optional<Module> getModule() {
            return Optional.ofNullable(module);
        }

        public void setGroup(Group grp) {
            this.group = grp;
        }

        public Optional<Group> getGroup() {
            return Optional.ofNullable(group);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof RemovePersonDescriptor)) {
                return false;
            }

            // state check
            RemovePersonDescriptor e = (RemovePersonDescriptor) other;
            Optional<Module> thisModule = getModule();
            Optional<Module> otherModule = e.getModule();
            boolean isModuleEqual = (thisModule.equals(otherModule)) // default equality of Optional<T> objects
                    || (thisModule.isPresent() && otherModule.isPresent() // or both modules are present
                    && thisModule.get().isSameModule(otherModule.get())); // and have the same identity

            return isModuleEqual && getGroup().equals(e.getGroup());
        }
    }
}
