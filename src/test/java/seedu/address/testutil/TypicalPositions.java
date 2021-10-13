package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.PositionBook;
import seedu.address.model.person.Person;
import seedu.address.model.position.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DATAENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_DATASCIENTIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DATAENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_DATASCIENTIST;


public class TypicalPositions {

    public static final Position DATAENGINEER = new PositionBuilder().withTitle(VALID_TITLE_DATAENGINEER)
            .withDescription(VALID_DESCRIPTION_DATAENGINEER).build();

    public static final Position DATASCIENTIST = new PositionBuilder().withTitle(VALID_TITLE_DATASCIENTIST)
            .withDescription(VALID_DESCRIPTION_DATASCIENTIST).build();

    private TypicalPositions() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static PositionBook getTypicalPositionBook() {
        PositionBook pb = new PositionBook();
        for (Position position : getTypicalPositions()) {
            pb.addPosition(position);
        }
        return pb;
    }


    public static List<Position> getTypicalPositions() {
        return new ArrayList<>(Arrays.asList(DATAENGINEER, DATASCIENTIST));
    }

}
