package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;

import java.util.Set;

import seedu.address.logic.commands.EditCommand.EditMemberDescriptor;
import seedu.address.logic.commands.PaddCommand;
import seedu.address.model.data.member.Member;
import seedu.address.model.position.Position;

/**
 * A utility class for Member.
 */
public class MemberUtil {

    /**
     * Returns an add command string for adding the {@code member}.
     */
    public static String getPaddCommand(Member member) {
        return PaddCommand.COMMAND_WORD + " " + getMemberDetails(member);
    }

    /**
     * Returns the part of command string for the given {@code member}'s details.
     */
    public static String getMemberDetails(Member member) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + member.getName().fullName + " ");
        sb.append(PREFIX_PHONE + member.getPhone().value + " ");
        if (member.getEmail().isPresent()) {
            sb.append(PREFIX_EMAIL + member.getEmail().get().value + " ");
        }
        if (member.getAddress().isPresent()) {
            sb.append(PREFIX_ADDRESS + member.getAddress().get().value + " ");
        }
        member.getPositions().stream().forEach(
            s -> sb.append(PREFIX_POSITION + s.positionName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditMemberDescriptor}'s details.
     */
    public static String getEditMemberDescriptorDetails(EditMemberDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        if (descriptor.getPositions().isPresent()) {
            Set<Position> positions = descriptor.getPositions().get();
            if (positions.isEmpty()) {
                sb.append(PREFIX_POSITION);
            } else {
                positions.forEach(s -> sb.append(PREFIX_POSITION).append(s.positionName).append(" "));
            }
        }
        return sb.toString();
    }
}
