package parser;

import dto.impl.SymbolsDtoImpl;
import dto.impl.WordsDtoImpl;
import entity.Sentence;
import entity.SentenceSymbol;
import entity.Symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class SentenceParser {

    private static final List<Character> SENTENCE_MAIN_SEPARATORS =
            new ArrayList<>(Arrays.asList('?', '!', ';', '.'));
    private static final List<Character> SENTENCE_SEPARATORS =
            new ArrayList<>(Arrays.asList(' ', '\n'));


    public List<Sentence> fillSentences(WordsDtoImpl words, SymbolsDtoImpl symbols) {
        List<Sentence> sentences = new ArrayList<>();

        Sentence sentence = new Sentence();

        int wordsCounter = 0;

        for (int symbolsIndex = 0; symbolsIndex < symbols.getAllSymbols().size(); symbolsIndex++) {
            Symbol symbol = symbols.getAllSymbols().get(symbolsIndex);

            if (checkSymbolIsFirstNextWordLetter(words, wordsCounter, symbol)) {
                sentence.getSentence().add(words.getWords().get(wordsCounter));
                symbolsIndex = symbolsIndex + words.getWordByIndex(wordsCounter).getWord().size() - 1;
                wordsCounter++;
            } else if (Character.isLetter(symbol.getSymbol())) {
                symbolsIndex++;
            } else if (checkSymbolIsAbsolutelyTheEndOfSentence(symbols, symbolsIndex)) {
                sentence.getSentence().add(new SentenceSymbol(symbol));
                sentence.getSentence().add(new SentenceSymbol(symbols.getNextSymbolByIndex(symbolsIndex)));
                symbolsIndex++;
                sentences.add(sentence);
                sentence = new Sentence();

            } else {
                sentence.getSentence().add(new SentenceSymbol(symbol));
            }
        }
        if (sentence.getSentence().size() > 0) {
            sentences.add(sentence);
        }
        return sentences;
    }


    private boolean checkSymbolIsAbsolutelyTheEndOfSentence(SymbolsDtoImpl symbols, int index) {
        return Stream.of(SENTENCE_MAIN_SEPARATORS)
                .flatMap(Collection::stream)
                .anyMatch(separator -> separator.equals(symbols.getSymbolByIndex(index).getSymbol()))
                && Stream.of(SENTENCE_SEPARATORS).flatMap(Collection::stream).
                anyMatch(separator -> separator.equals(symbols.getNextSymbolByIndex(index).getSymbol()));
    }

    private boolean checkSymbolIsFirstNextWordLetter(WordsDtoImpl words, int wordNumber, Symbol symbol) {
        return wordNumber < words.getWords().size()
                && symbol.getSymbol().equals(words.getWords().get(wordNumber).getWord().get(0).getSymbol());
    }
}
