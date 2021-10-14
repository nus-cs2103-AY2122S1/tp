package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ZOOM;

import java.util.Set;

import seedu.address.logic.commands.contact.CAddCommand;
import seedu.address.logic.commands.contact.CEditCommand.EditContactDescriptor;
import seedu.address.model.contact.Contact;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Contact.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code contact}.
     */
    public static String getCAddCommand(Contact contact) {
        return CAddCommand.COMMAND_WORD + " " + getPersonDetails(contact);
    }

    /**
     * Returns the part of command string for the given {@code contact}'s details.
     */
    public static String getPersonDetails(Contact contact) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + contact.getName().fullName + " ");
        sb.append(PREFIX_PHONE + contact.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + contact.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + contact.getAddress().value + " ");
        sb.append(PREFIX_TELEGRAM + contact.getTelegramHandle().handle + " ");
        sb.append(PREFIX_ZOOM + contact.getZoomLink().link + " ");
        contact.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditContactDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditContactDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getZoomLink().ifPresent(zoom -> sb.append(PREFIX_ZOOM).append(zoom.link).append(" "));
        descriptor.getTelegramHandle().ifPresent(telegramHandle -> sb.append(PREFIX_TELEGRAM)
            .append(telegramHandle.handle).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (!tags.isEmpty()) {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        if (descriptor.getTagsToDelete().isPresent()) {
            Set<Tag> tagsToDelete = descriptor.getTagsToDelete().get();
            if (!tagsToDelete.isEmpty()) {
                tagsToDelete.forEach(s -> sb.append(PREFIX_DELETE_TAG).append(s.tagName).append(" "));
            }
        }
        if (descriptor.isShouldDeleteAllTags()) {
            sb.append(PREFIX_DELETE_TAG).append("* ");
        }
        return sb.toString();
    }
}
