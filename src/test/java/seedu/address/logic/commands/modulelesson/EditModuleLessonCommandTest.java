package seedu.address.logic.commands.modulelesson;

import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2040;
import static seedu.address.model.util.SampleDataUtil.parseModuleCode;
import static seedu.address.testutil.TypicalModuleLessons.getTypicalAddressBook;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.modulelesson.EditModuleLessonCommand.EditLessonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.modulelesson.ModuleLesson;
import seedu.address.model.person.ModuleCode;
import seedu.address.testutil.EditLessonDescriptorBuilder;
import seedu.address.testutil.ModuleLessonBuilder;

/**
 * Contains integration tests (interaction with {@code Model}) and unit tests for {@EditModuleLessonCommand}.
 */
public class EditModuleLessonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

//    @Test
//    public void execute_someFieldsSpecifiedUnfilteredList_success() {
//        //TODO
//        Index indexLastLesson = Index.fromOneBased(model.getFilteredModuleLessonList().size());
//        ModuleLesson lastLesson = model.getFilteredModuleLessonList().get(indexLastLesson.getZeroBased());
//
//        ModuleLessonBuilder lessonInList = new ModuleLessonBuilder(lastLesson);
//        ModuleLesson editedLesson = lessonInList.withModuleCode(VALID_MODULE_CODE_CS2040).build();
//
//        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder()
//                .withModuleCode(new HashSet<ModuleCode>(parseModuleCode(VALID_MODULE_CODE_CS2040))).build()
//    }

}
