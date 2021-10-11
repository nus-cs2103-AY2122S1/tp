package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.moduleclass.ModuleClass;

/**
 * A utility class containing a list of {@code ModuleClass} objects to be used in tests.
 */
public class TypicalModuleClasses {

    private TypicalModuleClasses() {} //prevents instantiation

    public static final ModuleClass CS2100_LAB1 = new ModuleClassBuilder()
            .withModuleCode("CS2100")
            .withDay("2")
            .withTime("15:00")
            .withRemark("COM1 0113")
            .build();

    public static final ModuleClass CS2103_TUT1 = new ModuleClassBuilder()
            .withModuleCode("CS2103")
            .withDay("4")
            .withTime("09:00")
            .withRemark("online")
            .build();

    public static final ModuleClass CS2100_TUT1 = new ModuleClassBuilder()
            .withModuleCode("CS2100")
            .withDay("3")
            .withTime("17:00")
            .withRemark("COM1 01-20")
            .build();

    /**
     * Returns an {@code AddressBook} with all the typical classes.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (ModuleClass moduleClass : getTypicalModuleClasses()) {
            ab.addClass(moduleClass);
        }
        return ab;
    }

    public static List<ModuleClass> getTypicalModuleClasses() {
        return new ArrayList<>(Arrays.asList(CS2100_LAB1, CS2100_TUT1, CS2103_TUT1));
    }
}
