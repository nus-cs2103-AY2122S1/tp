package seedu.gamebook.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.gamebook.logic.commands.EditCommand;
import seedu.gamebook.logic.parser.exceptions.ParseException;
import seedu.gamebook.model.gameentry.Amount;
import seedu.gamebook.model.gameentry.DatePlayed;
import seedu.gamebook.model.gameentry.Duration;
import seedu.gamebook.model.gameentry.EndAmount;
import seedu.gamebook.model.gameentry.GameEntry;
import seedu.gamebook.model.gameentry.GameType;
import seedu.gamebook.model.gameentry.Location;
import seedu.gamebook.model.gameentry.StartAmount;
import seedu.gamebook.model.tag.Tag;

public class EditGameEntryDescriptorBuilder {

    private EditCommand.EditGameEntryDescriptor descriptor;


    public EditGameEntryDescriptorBuilder() {
        descriptor = new EditCommand.EditGameEntryDescriptor();
    }

    public EditGameEntryDescriptorBuilder(EditCommand.EditGameEntryDescriptor descriptor) {
        this.descriptor = new EditCommand.EditGameEntryDescriptor(descriptor);
    }

    /**
     * Initializes the GameEntryBuilder with the data of {@code gameEntryToCopy}.
     */
    public EditGameEntryDescriptorBuilder(GameEntry gameEntryToCopy) {
        descriptor = new EditCommand.EditGameEntryDescriptor();
        descriptor.setGameType(gameEntryToCopy.getGameType());
        descriptor.setStartAmount(gameEntryToCopy.getStartAmount());
        descriptor.setEndAmount(gameEntryToCopy.getEndAmount());
        descriptor.setDate(gameEntryToCopy.getDate());
        descriptor.setDuration(gameEntryToCopy.getDuration());
        descriptor.setLocation(gameEntryToCopy.getLocation());
        descriptor.setTags(gameEntryToCopy.getTags());
    }

    /**
     * Sets the {@code GameType} of the {@code GameEntry} that we are building.
     */
    public EditGameEntryDescriptorBuilder withGameType(GameType gameType) {
        descriptor.setGameType(gameType);
        return this;
    }

    /**
     * Sets the profit of the {@code GameEntry} that we are building.
     */
    public EditGameEntryDescriptorBuilder withProfit(Amount profit) {
        descriptor.setStartAmount(new StartAmount("0"));
        descriptor.setEndAmount(new EndAmount(profit.getAmount()));
        return this;
    }

    /**
     * Sets the {@code DatePlayed} of the {@code GameEntry} that we are building.
     *
     * @throws ParseException if the given {@code datePlayed} is invalid.
     */
    public EditGameEntryDescriptorBuilder withDatePlayed (DatePlayed datePlayed) {
        descriptor.setDate(datePlayed);
        return this;
    }

    /**
     * Sets the duration of the {@code GameEntry} that we are building.
     *
     */
    public EditGameEntryDescriptorBuilder withDuration(Duration duration) {
        descriptor.setDuration(duration);
        return this;
    }

    /**
     * Sets the location of the {@code GameEntry} that we are building.
     *
     */
    public EditGameEntryDescriptorBuilder withLocation(Location location) {
        descriptor.setLocation(location);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code GameEntry} that we are building.
     */
    public EditGameEntryDescriptorBuilder withTags(String ... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }


    public EditCommand.EditGameEntryDescriptor build() {
        return descriptor;
    }
}
