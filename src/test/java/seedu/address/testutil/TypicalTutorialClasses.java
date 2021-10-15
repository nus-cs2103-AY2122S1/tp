package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G102;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_G101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_G102;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Classmate;
import seedu.address.model.tutorialclass.TutorialClass;

/**
 * A utility class containing a list of {@code TutorialClass} objects to be used in tests.
 */
public class TypicalTutorialClasses {
    public static final TutorialClass G101 = new TutorialClassBuilder().withClassCode(VALID_CLASSCODE_G101)
            .withSchedule(VALID_SCHEDULE_G101).build();
    public static final TutorialClass G102 = new TutorialClassBuilder().withClassCode(VALID_CLASSCODE_G102)
            .withSchedule(VALID_SCHEDULE_G102).build();

    // Prevent instantiation
    private TypicalTutorialClasses() {}

    /**
     * Returns an {@code Classmate} with all the typical students.
     */
    public static Classmate getTypicalClassmate() {
        Classmate ab = new Classmate();
        for (TutorialClass tutorialClass : getTypicalTutorialClasses()) {
            ab.addTutorialClass(tutorialClass);
        }
        return ab;
    }

    public static List<TutorialClass> getTypicalTutorialClasses() {
        return new ArrayList<>(Arrays.asList(G101, G102));
    }

}
