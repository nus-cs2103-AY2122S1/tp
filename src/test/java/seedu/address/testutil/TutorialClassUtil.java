package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.AddClassCommand;
import seedu.address.model.tutorialclass.TutorialClass;

public class TutorialClassUtil {

    /**
     * Returns an add class command string for adding the {@code TutorialClass}.
     */
    public static String getAddClassCommand(TutorialClass tutorialClass) {
        return AddClassCommand.COMMAND_WORD + " " + getTutorialClassDetails(tutorialClass);
    }

    public static String getTutorialClassDetails(TutorialClass tutorialClass) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_CLASSCODE + tutorialClass.getClassCode().value + " ");
        sb.append(PREFIX_SCHEDULE + tutorialClass.getSchedule().value + " ");
        tutorialClass.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }
}
