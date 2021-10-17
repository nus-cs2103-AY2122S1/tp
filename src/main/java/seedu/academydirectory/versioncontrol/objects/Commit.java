package seedu.academydirectory.versioncontrol.objects;

import java.util.Date;
import java.util.function.Supplier;

public class Commit extends VcObject {
    public static final Commit NULL = new Commit("NULL", null, null, null, null, null);

    // Commit specific objects
    private final String author;
    private final Date date;
    private final String message;

    // References
    private final Supplier<Commit> parent;
    private final Supplier<Tree> blob;

    /**
     * Creates a Commit object to work with programmatically. A Commit object SHOULD NOT be instantiated directly.
     * Instead, use a CommitFactory object to ensure all Commit objects follow the established contract
     * @param message commit message. May be omitted
     * @param date date and time when Commit object is created
     * @param hash commit hash
     * @param parent parent Commit that current Commit object is pointing to
     * @param blob file referenced by current Commit object
     */
    public Commit(String hash, String author, Date date, String message, Supplier<Commit> parent, Supplier<Tree> blob) {
        super(hash);
        this.author = author;
        this.date = date;
        this.message = message == null ? "" : message;
        this.parent = parent;
        this.blob = blob;
    }

    @Override
    public String toString() {
        return author + date.toString() + message + parent.get().getHash() + blob.get().getHash();
    }
}
