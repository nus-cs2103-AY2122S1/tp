package seedu.address.model.claim;

import java.util.Objects;

public class Claim {
    private final Title title;
    private final Description description;
    private final Status status;

    public Claim(Title title, Description description, Status status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Title: ")
               .append(getTitle())
               .append("; Description: ")
               .append(getDescription())
               .append("; Status: ")
               .append(getStatus());
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Claim)) {
            return false;
        }

        Claim otherClaim = (Claim) o;
        return this.title.equals(otherClaim.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
