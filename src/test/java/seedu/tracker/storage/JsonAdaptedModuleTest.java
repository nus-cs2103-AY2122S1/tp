package seedu.tracker.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tracker.storage.JsonAdaptedModule.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.tracker.testutil.Assert.assertThrows;
import static seedu.tracker.testutil.TypicalModules.CS2101;
import static seedu.tracker.testutil.TypicalModules.GEQ1000;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.tracker.commons.exceptions.IllegalValueException;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.Code;
import seedu.tracker.model.module.Description;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.Module;
import seedu.tracker.model.module.Title;
import seedu.tracker.testutil.ModuleBuilder;

public class JsonAdaptedModuleTest {
    private static final String INVALID_CODE = "CS21@1";
    private static final String INVALID_TITLE = "+651234";
    private static final String INVALID_DESCRIPTION = " ";
    private static final int INVALID_MC = -1;
    private static final String INVALID_TAG = "#friend";
    private static final int INVALID_ACADEMIC_YEAR = 9;
    private static final int INVALID_SEMESTER = -1;

    private static final Module GEQ1000Scheduled =
            new ModuleBuilder(GEQ1000).withAcademicCalendar(1, 2).build();
    private static final String VALID_CODE = GEQ1000Scheduled.getCode().toString();
    private static final String VALID_TITLE = GEQ1000Scheduled.getTitle().toString();
    private static final String VALID_DESCRIPTION = GEQ1000Scheduled.getDescription().toString();
    private static final int VALID_MC = GEQ1000Scheduled.getMc().value;
    private static final int VALID_ACADEMIC_YEAR = GEQ1000Scheduled.getAcademicCalendar().getAcademicYear().value;
    private static final int VALID_SEMESTER = GEQ1000Scheduled.getAcademicCalendar().getSemester().value;

    private static final List<JsonAdaptedTag> VALID_TAGS = GEQ1000.getTags().stream()
        .map(JsonAdaptedTag::new)
        .collect(Collectors.toList());

    @Test
    public void toModelType_validModuleDetailsNoAcademicYear_returnsModule() throws Exception {
        JsonAdaptedModule module = new JsonAdaptedModule(CS2101);
        assertEquals(CS2101, module.toModelType());
    }

    @Test
    public void toModelType_validModuleDetailsWithAcademicYear_returnsModule() throws Exception {
        JsonAdaptedModule module = new JsonAdaptedModule(GEQ1000Scheduled);
        assertEquals(GEQ1000Scheduled, module.toModelType());
    }

    @Test
    public void toModelType_invalidCode_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(INVALID_CODE, VALID_TITLE, VALID_DESCRIPTION,
                VALID_MC, VALID_ACADEMIC_YEAR, VALID_SEMESTER, VALID_TAGS);
        String expectedMessage = Code.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullCode_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(null, VALID_TITLE, VALID_DESCRIPTION,
                VALID_MC, VALID_ACADEMIC_YEAR, VALID_SEMESTER, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Code.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_CODE, INVALID_TITLE, VALID_DESCRIPTION,
                VALID_MC, VALID_ACADEMIC_YEAR, VALID_SEMESTER, VALID_TAGS);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_CODE, null, VALID_DESCRIPTION,
                VALID_MC, VALID_ACADEMIC_YEAR, VALID_SEMESTER, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_CODE, VALID_TITLE, null,
                VALID_MC, VALID_ACADEMIC_YEAR, VALID_SEMESTER, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidMc_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_CODE, VALID_TITLE, VALID_DESCRIPTION,
                INVALID_MC, VALID_ACADEMIC_YEAR, VALID_SEMESTER, VALID_TAGS);
        String expectedMessage = Mc.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_CODE, VALID_TITLE, VALID_DESCRIPTION,
                VALID_MC, VALID_ACADEMIC_YEAR, VALID_SEMESTER, invalidTags);
        assertThrows(IllegalValueException.class, module::toModelType);
    }

    @Test
    public void toModelType_invalidAcademicYear_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_CODE, VALID_TITLE, VALID_DESCRIPTION,
                VALID_MC, INVALID_ACADEMIC_YEAR, VALID_SEMESTER, VALID_TAGS);
        String expectedMessage = AcademicYear.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidSemester_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_CODE, VALID_TITLE, VALID_DESCRIPTION,
                VALID_MC, VALID_ACADEMIC_YEAR, INVALID_SEMESTER, VALID_TAGS);
        String expectedMessage = Semester.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }
}
