package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddClientCommand;
import seedu.address.logic.commands.AddProductCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.PhoneNumber;
import seedu.address.model.commons.ID;
import seedu.address.model.commons.Name;
import seedu.address.model.order.Order;
import seedu.address.model.product.Quantity;

public class AddClientCommandParserTest {
    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        AddClientCommandParser parser = new AddClientCommandParser(new ModelStub());
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_compulsoryFieldsMissing_throwsParseException() {
        AddClientCommandParser parser = new AddClientCommandParser(new ModelStub());

        // empty args
        assertThrows(ParseException.class, () -> parser.parse(""));
        assertThrows(ParseException.class, () -> parser.parse(" "));

        // name is not provided
        assertThrows(ParseException.class, () -> parser.parse("-pn 12345678"));
        assertThrows(ParseException.class, () -> parser.parse(" -pn 42665"));
        assertThrows(ParseException.class, () -> parser.parse("-pn 545465 -e email@email, -a address"));

        // phone number is not provided
        assertThrows(ParseException.class, () -> parser.parse("random name"));
        assertThrows(ParseException.class, () -> parser.parse("random name -pn"));
        assertThrows(ParseException.class, () -> parser.parse("random name -pn "));
        assertThrows(ParseException.class, () -> parser.parse("random name -p 1546424"));
        assertThrows(ParseException.class, () -> parser.parse("name -pn -e email@email.com -a address"));
        assertThrows(ParseException.class, () -> parser.parse("name -e email@email.com -a address"));
    }

    @Test
    public void parse_invalidAttributes_throwsParseException() {
        AddClientCommandParser parser = new AddClientCommandParser(new ModelStub());
        // invalid phone number
        assertThrows(ParseException.class, () -> parser.parse("name -pn phone"));
        assertThrows(ParseException.class, () -> parser.parse(
                "name -pn phone -e email@email.com -a address"));

        // invalid email
        assertThrows(ParseException.class, () -> parser.parse("name -pn 43530251 -e email"));
        assertThrows(ParseException.class, () -> parser.parse("name -pn 43532510 -e @email"));
        assertThrows(ParseException.class, () -> parser.parse(
                "name -pn 43532510 -e @email -a Singapore"));

        // invalid orders
        assertThrows(ParseException.class, () -> parser.parse("name -pn 2342532 -o -o "));
        assertThrows(ParseException.class, () -> parser.parse("name -pn 2342532 -o -100 0 10/27"));
        assertThrows(ParseException.class, () -> parser.parse("name -pn 2342532 -o 0 -10 2021/10/27"));
        assertThrows(ParseException.class, () -> parser.parse("name -pn 2342532 -o 0 1 2021/15/20"));
        assertThrows(ParseException.class, () -> parser.parse("name -pn 2342532 -o 0 1 "));
    }

    @Test
    public void parse_allFieldsPresent_success() {
        ModelStub model = new ModelStub();
        AddClientCommandParser parser = new AddClientCommandParser(model);
        // add a product
        String addProductResult = "";
        try {
            AddProductCommandParser addProductCommandParser = new AddProductCommandParser();
            AddProductCommand addProductCommand = addProductCommandParser.parse("pen -$ 10 -q 10");
            addProductResult = addProductCommand.execute(model).getFeedbackToUser();
        } catch (ParseException | CommandException e) {
            fail();
        }
        // information of a client.
        Name name = new Name("John Doe");
        PhoneNumber phoneNumber = new PhoneNumber("12345678");
        Email email = new Email("john.d@email.com");
        Address address = new Address("Singapore");
        Set<Order> orders = new HashSet<>();
        AddClientCommand.AddClientDescriptor descriptor =
                new AddClientCommand.AddClientDescriptor(name, phoneNumber);

        // name and phone number are provided
        testValidAttributes("John Doe -pn 12345678", descriptor, parser, model);

        // name, phone number and email are provided
        descriptor.setEmail(email);
        testValidAttributes("John Doe -pn 12345678 -e john.d@email.com", descriptor, parser, model);

        // name, phone number, email and address are provided
        descriptor.setAddress(address);
        testValidAttributes("John Doe -pn 12345678 -e john.d@email.com -a Singapore", descriptor, parser, model);

        // name, phone number and address are provided
        descriptor.setEmail(null);
        testValidAttributes("John Doe -pn 12345678 -a Singapore", descriptor, parser, model);

        // name, phone number and orders are provided
        descriptor.setAddress(null);
        String idString = addProductResult
                .substring(addProductResult.indexOf("ID: "), addProductResult.indexOf("; Name"))
                .substring(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        Order order = new Order(new ID(Integer.parseInt(idString)), new Quantity("1"),
                LocalDate.parse("2021/10/27", formatter), model);
        orders.add(order);
        descriptor.setOrders(orders);
        testValidAttributes("John Doe -pn 12345678 -o " + idString + " 1 2021/10/27", descriptor, parser, model);
    }

    private void testValidAttributes(String args, AddClientCommand.AddClientDescriptor descriptor,
                                     AddClientCommandParser parser, Model model) {
        try {
            CommandResult actualResult = parser.parse(args).execute(model);
            CommandResult expectedResult = new AddClientCommand(descriptor).execute(model);
            // compare the feedback to user excluding the id.
            String actualString = actualResult.getFeedbackToUser();
            actualString = actualString.substring(actualString.indexOf("Name"));
            String expectedString = expectedResult.getFeedbackToUser();
            expectedString = expectedString.substring(expectedString.indexOf("Name"));
            assertEquals(expectedString, actualString);
        } catch (ParseException | CommandException e) {
            fail();
        }
    }

    public static class ModelStub extends ModelManager {
        /**
         * Assume there are no duplicate clients, return false.
         *
         * @param client the client to be checked.
         * @return False.
         */
        @Override
        public boolean hasClient(Client client) {
            return false;
        }
    }
}
