package factory;

import java.io.IOException;

public interface Readable {

    StringBuilder read(String pathToFile) throws IOException;

}
