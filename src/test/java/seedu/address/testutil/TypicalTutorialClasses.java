package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G01;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G02;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G06;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_G01;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_G02;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_G06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Classmate;
import seedu.address.model.tutorialclass.TutorialClass;

/**
 * A utility class containing a list of {@code TutorialClass} objects to be used in tests.
 */
public class TypicalTutorialClasses {
    public static final TutorialClass G01 = new TutorialClassBuilder().withClassCode(VALID_CLASSCODE_G01)
            .withSchedule(VALID_SCHEDULE_G01).build();
    public static final TutorialClass G02 = new TutorialClassBuilder().withClassCode(VALID_CLASSCODE_G02)
            .withSchedule(VALID_SCHEDULE_G02).build();
    public static final TutorialClass G06 = new TutorialClassBuilder().withClassCode(VALID_CLASSCODE_G06)
            .withSchedule(VALID_SCHEDULE_G06).build();

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
        return new ArrayList<>(Arrays.asList(G01, G02, G06));
    }

}
