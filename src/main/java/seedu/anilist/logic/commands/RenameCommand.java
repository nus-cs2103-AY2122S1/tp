package seedu.anilist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.anilist.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.anilist.model.Model.PREDICATE_SHOW_ALL_ANIME;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.anilist.commons.core.Messages;
import seedu.anilist.commons.core.index.Index;
import seedu.anilist.logic.commands.exceptions.CommandException;
import seedu.anilist.model.Model;
import seedu.anilist.model.anime.Anime;
import seedu.anilist.model.anime.Episode;
import seedu.anilist.model.anime.Name;
import seedu.anilist.model.anime.Status;
import seedu.anilist.model.tag.Tag;

public class RenameCommand extends Command {
    public static final String COMMAND_WORD = "rename";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Rename the anime identified "
        + "by the index number used in the displayed anime list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + PREFIX_NAME + "NAME\n"
        + "Example: " + COMMAND_WORD + " 1 " + PREFIX_NAME + "Hyouka";

    public static final String MESSAGE_RENAME_ANIME_SUCCESS = "Edited Anime name: %1$s";
    public static final String MESSAGE_NOT_RENAMED = "A name should be provided.";
    public static final String MESSAGE_DUPLICATE_ANIME = "This anime already exists in the anime list.";

    private final Index index;
    private final RenameCommand.NameDescriptor nameDescriptor;

    /**
     * @param index of the anime in the filtered anime list to rename
     * @param nameDescriptor name to rename anime with
     */
    public RenameCommand(Index index, RenameCommand.NameDescriptor nameDescriptor) {
        requireNonNull(index);
        requireNonNull(nameDescriptor);

        this.index = index;
        this.nameDescriptor = new RenameCommand.NameDescriptor(nameDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Anime> lastShownList = model.getFilteredAnimeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ANIME_DISPLAYED_INDEX);
        }

        Anime animeToRename = lastShownList.get(index.getZeroBased());
        Anime renamedAnime = createRenamedAnime(animeToRename, nameDescriptor);

        if (!animeToRename.isSameAnime(renamedAnime) && model.hasAnime(renamedAnime)) {
            throw new CommandException(MESSAGE_DUPLICATE_ANIME);
        }

        model.setAnime(animeToRename, renamedAnime);
        model.updateFilteredAnimeList(PREDICATE_SHOW_ALL_ANIME);
        return new CommandResult(String.format(MESSAGE_RENAME_ANIME_SUCCESS, renamedAnime));
    }

    /**
     * Creates and returns a {@code Anime} with the name of {@code animeToRename}
     * edited with {@code nameDescriptor}.
     */
    private static Anime createRenamedAnime(Anime animeToRename, RenameCommand.NameDescriptor nameDescriptor) {
        assert animeToRename != null;

        Name updatedName = nameDescriptor.getName().orElse(animeToRename.getName());
        Episode episode = animeToRename.getEpisode();
        Status status = animeToRename.getStatus();
        Set<Tag> updatedTags = animeToRename.getTags();

        return new Anime(updatedName, episode, status, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RenameCommand)) {
            return false;
        }

        // state check
        RenameCommand e = (RenameCommand) other;
        return index.equals(e.index)
            && nameDescriptor.equals(e.nameDescriptor);
    }

    /**
     * Stores the details to edit the anime with. Each non-empty field value will replace the
     * corresponding field value of the anime.
     */
    public static class NameDescriptor {
        private Name name;

        public NameDescriptor() {}

        /**
         * Copy constructor.
         */
        public NameDescriptor(RenameCommand.NameDescriptor toCopy) {
            setName(toCopy.name);
        }

        /**
         * Returns true if name is changed.
         */
        public boolean isNameUpdated() {
            return name != null;
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof RenameCommand.NameDescriptor)) {
                return false;
            }

            // state check
            RenameCommand.NameDescriptor e = (RenameCommand.NameDescriptor) other;
            return getName().equals(e.getName());
        }
    }
}
