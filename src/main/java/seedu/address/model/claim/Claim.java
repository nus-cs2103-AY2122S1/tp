package seedu.address.model.claim;

public class Claim {
    private Title title;
    private Description description;
    private Status status;

    public Claim(Title title, Description description, Status status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
