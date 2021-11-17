package socialite.testutil;

import java.util.Set;

import socialite.logic.commands.AddCommand;
import socialite.logic.commands.EditCommand;
import socialite.logic.parser.CliSyntax;
import socialite.model.person.Person;
import socialite.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Person person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(CliSyntax.PREFIX_NAME + person.getName().fullName + " ");
        sb.append(CliSyntax.PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(CliSyntax.PREFIX_REMARK + person.getRemark().get() + " ");
        person.getTags().stream().forEach(
            s -> sb.append(CliSyntax.PREFIX_TAG + s.tagName + " ")
        );
        sb.append(CliSyntax.PREFIX_FACEBOOK + person.getFacebook().get() + " ");
        sb.append(CliSyntax.PREFIX_INSTAGRAM + person.getInstagram().get() + " ");
        sb.append(CliSyntax.PREFIX_TELEGRAM + person.getTelegram().get() + " ");
        sb.append(CliSyntax.PREFIX_TIKTOK + person.getTiktok().get() + " ");
        sb.append(CliSyntax.PREFIX_TWITTER + person.getTwitter().get() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditCommand.EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(CliSyntax.PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(CliSyntax.PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getRemark().ifPresent(remark -> sb.append(CliSyntax.PREFIX_REMARK).append(remark.get()).append(" "));

        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(CliSyntax.PREFIX_TAG).append(" ");
            } else {
                tags.forEach(s -> sb.append(CliSyntax.PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        descriptor.getFacebook().ifPresent(facebook -> sb.append(CliSyntax.PREFIX_FACEBOOK)
                .append(facebook.get()).append(" "));
        descriptor.getInstagram().ifPresent(instagram -> sb.append(CliSyntax.PREFIX_INSTAGRAM)
                .append(instagram.get()).append(" "));
        descriptor.getTelegram().ifPresent(telegram -> sb.append(CliSyntax.PREFIX_TELEGRAM)
                .append(telegram.get()).append(" "));
        descriptor.getTwitter().ifPresent(twitter -> sb.append(CliSyntax.PREFIX_TWITTER)
                .append(twitter.get()).append(" "));
        descriptor.getTikTok().ifPresent(tiktok -> sb.append(CliSyntax.PREFIX_TIKTOK)
                .append(tiktok.get()).append(" "));

        return sb.toString();
    }
}
