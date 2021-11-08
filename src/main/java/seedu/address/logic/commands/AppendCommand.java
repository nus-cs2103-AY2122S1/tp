package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRAMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARKS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.interaction.Interaction;
import seedu.address.model.person.Compatibility;
import seedu.address.model.person.Email;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.remark.Remark;
import seedu.address.model.skill.Framework;
import seedu.address.model.skill.Language;
import seedu.address.model.skill.Skill;
import seedu.address.model.tag.Tag;

/**
 * Appends a new data field to the existing data fields of a person in ComputingConnection.
 *
 * Only applicable to data fields which can have more than 1 value.
 */
public class AppendCommand extends Command {

    public static final String COMMAND_WORD = "append";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Appends a new data field to "
            + "existing data fields of a person. "
            + "Only applicable to data fields which can have more than 1 value.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_SKILL + "SKILL] "
            + "[" + PREFIX_LANGUAGE + "LANGUAGE] "
            + "[" + PREFIX_FRAMEWORK + "FRAMEWORK] "
            + "[" + PREFIX_TAG + "TAG] "
            + "[" + PREFIX_REMARKS + "REMARK]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_LANGUAGE + "python";

    public static final String MESSAGE_APPEND_PERSON_SUCCESS = "Appended data field: %1$s";
    public static final String MESSAGE_NOT_APPENDED = "At least one field to append must be provided";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final AppendPersonDescriptor appendPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to append to
     * @param appendPersonDescriptor detail to append to the person
     */
    public AppendCommand(Index index, AppendPersonDescriptor appendPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(appendPersonDescriptor);

        this.index = index;
        this.appendPersonDescriptor = new AppendPersonDescriptor(appendPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        List<Person> lastViewedPerson = model.getViewedPerson();
        boolean updateViewed = false;

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToAppendTo = lastShownList.get(index.getZeroBased());
        if (!lastViewedPerson.isEmpty() && lastViewedPerson.get(0).equals(personToAppendTo)) {
            updateViewed = true;
        }
        Person appendedToPerson = createAppendedPerson(personToAppendTo, appendPersonDescriptor);

        if (!personToAppendTo.isSamePerson(appendedToPerson) && model.hasPerson(appendedToPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToAppendTo, appendedToPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        if (updateViewed) {
            model.updateViewedPerson(new Predicate<Person>() {
                @Override
                public boolean test(Person person) {
                    return person.equals(appendedToPerson);
                }
            });
        }

        return new CommandResult(String.format(MESSAGE_APPEND_PERSON_SUCCESS, appendedToPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToAppendTo}
     * edited with {@code appendPersonDescriptor}.
     */
    private static Person createAppendedPerson(Person personToAppendTo, AppendPersonDescriptor appendPersonDescriptor) {
        assert personToAppendTo != null;

        Name previousName = personToAppendTo.getName();
        Email previousEmail = personToAppendTo.getEmail();
        Faculty previousFaculty = personToAppendTo.getFaculty();
        Major previousMajor = personToAppendTo.getMajor();
        Compatibility previousCompatibility = personToAppendTo.getCompatibility();
        Set<Interaction> previousInteraction = personToAppendTo.getInteractions();

        Set<Skill> newSkills = appendPersonDescriptor.getSkills().orElse(Set.of()); // Else, empty set
        Set<Skill> currentSkills = personToAppendTo.getSkills();
        Set<Skill> updatedSkills = new HashSet<>();
        updatedSkills.addAll(currentSkills);
        updatedSkills.addAll(newSkills);

        Set<Language> newLanguages = appendPersonDescriptor.getLanguages().orElse(Set.of()); // Else, empty set
        Set<Language> currentLanguages = personToAppendTo.getLanguages();
        Set<Language> updatedLanguages = new HashSet<>();
        updatedLanguages.addAll(currentLanguages);
        updatedLanguages.addAll(newLanguages);

        Set<Framework> newFrameworks = appendPersonDescriptor.getFrameworks().orElse(Set.of()); // Else, empty set
        Set<Framework> currentFrameworks = personToAppendTo.getFrameworks();
        Set<Framework> updatedFrameworks = new HashSet<>();
        updatedFrameworks.addAll(currentFrameworks);
        updatedFrameworks.addAll(newFrameworks);

        Set<Tag> newTags = appendPersonDescriptor.getTags().orElse(Set.of()); // Else, empty set
        Set<Tag> currentTags = personToAppendTo.getTags();
        Set<Tag> updatedTags = new HashSet<>();
        updatedTags.addAll(currentTags);
        updatedTags.addAll(newTags);

        Set<Remark> newRemarks = appendPersonDescriptor.getRemarks().orElse(Set.of()); // Else, empty set
        Set<Remark> currentRemarks = personToAppendTo.getRemarks();
        Set<Remark> updatedRemarks = new HashSet<>();
        updatedRemarks.addAll(currentRemarks);
        updatedRemarks.addAll(newRemarks);

        return new Person(previousName, previousEmail, previousFaculty, previousMajor,
                previousCompatibility, updatedSkills, updatedLanguages, updatedFrameworks,
                updatedTags, updatedRemarks, previousInteraction);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppendCommand)) {
            return false;
        }

        // state check
        AppendCommand e = (AppendCommand) other;
        return index.equals(e.index)
                && appendPersonDescriptor.equals(e.appendPersonDescriptor);
    }

    /**
     * Stores the details to append the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class AppendPersonDescriptor {
        private Set<Skill> skills;
        private Set<Language> languages;
        private Set<Framework> frameworks;
        private Set<Tag> tags;
        private Set<Remark> remarks;

        public AppendPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public AppendPersonDescriptor(AppendPersonDescriptor toCopy) {
            setSkills(toCopy.skills);
            setLanguages(toCopy.languages);
            setFrameworks(toCopy.frameworks);
            setTags(toCopy.tags);
            setRemarks(toCopy.remarks);
        }

        /**
         * Returns true if at least one field has been appended.
         */
        public boolean isAnyFieldAppended() {
            return CollectionUtil.isAnyNonNull(skills, languages, frameworks, tags, remarks);
        }

        /**
         * Sets {@code skill} to this object's {@code skills}.
         * A defensive copy of {@code skills} is used internally.
         */
        public void setSkills(Set<Skill> skills) {
            this.skills = (skills != null) ? new HashSet<>(skills) : null;
        }

        /**
         * Returns an unmodifiable skill set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code skills} is null.
         */
        public Optional<Set<Skill>> getSkills() {
            return (skills != null) ? Optional.of(Collections.unmodifiableSet(skills)) : Optional.empty();
        }

        /**
         * Sets {@code language} to this object's {@code languages}.
         * A defensive copy of {@code languages} is used internally.
         */
        public void setLanguages(Set<Language> languages) {
            this.languages = (languages != null) ? new HashSet<>(languages) : null;
        }

        /**
         * Returns an unmodifiable language set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code languages} is null.
         */
        public Optional<Set<Language>> getLanguages() {
            return (languages != null) ? Optional.of(Collections.unmodifiableSet(languages)) : Optional.empty();
        }

        /**
         * Sets {@code framework} to this object's {@code frameworks}.
         * A defensive copy of {@code frameworks} is used internally.
         */
        public void setFrameworks(Set<Framework> frameworks) {
            this.frameworks = (frameworks != null) ? new HashSet<>(frameworks) : null;
        }

        /**
         * Returns an unmodifiable framework set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code frameworks} is null.
         */
        public Optional<Set<Framework>> getFrameworks() {
            return (frameworks != null) ? Optional.of(Collections.unmodifiableSet(frameworks)) : Optional.empty();
        }

        /**
         * Sets {@code tag} to this object's {@code tags}.
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

        /**
         * Sets {@code remarks} to this object's {@code remarks}.
         * A defensive copy of {@code remarks} is used internally.
         */
        public void setRemarks(Set<Remark> remarks) {
            this.remarks = (remarks != null) ? new HashSet<>(remarks) : null;
        }

        /**
         * Returns an unmodifiable remarks set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code remarks} is null.
         */
        public Optional<Set<Remark>> getRemarks() {
            return (remarks != null) ? Optional.of(Collections.unmodifiableSet(remarks)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof AppendPersonDescriptor)) {
                return false;
            }

            // state check
            AppendPersonDescriptor e = (AppendPersonDescriptor) other;
            return getSkills().equals(e.getSkills())
                    && getLanguages().equals(e.getLanguages())
                    && getFrameworks().equals(e.getFrameworks())
                    && getTags().equals(e.getTags())
                    && getRemarks().equals(e.getRemarks());
        }
    }
}
