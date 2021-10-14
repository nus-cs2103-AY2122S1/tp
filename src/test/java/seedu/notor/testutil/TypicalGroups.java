package seedu.notor.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.notor.model.group.SuperGroup;

public class TypicalGroups {
    public static final SuperGroup ORBITAL = new SuperGroup("Orbital");
    public static final SuperGroup CS2103 = new SuperGroup("CS2103");


    public static final List<SuperGroup> getSuperGroups() {
        if (ORBITAL.findSubGroup("Group1") == null) {
            ORBITAL.addSubGroup(TypicalSubGroups.ORBITAL_GROUP1);
        }
        ArrayList<SuperGroup> temp = new ArrayList<>();
        temp.add(ORBITAL);
        temp.add(CS2103);
        return temp;
    }
}
