package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ReserveCommand;
import seedu.address.model.person.Phone;

class ReserveCommandParserTest {
    private ReserveCommandParser parser = new ReserveCommandParser();

    @Test
    public void parse_allFieldPresent_success() {
        ReserveCommand expected = new ReserveCommand(
                new Phone("98765432"),
                2,
                LocalDateTime.parse("11/11/2021 2030", DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));

        assertParseSuccess(parser, "2 p/98765432 at/11/11/2021 2030", expected);
    }
}
