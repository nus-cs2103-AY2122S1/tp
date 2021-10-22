package seedu.tracker.testutil;

import static seedu.tracker.logic.parser.CliSyntax.PREFIX_CODE;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_MC;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.tracker.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.tracker.logic.commands.AddCommand;
import seedu.tracker.model.module.Module;

/**
 * A utility class for Module.
 */
public class ModuleUtil {

    /**
     * Returns an add command string for adding the {@code module}.
     */
    public static String getAddCommand(Module module) {
        return AddCommand.COMMAND_WORD + " " + getModuleDetails(module);
    }

    /**
     * Returns the part of command string for the given {@code module}'s details.
     */
    public static String getModuleDetails(Module module) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_CODE + module.getCode().value + " ");
        sb.append(PREFIX_TITLE + module.getTitle().value + " ");
        sb.append(PREFIX_DESCRIPTION + module.getDescription().value + " ");
        sb.append(PREFIX_MC + String.format("%s", module.getMc().value) + " ");
        module.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    // The code following related to EditCommand test.
    // /**
    // * Returns the part of command string for the given {@code EditModuleDescriptor}'s details.
    // */
    // public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
    //    StringBuilder sb = new StringBuilder();
    //    descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
    //    descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
    //    descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
    //    descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
    //    if (descriptor.getTags().isPresent()) {
    //        Set<Tag> tags = descriptor.getTags().get();
    //        if (tags.isEmpty()) {
    //            sb.append(PREFIX_TAG);
    //        } else {
    //            tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
    //        }
    //    }
    //    return sb.toString();
    // }
}
