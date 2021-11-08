package seedu.address.testutil;

import seedu.address.model.EzFoodie;
import seedu.address.model.member.Member;

/**
 * A utility class to help with building EzFoodie objects.
 * Example usage: <br>
 *     {@code EzFoodie ef = new EzFoodieBuilder().withMember("John", "Doe").build();}
 */
public class EzFoodieBuilder {

    private EzFoodie ezFoodie;

    public EzFoodieBuilder() {
        ezFoodie = new EzFoodie();
    }

    public EzFoodieBuilder(EzFoodie ezFoodie) {
        this.ezFoodie = ezFoodie;
    }

    /**
     * Adds a new {@code Member} to the {@code EzFoodie} that we are building.
     */
    public EzFoodieBuilder withMember(Member member) {
        ezFoodie.addMember(member);
        return this;
    }

    public EzFoodie build() {
        return ezFoodie;
    }
}
