package nustracker.logic.parser;

import static nustracker.logic.commands.CommandTestUtil.INVALID_EXPORT_FILE_LENGTH;
import static nustracker.logic.commands.CommandTestUtil.INVALID_EXPORT_FILE_NAME_1;
import static nustracker.logic.commands.CommandTestUtil.INVALID_EXPORT_FILE_NAME_2;
import static nustracker.logic.commands.CommandTestUtil.INVALID_EXPORT_FILE_NAME_3;
import static nustracker.logic.commands.CommandTestUtil.INVALID_EXPORT_FILE_NAME_4;
import static nustracker.logic.commands.CommandTestUtil.INVALID_EXPORT_FILE_NAME_5;
import static nustracker.logic.commands.CommandTestUtil.INVALID_EXPORT_FILE_NAME_6;
import static nustracker.logic.commands.CommandTestUtil.INVALID_EXPORT_FILE_NAME_7;
import static nustracker.logic.commands.CommandTestUtil.INVALID_EXPORT_FILE_NAME_8;

import org.junit.jupiter.api.Test;

import nustracker.logic.parser.exceptions.ParseException;
import nustracker.testutil.Assert;

class ExportCommandParserTest {

    @Test
    void parse_invalidCharacter1_throwsParseException() {
        Assert.assertThrows(ParseException.class, () ->
                new ExportCommandParser().parse(INVALID_EXPORT_FILE_NAME_1));
    }

    @Test
    void parse_invalidCharacter2_throwsParseException() {
        Assert.assertThrows(ParseException.class, () ->
                new ExportCommandParser().parse(INVALID_EXPORT_FILE_NAME_2));
    }

    @Test
    void parse_invalidCharacter3_throwsParseException() {
        Assert.assertThrows(ParseException.class, () ->
                new ExportCommandParser().parse(INVALID_EXPORT_FILE_NAME_3));
    }

    @Test
    void parse_invalidCharacter4_throwsParseException() {
        Assert.assertThrows(ParseException.class, () ->
                new ExportCommandParser().parse(INVALID_EXPORT_FILE_NAME_4));
    }

    @Test
    void parse_invalidCharacter5_throwsParseException() {
        Assert.assertThrows(ParseException.class, () ->
                new ExportCommandParser().parse(INVALID_EXPORT_FILE_NAME_5));
    }

    @Test
    void parse_invalidCharacter6_throwsParseException() {
        Assert.assertThrows(ParseException.class, () ->
                new ExportCommandParser().parse(INVALID_EXPORT_FILE_NAME_6));
    }

    @Test
    void parse_invalidCharacter7_throwsParseException() {
        Assert.assertThrows(ParseException.class, () ->
                new ExportCommandParser().parse(INVALID_EXPORT_FILE_NAME_7));
    }

    @Test
    void parse_invalidCharacter8_throwsParseException() {
        Assert.assertThrows(ParseException.class, () ->
                new ExportCommandParser().parse(INVALID_EXPORT_FILE_NAME_8));
    }

    @Test
    void parse_invalidExportFileLength_throwsParseException() {
        Assert.assertThrows(ParseException.class, () ->
                new ExportCommandParser().parse(INVALID_EXPORT_FILE_LENGTH));
    }
}
