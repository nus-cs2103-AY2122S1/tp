package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRAMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERACTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARKS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
 * Removes a data field at a specific index of a person in ComputingConnection.
 *
 * Only applicable to data fields which can have more than 1 value.
 */
public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "rm";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a data field at a  "
            + "specific index of a person. "
            + "Only applicable to data fields which can have more than 1 value.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_SKILL + "INDEX] "
            + "[" + PREFIX_LANGUAGE + "INDEX] "
            + "[" + PREFIX_FRAMEWORK + "INDEX] "
            + "[" + PREFIX_TAG + "INDEX]"
            + "[" + PREFIX_REMARKS + "INDEX]...\n"
            + "[" + PREFIX_INTERACTION + "INDEX]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_LANGUAGE + "3";

    public static final String MESSAGE_REMOVE_FIELD_SUCCESS = "Remove data field: %1$s";
    public static final String MESSAGE_NOT_REMOVED = "At least one field to remove must be provided";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in ComputingConnection.";
    private static final String MESSAGE_INVALID_FIELD = "The specified index to delete is out of bounds!";

    private final Index index;
    private final RemovePersonDescriptor removePersonDescriptor;

    /**
     * @param index of the person in the filtered person list to remove from
     * @param removePersonDescriptor detail to remove from the person
     */
    public RemoveCommand(Index index, RemovePersonDescriptor removePersonDescriptor) {
        requireNonNull(index);
        requireNonNull(removePersonDescriptor);

        this.index = index;
        this.removePersonDescriptor = new RemovePersonDescriptor(removePersonDescriptor);
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

        Person personToRemoveFrom = lastShownList.get(index.getZeroBased());
        if (!lastViewedPerson.isEmpty() && lastViewedPerson.get(0).equals(personToRemoveFrom)) {
            updateViewed = true;
        }
        Person personRemovedFrom = createRemovePerson(personToRemoveFrom, removePersonDescriptor);

        if (!personToRemoveFrom.isSamePerson(personRemovedFrom) && model.hasPerson(personRemovedFrom)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToRemoveFrom, personRemovedFrom);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);


        if (updateViewed) {
            model.updateViewedPerson(new Predicate<Person>() {
                @Override
                public boolean test(Person person) {
                    return person.equals(personRemovedFrom);
                }
            });
        }

        return new CommandResult(String.format(MESSAGE_REMOVE_FIELD_SUCCESS, personRemovedFrom));
    }

    private static Person createRemovePerson(Person personToRemoveFrom, RemovePersonDescriptor
            removePersonDescriptor) throws CommandException {
        assert personToRemoveFrom != null;

        Name previousName = personToRemoveFrom.getName();
        Email previousEmail = personToRemoveFrom.getEmail();
        Faculty previousFaculty = personToRemoveFrom.getFaculty();
        Major previousMajor = personToRemoveFrom.getMajor();
        Compatibility previousCompatibility = personToRemoveFrom.getCompatibility();

        //Convert Set of Skills to an alphabetically sorted Array
        Set<Index> indexesOfSkillsToRemove = removePersonDescriptor
                .getIndexesOfSkillsToRemove().orElse(Set.of());
        Set<Skill> previousSkills = personToRemoveFrom.getSkills();
        Set<Skill> updatedSkills = removeFromSet(indexesOfSkillsToRemove, previousSkills);

        //Convert Set of Languages to an alphabetically sorted Array
        Set<Index> indexesOfLanguagesToRemove = removePersonDescriptor
                .getIndexesOfLanguagesToRemove().orElse(Set.of());
        Set<Language> previousLanguages = personToRemoveFrom.getLanguages();
        Set<Language> updatedLanguages = removeFromSet(indexesOfLanguagesToRemove, previousLanguages);

        //Convert Set of Frameworks to an alphabetically sorted Array
        Set<Index> indexesOfFrameworksToRemove = removePersonDescriptor
                .getIndexesOfFrameworksToRemove().orElse(Set.of());
        Set<Framework> previousFrameworks = personToRemoveFrom.getFrameworks();
        Set<Framework> updatedFrameworks = removeFromSet(indexesOfFrameworksToRemove, previousFrameworks);

        //Convert Set of Tags to an alphabetically sorted Array
        Set<Index> indexesOfTagsToRemove = removePersonDescriptor
                .getIndexesOfTagsToRemove().orElse(Set.of());
        Set<Tag> previousTags = personToRemoveFrom.getTags();
        Set<Tag> updatedTags = removeFromSet(indexesOfTagsToRemove, previousTags);

        //Convert Set of Remarks to an alphabetically sorted Array
        Set<Index> indexesOfRemarksToRemove = removePersonDescriptor
                .getIndexesOfRemarksToRemove().orElse(Set.of());
        Set<Remark> previousRemarks = personToRemoveFrom.getRemarks();
        Set<Remark> updatedRemarks = removeFromSet(indexesOfRemarksToRemove, previousRemarks);

        //Convert Set of Interactions to an alphabetically sorted Array
        Set<Index> indexesOfInteractionsToRemove = removePersonDescriptor
                .getIndexOfInteractionsToRemove().orElse(Set.of());
        Set<Interaction> previousInteractions = personToRemoveFrom.getInteractions();
        Set<Interaction> updatedInteractions = removeFromInteractions(indexesOfInteractionsToRemove,
                previousInteractions);

        return new Person(previousName, previousEmail, previousFaculty, previousMajor,
                previousCompatibility, updatedSkills, updatedLanguages,
                updatedFrameworks, updatedTags, updatedRemarks, updatedInteractions);
    }

    /**
     * Removes an element at a specific index from a data field.
     * Valid sets to remove from are sets which are sorted alphanumerically:
     * skills, languages, frameworks, tags, remarks.
     */
    private static <T> Set<T> removeFromSet(Set<Index> indexesToRemove, Set<T> previousSet)
            throws CommandException {

        // Arrange previous skills in an array
        ArrayList<T> elementArray = new ArrayList<>();
        elementArray.addAll(previousSet);
        elementArray.sort(Comparator.comparing(Object::toString)); //Does this work?

        // Convert the set of Indexes to an array of integers
        Index[] indexesArray = indexesToRemove.toArray(new Index[0]);
        int[] intIndexesArray = new int[indexesArray.length];
        for (int i = 0; i < indexesArray.length; i++) {
            intIndexesArray[i] = indexesArray[i].getZeroBased();
        }

        // Sort indexes of data fields to remove in ascending order.
        Arrays.sort(intIndexesArray);

        // For each specified index, replace the element with null
        for (int j = intIndexesArray.length; j >= 1; j--) {
            int indexOfElementToRemove = intIndexesArray[j - 1];
            if (indexOfElementToRemove > elementArray.size() - 1) {
                throw new CommandException(MESSAGE_INVALID_FIELD);
            }
            elementArray.set(indexOfElementToRemove, null);
        }

        //Convert elementArray to Set<T> again
        Set<T> updatedSet = new HashSet<>();
        for (T t : elementArray) {
            if (t != null) {
                updatedSet.add(t);
            }
        }

        return updatedSet;
    }

    /**
     * Removes an element at a specific index from the interaction data field
     * Sorted by date:
     */
    private static Set<Interaction> removeFromInteractions(Set<Index> indexesToRemove, Set<Interaction> previousSet)
            throws CommandException {

        // Arrange previous skills in an array
        ArrayList<Interaction> elementArray = new ArrayList<>();
        elementArray.addAll(previousSet);
        elementArray.sort(Comparator.comparing(interaction -> interaction.date));
        //TODO: Test sorting and corresponding GUI display

        // Convert the set of Indexes to an array of integers
        Index[] indexesArray = indexesToRemove.toArray(new Index[0]);
        int[] intIndexesArray = new int[indexesArray.length];
        for (int i = 0; i < indexesArray.length; i++) {
            intIndexesArray[i] = indexesArray[i].getZeroBased();
        }

        // Sort indexes of data fields to remove in ascending order.
        Arrays.sort(intIndexesArray);

        // For each specified index, replace the element with null
        for (int j = intIndexesArray.length; j >= 1; j--) {
            int indexOfElementToRemove = intIndexesArray[j - 1];
            if (indexOfElementToRemove > elementArray.size() - 1) {
                throw new CommandException(MESSAGE_INVALID_FIELD);
            }
            elementArray.set(indexOfElementToRemove, null);
        }

        //Convert elementArray to Set<T> again
        Set<Interaction> updatedSet = new HashSet<>();
        for (Interaction i : elementArray) {
            if (i != null) {
                updatedSet.add(i);
            }
        }

        return updatedSet;
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
                && removePersonDescriptor.equals(e.removePersonDescriptor);
    }


    /**
     * Stores the details to remove the data field of a person. Each non-empty field value
     * will replace the corresponding field value of the person.
     */
    public static class RemovePersonDescriptor {
        private Set<Index> indexesOfSkillsToRemove;
        private Set<Index> indexesOfLanguagesToRemove;
        private Set<Index> indexesOfFrameworksToRemove;
        private Set<Index> indexesOfTagsToRemove;
        private Set<Index> indexesOfRemarksToRemove;
        private Set<Index> indexesOfInteractionsToRemove;

        public RemovePersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public RemovePersonDescriptor(RemovePersonDescriptor toCopy) {
            setIndexesOfSkillsToRemove(toCopy.indexesOfSkillsToRemove);
            setIndexesOfLanguagesToRemove(toCopy.indexesOfLanguagesToRemove);
            setIndexesOfFrameworksToRemove(toCopy.indexesOfFrameworksToRemove);
            setIndexesOfTagsToRemove(toCopy.indexesOfTagsToRemove);
            setIndexesOfRemarksToRemove(toCopy.indexesOfRemarksToRemove);
            setIndexesOfInteractionsToRemove(toCopy.indexesOfInteractionsToRemove);
        }

        /**
         * Returns true if at least one field has been removed.
         */
        public boolean isAnyFieldRemoved() {
            return CollectionUtil.isAnyNonNull(indexesOfSkillsToRemove, indexesOfLanguagesToRemove,
                    indexesOfFrameworksToRemove, indexesOfTagsToRemove,
                    indexesOfRemarksToRemove, indexesOfInteractionsToRemove);
        }

        /**
         * Sets {@code skill} to this object's {@code skills}.
         * A defensive copy of {@code skills} is used internally.
         */
        public void setIndexesOfSkillsToRemove(Set<Index> indexesOfSkillsToRemove) {
            this.indexesOfSkillsToRemove = (indexesOfSkillsToRemove != null)
                    ? new HashSet<>(indexesOfSkillsToRemove)
                    : null;
        }

        /**
         * Returns an unmodifiable skill set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code skills} is null.
         */
        public Optional<Set<Index>> getIndexesOfSkillsToRemove() {
            return (indexesOfSkillsToRemove != null)
                    ? Optional.of(Collections.unmodifiableSet(indexesOfSkillsToRemove))
                    : Optional.empty();
        }

        /**
         * Sets {@code language} to this object's {@code languages}.
         * A defensive copy of {@code language} is used internally.
         */
        public void setIndexesOfLanguagesToRemove(Set<Index> indexesOfLanguagesToRemove) {
            this.indexesOfLanguagesToRemove = (indexesOfLanguagesToRemove != null)
                    ? new HashSet<>(indexesOfLanguagesToRemove)
                    : null;
        }

        /**
         * Returns an unmodifiable language set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code languages} is null.
         */
        public Optional<Set<Index>> getIndexesOfLanguagesToRemove() {
            return (indexesOfLanguagesToRemove != null)
                    ? Optional.of(Collections.unmodifiableSet(indexesOfLanguagesToRemove))
                    : Optional.empty();
        }

        /**
         * Sets {@code framework} to this object's {@code frameworks}.
         * A defensive copy of {@code framework} is used internally.
         */
        public void setIndexesOfFrameworksToRemove(Set<Index> indexesOfFrameworksToRemove) {
            this.indexesOfFrameworksToRemove = (indexesOfFrameworksToRemove != null)
                    ? new HashSet<>(indexesOfFrameworksToRemove)
                    : null;
        }

        /**
         * Returns an unmodifiable framework set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code frameworks} is null.
         */
        public Optional<Set<Index>> getIndexesOfFrameworksToRemove() {
            return (indexesOfFrameworksToRemove != null)
                    ? Optional.of(Collections.unmodifiableSet(indexesOfFrameworksToRemove))
                    : Optional.empty();
        }

        /**
         * Sets {@code tag} to this object's {@code tags}.
         * A defensive copy of {@code tag} is used internally.
         */
        public void setIndexesOfTagsToRemove(Set<Index> indexesOfTagsToRemove) {
            this.indexesOfTagsToRemove = (indexesOfTagsToRemove != null)
                    ? new HashSet<>(indexesOfTagsToRemove)
                    : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Index>> getIndexesOfTagsToRemove() {
            return (indexesOfTagsToRemove != null)
                    ? Optional.of(Collections.unmodifiableSet(indexesOfTagsToRemove))
                    : Optional.empty();
        }

        /**
         * Sets {@code remark} to this object's {@code remarks}.
         * A defensive copy of {@code remark} is used internally.
         */
        public void setIndexesOfRemarksToRemove(Set<Index> indexesOfRemarksToRemove) {
            this.indexesOfRemarksToRemove = (indexesOfRemarksToRemove != null)
                    ? new HashSet<>(indexesOfRemarksToRemove)
                    : null;
        }

        /**
         * Returns an unmodifiable remark set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code remarks} is null.
         */
        public Optional<Set<Index>> getIndexesOfRemarksToRemove() {
            return (indexesOfRemarksToRemove != null)
                    ? Optional.of(Collections.unmodifiableSet(indexesOfRemarksToRemove))
                    : Optional.empty();
        }

        /**
         * Sets {@code interaction} to this object's {@code interactions}.
         * A defensive copy of {@code interaction} is used internally.
         */
        public void setIndexesOfInteractionsToRemove(Set<Index> indexesOfInteractionsToRemove) {
            this.indexesOfInteractionsToRemove = (indexesOfInteractionsToRemove != null)
                    ? new HashSet<>(indexesOfInteractionsToRemove)
                    : null;
        }

        /**
         * Returns an unmodifiable interaction set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code interactions} is null.
         */
        public Optional<Set<Index>> getIndexOfInteractionsToRemove() {
            return (indexesOfInteractionsToRemove != null)
                    ? Optional.of(Collections.unmodifiableSet(indexesOfInteractionsToRemove))
                    : Optional.empty();
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
            return getIndexesOfSkillsToRemove().equals(e.getIndexesOfSkillsToRemove())
                    && getIndexesOfLanguagesToRemove().equals(e.getIndexesOfLanguagesToRemove())
                    && getIndexesOfFrameworksToRemove().equals(e.getIndexesOfFrameworksToRemove())
                    && getIndexesOfTagsToRemove().equals(e.getIndexesOfTagsToRemove())
                    && getIndexesOfRemarksToRemove().equals(e.getIndexesOfRemarksToRemove())
                    && getIndexOfInteractionsToRemove().equals(e.getIndexOfInteractionsToRemove());
        }
    }
}
