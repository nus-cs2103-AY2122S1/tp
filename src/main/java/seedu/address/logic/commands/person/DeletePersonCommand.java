package seedu.address.logic.commands.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lessoncode.LessonCode;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.ModuleCodesContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeletePersonCommand extends Command {

    public static final String MESSAGE_USAGE = "delete: "
            + "Deletes the person identified by the index number used in the displayed person list "
            + "or by module code.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_MODULE_CODE + "MODULE CODE\n"
            + "Example: delete 1 , delete 1-3 , delete "
            + PREFIX_MODULE_CODE + "CS2040S";

    public static final String MESSAGE_DELETE_BY_MODULE_USAGE = "delete: "
            + "Delete only accepts one batch delete by Module Code at a time\n"
            + "Example: delete " + PREFIX_MODULE_CODE + "CS2040S";

    public static final String MESSAGE_NUMBER_DELETED_PERSON = "%d Deleted Persons: \n";
    public static final String MESSAGE_NUMBER_EDITED_PERSON = "\n%d Edited Persons: \n";
    public static final String MESSAGE_DELETE_SUCCESS = "%1$s \n";
    public static final String MESSAGE_NO_SUCH_MODULE_CODE = "No such Module Code";

    private final Index targetIndex;
    private final Index endIndex;
    private final Predicate<Person> predicate;
    private ModuleCode moduleCode;

    /**
     * Creates a DeleteCommand to delete the person at specified index
     *
     * @param targetIndex the person to be deleted
     */
    public DeletePersonCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        endIndex = targetIndex;
        predicate = Model.PREDICATE_SHOW_ALL_PERSONS;
    }

    /**
     * Creates a DeleteCommand to delete the persons between the specified indexes.
     *
     * @param targetIndex the first person to be deleted
     * @param endIndex the last person to be deleted
     */
    public DeletePersonCommand(Index targetIndex, Index endIndex) {
        this.targetIndex = targetIndex;
        this.endIndex = endIndex;
        predicate = Model.PREDICATE_SHOW_ALL_PERSONS;
    }

    /**
     * Creates a DeleteCommand to delete the persons with the specified predicate
     *
     * @param predicate the condition to delete the person
     * @param moduleCode the specified module code to delete
     */
    public DeletePersonCommand(ModuleCodesContainsKeywordsPredicate predicate, ModuleCode moduleCode) {
        targetIndex = Index.fromZeroBased(0);
        endIndex = Index.fromZeroBased(0);
        this.predicate = predicate;
        this.moduleCode = moduleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int sizeOfPersonList = model.getFilteredPersonList().size();
        String successMessage;

        if (predicate != Model.PREDICATE_SHOW_ALL_PERSONS) {
            successMessage = deleteByModule(model);
        } else if (targetIndex.getZeroBased() >= sizeOfPersonList) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } else if (targetIndex.getZeroBased() > endIndex.getZeroBased()
                || endIndex.getZeroBased() >= sizeOfPersonList) {
            throw new CommandException(Messages.MESSAGE_INVALID_RANGE);
        } else {
            successMessage = deleteAll(model);
        }
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(successMessage);
    }

    private String deleteByModule(Model model) throws CommandException {
        model.updateFilteredPersonList(predicate);
        if (model.getFilteredPersonList().isEmpty()) {
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
            throw new CommandException(MESSAGE_NO_SUCH_MODULE_CODE);
        } else if (isDeleteLesson()) {
            model.updateFilteredPersonList(predicate);
            return deleteLessonTag(model, model.getFilteredPersonList());
        } else {
            model.updateFilteredPersonList(predicate);
            return deleteRelatedPersonByModuleCode(model, model.getFilteredPersonList());
        }
    }

    private boolean isDeleteLesson() {
        return moduleCode.getLessonCodes().size() > 0;
    }

    private String deleteLessonTag(Model model, List<Person> filteredList) {
        int first = 0;
        int last = filteredList.size() - 1;
        int numberOfDeletedPersons = 0;
        int numberOfEditedPersons = 0;
        StringBuilder deletedPersons = new StringBuilder();
        StringBuilder editedPersons = new StringBuilder();
        while (last >= first) {
            Person personToCheck = filteredList.get(last);
            if (hasMoreThan1Lesson(personToCheck.get(moduleCode))) {
                deleteModuleLesson(personToCheck, model, personToCheck.get(moduleCode));
                editedPersons.insert(0, String.format(MESSAGE_DELETE_SUCCESS, personToCheck));
                numberOfEditedPersons++;
            } else {
                deletedPersons.insert(0, String.format(MESSAGE_DELETE_SUCCESS, filteredList.get(last)));
                model.deletePerson(filteredList.get(last));
                numberOfDeletedPersons++;
            }
            last--;
        }
        return String.format(MESSAGE_NUMBER_DELETED_PERSON, numberOfDeletedPersons) + deletedPersons
                + String.format(MESSAGE_NUMBER_EDITED_PERSON, numberOfEditedPersons) + editedPersons;
    }

    private boolean hasMoreThan1Lesson(ModuleCode moduleCode) {
        return moduleCode.getLessonCodes().size() > 1;
    }

    private void deleteModuleLesson(Person person, Model model, ModuleCode personModuleCode) {
        Set<LessonCode> tags = new HashSet<>(personModuleCode.getLessonCodes());
        String onlyTags = moduleCode.toString().substring(moduleCode.toString().indexOf(" ") + 1);
        tags.remove(new LessonCode(onlyTags));

        ModuleCode newModuleCode = new ModuleCode(moduleCode.getModuleCodeName(), tags);
        Set<ModuleCode> moduleCodes = new HashSet<>(person.getModuleCodes());
        moduleCodes.remove(moduleCode);
        moduleCodes.add(newModuleCode);

        EditPersonCommand.EditPersonDescriptor editPersonDescriptor = new EditPersonCommand.EditPersonDescriptor();
        editPersonDescriptor.setModuleCodes(moduleCodes);

        Person editedPerson = new Person(person.getName(), person.getEmail(), moduleCodes, person.getPhone(),
                person.getTeleHandle(), person.getRemark());
        model.setPerson(person, editedPerson);
    }

    private String deleteRelatedPersonByModuleCode(Model model, List<Person> filteredList) {
        int first = 0;
        int last = filteredList.size() - 1;
        int numberOfDeletedPersons = 0;
        int numberOfEditedPersons = 0;
        StringBuilder deletedPersons = new StringBuilder();
        StringBuilder editedPersons = new StringBuilder();
        while (last >= first) {
            Person personToCheck = filteredList.get(last);
            if (personToCheck.getModuleCodes().size() > 1) {
                deleteModuleCodeTag(personToCheck, model);
                editedPersons.insert(0, String.format(MESSAGE_DELETE_SUCCESS, personToCheck));
                numberOfEditedPersons++;
            } else {
                deletedPersons.insert(0, String.format(MESSAGE_DELETE_SUCCESS, filteredList.get(last)));
                model.deletePerson(filteredList.get(last));
                numberOfDeletedPersons++;
            }
            last--;
        }
        return String.format(MESSAGE_NUMBER_DELETED_PERSON, numberOfDeletedPersons) + deletedPersons
                + String.format(MESSAGE_NUMBER_EDITED_PERSON, numberOfEditedPersons) + editedPersons;
    }

    private void deleteModuleCodeTag(Person person, Model model) {
        Set<ModuleCode> moduleCodes = new HashSet<>(person.getModuleCodes());
        assert moduleCodes.contains(moduleCode);
        moduleCodes.remove(moduleCode);
        EditPersonCommand.EditPersonDescriptor editPersonDescriptor = new EditPersonCommand.EditPersonDescriptor();
        editPersonDescriptor.setModuleCodes(moduleCodes);

        Person editedPerson = new Person(person.getName(), person.getEmail(), moduleCodes, person.getPhone(),
                person.getTeleHandle(), person.getRemark());
        model.setPerson(person, editedPerson);
    }

    private String deleteAll(Model model) {
        List<Person> lastShownList = model.getFilteredPersonList();
        int first = targetIndex.getZeroBased();
        int last = endIndex.getZeroBased();
        int numberOfDeletedPersons = 0;
        StringBuilder deletedPersons = new StringBuilder();

        while (last >= first) {
            Person personToDelete = lastShownList.get(last);
            model.deletePerson(personToDelete);
            deletedPersons.insert(0, String.format(MESSAGE_DELETE_SUCCESS, personToDelete));
            numberOfDeletedPersons++;
            last--;
        }
        return String.format(MESSAGE_NUMBER_DELETED_PERSON, numberOfDeletedPersons) + deletedPersons;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePersonCommand // instanceof handles nulls
                && targetIndex.equals(((DeletePersonCommand) other).targetIndex)
                && endIndex.equals(((DeletePersonCommand) other).endIndex)
                && predicate.equals(((DeletePersonCommand) other).predicate)); // state check
    }
}
