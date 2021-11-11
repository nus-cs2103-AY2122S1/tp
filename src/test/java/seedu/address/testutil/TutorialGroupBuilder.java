package seedu.address.testutil;

import seedu.address.model.student.ClassCode;
import seedu.address.model.tutorialgroup.GroupNumber;
import seedu.address.model.tutorialgroup.GroupType;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class TutorialGroupBuilder {

    public static final String DEFAULT_CLASSCODE = "G06";
    public static final String DEFAULT_GROUPNUMBER = "1";
    public static final String DEFAULT_GROUPTYPE = "OP1";

    private ClassCode classCode;
    private GroupNumber groupNumber;
    private GroupType groupType;

    /**
     * Creates a {@code TutorialClassBuilder} with the default details.
     */
    public TutorialGroupBuilder() {
        classCode = new ClassCode(DEFAULT_CLASSCODE);
        groupNumber = new GroupNumber(DEFAULT_GROUPNUMBER);
        groupType = new GroupType(DEFAULT_GROUPTYPE);
    }

    /**
     * Initializes the TutorialClassBuilder with the data of {@code tutorialCLassToCopy}.
     */
    public TutorialGroupBuilder(TutorialGroup tutorialGroupToCopy) {
        classCode = tutorialGroupToCopy.getClassCode();
        groupNumber = tutorialGroupToCopy.getGroupNumber();
        groupType = tutorialGroupToCopy.getGroupType();
    }

    /**
     * Sets the {@code ClassCode} of the {@code TutorialClass} that we are building.
     */
    public TutorialGroupBuilder withClassCode(String classCode) {
        this.classCode = new ClassCode(classCode);
        return this;
    }

    /**
     * Sets the {@code ClassCode} of the {@code TutorialClass} that we are building.
     */
    public TutorialGroupBuilder withGroupNumber(String groupNumber) {
        this.groupNumber = new GroupNumber(groupNumber);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code TutorialClass} that we are building.
     */
    public TutorialGroupBuilder withGroupType(String groupType) {
        this.groupType = new GroupType(groupType);
        return this;
    }

    public TutorialGroup build() {
        return new TutorialGroup(groupNumber, classCode, groupType);
    }
}
