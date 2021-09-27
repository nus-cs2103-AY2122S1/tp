package seedu.address.model.person;

import java.util.ArrayList;

public class Attendance {

    public final ArrayList<Integer> value;

    public Attendance() {
        value = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            value.add(0);
        }
    }

}
