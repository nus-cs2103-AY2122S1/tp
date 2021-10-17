package seedu.academydirectory.versioncontrol.objects;

import java.util.HashMap;

public class Tree extends VcObject {
    public static final Tree NULL = new Tree("NULL", null, null);

    private final HashMap<String, String> hashMap;

    /**
     * Creates a Tree object to work with programmatically. A Tree object SHOULD NOT be instantiated directly.
     * Instead, use a BlobFactory object to ensure all Tree objects follow the established contract
     * @param fileName name of file to be version controlled
     * @param vcName version controlled name of the file
     */
    public Tree(String hash, String fileName, String vcName) {
        super(hash);
        hashMap = new HashMap<>();
        hashMap.put(vcName, fileName);
    }
}
