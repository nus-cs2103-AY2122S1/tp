package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRAMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
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
            + "[" + PREFIX_TAG + "INDEX]...\n"
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
        this.removePersonDescriptor = removePersonDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToRemoveFrom = lastShownList.get(index.getZeroBased());
        Person personRemovedFrom = createRemovePerson(personToRemoveFrom, removePersonDescriptor);

        if (!personToRemoveFrom.isSamePerson(personRemovedFrom) && model.hasPerson(personRemovedFrom)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToRemoveFrom, personRemovedFrom);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_REMOVE_FIELD_SUCCESS, personRemovedFrom));
    }

    private static Person createRemovePerson(Person personToRemoveFrom, RemovePersonDescriptor
    removePersonDescriptor) throws CommandException {
        assert personToRemoveFrom != null;

        Name previousName = personToRemoveFrom.getName();
        Email previousEmail = personToRemoveFrom.getEmail();
        Faculty previousFaculty = personToRemoveFrom.getFaculty();
        Major previousMajor = personToRemoveFrom.getMajor();

        //Convert Set of Skills to an alphabetically sorted Array
        Set<Index> indexesOfSkillsToRemove = removePersonDescriptor.getSkillIndexes().orElse(Set.of());
        Set<Skill> previousSkills = personToRemoveFrom.getSkills();
        Set<Skill> updatedSkills = removeFromSkills(indexesOfSkillsToRemove, previousSkills);

        //Convert Set of Languages to an alphabetically sorted Array
        Set<Index> indexesOfLanguagesToRemove = removePersonDescriptor.getLanguageIndexes().orElse(Set.of());
        Set<Language> previousLanguages = personToRemoveFrom.getLanguages();
        Set<Language> updatedLanguages = removeFromLanguages(indexesOfLanguagesToRemove, previousLanguages);

        //Convert Set of Frameworks to an alphabetically sorted Array
        Set<Index> indexesOfFrameworksToRemove = removePersonDescriptor.getFrameworkIndexes().orElse(Set.of());
        Set<Framework> previousFrameworks = personToRemoveFrom.getFrameworks();
        Set<Framework> updatedFrameworks = removeFromFrameworks(indexesOfFrameworksToRemove, previousFrameworks);

        //Convert Set of Tags to an alphabetically sorted Array
        Set<Index> indexesOfTagsToRemove = removePersonDescriptor.getTagIndexes().orElse(Set.of());
        Set<Tag> previousTags = personToRemoveFrom.getTags();
        Set<Tag> updatedTags = removeFromTags(indexesOfTagsToRemove, previousTags);

        return new Person(previousName, previousEmail, previousFaculty, previousMajor,
                updatedSkills, updatedLanguages, updatedFrameworks, updatedTags);
    }

    private static Set<Skill> removeFromSkills(Set<Index> indexesToRemove, Set<Skill> previousSkills) throws CommandException {
        // Arrange previous skills in an array
        Skill[] skillArray = previousSkills.toArray(new Skill[0]);
        Arrays.sort(skillArray, Comparator.comparing(skill -> skill.skillName));

        // Convert the set of Indexes to an array of integers
        Index[] indexesArray = indexesToRemove.toArray(new Index[0]);
        int[] intIndexesArray = new int[indexesArray.length];
        for (int i = 0; i < indexesArray.length; i ++) {
            intIndexesArray[i] = indexesArray[i].getZeroBased();
        }

        // Sort indexes of data fields to remove in ascending order.
        Arrays.sort(intIndexesArray);

        // For each specified index, remove corresponding skill in skillArray
        for (int j = intIndexesArray.length; j >= 1; j --) {
            int indexOfSkillToRemove = intIndexesArray[j - 1];
            if (indexOfSkillToRemove > skillArray.length - 1) {
                throw new CommandException(MESSAGE_INVALID_FIELD);
            }
            skillArray[indexOfSkillToRemove] = null;
        }

        //Convert skillArray to Set<Skill> again
        Set<Skill> updatedSkills = new HashSet<>();
        for (Skill s : skillArray) {
            if (s != null) {
                updatedSkills.add(s);
            }
        }

        return updatedSkills;
    }

    private static Set<Language> removeFromLanguages(Set<Index> indexesToRemove, Set<Language> previousLanguages)
            throws CommandException {
        // Arrange previous languages in an array
        Language[] languageArray = previousLanguages.toArray(new Language[0]);
        Arrays.sort(languageArray, Comparator.comparing(language -> language.languageName));

        // Convert the set of Indexes to an array of integers
        Index[] indexesArray = indexesToRemove.toArray(new Index[0]);
        int[] intIndexesArray = new int[indexesArray.length];
        for (int i = 0; i < indexesArray.length; i ++) {
            intIndexesArray[i] = indexesArray[i].getZeroBased();
        }

        // Sort indexes of data fields to remove in ascending order.
        Arrays.sort(intIndexesArray);

        // For each specified index, remove corresponding language in languageArray
        for (int j = intIndexesArray.length; j >= 1; j --) {
            int indexOfLanguageToRemove = intIndexesArray[j - 1];
            if (indexOfLanguageToRemove > languageArray.length - 1) {
                throw new CommandException(MESSAGE_INVALID_FIELD);
            }
            languageArray[indexOfLanguageToRemove] = null;
        }

        //Convert languageArray to Set<Language> again
        Set<Language> updatedLanguages = new HashSet<>();
        for (Language s : languageArray) {
            if (s != null) {
                updatedLanguages.add(s);
            }
        }

        return updatedLanguages;
    }

    private static Set<Framework> removeFromFrameworks(Set<Index> indexesToRemove, Set<Framework> previousFrameworks) throws CommandException {
        // Arrange previous frameworks in an array
        Framework[] frameworkArray = previousFrameworks.toArray(new Framework[0]);
        Arrays.sort(frameworkArray, Comparator.comparing(framework -> framework.frameworkName));

        // Convert the set of Indexes to an array of integers
        Index[] indexesArray = indexesToRemove.toArray(new Index[0]);
        int[] intIndexesArray = new int[indexesArray.length];
        for (int i = 0; i < indexesArray.length; i ++) {
            intIndexesArray[i] = indexesArray[i].getZeroBased();
        }

        // Sort indexes of data fields to remove in ascending order.
        Arrays.sort(intIndexesArray);

        // For each specified index, remove corresponding framework in frameworkArray
        for (int j = intIndexesArray.length; j >= 1; j --) {
            int indexOfFrameworkToRemove = intIndexesArray[j - 1];
            if (indexOfFrameworkToRemove > frameworkArray.length - 1) {
                throw new CommandException(MESSAGE_INVALID_FIELD);
            }
            frameworkArray[indexOfFrameworkToRemove] = null;
        }

        //Convert frameworkArray to Set<Framework> again
        Set<Framework> updatedFrameworks = new HashSet<>();
        for (Framework s : frameworkArray) {
            if (s != null) {
                updatedFrameworks.add(s);
            }
        }

        return updatedFrameworks;
    }

    private static Set<Tag> removeFromTags(Set<Index> indexesToRemove, Set<Tag> previousTags) throws CommandException {
        // Arrange previous tags in an array
        Tag[] tagArray = previousTags.toArray(new Tag[0]);
        Arrays.sort(tagArray, Comparator.comparing(tag -> tag.tagName));

        // Convert the set of Indexes to an array of integers
        Index[] indexesArray = indexesToRemove.toArray(new Index[0]);
        int[] intIndexesArray = new int[indexesArray.length];
        for (int i = 0; i < indexesArray.length; i ++) {
            intIndexesArray[i] = indexesArray[i].getZeroBased();
        }

        // Sort indexes of data fields to remove in ascending order.
        Arrays.sort(intIndexesArray);

        // For each specified index, remove corresponding tag in tagArray
        for (int j = intIndexesArray.length; j >= 1; j --) {
            int indexOfTagToRemove = intIndexesArray[j - 1];
            if (indexOfTagToRemove > tagArray.length - 1) {
                throw new CommandException(MESSAGE_INVALID_FIELD);
            }
            tagArray[indexOfTagToRemove] = null;
        }

        //Convert tagArray to Set<Tag> again
        Set<Tag> updatedTags = new HashSet<>();
        for (Tag s : tagArray) {
            if (s != null) {
                updatedTags.add(s);
            }
        }

        return updatedTags;
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
        private Set<Index> skillIndexes;
        private Set<Index> languageIndexes;
        private Set<Index> frameworkIndexes;
        private Set<Index> tagIndexes;

        public RemovePersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public RemovePersonDescriptor(RemovePersonDescriptor toCopy) {
            setSkillIndexes(toCopy.skillIndexes);
            setLanguageIndexes(toCopy.languageIndexes);
            setFrameworkIndexes(toCopy.frameworkIndexes);
            setTagIndexes(toCopy.tagIndexes);
        }

        /**
         * Returns true if at least one field has been removed.
         */
        public boolean isAnyFieldRemoved() {
            return CollectionUtil.isAnyNonNull(skillIndexes, languageIndexes, frameworkIndexes, tagIndexes);
        }

        /**
         * Sets {@code skill} to this object's {@code skills}.
         * A defensive copy of {@code skills} is used internally.
         */
        public void setSkillIndexes(Set<Index> skillIndexes) {
            this.skillIndexes = (skillIndexes != null) ? new HashSet<>(skillIndexes) : null;
        }

        /**
         * Returns an unmodifiable skill set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code skills} is null.
         */
        public Optional<Set<Index>> getSkillIndexes() {
            return (skillIndexes != null) ? Optional.of(Collections.unmodifiableSet(skillIndexes)) : Optional.empty();
        }

        /**
         * Sets {@code language} to this object's {@code languages}.
         * A defensive copy of {@code language} is used internally.
         */
        public void setLanguageIndexes(Set<Index> languageIndexes) {
            this.languageIndexes = (languageIndexes != null) ? new HashSet<>(languageIndexes) : null;
        }

        /**
         * Returns an unmodifiable language set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code languages} is null.
         */
        public Optional<Set<Index>> getLanguageIndexes() {
            return (languageIndexes != null) ? Optional.of(Collections.unmodifiableSet(languageIndexes)) : Optional.empty();
        }

        /**
         * Sets {@code framework} to this object's {@code frameworks}.
         * A defensive copy of {@code framework} is used internally.
         */
        public void setFrameworkIndexes(Set<Index> frameworkIndexes) {
            this.frameworkIndexes = (frameworkIndexes != null) ? new HashSet<>(frameworkIndexes) : null;
        }

        /**
         * Returns an unmodifiable framework set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code frameworks} is null.
         */
        public Optional<Set<Index>> getFrameworkIndexes() {
            return (frameworkIndexes != null) ? Optional.of(Collections.unmodifiableSet(frameworkIndexes)) : Optional.empty();
        }

        /**
         * Sets {@code tag} to this object's {@code tags}.
         * A defensive copy of {@code tag} is used internally.
         */
        public void setTagIndexes(Set<Index> tagIndexes) {
            this.tagIndexes = (tagIndexes != null) ? new HashSet<>(tagIndexes) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Index>> getTagIndexes() {
            return (tagIndexes != null) ? Optional.of(Collections.unmodifiableSet(tagIndexes)) : Optional.empty();
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
            return getSkillIndexes().equals(e.getSkillIndexes())
                    && getLanguageIndexes().equals(e.getLanguageIndexes())
                    && getFrameworkIndexes().equals(e.getFrameworkIndexes())
                    && getTagIndexes().equals(e.getTagIndexes());
        }
    }
}
