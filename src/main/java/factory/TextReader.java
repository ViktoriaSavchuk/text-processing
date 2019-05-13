package factory;

import exception.IncompatibleTextFileType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextReader {

    private static final String TYPE_REGEX = "\\.[A-z]{3,4}$";
    private static final Logger LOGGER = LogManager.getLogger(TextReader.class);


    public StringBuilder initializeText(String pathToFile) {

        String type = null;
        try {
            type = getFileType(pathToFile);
            LOGGER.info("File type was matched: " + type);
        } catch (Exception e) {
            LOGGER.error("Can not parse file type: " + e);
            System.out.println("You have entered an invalid path or file type");
        }

        switch (type) {
            case ".pdf": {
                System.out.println("Working with pdf type file");
                return new PdfTypeReader().read(pathToFile);
            }
            case ".txt": {
                System.out.println("Working with txt type file");
                return new TxtTypeReader().read(pathToFile);
            }
            default: {
                throw new IncompatibleTextFileType();
            }
        }
    }

    private String getFileType(String pathToFile) {

        Matcher matcher = Pattern.compile(TYPE_REGEX)
                .matcher(pathToFile);

        return matcher.find() ? matcher.group(0) : null;

    }
}
