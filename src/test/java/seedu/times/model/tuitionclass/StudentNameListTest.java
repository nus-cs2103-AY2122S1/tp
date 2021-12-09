package seedu.times.model.tuitionclass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.times.model.person.Name;
import seedu.times.model.tuitionclass.exceptions.DuplicateStudentInClassException;

/**
 * Tests for {@code StudentNameList}
 * Contains integration test with {@code Name}
 */
public class StudentNameListTest {
    private Name amelia = new Name("Amelia");
    private Name jake = new Name("Jake");
    private Name peter = new Name("Peter");
    private Name wang = new Name("Wang Lu Xun");
    private Name naomi = new Name("Naomi");
    private Name terry = new Name("Terry");
    private Name guoJun = new Name("Guo Jun");
    private Name yaoMing = new Name("Yao Ming");

    private List<Name> nameList = new ArrayList<>(Arrays.asList(amelia, jake, peter, wang));

    private String[] validNames1 = {"Amelia", "Jake", "Peter", "Wang Lu Xun"};
    private String[] validNames2 = {"Guo Jun", "Naomi", "Terry", "Yao Ming"};
    private String[] validNamesCombined = {"Amelia", "Jake", "Peter", "Wang Lu Xun", "Guo Jun", "Naomi",
        "Terry", "Yao Ming"};
    private StudentNameList studentNameListSampleOne = new StudentNameList(validNames1);
    private StudentNameList studentNameListSampleTwo = new StudentNameList(validNames2);
    private StudentNameList studentNameListSampleCombined = new StudentNameList(validNamesCombined);

    @Test void equals() {
        String[] validNames3 = {"Jake", "Amelia", "Peter", "Wang Lu Xun"};

        //same obj returns true
        assertTrue(studentNameListSampleOne.equals(studentNameListSampleOne));

        //list of same name returns true
        assertTrue(studentNameListSampleOne.equals(new StudentNameList(validNames1)));

        //different list returns false
        assertFalse(studentNameListSampleOne.equals(studentNameListSampleTwo));

        //same list with different order returns false
        assertFalse(studentNameListSampleOne.equals(new StudentNameList(validNames3)));
    }

    @Test
    public void contains() {
        //null
        assertThrows(NullPointerException.class, () -> studentNameListSampleOne.contains(null));

        //contains returns true
        assertTrue(studentNameListSampleOne.contains(amelia));
        assertTrue(studentNameListSampleOne.contains(jake));
        assertTrue(studentNameListSampleTwo.contains(guoJun));
        assertTrue(studentNameListSampleTwo.contains(naomi));

        //not contain returns false
        assertFalse(studentNameListSampleOne.contains(yaoMing));
        assertFalse(studentNameListSampleOne.contains(terry));
        assertFalse(studentNameListSampleTwo.contains(wang));
        assertFalse(studentNameListSampleTwo.contains(peter));
    }

    @Test
    public void add() {
        StudentNameList studentList = new StudentNameList(validNames1);

        //null
        assertThrows(NullPointerException.class, () -> studentList.add(null));

        //duplicate
        assertThrows(DuplicateStudentInClassException.class, () -> studentList.add(jake));

        //non-duplicate adds successfully
        studentList.add(guoJun);
        studentList.add(naomi);
        studentList.add(terry);
        studentList.add(yaoMing);
        assertEquals(studentList, studentNameListSampleCombined);
    }

    @Test
    public void addAll() {
        //duplicates are not added
        StudentNameList studentList = new StudentNameList(validNames1);
        studentList.addAll(studentNameListSampleOne);
        assertEquals(studentList, studentNameListSampleOne);

        //new names are added successfully
        StudentNameList studentList2 = new StudentNameList(validNames2);
        studentList.addAll(studentList2);
        assertEquals(studentList, studentNameListSampleCombined);
    }

    @Test
    public void remove() {
        StudentNameList studentList = new StudentNameList(validNamesCombined);

        //remove a name that doesn't exists does nothing
        studentList.remove(new Name("Maurice"));
        assertEquals(studentList, studentNameListSampleCombined);

        //remove names that exist removes them successfully
        studentList.remove(amelia);
        studentList.remove(jake);
        studentList.remove(peter);
        studentList.remove(wang);
        assertEquals(studentList, studentNameListSampleTwo);
    }

    @Test
    public void removeAll() {
        StudentNameList studentList = new StudentNameList(validNamesCombined);

        //remove names that don't exists does nothing
        studentList.removeAll(Arrays.asList(new Name("James"), new Name("Kobe")));
        assertEquals(studentList, studentNameListSampleCombined);

        //remove names that exist removes them successfully
        studentList.removeAll(Arrays.asList(peter, wang, jake, amelia));
        assertEquals(studentList, studentNameListSampleTwo);
    }
}
