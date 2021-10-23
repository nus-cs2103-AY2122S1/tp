package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.modulelesson.ModuleLesson;

/**
 * A utility class containing a list of {@code ModuleLesson} objects to be used in tests.
 */
public class TypicalModuleLessons {

    public static final ModuleLesson CS2100_LAB1 = new ModuleLessonBuilder()
            .withModuleCode("CS2100 B31")
            .withLessonDay("2")
            .withLessonTime("15:00")
            .withRemark("COM1 0113")
            .build();
    public static final ModuleLesson CS2103_TUT1 = new ModuleLessonBuilder()
            .withModuleCode("CS2103 T09")
            .withLessonDay("4")
            .withLessonTime("09:00")
            .withRemark("online")
            .build();
    public static final ModuleLesson CS2106_TUT1 = new ModuleLessonBuilder()
            .withModuleCode("CS2106 T18")
            .withLessonDay("3")
            .withLessonTime("17:00")
            .withRemark("COM1 01-20")
            .build();

    private TypicalModuleLessons() {} //prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical classes.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (ModuleLesson moduleLesson : getTypicalModuleClasses()) {
            ab.addLesson(moduleLesson);
        }
        return ab;
    }

    public static List<ModuleLesson> getTypicalModuleClasses() {
        return new ArrayList<>(Arrays.asList(CS2100_LAB1, CS2103_TUT1, CS2106_TUT1));
    }
}
