package seedu.notor.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.notor.model.common.Name;
import seedu.notor.model.group.SuperGroup;

public class TypicalGroups {
    public static List<SuperGroup> getSuperGroups() {
        SuperGroup orbital = new SuperGroup(new Name("Orbital"));
        SuperGroup cs2103 = new SuperGroup(new Name("CS2103"));
        if (orbital.findSubGroup("Group1") == null) {
            orbital.addSubGroup(TypicalSubGroups.ORBITAL_GROUP1);
        }
        ArrayList<SuperGroup> temp = new ArrayList<>();
        temp.add(orbital);
        temp.add(cs2103);
        return temp;
    }
}
