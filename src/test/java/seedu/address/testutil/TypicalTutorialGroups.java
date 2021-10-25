package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G101;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G102;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPTYPE_OP1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPTYPE_OP2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Classmate;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * A utility class containing a list of {@code TutorialGroup} objects to be used in tests.
 */
public class TypicalTutorialGroups {
    public static final TutorialGroup G101_OP1_1 = new TutorialGroupBuilder().withClassCode(VALID_CLASSCODE_G101)
            .withGroupName(VALID_GROUPNAME_1).withGroupType(VALID_GROUPTYPE_OP1).build();
    public static final TutorialGroup G102_OP2_2 = new TutorialGroupBuilder().withClassCode(VALID_CLASSCODE_G102)
            .withGroupName(VALID_GROUPNAME_2).withGroupType(VALID_GROUPTYPE_OP2).build();

    // Prevent instantiation
    private TypicalTutorialGroups() {}

    /**
     * Returns an {@code Classmate} with all the typical tutorial gruops.
     */
    public static Classmate getTypicalClassmate() {
        Classmate ab = new Classmate();
        for (TutorialGroup tutorialGroup : getTypicalTutorialGroups()) {
            ab.addTutorialGroup(tutorialGroup);
        }
        return ab;
    }

    public static List<TutorialGroup> getTypicalTutorialGroups() {
        return new ArrayList<>(Arrays.asList(G101_OP1_1, G102_OP2_2));
    }

}