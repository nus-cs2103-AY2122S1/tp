package seedu.address.model.alias;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AliasMapTest {

    @Test
    public void convertAliasesIfPresent_present_success() {
        AliasMap aliases = new AliasMap();
        aliases.add(new Alias(new Shortcut("lf"), new CommandWord("listf")));
        assertEquals("listf", aliases.convertAliasIfPresent("lf"));
    }

    @Test
    public void convertAliasesIfPresent_notPresent_success() {
        AliasMap aliases = new AliasMap();
        assertEquals("listf", aliases.convertAliasIfPresent("listf"));
    }

    @Test
    public void convertAliasesIfPresent_multiplePresent_success() {
        AliasMap aliases = new AliasMap();
        aliases.add(new Alias(new Shortcut("lf"), new CommandWord("listf")));
        aliases.add(new Alias(new Shortcut("list"), new CommandWord("listf")));

        assertEquals("listf", aliases.convertAliasIfPresent("lf"));
        assertEquals("listf", aliases.convertAliasIfPresent("list"));
    }
}
