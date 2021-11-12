package seedu.teachbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.teachbook.storage.JsonAdaptedClass.MESSAGE_DUPLICATE_STUDENT;
import static seedu.teachbook.storage.JsonAdaptedClass.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.teachbook.testutil.Assert.assertThrows;
import static seedu.teachbook.testutil.TypicalClasses.getTypicalClasses;
import static seedu.teachbook.testutil.TypicalStudents.BENSON;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.teachbook.commons.exceptions.IllegalValueException;
import seedu.teachbook.model.classobject.ClassName;
import seedu.teachbook.testutil.TypicalClasses;


public class JsonAdaptedClassTest {
    private static final String INVALID_CLASSNAME = "4@1";

    private static final String VALID_CLASSNAME = getTypicalClasses().get(0).getClassName().toString();
    private static final List<JsonAdaptedStudent> VALID_STUDENTS =
            getTypicalClasses().get(0).getUniqueStudentListOfThisClass().asUnmodifiableObservableList().stream()
                    .map(JsonAdaptedStudent::new)
                    .collect(Collectors.toList());


    @Test
    public void toModelType_validClassDetails_returnsClass() throws Exception {
        JsonAdaptedClass classObj = new JsonAdaptedClass(TypicalClasses.getAClass());
        assertEquals(TypicalClasses.getAClass(), classObj.toModelType());
    }

    @Test
    public void toModelType_nullClassList_returnsClass() throws Exception {
        JsonAdaptedClass classObj = new JsonAdaptedClass(getTypicalClasses().get(1).getClassName().toString(), null);
        assertEquals(getTypicalClasses().get(1), classObj.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedClass classObj = new JsonAdaptedClass(INVALID_CLASSNAME, VALID_STUDENTS);
        String expectedMessage = ClassName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, classObj::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedClass classObj = new JsonAdaptedClass(null, VALID_STUDENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ClassName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, classObj::toModelType);
    }

    @Test
    public void toModelType_invalidStudents_throwsIllegalValueException() {
        List<JsonAdaptedStudent> invalidStudents =
                getTypicalClasses().get(0).getUniqueStudentListOfThisClass().asUnmodifiableObservableList().stream()
                        .map(JsonAdaptedStudent::new)
                        .collect(Collectors.toList());
        invalidStudents.add(new JsonAdaptedStudent(BENSON)); //duplicate student
        JsonAdaptedClass classObj = new JsonAdaptedClass(VALID_CLASSNAME, invalidStudents);
        assertThrows(IllegalValueException.class,
                MESSAGE_DUPLICATE_STUDENT,
                classObj::toModelType);
    }

}
