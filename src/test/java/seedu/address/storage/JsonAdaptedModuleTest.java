package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedModule.MODULE_MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.MODULE_1;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModuleName;

public class JsonAdaptedModuleTest {
    private static final String INVALID_MODULE_NAME = "1000";

    private static final List<JsonAdaptedStudent> VALID_STUDENT_LIST = MODULE_1
            .getStudentList()
            .stream()
            .map(JsonAdaptedStudent::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTask> VALID_TASK_LIST = MODULE_1
            .getTaskList()
            .asUnmodifiableObservableList()
            .stream()
            .map(JsonAdaptedTask::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_invalidModuleName_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(INVALID_MODULE_NAME, VALID_STUDENT_LIST, VALID_TASK_LIST);
        String expectedMessage = ModuleName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullModuleName_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(null, VALID_STUDENT_LIST, VALID_TASK_LIST);
        String expectedMessage = String.format(MODULE_MISSING_FIELD_MESSAGE_FORMAT, ModuleName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }
}
