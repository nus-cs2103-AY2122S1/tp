package seedu.academydirectory.versioncontrol.controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import seedu.academydirectory.versioncontrol.objects.VcObject;
import seedu.academydirectory.versioncontrol.utils.HashGenerator;

public abstract class Controller<T extends VcObject> {
    protected final HashGenerator generator;
    protected final Path vcPath;

    /**
     * Creates a Controller to build
     * @param generator
     * @param vcPath
     */
    protected Controller(HashGenerator generator, Path vcPath) {
        this.generator = generator;
        this.vcPath = vcPath;
    }

    public abstract List<String> getWriteableFormat(T vcObject);

    /**
     * Writes a given VcObject to file, with filename being the VcObject object's hash
     * @param vcObject to be written to file
     * @throws IOException Unable to write to file
     */
    public void write(T vcObject) throws IOException {
        Path commitPath = this.vcPath.resolve(Path.of(vcObject.getHash()));
        FileWriter writer = new FileWriter(String.valueOf(commitPath));

        List<String> writeableVcObject = this.getWriteableFormat(vcObject);
        writeableVcObject.forEach(x -> {
            try {
                writer.append(x).append(System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        writer.close();
    }
}
