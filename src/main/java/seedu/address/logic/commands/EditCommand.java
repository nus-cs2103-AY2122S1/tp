package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_VENUE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDate;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskTime;
import seedu.address.model.task.Venue;

/**
 * Edits the details of an existing person in the address book.
 * Edits the details of an existing task.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String DESCRIPTION = "Edits the details of the person/task identified.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": " + DESCRIPTION
            + " by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer less than or equal to " + Integer.MAX_VALUE + ")\n"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]"
            + "[" + PREFIX_DESCRIPTION + "Description]"
            + "[" + PREFIX_TASK_INDEX + " TASK_INDEX (must be a positive integer less than or equal to "
            + Integer.MAX_VALUE + ")\n"
            + "[" + PREFIX_TASK_DESCRIPTION + " TASK_NAME] "
            + "[" + PREFIX_TASK_DATE + " TASK_DATE] "
            + "[" + PREFIX_TASK_TIME + " TASK_TIME] "
            + "[" + PREFIX_TASK_VENUE + " TASK_ADDRESS] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_TASK_INDEX + " 2 "
            + PREFIX_TASK_DESCRIPTION + " Assignment Discussion";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s ";
    public static final String MESSAGE_TASK_NOT_EDITED = "At least one field of task to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "Task already exists.";
    public static final String MESSAGE_INVALID_TASK = "The size of %1$s's task list is not that big";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    private final Index targetTaskIndex;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * Constructor for an EditCommand to edit both a person's details and the tasks.
     *
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     * @param targetTaskIndex of the person in the filtered person list
     * @param editTaskDescriptor details to edit the task with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor,
                       Index targetTaskIndex, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
        this.targetTaskIndex = targetTaskIndex;
        this.editTaskDescriptor = editTaskDescriptor;
    }

    /**
     * Constructor for an EditCommand to edit a person's details
     *
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
        this.targetTaskIndex = null;
        this.editTaskDescriptor = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        String editedTaskMessage = "";

        if (targetTaskIndex != null) {
            List<Task> tasks = new ArrayList<>(personToEdit.getTasks());

            if (targetTaskIndex.getZeroBased() >= tasks.size()) {
                throw new CommandException(String.format(MESSAGE_INVALID_TASK, personToEdit.getName()));
            }

            Task taskToEdit = tasks.get(targetTaskIndex.getZeroBased());
            Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

            if (taskToEdit.equals(editedTask)) {
                throw new CommandException(MESSAGE_DUPLICATE_TASK);
            }

            tasks.set(targetTaskIndex.getZeroBased(), editedTask);
            editedPerson = new Person(
                    editedPerson.getName(), editedPerson.getPhone(), editedPerson.getEmail(),
                    editedPerson.getAddress(), editedPerson.getTags(), tasks, editedPerson.getDescription(),
                    editedPerson.isImportant()
            );

            editedTaskMessage = String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask);
        }
        // If the edited details result in a duplicate person, throw an exception.
        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        model.setPerson(personToEdit, editedPerson);

        if (targetTaskIndex == null) {
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson));
        } else {
            model.displayPersonTaskList(editedPerson);
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedPerson) + "\n"
                    + editedTaskMessage);
        }
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Description updatedDescription = editPersonDescriptor.getDescription().orElse(personToEdit.getDescription());
        List<Task> tasks = editPersonDescriptor.getTasks().orElse(personToEdit.getTasks());
        Boolean updatedisImportant = editPersonDescriptor.getImportance().orElse(personToEdit.isImportant());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, tasks,
                updatedDescription, updatedisImportant);
    }

    private static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert(taskToEdit != null);

        TaskName updatedName = editTaskDescriptor.getTaskName().orElse(taskToEdit.getTaskName());
        TaskDate updatedDate = editTaskDescriptor.getTaskDate().orElse(taskToEdit.getDate());
        TaskTime updatedTime = editTaskDescriptor.getTaskTime().orElse(taskToEdit.getTime());
        Venue updatedVenue = editTaskDescriptor.getTaskVenue().orElse(taskToEdit.getVenue());

        return new Task(updatedName, updatedDate, updatedTime, updatedVenue);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {

        private Name name;

        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private Description description;
        private List<Task> tasks;
        private Boolean isImportant;
        public EditPersonDescriptor() {}
        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setDescription(toCopy.description);
            setTasks(toCopy.tasks);
            setImportance(toCopy.isImportant);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, tasks, description, isImportant);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setTasks(List<Task> tasks) {
            this.tasks = tasks;
        }

        public Optional<List<Task>> getTasks() {
            return Optional.ofNullable(tasks);
        }

        public void setImportance(Boolean isImportant) {
            this.isImportant = isImportant;
        }

        public Optional<Boolean> getImportance() {
            return Optional.ofNullable(isImportant);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags())
                    && getTasks().equals(e.getTasks())
                    && getDescription().equals(e.getDescription());
        }


    }
    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private TaskName taskName;

        private TaskDate taskDate;
        private TaskTime taskTime;
        private Venue taskVenue;
        public EditTaskDescriptor() {}
        /**
         * Copy constructor.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setTaskName(toCopy.taskName);
            setTaskDate(toCopy.taskDate);
            setTaskTime(toCopy.taskTime);
            setTaskVenue(toCopy.taskVenue);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(taskName, taskDate, taskTime, taskVenue);
        }

        public void setTaskName(TaskName taskName) {
            this.taskName = taskName;
        }

        public Optional<TaskName> getTaskName() {
            return Optional.ofNullable(taskName);
        }

        public void setTaskDate(TaskDate taskDate) {
            this.taskDate = taskDate;
        }

        public Optional<TaskDate> getTaskDate() {
            return Optional.ofNullable(taskDate);
        }

        public void setTaskTime(TaskTime taskTime) {
            this.taskTime = taskTime;
        }

        public Optional<TaskTime> getTaskTime() {
            return Optional.ofNullable(taskTime);
        }

        public void setTaskVenue(Venue venue) {
            this.taskVenue = venue;
        }

        public Optional<Venue> getTaskVenue() {
            return Optional.ofNullable(taskVenue);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskDescriptor)) {
                return false;
            }

            // state check
            EditTaskDescriptor e = (EditTaskDescriptor) other;

            return getTaskName().equals(e.getTaskName())
                    && getTaskDate().equals(e.getTaskDate())
                    && getTaskTime().equals(e.getTaskTime())
                    && getTaskVenue().equals(e.getTaskVenue());
        }

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        if (index.equals(e.index) && editPersonDescriptor.equals(e.editPersonDescriptor)) {
            if (editTaskDescriptor != null && e.editTaskDescriptor == null
                    || editTaskDescriptor == null && e.editTaskDescriptor != null) {
                return false;
            } else {
                boolean hasSameNonNullIndex = targetTaskIndex != null && e.targetTaskIndex != null
                        && targetTaskIndex.equals(e.targetTaskIndex);
                if (editTaskDescriptor != null) {
                    return editTaskDescriptor.equals(e.editTaskDescriptor) && (targetTaskIndex == null
                            && e.targetTaskIndex == null) || hasSameNonNullIndex;
                } else {
                    return (targetTaskIndex == null && e.targetTaskIndex == null)
                            || hasSameNonNullIndex;
                }
            }
        }
        return false;
    }

    public String getCommand() {
        return COMMAND_WORD;
    }

    public String getDescription() {
        return DESCRIPTION;
    }
}
