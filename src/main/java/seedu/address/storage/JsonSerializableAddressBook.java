package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.Client;
import seedu.address.model.product.Product;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {
    public static final String MESSAGE_DUPLICATE_CLIENT = "Clients list contains duplicate client(s).";
    public static final String MESSAGE_DUPLICATE_PRODUCT = "Products list contains duplicate product(s).";

    // todo remove later
    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedClient> clients = new ArrayList<>();
    private final List<JsonAdaptedProduct> products = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons, clients and products.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("clients") List<JsonAdaptedClient> clients,
                                       @JsonProperty("products") List<JsonAdaptedProduct> products) {
        if (clients != null) {
            this.clients.addAll(clients);
        }
        if (products != null) {
            this.products.addAll(products);
        }
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        clients.addAll(source.getClientList().stream().map(JsonAdaptedClient::new).collect(Collectors.toList()));
        products.addAll(source.getProductList().stream().map(JsonAdaptedProduct::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();

        for (JsonAdaptedClient jsonAdaptedClient : clients) {
            Client client = jsonAdaptedClient.toModelType();
            if (addressBook.hasClient(client)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLIENT);
            }
            addressBook.addClient(client);
        }

        for (JsonAdaptedProduct jsonAdaptedProduct : products) {
            Product product = jsonAdaptedProduct.toModelType();
            if (addressBook.hasProduct(product)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PRODUCT);
            }
            addressBook.addProduct(product);
        }

        return addressBook;
    }
}
