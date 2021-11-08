package seedu.address.model.util;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.util.EncryptUtil;
import seedu.address.model.Account;
import seedu.address.model.EzFoodie;
import seedu.address.model.ReadOnlyAccount;
import seedu.address.model.ReadOnlyEzFoodie;
import seedu.address.model.Timestamp;
import seedu.address.model.account.Password;
import seedu.address.model.member.Address;
import seedu.address.model.member.Credit;
import seedu.address.model.member.Email;
import seedu.address.model.member.Member;
import seedu.address.model.member.Name;
import seedu.address.model.member.Phone;
import seedu.address.model.member.Point;
import seedu.address.model.reservation.DateTime;
import seedu.address.model.reservation.Remark;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Billing;
import seedu.address.model.transaction.Transaction;

/**
 * Contains utility methods for populating {@code EzFoodie} with sample data.
 */
public class SampleDataUtil {
    public static Member[] getSampleMembers() {
        return new Member[] {
            new Member(new seedu.address.model.member.Id("00001"), new Name("Alex Yeoh"), new Phone("87438807"),
                    new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                    new Timestamp("1609545600000"), new Credit("90"), new Point("90"),
                    getTransactionList(new Transaction(new seedu.address.model.transaction.Id("000001"),
                            new Timestamp("1609545600000"), new Billing("90.00"))),
                    getReservationList(new Reservation(
                            new seedu.address.model.reservation.Id("000001"), new DateTime("2021-01-20 00:00"),
                            new Remark("2 people"))),
                    getTagSet()),
            new Member(new seedu.address.model.member.Id("00002"),
                    new Name("Bernice Yu"), new Phone("99272758"),
                    new Email("berniceyu@example.com"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    new Timestamp("1609549200000"), new Credit("525"), new Point("525"),
                    getTransactionList(
                            new Transaction(new seedu.address.model.transaction.Id("000001"),
                                    new Timestamp("1611104400000"), new Billing("200.12")),
                            new Transaction(new seedu.address.model.transaction.Id("000002"),
                                    new Timestamp("1611190800000"), new Billing("325.30"))),
                    getReservationList(
                            new Reservation(new seedu.address.model.reservation.Id("000001"),
                                    new DateTime("2021-01-20 01:00"), new Remark("2 people")),
                            new Reservation(new seedu.address.model.reservation.Id("000002"),
                                    new DateTime("2021-01-21 01:00"), new Remark("3 people"))),
                    getTagSet()),
            new Member(new seedu.address.model.member.Id("00003"),
                    new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new Email("charlotte@example.com"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new Timestamp("1609552800000"), new Credit("150"), new Point("150"),
                    getTransactionList(new Transaction(new seedu.address.model.transaction.Id("000001"),
                            new Timestamp("1611108000000"), new Billing("150.00"))),
                    getReservationList(new Reservation(new seedu.address.model.reservation.Id("000001"),
                            new DateTime("2021-01-20 02:00"), new Remark("2 people"))),
                    getTagSet()),
            new Member(new seedu.address.model.member.Id("00004"),
                    new Name("David Li"), new Phone("91031282"),
                    new Email("lidavid@example.com"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    new Timestamp("1609632000000"), new Credit("401"), new Point("401"),
                    getTransactionList(
                            new Transaction(new seedu.address.model.transaction.Id("000001"),
                                    new Timestamp("1611111600000"), new Billing("100.50")),
                            new Transaction(new seedu.address.model.transaction.Id("000002"),
                                    new Timestamp("1611198000000"), new Billing("200.50")),
                            new Transaction(new seedu.address.model.transaction.Id("000003"),
                                    new Timestamp("1611284400000"), new Billing("100.00"))),
                    getReservationList(
                            new Reservation(new seedu.address.model.reservation.Id("000001"),
                                    new DateTime("2021-01-20 03:00"), new Remark("2 people")),
                            new Reservation(new seedu.address.model.reservation.Id("000002"),
                                    new DateTime("2021-01-21 03:00"), new Remark("3 people")),
                            new Reservation(new seedu.address.model.reservation.Id("000003"),
                                    new DateTime("2021-01-22 03:00"), new Remark("4 people"))),
                    getTagSet()),
            new Member(new seedu.address.model.member.Id("00005"),
                    new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new Email("irfan@example.com"), new Address("Blk 47 Tampines Street 20, #17-35"),
                    new Timestamp("1609635600000"), new Credit("1010"), new Point("1010"),
                    getTransactionList(
                            new Transaction(new seedu.address.model.transaction.Id("000001"),
                                    new Timestamp("1611115200000"), new Billing("1000.00")),
                            new Transaction(new seedu.address.model.transaction.Id("000002"),
                                    new Timestamp("1611201600000"), new Billing("10.00"))),
                    getReservationList(
                            new Reservation(new seedu.address.model.reservation.Id("000001"),
                                    new DateTime("2021-01-20 04:00"), new Remark("2 people")),
                            new Reservation(new seedu.address.model.reservation.Id("000002"),
                                    new DateTime("2021-01-21 04:00"), new Remark("3 people"))),
                    getTagSet()),
            new Member(new seedu.address.model.member.Id("00006"),
                    new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new Email("royb@example.com"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                    new Timestamp("1609639200000"), new Credit("110"), new Point("110"),
                    getTransactionList(
                            new Transaction(new seedu.address.model.transaction.Id("000001"),
                                    new Timestamp("1611118800000"), new Billing("10.00")),
                            new Transaction(new seedu.address.model.transaction.Id("000002"),
                                    new Timestamp("1611205200000"), new Billing("100.10"))),
                    getReservationList(
                            new Reservation(new seedu.address.model.reservation.Id("000001"),
                                    new DateTime("2021-01-20 05:00"), new Remark("2 people")),
                            new Reservation(new seedu.address.model.reservation.Id("000002"),
                                    new DateTime("2021-01-21 05:00"), new Remark("3 people"))),
                    getTagSet())
        };
    }

    /**
     * Gets default password.
     */
    public static ReadOnlyAccount getDefaultPassword() {
        Password password;
        try {
            password = new Password(EncryptUtil.hash(Password.DEFAULT_PLAINTEXT_PASSWORD));
        } catch (NoSuchAlgorithmException e) {
            password = new Password("");
        }
        return new Account(password);
    }

    /**
     * Gets Sample EzFoodie.
     */
    public static ReadOnlyEzFoodie getSampleEzFoodie() {
        EzFoodie sampleEf = new EzFoodie();
        for (Member sampleMember : getSampleMembers()) {
            sampleEf.addMember(sampleMember);
        }
        return sampleEf;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a reservation list containing the list of reservations given.
     */
    public static List<Reservation> getReservationList(Reservation... reservations) {
        return Arrays.stream(reservations).collect(Collectors.toList());
    }

    /**
     * Returns a transaction list containing the list of transactions given.
     */
    public static List<Transaction> getTransactionList(Transaction... transactions) {
        return Arrays.stream(transactions).collect(Collectors.toList());
    }

}
