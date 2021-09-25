package seedu.address.model.participant;

import java.util.HashMap;
import java.util.Locale;

public class ParticipantId {

    private String id;

    private ParticipantId(Participant p) {
        this.id = encode(p);
    }

    public static ParticipantId of(Participant p) {
        return new ParticipantId(p);
    }

    // simple encoding
    public static String encode(Participant p) {
        // may change in the future
        String[] sections = p.getName().fullName.trim().split(" ");
        if (sections.length == 1) {
            String name = sections[0];
            return name.length() < 6 ? name.toLowerCase() : name.substring(0, 6).toLowerCase();
        } else {
            String first = sections[0];
            String last = sections[sections.length - 1];
            String firstPart = first.length() < 3 ? first : first.substring(0, 3);
            String lastPart = last.length() < 3 ? last : last.substring(0, 3);
            return firstPart.toLowerCase() + lastPart.toLowerCase();
        }
    }

    @Override
    public String toString() {
        return this.id;
    }
}

