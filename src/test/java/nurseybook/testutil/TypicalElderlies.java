package nurseybook.testutil;

import static nurseybook.logic.commands.CommandTestUtil.VALID_AGE_AMY;
import static nurseybook.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static nurseybook.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static nurseybook.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static nurseybook.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static nurseybook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static nurseybook.logic.commands.CommandTestUtil.VALID_NOK_ADDRESS_AMY;
import static nurseybook.logic.commands.CommandTestUtil.VALID_NOK_ADDRESS_BOB;
import static nurseybook.logic.commands.CommandTestUtil.VALID_NOK_EMAIL_AMY;
import static nurseybook.logic.commands.CommandTestUtil.VALID_NOK_EMAIL_BOB;
import static nurseybook.logic.commands.CommandTestUtil.VALID_NOK_NAME_AMY;
import static nurseybook.logic.commands.CommandTestUtil.VALID_NOK_NAME_BOB;
import static nurseybook.logic.commands.CommandTestUtil.VALID_NOK_PHONE_AMY;
import static nurseybook.logic.commands.CommandTestUtil.VALID_NOK_PHONE_BOB;
import static nurseybook.logic.commands.CommandTestUtil.VALID_NOK_RELATIONSHIP_AMY;
import static nurseybook.logic.commands.CommandTestUtil.VALID_NOK_RELATIONSHIP_BOB;
import static nurseybook.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_AMY;
import static nurseybook.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_BOB;
import static nurseybook.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static nurseybook.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nurseybook.model.NurseyBook;
import nurseybook.model.person.Elderly;

/**
 * A utility class containing a list of {@code Elderly} objects to be used in tests.
 */
public class TypicalElderlies {

    public static final ElderlyBuilder ALICE_BUILDER = new ElderlyBuilder().withName("Alice Pauline")
            .withAge("40").withGender("F").withRoomNumber("15").withNokName("Alice Johnson").withRelationship("Mother")
            .withPhone("94351253").withEmail("alice@example.com")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withRemark("She likes aardvarks.").withTags("friends");
    public static final ElderlyBuilder BENSON_BUILDER = new ElderlyBuilder().withName("Benson Meier")
            .withAge("40").withGender("M").withRoomNumber("21").withNokName("Benson Michael").withRelationship("Father")
            .withPhone("98765432").withEmail("johnd@example.com")
            .withAddress("311, Clementi Ave 2, #02-25").withRemark("He can't take beer!")
            .withTags("diabetes", "friends");
    public static final ElderlyBuilder CARL_BUILDER = new ElderlyBuilder().withName("Carl Kurz")
            .withAge("40").withGender("M").withRoomNumber("9").withNokName("Carl Twain").withRelationship("Brother")
            .withPhone("95352563").withEmail("heinz@example.com").withAddress("wall street").withRemark("");
    public static final ElderlyBuilder DANIEL_BUILDER = new ElderlyBuilder().withName("Daniel Meier")
            .withAge("40").withGender("M").withRoomNumber("27").withNokName("Daniel Mustafa").withRelationship("Sister")
            .withPhone("87652533").withEmail("cornelia@example.com")
            .withAddress("10th street").withTags("friends").withRemark("");
    public static final ElderlyBuilder ELLE_BUILDER = new ElderlyBuilder().withName("Elle Meyer")
            .withAge("40").withGender("F").withRoomNumber("11").withNokName("Elle Salmon").withRelationship("Daughter")
            .withPhone("94823224").withEmail("werner@example.com").withAddress("michegan ave").withRemark("");
    public static final ElderlyBuilder FIONA_BUILDER = new ElderlyBuilder().withName("Fiona Kunz")
            .withAge("40").withGender("F").withRoomNumber("6").withNokName("Fiona James").withRelationship("Sister")
            .withPhone("94822427").withEmail("lydia@example.com").withAddress("little tokyo").withRemark("");
    public static final ElderlyBuilder GEORGE_BUILDER = new ElderlyBuilder().withName("George Best")
            .withAge("40").withGender("M").withRoomNumber("299").withNokName("George Bestron")
            .withRelationship("Father").withPhone("94824042").withRemark("").withEmail("anna@example.com")
            .withAddress("4th street");
    public static final Elderly ALICE = ALICE_BUILDER.build();
    public static final Elderly BENSON = BENSON_BUILDER.build();
    public static final Elderly CARL = CARL_BUILDER.build();
    public static final Elderly DANIEL = DANIEL_BUILDER.build();
    public static final Elderly ELLE = ELLE_BUILDER.build();
    public static final Elderly FIONA = FIONA_BUILDER.build();
    public static final Elderly GEORGE = GEORGE_BUILDER.build();

    // Manually added
    public static final Elderly HOON = new ElderlyBuilder().withName("Hoon Meier").withRoomNumber("77")
            .withNokName("Chara Meier").withRelationship("Daughter").withPhone("84822424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Elderly IDA = new ElderlyBuilder().withName("Ida Mueller").withRoomNumber("82")
            .withNokName("Dina Mueller").withRelationship("Mother").withPhone("84823131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Elderly's details found in {@code CommandTestUtil}
    public static final Elderly AMY = new ElderlyBuilder().withName(VALID_NAME_AMY)
            .withAge(VALID_AGE_AMY).withGender(VALID_GENDER_AMY).withRoomNumber(VALID_ROOM_NUMBER_AMY)
            .withNokName(VALID_NOK_NAME_AMY).withPhone(VALID_NOK_PHONE_AMY).withAddress(VALID_NOK_ADDRESS_AMY)
            .withEmail(VALID_NOK_EMAIL_AMY).withRelationship(VALID_NOK_RELATIONSHIP_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Elderly BOB = new ElderlyBuilder().withName(VALID_NAME_BOB)
            .withAge(VALID_AGE_BOB).withGender(VALID_GENDER_BOB).withRoomNumber(VALID_ROOM_NUMBER_BOB)
            .withNokName(VALID_NOK_NAME_BOB).withPhone(VALID_NOK_PHONE_BOB).withAddress(VALID_NOK_ADDRESS_BOB)
            .withEmail(VALID_NOK_EMAIL_BOB).withRelationship(VALID_NOK_RELATIONSHIP_BOB)
            .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalElderlies() {} // prevents instantiation

    /**
     * Returns an {@code NurseyBook} with all the typical elderlies.
     */
    public static NurseyBook getTypicalNurseyBook() {
        NurseyBook nb = new NurseyBook();
        for (Elderly elderly : getTypicalElderlies()) {
            nb.addElderly(elderly);
        }
        return nb;
    }

    /**
     * Returns a list of elderly builders to build new elderly objects.
     */
    public static List<ElderlyBuilder> getTypicalElderlyBuilders() {
        return new ArrayList<>(Arrays.asList(ALICE_BUILDER, BENSON_BUILDER,
                CARL_BUILDER, DANIEL_BUILDER, ELLE_BUILDER, FIONA_BUILDER, GEORGE_BUILDER));
    }

    public static List<Elderly> getTypicalElderlies() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
