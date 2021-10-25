package seedu.modulink.testutil;

import static seedu.modulink.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_GITHUB_USERNAME;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.modulink.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;

import java.util.Set;

import seedu.modulink.logic.commands.CreateCommand;
import seedu.modulink.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.modulink.model.person.Person;
import seedu.modulink.model.tag.Mod;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getCreateCommand(Person person) {
        return CreateCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_ID + person.getStudentId().value + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_GITHUB_USERNAME + person.getGithubUsername().value + " ");
        sb.append(PREFIX_TELEGRAM_HANDLE + person.getTelegramHandle().value + " ");
        person.getMods().stream().forEach(
            s -> sb.append(PREFIX_MOD + s.modName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getStudentId().ifPresent(studentId -> sb.append(PREFIX_ID).append(studentId.value).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getGitHubUsername().ifPresent(gitHubUsername -> sb.append(PREFIX_GITHUB_USERNAME)
                .append(gitHubUsername.value).append(" "));
        descriptor.getTelegramHandle().ifPresent(telegramHandle -> sb.append(PREFIX_TELEGRAM_HANDLE)
                .append(telegramHandle.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Mod> mods = descriptor.getTags().get();
            if (mods.isEmpty()) {
                sb.append(PREFIX_MOD);
            } else {
                mods.forEach(s -> sb.append(PREFIX_MOD).append(s.modName).append(" "));
            }
        }
        return sb.toString();
    }
}
