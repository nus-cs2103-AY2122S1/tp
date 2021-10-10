package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.group.SubGroup;
import seedu.address.model.group.SuperGroup;

public class TypicalGroups {
    public static final SuperGroup ORBITAL = new SuperGroup("Orbital");
    public static final SuperGroup CS2103 = new SuperGroup("CS2103");
    public static final SubGroup ORBITAL_GROUP1 = new SubGroup("Group1", ORBITAL);

    public static final List<SuperGroup> getSuperGroups() {
        ORBITAL.addSubGroup(ORBITAL_GROUP1);
        ArrayList<SuperGroup> temp = new ArrayList<>();
        temp.add(ORBITAL);
        temp.add(CS2103);
        return temp;
    }

}
