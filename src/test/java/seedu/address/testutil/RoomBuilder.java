package seedu.address.testutil;

import static seedu.address.model.util.SampleDataUtil.getTagSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.room.Room;
import seedu.address.model.room.RoomNumber;
import seedu.address.model.room.Vacancy;
import seedu.address.model.tag.Tag;

public class RoomBuilder {

    public static final String DEFAULT_ROOM_NUMBER = "777";
    public static final Vacancy DEFAULT_VACANCY = Vacancy.VACANT;
    public static final Set<Tag> DEFAULT_TAGS = getTagSet("normal");

    private RoomNumber number;
    private Vacancy vacancy;
    private Set<Tag> tags;

    /**
     * Creates a {@code RoomBuilder} with the default details.
     */
    public RoomBuilder() {
        number = new RoomNumber(DEFAULT_ROOM_NUMBER);
        vacancy = DEFAULT_VACANCY;
        tags = DEFAULT_TAGS;
    }

    /**
     * Initializes the RoomBuilder with the data of {@code roomToCopy}.
     */
    public RoomBuilder(Room roomToCopy) {
        number = roomToCopy.getRoomNumber();
        vacancy = roomToCopy.getVacancy();
        tags = roomToCopy.getTags();
    }

    /**
     * Sets the {@code RoomNumber} of the {@code Room} that we are building.
     */
    public RoomBuilder withNumber(String number) {
        this.number = new RoomNumber(number);
        return this;
    }


    /**
     * Sets the {@code Vacancy} of the {@code Room} that we are building.
     */
    public RoomBuilder withVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
        return this;
    }

    /**
     * Sets the {@code Tags} of the {@code Room} that we are building.
     */
    public RoomBuilder withTags(Tag[] tags) {
        Set<Tag> editedSet = new HashSet<>();
        editedSet.addAll(Arrays.asList(tags.clone()));
        this.tags = editedSet;
        return this;
    }

    public Room build() {
        return new Room(number, vacancy, tags);
    }

}
