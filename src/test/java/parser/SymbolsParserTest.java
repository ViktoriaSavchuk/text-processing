package parser;

import dto.impl.SentencesDtoImpl;
import dto.impl.SymbolsDtoImpl;
import dto.impl.WordsDtoImpl;
import entity.Symbol;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SymbolsParserTest {

    private SymbolsParser symbolsParser = new SymbolsParser();

    private static final StringBuilder INPUT_TEXT1 = new StringBuilder()
            .append("Preface..\nJava! 1.2—some? wo");

    private static final StringBuilder INPUT_TEXT2 = new StringBuilder()
            .append("Preface.Java! 1.2some? wo");

    private static final List<Symbol> EXPECTED_SYMBOL_LIST = new ArrayList<>();

    static {
        EXPECTED_SYMBOL_LIST.add(new Symbol('P'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('r'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('e'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('f'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('a'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('c'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('e'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('.'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('.'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('\n'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('J'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('a'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('v'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('a'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('!'));
        EXPECTED_SYMBOL_LIST.add(new Symbol(' '));
        EXPECTED_SYMBOL_LIST.add(new Symbol('1'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('.'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('2'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('—'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('s'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('o'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('m'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('e'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('?'));
        EXPECTED_SYMBOL_LIST.add(new Symbol(' '));
        EXPECTED_SYMBOL_LIST.add(new Symbol('w'));
        EXPECTED_SYMBOL_LIST.add(new Symbol('o'));
    }


    @Test
    public void shouldReturnListOfSymbols() {

        List<Symbol> expected = EXPECTED_SYMBOL_LIST;
        List<Symbol> actual = symbolsParser.fillSymbols(INPUT_TEXT1);
        assertEquals(expected, actual);
    }


    @Test
    public void shouldNotReturnRightList() {
        List<Symbol> expected = EXPECTED_SYMBOL_LIST;
        List<Symbol> actual = symbolsParser.fillSymbols(INPUT_TEXT2);
        assertNotEquals(expected, actual);
    }

    @Test
    public void emptyStringBuilderReturnEmpty() {

        List<Symbol> actualSymbolList = symbolsParser.fillSymbols(new StringBuilder());
        SymbolsDtoImpl actualSymbolsDto = new SymbolsDtoImpl(actualSymbolList);

        List<Symbol> emptySymbolList = new ArrayList<>();
        SymbolsDtoImpl expectedSymbolsDto = new SymbolsDtoImpl(emptySymbolList);

        WordsParser wordsParser = new WordsParser();
        WordsDtoImpl actualWordsDto = new WordsDtoImpl(wordsParser.fillWords(actualSymbolsDto));
        WordsDtoImpl expectedWordsDto = new WordsDtoImpl(wordsParser.fillWords(expectedSymbolsDto));


        SentenceParser sentenceParser = new SentenceParser();
        SentencesDtoImpl actualSentencesDto = new SentencesDtoImpl
                (sentenceParser.fillSentences(actualWordsDto, actualSymbolsDto));
        SentencesDtoImpl expectedSentencesDto = new SentencesDtoImpl
                (sentenceParser.fillSentences(expectedWordsDto, expectedSymbolsDto));


        assertEquals(expectedSymbolsDto.getAllSymbols(), actualSymbolsDto.getAllSymbols());
        assertEquals(expectedWordsDto.getWords(), actualWordsDto.getWords());
        assertEquals(expectedSentencesDto.getAllSentences(), actualSentencesDto.getAllSentences());
    }
}