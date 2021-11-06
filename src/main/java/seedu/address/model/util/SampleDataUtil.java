package seedu.address.model.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.PhoneNumber;
import seedu.address.model.commons.Name;
import seedu.address.model.order.Order;
import seedu.address.model.product.Product;
import seedu.address.model.product.Quantity;
import seedu.address.model.product.UnitPrice;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Client[] getSampleClients() {
        return new Client[]{
                new Client(new Name("Alex Yeoh"), new PhoneNumber("87438807"), new Email("alexyeoh@example.com"),
                        null, new HashSet<>(Arrays.asList(
                        new Order(new Name("iPhone 13"), new Quantity("3"), LocalDate.parse("2018-03-13")),
                        new Order(new Name("Nintendo Switch Ring Fit Adventure"), new Quantity("26"),
                                LocalDate.parse("2021-02-27")),
                        new Order(new Name("Air Pods Pro"), new Quantity("10"), LocalDate.parse("2012-10-03"))
                ))),
                new Client(new Name("Bernice Yu"), new PhoneNumber("99272758"), new Email("berniceyu@qq.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new HashSet<>(Arrays.asList(
                        new Order(new Name("OSIM UInfinity Massage Chair"), new Quantity("57"),
                                LocalDate.parse("2020-11-06")),
                        new Order(new Name("Samsung Smart TV"), new Quantity("60"), LocalDate.parse("2017-11-03"))
                ))),
                new Client(new Name("Charlotte Olivero"), new PhoneNumber("93210283"), new Email("charlotte@abc.com"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new HashSet<>(Arrays.asList(
                        new Order(new Name("PS5"), new Quantity("61"), LocalDate.parse("2016-11-09")),
                        new Order(new Name("Dyson Supersonic Hair Dryer"), new Quantity("33"),
                                LocalDate.parse("2018-12-23"))
                ))),
                new Client(new Name("David Li"), new PhoneNumber("91031282"), new Email("lidavid@gmail.com"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new HashSet<>(Arrays.asList(
                        new Order(new Name("PS5"), new Quantity("99"), LocalDate.parse("2016-11-30")),
                        new Order(new Name("iPhone 13"), new Quantity("13"), LocalDate.parse("2015-06-19")),
                        new Order(new Name("Samsung Smart TV"), new Quantity("37"), LocalDate.parse("2014-01-29")),
                        new Order(new Name("Panadol"), new Quantity("55"), LocalDate.parse("2011-02-03"))
                ))),
                new Client(new Name("Irfan Ibrahim"), new PhoneNumber("92492021"), new Email("irfan@sciencedaily.com"),
                        new Address("Blk 47 Tampines Street 20, #17-35"), new HashSet<>(Arrays.asList(
                        new Order(new Name("PS5"), new Quantity("62"), LocalDate.parse("2020-12-31"))
                ))),
                new Client(new Name("Roy Balakrishnan"), new PhoneNumber("92624417"), new Email("royb@hotmail.com"),
                        new Address("Blk 45 Aljunied Street 85, #11-31"), new HashSet<>(Arrays.asList(
                        new Order(new Name("Panadol"), new Quantity("70"), LocalDate.parse("2019-07-16")),
                        new Order(new Name("N95 Mask"), new Quantity("25"), LocalDate.parse("2012-09-28"))
                ))),
                new Client(new Name("Minny Boteman"), new PhoneNumber("93513517"), null,
                        new Address("Blk 30 Geylang Street 29, #06-40"), new HashSet<>(Arrays.asList(
                        new Order(new Name("iPhone 13"), new Quantity("41"), LocalDate.parse("2015-01-03")),
                        new Order(new Name("Panadol"), new Quantity("29"), LocalDate.parse("2012-03-14")),
                        new Order(new Name("Nintendo Switch Ring Fit Adventure"), new Quantity("9"),
                                LocalDate.parse("2017-02-28"))
                ))),
                new Client(new Name("Benjamen Dowsett"), new PhoneNumber("97596150"), new Email("bendow@xyz.com"),
                        null, new HashSet<>()),
                new Client(new Name("Fanya Toone"), new PhoneNumber("92048150"), new Email("ftoone2@google.com.au"),
                        new Address("Blk 35 Mandalay Road, #13–37"), new HashSet<>(Arrays.asList(
                        new Order(new Name("OSIM UInfinity Massage Chair"), new Quantity("97"),
                                LocalDate.parse("2017-06-06")),
                        new Order(new Name("iPhone 13"), new Quantity("26"), LocalDate.parse("2020-11-05"))
                ))),
                new Client(new Name("Roslyn Hughf"), new PhoneNumber("96217157"), new Email("rhughf4@yellowpages.com"),
                        new Address("370 Orchard Road, #07-08, International Building"), new HashSet<>(Arrays.asList(
                        new Order(new Name("iPhone 13"), new Quantity("3"), LocalDate.parse("2021-10-12")),
                        new Order(new Name("Air Humidifier"), new Quantity("37"), LocalDate.parse("2011-09-28")),
                        new Order(new Name("Dyson Supersonic Hair Dryer"), new Quantity("63"),
                                LocalDate.parse("2019-08-09"))
                )))
        };
    }

    public static Product[] getSampleProducts() {
        return new Product[]{
                new Product(new Name("iPhone 13"), new UnitPrice("1699"), new Quantity("516")),
                new Product(new Name("Air Pods Pro"), new UnitPrice("369"), new Quantity("465")),
                new Product(new Name("Panadol"), new UnitPrice("12"), new Quantity("519")),
                new Product(new Name("N95 Mask"), new UnitPrice("10"), new Quantity("35849")),
                new Product(new Name("Nintendo Switch Ring Fit Adventure"), new UnitPrice("279"), new Quantity("651")),
                new Product(new Name("PS5"), new UnitPrice("1499"), new Quantity("3")),
                new Product(new Name("Samsung Smart TV"), new UnitPrice("1100"), new Quantity("516")),
                new Product(new Name("Air Humidifier"), new UnitPrice("549"), new Quantity("2882")),
                new Product(new Name("Dyson Supersonic Hair Dryer"), new UnitPrice("600"), new Quantity("285")),
                new Product(new Name("OSIM UInfinity Massage Chair"), new UnitPrice("3200"), new Quantity("782"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();

        for (Client sampleClient : getSampleClients()) {
            sampleAb.addClient(sampleClient);
        }

        for (Product sampleProduct : getSampleProducts()) {
            sampleAb.addProduct(sampleProduct);
        }

        return sampleAb;
    }
}
