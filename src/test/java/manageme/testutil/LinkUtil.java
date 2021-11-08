package manageme.testutil;

import static manageme.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static manageme.logic.parser.CliSyntax.PREFIX_MODULE;
import static manageme.logic.parser.CliSyntax.PREFIX_NAME;

import manageme.logic.commands.link.AddLinkCommand;
import manageme.logic.commands.link.EditLinkCommand.EditLinkDescriptor;
import manageme.model.link.Link;

/**
 * A utility class for Link.
 */
public class LinkUtil {

    /**
     * Returns an add command string for adding the {@code link}.
     */
    public static String getAddLinkCommand(Link link) {
        return AddLinkCommand.COMMAND_WORD + " " + getLinkDetails(link);
    }

    /**
     * Returns the part of command string for the given {@code link}'s details.
     */
    public static String getLinkDetails(Link link) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + link.getName().value + " ");
        sb.append(PREFIX_ADDRESS + link.getAddress().value + " ");
        sb.append(PREFIX_MODULE + link.getLinkModule().value + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditLinkDescriptor}'s details.
     */
    public static String getEditLinkDescriptorDetails(EditLinkDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getLinkModule().ifPresent(module -> sb.append(PREFIX_MODULE).append(module.value).append(" "));
        return sb.toString();
    }
}
