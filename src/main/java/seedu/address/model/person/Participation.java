package seedu.address.model.person;

import java.util.ArrayList;

public class Participation {

    public final ArrayList<Integer> value;

    public Participation() {
        value = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            value.add(0);
        }
    }
}
