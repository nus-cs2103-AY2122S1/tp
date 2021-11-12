package seedu.notor.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.notor.model.common.Name;
import seedu.notor.model.group.SubGroup;

public class TypicalSubGroups {
    public static final SubGroup ORBITAL_GROUP1 = new SubGroup(new Name("Group1"), null, "Orbital");

    public static final List<SubGroup> getSubGroups() {
        ArrayList<SubGroup> temp = new ArrayList<>();
        temp.add(ORBITAL_GROUP1);
        return temp;
    }
}

