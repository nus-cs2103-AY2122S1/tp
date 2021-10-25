package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddClientCommand;
import seedu.address.logic.commands.AddProductCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteClientCommand;
import seedu.address.logic.commands.DeleteProductCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListClientCommand;
import seedu.address.logic.commands.ListProductCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ModelManager;
import seedu.address.model.client.Client;
import seedu.address.model.product.Product;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.ClientUtil;
import seedu.address.testutil.ProductBuilder;
import seedu.address.testutil.ProductUtil;

public class AddressBookParserTest {
    private final AddressBookParser parser = new AddressBookParser();
    private final ModelStub model = new ModelStub();

    @Test
    public void parseCommand_addClient() throws Exception {
        Client client = new ClientBuilder().build();
        AddClientCommand.AddClientDescriptor descriptor =
                new AddClientCommand.AddClientDescriptor(client.getName(), client.getPhoneNumber());
        descriptor.setEmail(client.getEmail());
        descriptor.setAddress(client.getAddress());
        descriptor.setOrders(client.getOrders());

        AddClientCommand expectedCommand =
                (AddClientCommand) parser.parseCommand(ClientUtil.getAddClientCommand(client), model);
        AddClientCommand actualCommand = new AddClientCommand(descriptor);
        String expectedOutput = expectedCommand.execute(model).getFeedbackToUser();
        String actualOutput = actualCommand.execute(model).getFeedbackToUser();
        assertEquals(expectedOutput.substring(expectedOutput.indexOf("Name")),
                actualOutput.substring(actualOutput.indexOf("Name")));
    }

    @Test
    public void parseCommand_addProduct() throws Exception {
        Product product = new ProductBuilder().build();
        AddProductCommand.AddProductDescriptor descriptor =
                new AddProductCommand.AddProductDescriptor(product.getName(), product.getUnitPrice());
        descriptor.setQuantity(product.getQuantity());

        AddProductCommand expectedCommand =
                (AddProductCommand) parser.parseCommand(ProductUtil.getAddProductCommand(product), model);
        AddProductCommand actualCommand = new AddProductCommand(descriptor);
        String expectedOutput = expectedCommand.execute(model).getFeedbackToUser();
        String actualOutput = actualCommand.execute(model).getFeedbackToUser();
        assertEquals(expectedOutput.substring(expectedOutput.indexOf("Name")),
                actualOutput.substring(actualOutput.indexOf("Name")));
    }

//    @Test
//    public void parseCommand_editClient() throws Exception {
//        Client client = new ClientBuilder().build();
//        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder(client).build();
//        EditClientCommand expectedCommand =
//                (EditClientCommand) parser.parseCommand(EditClientCommand.COMMAND_WORD + " " +
//                        INDEX_FIRST_CLIENT.getOneBased() + " " + ClientUtil.getEditClientDescriptorDetails(descriptor),
//                        model);
//        EditClientCommand actualCommand = new EditClientCommand(INDEX_FIRST_CLIENT, descriptor);
//        String expectedOutput = expectedCommand.execute(model).getFeedbackToUser();
//        String actualOutput = actualCommand.execute(model).getFeedbackToUser();
//        assertEquals(expectedOutput, actualOutput);
//    }

//    @Test
//    public void parseCommand_editProduct() throws Exception {
//        Product product = new ProductBuilder().build();
//        EditProductCommand.EditProductDescriptor descriptor = new EditProductCommand.EditProductDescriptor(product).build();
//        EditProductCommand expectedCommand =
//                (EditProductCommand) parser.parseCommand(EditProductCommand.COMMAND_WORD + " " +
//                                INDEX_FIRST_PRODUCT.getOneBased() + " " + ProductUtil.getEditProductDescriptorDetails(descriptor),
//                        model);
//        EditProductCommand actualCommand = new EditProductCommand(INDEX_FIRST_PRODUCT, descriptor);
//        String expectedOutput = expectedCommand.execute(model).getFeedbackToUser();
//        String actualOutput = actualCommand.execute(model).getFeedbackToUser();
//        assertEquals(expectedOutput, actualOutput);
//    }

    @Test
    public void parseCommand_deleteClient() throws Exception {
        DeleteClientCommand command = (DeleteClientCommand) parser.parseCommand(
                DeleteClientCommand.COMMAND_WORD + " " + INDEX_FIRST_CLIENT.getOneBased(), model);
        assertEquals(new DeleteClientCommand(INDEX_FIRST_CLIENT), command);
    }

    @Test
    public void parseCommand_deleteProduct() throws Exception {
        DeleteProductCommand command = (DeleteProductCommand) parser.parseCommand(
                DeleteProductCommand.COMMAND_WORD + " " + INDEX_FIRST_PRODUCT.getOneBased(), model);
        assertEquals(new DeleteProductCommand(INDEX_FIRST_PRODUCT), command);
    }

    @Test
    public void parseCommand_listClient() throws Exception {
        assertTrue(parser.parseCommand(ListClientCommand.COMMAND_WORD, model) instanceof ListClientCommand);
        assertTrue(parser.parseCommand(ListClientCommand.COMMAND_WORD + " 3", model) instanceof ListClientCommand);
    }

    @Test
    public void parseCommand_listProduct() throws Exception {
        assertTrue(parser.parseCommand(ListProductCommand.COMMAND_WORD, model) instanceof ListProductCommand);
        assertTrue(parser.parseCommand(ListProductCommand.COMMAND_WORD + " 3", model) instanceof ListProductCommand);
    }

//    @Test
//    public void parseCommand_findClient() throws Exception {
//        List<String> keywords = Arrays.asList("foo", "bar", "baz");
//        FindClientCommand command = (FindClientCommand) parser.parseCommand(
//                FindClientCommand.COMMAND_WORD + " " +
//                        keywords.stream().collect(Collectors.joining(" ")), model);
//        assertEquals(new FindClientCommand(new NameContainsKeywordsPredicate(keywords)), command);
//    }

//    @Test
//    public void parseCommand_findProduct() throws Exception {
//        List<String> keywords = Arrays.asList("foo", "bar", "baz");
//        FindProductCommand command = (FindProductCommand) parser.parseCommand(
//                FindProductCommand.COMMAND_WORD + " " +
//                        keywords.stream().collect(Collectors.joining(" ")), model);
//        assertEquals(new FindProductCommand(new NameContainsKeywordsPredicate(keywords)), command);
//    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD, model) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3", model) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD, model) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3", model) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD, model) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3", model) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parseCommand("", model));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parseCommand("unknownCommand", model));
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
