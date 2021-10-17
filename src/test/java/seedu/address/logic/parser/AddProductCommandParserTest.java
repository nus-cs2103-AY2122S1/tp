package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddProductCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ModelManager;
import seedu.address.model.commons.Name;
import seedu.address.model.product.Product;
import seedu.address.model.product.Quantity;
import seedu.address.model.product.UnitPrice;

public class AddProductCommandParserTest {
    private final AddProductCommandParser parser = new AddProductCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_compulsoryFieldsMissing_throwsParseException() {
        // empty args
        assertThrows(ParseException.class, () -> parser.parse(""));
        assertThrows(ParseException.class, () -> parser.parse(" "));

        // name is not provided
        assertThrows(ParseException.class, () -> parser.parse("-$ 15"));
        assertThrows(ParseException.class, () -> parser.parse(" -$ 10"));
        assertThrows(ParseException.class, () -> parser.parse("-$ 15 -q 120"));

        // unit price is not provided
        assertThrows(ParseException.class, () -> parser.parse("random name"));
        assertThrows(ParseException.class, () -> parser.parse("random name -$"));
        assertThrows(ParseException.class, () -> parser.parse("random name -$ "));
        assertThrows(ParseException.class, () -> parser.parse("random name -p 10"));
        assertThrows(ParseException.class, () -> parser.parse("name -$ -q 15"));
        assertThrows(ParseException.class, () -> parser.parse("name -q 300"));
    }

    @Test
    public void parse_invalidAttributes_throwsParseException() {
        // invalid unit price
        assertThrows(ParseException.class, () -> parser.parse("name -$ unit price"));
        assertThrows(ParseException.class, () -> parser.parse(
                "name -$ price -q 100"));

        // invalid quantity
        assertThrows(ParseException.class, () -> parser.parse("name -$ 10.00 -q -10"));
        assertThrows(ParseException.class, () -> parser.parse("name -$ 5 -q 10.5"));
    }

    @Test
    public void parse_allFieldsPresent_success() {
        Name name = new Name("pen");
        UnitPrice unitPrice = new UnitPrice("10");
        Quantity quantity = new Quantity("150");
        AddProductCommand.AddProductDescriptor descriptor =
                new AddProductCommand.AddProductDescriptor(name, unitPrice);

        // name and unit price are provided
        testValidAttributes("pen -$ 10", descriptor);

        // name, unit price and quantity are provided
        descriptor.setQuantity(quantity);
        testValidAttributes("pen -$ 10 -q 150", descriptor);
    }

    private void testValidAttributes(String args, AddProductCommand.AddProductDescriptor descriptor) {
        try {
            CommandResult actualResult = parser.parse(args).execute(new ModelStub());
            CommandResult expectedResult = new AddProductCommand(descriptor).execute(new ModelStub());
            // compare the feedback to user excluding the id.
            String actualString = actualResult.getFeedbackToUser();
            actualString = actualString.substring(actualString.indexOf("Name"));
            String expectedString = expectedResult.getFeedbackToUser();
            expectedString = expectedString.substring(expectedString.indexOf("Name"));
            assertEquals(expectedString, actualString);
            assertEquals(expectedResult.isShowHelp(), actualResult.isShowHelp());
            assertEquals(expectedResult.isExit(), actualResult.isExit());
        } catch (ParseException | CommandException e) {
            fail();
        }
    }

    public static class ModelStub extends ModelManager {
        /**
         * Assume there are no duplicate products, always return false.
         *
         * @param product the product to be checked.
         * @return False.
         */
        @Override
        public boolean hasProduct(Product product) {
            return false;
        }
    }
}
