package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class SetRoleReqCommandTest {

    @Test
    public void equals() {
        // Test same elements in set
        Set<String> roleReqSet1 = new HashSet<>(){};
        roleReqSet1.add("kitchen-1");
        roleReqSet1.add("bartender-1");
        SetRoleReqCommand expectedRoleReqCommand1 = new SetRoleReqCommand(roleReqSet1);
        SetRoleReqCommand expectedRoleReqCommand2 = new SetRoleReqCommand(roleReqSet1);
        assertTrue(expectedRoleReqCommand1.equals(expectedRoleReqCommand1));
        assertTrue(expectedRoleReqCommand1.equals(expectedRoleReqCommand2));

        // Different order in roleReqSet
        Set<String> roleReqSet2 = new HashSet<>(){};
        roleReqSet2.add("bartender-1");
        roleReqSet2.add("kitchen-1");
        SetRoleReqCommand expectedRoleReqCommand3 = new SetRoleReqCommand(roleReqSet2);
        assertTrue(expectedRoleReqCommand1.equals(expectedRoleReqCommand3));

        // Different roles in sets
        Set<String> roleReqSet3 = new HashSet<>(){};
        roleReqSet3.add("floor-1");
        roleReqSet3.add("kitchen-1");
        SetRoleReqCommand expectedRoleReqCommand4 = new SetRoleReqCommand(roleReqSet3);
        assertFalse(expectedRoleReqCommand1.equals(expectedRoleReqCommand4));

        // Different numbers in sets
        Set<String> roleReqSet4 = new HashSet<>(){};
        roleReqSet3.add("floor-2");
        roleReqSet3.add("kitchen-1");
        SetRoleReqCommand expectedRoleReqCommand5 = new SetRoleReqCommand(roleReqSet4);
        assertFalse(expectedRoleReqCommand5.equals(expectedRoleReqCommand4));
    }
}
