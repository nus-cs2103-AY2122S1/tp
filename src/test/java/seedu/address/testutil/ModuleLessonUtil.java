package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.address.logic.commands.modulelesson.EditModuleLessonCommand;
import seedu.address.model.modulelesson.ModuleLesson;

/**
 * A utility class for Module Lesson.
 */
public class ModuleLessonUtil {

    /**
     * Returns the part of command string for the given {@code moduleLesson}'s details.
     */
    public static String getModuleLessonDetails(ModuleLesson moduleLesson) {
        StringBuilder sb = new StringBuilder();
        String dayAsInt = moduleLesson.getDay().getDayAsIntString();

        sb.append(PREFIX_MODULE_CODE).append(moduleLesson.getModuleCode().toString()).append(" ");
        sb.append(PREFIX_LESSON_DAY).append(dayAsInt).append(" ");
        sb.append(PREFIX_LESSON_TIME).append(moduleLesson.getLessonStartTime().toString()).append(" ")
                .append(moduleLesson.getLessonEndTime().toString()).append(" ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditModuleLessonDescriptor}'s details.
     */
    public static String getEditLessonDescriptorDetails(EditModuleLessonCommand.EditLessonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getModuleCode()
                .ifPresent(moduleCode -> sb.append(PREFIX_MODULE_CODE).append(moduleCode).append(" "));
        descriptor.getLessonDay()
                .ifPresent(lessonDay -> sb.append(PREFIX_LESSON_DAY).append(lessonDay.getDayAsIntString()).append(" "));
        descriptor.getLessonStartTime()
                .ifPresent(lessonTime -> sb.append(PREFIX_LESSON_TIME).append(lessonTime).append(" "));
        descriptor.getLessonEndTime().ifPresent(lessonTime -> sb.append(lessonTime).append(" "));
        descriptor.getRemark().ifPresent(remark -> sb.append(PREFIX_REMARK).append(remark.value).append(" "));
        return sb.toString();
    }
}
