package seedu.address.testutil;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.logic.commands.AddStudentToGroupCommand;
import seedu.address.model.tutorialgroup.TutorialGroup;

import static seedu.address.logic.parser.CliSyntax.*;

public class TutorialGroupUtil {
    /**
     * Returns an add group command string for adding the {@code TutorialGroup}.
     */
    public static String getAddGroupCommand(TutorialGroup tutorialGroup) {
        return AddGroupCommand.COMMAND_WORD + " " + getTutorialGroupDetails(tutorialGroup);
    }

    public static String getAddStudentToGroupCommand(TutorialGroup tutorialGroup, Index index) {
        return AddStudentToGroupCommand.COMMAND_WORD + " " + index.getOneBased() + " " + getTutorialGroupDetails(tutorialGroup);
    }

    public static String getTutorialGroupDetails(TutorialGroup tutorialGroup) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_CLASSCODE + tutorialGroup.getClassCode().value + " ");
        sb.append(PREFIX_GROUPNUMBER + tutorialGroup.getGroupNumber().value + " ");
        sb.append(PREFIX_TYPE + tutorialGroup.getGroupType().value + " ");
        return sb.toString();
    }
}
