package factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class TxtTypeReader implements Readable {

    private static final Logger LOGGER = LogManager.getLogger(TxtTypeReader.class);


    @Override
    public StringBuilder read(String pathToFile) {

        StringBuilder textFromFile = new StringBuilder();
        String line;
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(pathToFile))) {
            line = bufferedReader.readLine();
            while (line != null) {
                textFromFile.append(line);
                textFromFile.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            LOGGER.info("The file has been read.");
            return textFromFile;

        } catch (FileNotFoundException e) {
            LOGGER.error("Can not find the file: " + e);
        } catch (IOException e) {
            LOGGER.error(e);

        }
        return null;

    }
}
