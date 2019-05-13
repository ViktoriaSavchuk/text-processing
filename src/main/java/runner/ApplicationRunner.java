package runner;

import dto.impl.SentencesDtoImpl;
import dto.impl.SymbolsDtoImpl;
import dto.impl.WordsDtoImpl;
import factory.TextReader;
import parser.SentenceParser;
import parser.SymbolsParser;
import parser.WordsParser;
import service.SentenceService;

public class ApplicationRunner {

    public void run(String path) {
        TextReader textReader = new TextReader();
        StringBuilder text = textReader.initializeText(path);

        SymbolsParser symbolsParser = new SymbolsParser();
        SymbolsDtoImpl symbols = new SymbolsDtoImpl(symbolsParser.fillSymbols(text));

        WordsParser wordsParser = new WordsParser();
        WordsDtoImpl words = new WordsDtoImpl(wordsParser.fillWords(symbols));

        SentenceParser sentenceParser = new SentenceParser();
        SentencesDtoImpl sentences = new SentencesDtoImpl(sentenceParser.fillSentences(words, symbols));

        SentenceService sentenceService = new SentenceService(sentences);
        System.out.println(sentenceService.wordOfInterrogativeSentencesGivenLength(4));
    }

}
