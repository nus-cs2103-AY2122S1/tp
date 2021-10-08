package seedu.anilist.testutil;

import static seedu.anilist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.anilist.logic.commands.AddCommand;
import seedu.anilist.logic.commands.EditCommand;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.tag.Tag;

/**
 * A utility class for Anime.
 */
public class AnimeUtil {

    /**
     * Returns an add command string for adding the {@code anime}.
     */
    public static String getAddCommand(Anime anime) {
        return AddCommand.COMMAND_WORD + " " + getAnimeDetails(anime);
    }

    /**
     * Returns the part of command string for the given {@code anime}'s details.
     */
    public static String getAnimeDetails(Anime anime) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + anime.getName().fullName + " ");
        anime.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditAnimeDescriptor}'s details.
     */
    public static String getEditAnimeDescriptorDetails(EditCommand.EditAnimeDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
