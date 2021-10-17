package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AliasesTest {

    @Test
    public void convertAliasesIfPresent_present_success() {
        Aliases aliases = new Aliases();
        aliases.add("lf", "listf");
        assertEquals("listf", aliases.convertAliasIfPresent("lf"));
    }

    @Test
    public void convertAliasesIfPresent_notPresent_success() {
        Aliases aliases = new Aliases();
        aliases.add("lf", "listf");
        assertEquals("listf", aliases.convertAliasIfPresent("listf"));
    }
}
