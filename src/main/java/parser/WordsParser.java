package parser;

import dto.impl.SymbolsDtoImpl;
import entity.Symbol;
import entity.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class WordsParser {

    private static final List<Character> WORD_MAIN_SEPARATORS =
            Arrays.asList('?', '!', ' ', ',', ':', ';', '\n', '—', '"', '“', '”');
    private static final Character DOT = '.';
    private static final Character DASH = '-';
    private static final char NEW_LINE = '\n';

    public List<Word> fillWords(SymbolsDtoImpl symbols) {
        Word word = new Word();

        List<Word> words = new ArrayList<>();

        for (int i = 0; i < symbols.getAllSymbols().size(); i++) {

            Symbol symbol = symbols.getAllSymbols().get(i);

            if (isNotAPunctuationMark(symbol)) {
                word.getWord().add(symbol);

            } else if (dotIsAPartOfWord(symbols, i)) {
                word.getWord().add(symbol);

            } else if (isMainPunctuationMark(symbols, i)
                    && word.getWord().size() > 0) {
                words.add(word);
                word = new Word();

            } else if (word.getWord().size() > 0
                    && !symbol.getSymbol().equals(DASH) && isNotAWordWrap(symbols, i)) {
                words.add(word);
                word = new Word();
            }
        }
        if (word.getWord().size() > 0) {
            words.add(word);
        }

        return words;
    }

    private boolean dotIsAPartOfWord(SymbolsDtoImpl symbols, int i) {
        return symbols.getSymbolByIndex(i).getSymbol().equals(DOT)
                && !symbols.getNextSymbolByIndex(i).getSymbol().equals(DOT) &&
                Stream.of(WORD_MAIN_SEPARATORS)
                        .flatMap(Collection::stream)
                        .noneMatch(separator -> separator.equals(symbols.getNextSymbolByIndex(i).getSymbol())
                        );
    }

    private boolean isNotAWordWrap(SymbolsDtoImpl symbols, int i) {
        return !symbols.getSymbolByIndex(i).getSymbol().equals(NEW_LINE)
                || !symbols.getPreviousSymbolByIndex(i).getSymbol().equals(DASH);
    }

    private boolean isMainPunctuationMark(SymbolsDtoImpl symbols, int i) {
        return WORD_MAIN_SEPARATORS.stream()
                .anyMatch(separator -> separator.equals(symbols.getSymbolByIndex(i).getSymbol())
                        && isNotAWordWrap(symbols, i)
                );
    }

    private boolean isNotAPunctuationMark(Symbol symbol) {
        return Stream.of(WORD_MAIN_SEPARATORS)
                .flatMap(Collection::stream)
                .noneMatch(separator -> separator.equals(symbol.getSymbol()))
                && !symbol.getSymbol().equals(DASH)
                && !symbol.getSymbol().equals(DOT);
    }


}
