package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.module.event.Event;
import seedu.address.model.module.member.Member;

public class SampleDataUtilTest {
    private final ReadOnlyAddressBook sampleAb = SampleDataUtil.getSampleAddressBook();

    @Test
    public void checkMemberOfEventInMemberList() {
        ObservableList<Member> memberList = sampleAb.getMemberList();
        ObservableList<Event> eventList = sampleAb.getEventList();
        for (Event event : eventList) {
            Set<Member> memberSet = event.getParticipants();
            memberSet.forEach(mem -> assertTrue(memberList.contains(mem)));
        }
    }
}
