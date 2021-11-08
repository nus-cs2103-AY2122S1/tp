package seedu.address.model.tutorialclass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTutorialClasses.G01;
import static seedu.address.testutil.TypicalTutorialGroups.TUT_01;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tutorialclass.exceptions.DuplicateTutorialClassException;
import seedu.address.model.tutorialclass.exceptions.TutorialClassNotFoundException;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.exceptions.TutorialGroupNotFoundException;


public class UniqueTutorialClassListTest {

    private final UniqueTutorialClassList uniqueTutorialClassList = new UniqueTutorialClassList();

    @Test
    public void contains_nullTutorialClass_throwsNullPointerException() {
        TutorialClass tutorialClass = null;
        assertThrows(NullPointerException.class, () -> uniqueTutorialClassList.contains(tutorialClass));
    }

    @Test
    public void contains_tutorialClassNotInList_returnsFalse() {
        assertFalse(uniqueTutorialClassList.contains(G01));
    }

    @Test
    public void contains_tutClassInList_returnsTrue() {
        uniqueTutorialClassList.add(G01);
        assertTrue(uniqueTutorialClassList.contains(G01));
    }

    @Test
    public void add_nullTutorialClass_throwsNullPointerException() {
        TutorialClass tutorialClass = null;
        assertThrows(NullPointerException.class, () -> uniqueTutorialClassList.add(tutorialClass));
    }

    @Test
    public void add_nullTutorialGroup_throwsNullPointerException() {
        TutorialGroup tutorialGroup = null;
        assertThrows(NullPointerException.class, () -> uniqueTutorialClassList.add(tutorialGroup));
    }

    @Test
    public void add_duplicateTutorialClass_throwsDuplicateStudentException() {
        uniqueTutorialClassList.add(G01);
        assertThrows(DuplicateTutorialClassException.class, () -> uniqueTutorialClassList.add(G01));
    }

    @Test
    public void remove_nullTutorialClass_throwsNullPointerException() {
        TutorialClass tutorialClass = null;
        assertThrows(NullPointerException.class, () -> uniqueTutorialClassList.remove(tutorialClass));
    }

    @Test
    public void remove_nullTutorialGroup_throwsNullPointerException() {
        TutorialGroup tutorialGroup = null;
        assertThrows(NullPointerException.class, () -> uniqueTutorialClassList.remove(tutorialGroup));
    }

    @Test
    public void remove_tutorialClassDoesNotExist_throwsStudentNotFoundException() {
        assertThrows(TutorialClassNotFoundException.class, () -> uniqueTutorialClassList.remove(G01));
    }

    @Test
    public void remove_tutorialGroupDoesNotExist_throwsStudentNotFoundException() {
        assertThrows(TutorialGroupNotFoundException.class, () -> uniqueTutorialClassList.remove(TUT_01));
    }

    @Test
    public void remove_existingTutorialClass_removesStudent() {
        uniqueTutorialClassList.add(G01);
        uniqueTutorialClassList.remove(G01);
        UniqueTutorialClassList expectedUniqueTutorialClassList = new UniqueTutorialClassList();
        assertEquals(expectedUniqueTutorialClassList, uniqueTutorialClassList);
    }

    @Test
    public void setTutorialClasses_nullUniqueStudentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueTutorialClassList.setTutorialClasses((UniqueTutorialClassList) null));
    }

    @Test
    public void setStudents_listWithDuplicateTutorialClasses_throwsDuplicateStudentException() {
        List<TutorialClass> listWithDuplicateClasses = Arrays.asList(G01, G01);
        assertThrows(DuplicateTutorialClassException.class, () ->
                uniqueTutorialClassList.setTutorialClasses(listWithDuplicateClasses));
    }

    @Test
    public void equals() {
        assertTrue(uniqueTutorialClassList.equals(uniqueTutorialClassList));

        assertFalse(uniqueTutorialClassList.equals(null));
        assertFalse(uniqueTutorialClassList.equals(1));

        UniqueTutorialClassList copy = new UniqueTutorialClassList();
        copy.add(G01);
        assertFalse(uniqueTutorialClassList.equals(copy));

    }

}
