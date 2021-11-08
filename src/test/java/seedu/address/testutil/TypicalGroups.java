package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_DESCRIPTION_CS1101S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_DESCRIPTION_CS1231S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_DESCRIPTION_CS2030S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_DESCRIPTION_CS2040S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_CS1101S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_CS1231S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_CS2030S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_NAME_CS2040S;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.group.Group;

/**
 * A utility class containing a list of {@code Group} objects to be used in tests.
 */
public class TypicalGroups {
    public static final String TYPICAL_GROUP_CS2103T_NAME = "CS2103T";
    public static final String TYPICAL_GROUP_CS2103T_DESC = "A Software engineering module";

    public static final String TYPICAL_GROUP_CS2101_NAME = "CS2101";
    public static final String TYPICAL_GROUP_CS2101_DESC = "An effective communication module";

    public static final Group TYPICAL_GROUP_CS2103T = new GroupBuilder()
            .withGroupName(TYPICAL_GROUP_CS2103T_NAME).withDescription(TYPICAL_GROUP_CS2103T_DESC)
            .withTypicalStudents().build();

    public static final Group TYPICAL_GROUP_CS2101 = new GroupBuilder()
            .withGroupName(TYPICAL_GROUP_CS2101_NAME).withDescription(TYPICAL_GROUP_CS2101_DESC).build();

    public static final Group CS1231S = new GroupBuilder().withGroupName(VALID_GROUP_NAME_CS1231S)
            .withDescription(VALID_GROUP_DESCRIPTION_CS1231S).withSampleStudents().build();
    public static final Group CS1101S = new GroupBuilder().withGroupName(VALID_GROUP_NAME_CS1101S)
            .withDescription(VALID_GROUP_DESCRIPTION_CS1101S).withSampleStudents().build();
    public static final Group CS2030S = new GroupBuilder().withGroupName(VALID_GROUP_NAME_CS2030S)
            .withDescription(VALID_GROUP_DESCRIPTION_CS2030S).withSampleStudents().build();
    public static final Group CS2040S = new GroupBuilder().withGroupName(VALID_GROUP_NAME_CS2040S)
            .withDescription(VALID_GROUP_DESCRIPTION_CS2040S).withSampleStudents().build();

    private TypicalGroups() {} // prevents instantiation

    public static List<Group> getTypicalGroups() {
        return new ArrayList<>(Arrays.asList(
                new GroupBuilder(TYPICAL_GROUP_CS2103T).build(),
                new GroupBuilder(TYPICAL_GROUP_CS2101).build(),
                new GroupBuilder(CS1231S).build(),
                new GroupBuilder(CS1101S).build(),
                new GroupBuilder(CS2030S).build(),
                new GroupBuilder(CS2040S).build()));
    }
}
