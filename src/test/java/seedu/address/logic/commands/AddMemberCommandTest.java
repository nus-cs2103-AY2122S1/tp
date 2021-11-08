package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.EzFoodie;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAccount;
import seedu.address.model.ReadOnlyEzFoodie;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.member.Member;
import seedu.address.testutil.MemberBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddMemberCommand}.
 */
public class AddMemberCommandTest {

    /**
     * Tests if adding a null member will throw exception.
     */
    @Test
    public void constructor_nullMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMemberCommand(null));
    }

    /**
     * Checks if adding a valid member is accepted by model.
     *
     * @throws Exception
     */
    @Test
    public void execute_memberAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingMemberAdded modelStub = new ModelStubAcceptingMemberAdded();
        Member validMember = new MemberBuilder().build();

        CommandResult commandResult = new AddMemberCommand(validMember).execute(modelStub);

        assertEquals(String.format(AddMemberCommand.MESSAGE_SUCCESS, validMember), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validMember), modelStub.membersAdded);
    }

    /**
     * Checks if adding a duplicate member will throw CommandException.
     */
    @Test
    public void execute_duplicateMember_throwsCommandException() {
        Member validMember = new MemberBuilder().build();
        AddMemberCommand addMemberCommand = new AddMemberCommand(validMember);
        ModelStub modelStub = new ModelStubWithMember(validMember);

        assertThrows(CommandException.class,
                AddMemberCommand.MESSAGE_DUPLICATE_MEMBER, () -> addMemberCommand.execute(modelStub));
    }

    /**
     * Stands for equals method by comparing AddMemberCommand objects.
     */
    @Test
    public void equals() {
        Member alice = new MemberBuilder().withName("Alice").build();
        Member bob = new MemberBuilder().withName("Bob").build();
        AddMemberCommand addAliceCommand = new AddMemberCommand(alice);
        AddMemberCommand addBobCommand = new AddMemberCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddMemberCommand addAliceCommandCopy = new AddMemberCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different member -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {

        /**
         * Sets up user prefs.
         *
         * @param userPrefs Read-only user prefs.
         */
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Gets user prefs.
         *
         * @return Read-only user prefs.
         */
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Gets Gui settings defined.
         *
         * @return GuiSettings object.
         */
        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Sets up Gui settings.
         *
         * @param guiSettings GuiSettings object.
         */
        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Gets the path of account file stored.
         *
         * @return Path object.
         */
        @Override
        public Path getAccountFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Sets up the path for account file.
         *
         * @param accountFilePath Path of account file.
         */
        @Override
        public void setAccountFilePath(Path accountFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Gets the path of ezFoodie file.
         *
         * @return Path object.
         */
        @Override
        public Path getEzFoodieFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Sets up the path of ezFoodie file.
         *
         * @param ezFoodieFilePath Path of ezFoodie.
         */
        @Override
        public void setEzFoodieFilePath(Path ezFoodieFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Sets up account.
         *
         * @param account Read-only account.
         */
        @Override
        public void setAccount(ReadOnlyAccount account) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Gets account.
         *
         * @return Read-only account.
         */
        @Override
        public ReadOnlyAccount getAccount() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Adds member.
         *
         * @param member Member object.
         */
        @Override
        public void addMember(Member member) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Sets up ezFoodie.
         *
         * @param newData Read-only ezFoodie.
         */
        @Override
        public void setEzFoodie(ReadOnlyEzFoodie newData) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Gets ezFoodie.
         *
         * @return Read-only ezFoodie.
         */
        @Override
        public ReadOnlyEzFoodie getEzFoodie() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Checks if a member is contained.
         *
         * @param member Member object.
         * @return True if contains, false otherwise.
         */
        @Override
        public boolean hasMember(Member member) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Checks if a member is contained by given predicate.
         *
         * @param member    Member object.
         * @param predicate Predicate to carry out the checking procedure.
         * @return True if contains, false otherwise.
         */
        @Override
        public boolean hasMember(Member member, Predicate<Member> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Deletes a member.
         *
         * @param target Target member object.
         */
        @Override
        public void deleteMember(Member target) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Sets up an edited member to a target member.
         *
         * @param target       Member to be set.
         * @param editedMember Edited member to set.
         */
        @Override
        public void setMember(Member target, Member editedMember) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Gets updated member list stored.
         *
         * @return An observable list of members.
         */
        @Override
        public ObservableList<Member> getUpdatedMemberList() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Gets updated member list stored for front end viewing purpose.
         *
         * @return An observable list of members for front end viewing purpose.
         */
        @Override
        public ObservableList<Member> getUpdatedMemberListForView() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Updates filtered member list stored.
         *
         * @param predicate Predicate to carry out the filtering process.
         */
        @Override
        public void updateFilteredMemberList(Predicate<Member> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Updates filtered member list stored for front end viewing purpose.
         *
         * @param predicate Predicate to carry out the filtering process.
         */
        @Override
        public void updateFilteredMemberListForView(Predicate<Member> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Updates the member list stored.
         *
         * @param comparator Comparator used to update the member list.
         */
        @Override
        public void updateSortedMemberList(Comparator<Member> comparator) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single member.
     */
    private class ModelStubWithMember extends ModelStub {
        private final Member member;

        /**
         * Constructs a ModelStubWithMember.
         *
         * @param member Member to be contained.
         */
        ModelStubWithMember(Member member) {
            requireNonNull(member);
            this.member = member;
        }

        /**
         * Overrides hasMember method of ModelStub.
         *
         * @param member Member object.
         * @return True is the member is contained in this ModelStub, false otherwise.
         */
        @Override
        public boolean hasMember(Member member) {
            requireNonNull(member);
            return this.member.isSameMember(member);
        }
    }

    /**
     * A Model stub that always accept the member being added.
     */
    private class ModelStubAcceptingMemberAdded extends ModelStub {

        /**
         * Stands for members added to this ModelStub.
         */
        final ArrayList<Member> membersAdded = new ArrayList<>();

        /**
         * Overrides hasMember method of ModelStub.
         *
         * @param member Member object.
         * @return True is the member is contained in this ModelStub, false otherwise.
         */
        @Override
        public boolean hasMember(Member member) {
            requireNonNull(member);
            return membersAdded.stream().anyMatch(member::isSameMember);
        }

        /**
         * Overrides addMember method of ModelStub.
         *
         * @param member Member object.
         */
        @Override
        public void addMember(Member member) {
            requireNonNull(member);
            membersAdded.add(member);
        }

        /**
         * Gets ezFoodie
         *
         * @return A new default ezFoodie object.
         */
        @Override
        public ReadOnlyEzFoodie getEzFoodie() {
            return new EzFoodie();
        }
    }

}
