package seedu.academydirectory.versioncontrol.objects;

import java.util.Arrays;
import java.util.Date;
import java.util.function.Supplier;

public class Commit extends VcObject {
    public static final Commit NULL = new Commit("NULL", null, null, null, null, null);

    // Commit specific objects
    private final String author;
    private final Date date;
    private final String message;

    // References
    private final Supplier<Commit> parentSupplier;
    private final Supplier<Tree> treeSupplier;

    /**
     * Creates a Commit object to work with programmatically. A Commit object SHOULD NOT be instantiated directly.
     * Instead, use a CommitController object to ensure all Commit objects follow the established contract
     * @param message commit message. May be omitted
     * @param date date and time when Commit object is created
     * @param hash commit hash
     * @param parentSupplier  supplier to parent Commit that current Commit object is pointing to
     * @param treeSupplier supplier to Tree referenced by current Commit object
     */
    public Commit(String hash, String author, Date date, String message, Supplier<Commit> parentSupplier,
                  Supplier<Tree> treeSupplier) {
        super(hash);
        this.author = author;
        this.date = date;
        this.message = message == null ? "" : message.substring(0, Math.min(message.length(), 128));
        this.parentSupplier = parentSupplier;
        this.treeSupplier = treeSupplier;
    }

    @Override
    public String toString() {
        if (this.equals(Commit.NULL)) {
            return "NULL";
        }
        return Arrays.toString(new String[]{getHash(), author, date.toString(), message});
    }

    public String getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public Supplier<Commit> getParentSupplier() {
        return parentSupplier;
    }

    public Supplier<Tree> getTreeSupplier() {
        return treeSupplier;
    }
}
