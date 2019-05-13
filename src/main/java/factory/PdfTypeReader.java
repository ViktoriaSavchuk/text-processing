package factory;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class PdfTypeReader implements Readable {

    private static final Logger LOGGER = LogManager.getLogger(PdfTypeReader.class);

    @Override
    public StringBuilder read(String pathToFile) {

        PdfReader pdfReader = null;
        try {
            pdfReader = new PdfReader(pathToFile);
            StringBuilder textFromFile = new StringBuilder();
            PdfReaderContentParser parser = new PdfReaderContentParser(pdfReader);
            TextExtractionStrategy strategy;

            for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
                strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
                textFromFile.append(strategy.getResultantText());
            }

            LOGGER.info("The file has been read.");
            return textFromFile;

        } catch (IOException e) {
            LOGGER.error("Not able to read file " + pathToFile, e);

        } finally {
            if (pdfReader != null) {
                pdfReader.close();
            }
        }
        return null;
    }
}
