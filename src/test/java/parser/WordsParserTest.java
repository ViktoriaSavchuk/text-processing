package parser;

import dto.impl.SymbolsDtoImpl;
import entity.Symbol;
import entity.Word;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class WordsParserTest {


    private static final WordsParser WORDS_PARSER = new WordsParser();

    private static final Symbol LOWER_CASE_LETTER = new Symbol('j');
    private static final Symbol UPPER_CASE_LETTER = new Symbol('J');
    private static final Symbol DASH = new Symbol('-');
    private static final List<Character> WORD_MAIN_SEPARATORS =
            Arrays.asList('?', '!', ' ', ',', ':', ';', '\n', '—', '"', '“', '”','(',')');

    private static final List<Symbol> SYMBOLS = new ArrayList<>();

    static {
        SYMBOLS.add(new Symbol('P'));
        SYMBOLS.add(new Symbol('r'));
        SYMBOLS.add(new Symbol('e'));
        SYMBOLS.add(new Symbol('-'));
        SYMBOLS.add(new Symbol('\n'));
        SYMBOLS.add(new Symbol('f'));
        SYMBOLS.add(new Symbol('a'));
        SYMBOLS.add(new Symbol('c'));
        SYMBOLS.add(new Symbol('e'));
        SYMBOLS.add(new Symbol('.'));
        SYMBOLS.add(new Symbol('.'));
        SYMBOLS.add(new Symbol('.'));
        SYMBOLS.add(new Symbol('\n'));
        SYMBOLS.add(new Symbol('J')); //13
        SYMBOLS.add(new Symbol('a'));
        SYMBOLS.add(new Symbol('v'));
        SYMBOLS.add(new Symbol('a'));
        SYMBOLS.add(new Symbol('!'));
        SYMBOLS.add(new Symbol(' '));
        SYMBOLS.add(new Symbol('1'));
        SYMBOLS.add(new Symbol('.'));
        SYMBOLS.add(new Symbol('2'));
        SYMBOLS.add(new Symbol('—'));
        SYMBOLS.add(new Symbol('s'));
        SYMBOLS.add(new Symbol('o'));
        SYMBOLS.add(new Symbol('m'));
        SYMBOLS.add(new Symbol('e'));//26
        SYMBOLS.add(new Symbol('?'));
        SYMBOLS.add(new Symbol(' '));
        SYMBOLS.add(new Symbol('w'));
        SYMBOLS.add(new Symbol('o'));
    }

    private static SymbolsDtoImpl SYMBOLS_DTO = new SymbolsDtoImpl(SYMBOLS);

    private static List<Word> EXPECTED_WORDS = new ArrayList<>();

    static {
        Word word1 = new Word();
        word1.getWord().add(SYMBOLS.get(0));
        word1.getWord().add(SYMBOLS.get(1));
        word1.getWord().add(SYMBOLS.get(2));
        word1.getWord().add(SYMBOLS.get(5));
        word1.getWord().add(SYMBOLS.get(6));
        word1.getWord().add(SYMBOLS.get(7));
        word1.getWord().add(SYMBOLS.get(8));

        Word word2 = new Word();
        word2.getWord().add(SYMBOLS.get(13));
        word2.getWord().add(SYMBOLS.get(14));
        word2.getWord().add(SYMBOLS.get(15));
        word2.getWord().add(SYMBOLS.get(16));

        Word word3 = new Word();
        word3.getWord().add(SYMBOLS.get(19));
        word3.getWord().add(SYMBOLS.get(20));
        word3.getWord().add(SYMBOLS.get(21));

        Word word4 = new Word();
        word4.getWord().add(SYMBOLS.get(23));
        word4.getWord().add(SYMBOLS.get(24));
        word4.getWord().add(SYMBOLS.get(25));
        word4.getWord().add(SYMBOLS.get(26));

        Word word5 = new Word();
        word5.getWord().add(SYMBOLS.get(29));
        word5.getWord().add(SYMBOLS.get(30));

        EXPECTED_WORDS.add(word1);
        EXPECTED_WORDS.add(word2);
        EXPECTED_WORDS.add(word3);
        EXPECTED_WORDS.add(word4);
        EXPECTED_WORDS.add(word5);
    }


    private Method method;

    private void makeMethodWithOneArgumentAccessible(String methodName) throws NoSuchMethodException {
        method = WORDS_PARSER.getClass().getDeclaredMethod(methodName, Symbol.class);
        method.setAccessible(true);
    }

    private void makeMethodWithTwoArgumentsAccessible(String methodName) throws NoSuchMethodException {
        method = WORDS_PARSER.getClass().getDeclaredMethod(methodName, SymbolsDtoImpl.class, int.class);
        method.setAccessible(true);
    }

    @Test
    public void shouldReturnTrueForAnyLetter() throws Exception {
        makeMethodWithOneArgumentAccessible("isNotAPunctuationMark");
        assertEquals(true, method.invoke(WORDS_PARSER, LOWER_CASE_LETTER));
        assertEquals(true, method.invoke(WORDS_PARSER, UPPER_CASE_LETTER));
    }

    @Test
    public void shouldReturnFalseForDash() throws Exception {
        makeMethodWithOneArgumentAccessible("isNotAPunctuationMark");
        assertEquals(false, method.invoke(WORDS_PARSER, DASH));
    }

    @Test
    public void shouldReturnFalseForMainSeparators() throws Exception {
        makeMethodWithOneArgumentAccessible("isNotAPunctuationMark");

        for (Character separator : WORD_MAIN_SEPARATORS) {
            assertEquals(false, method.invoke(WORDS_PARSER, new Symbol(separator)));
        }
    }

    @Test
    public void shouldReturnTrueForMainSeparators() throws Exception {
        makeMethodWithTwoArgumentsAccessible("isMainPunctuationMark");

        assertEquals(true, method.invoke(WORDS_PARSER, SYMBOLS_DTO,
                SYMBOLS_DTO.getAllSymbols().indexOf(new Symbol('!'))));
        assertEquals(true, method.invoke(WORDS_PARSER, SYMBOLS_DTO,
                SYMBOLS_DTO.getAllSymbols().indexOf(new Symbol('?'))));
    }

    @Test
    public void shouldReturnFalseForDot() throws Exception {
        makeMethodWithTwoArgumentsAccessible("isMainPunctuationMark");
        assertEquals(false, method.invoke(WORDS_PARSER, SYMBOLS_DTO, 9));
    }

    @Test
    public void dashIsWordWrap() throws Exception {
        makeMethodWithTwoArgumentsAccessible("isNotAWordWrap");
        System.out.println(SYMBOLS_DTO.getSymbolByIndex(4));
        assertEquals(false, method.invoke(WORDS_PARSER, SYMBOLS_DTO, 4));
    }

    @Test
    public void newLineIsNotAPartOfWordWrap() throws Exception {
        makeMethodWithTwoArgumentsAccessible("isNotAWordWrap");
        assertEquals(true, method.invoke(WORDS_PARSER, SYMBOLS_DTO,
                SYMBOLS_DTO.getAllSymbols().lastIndexOf(new Symbol('\n'))));
    }

    @Test
    public void someSymbolIsNotAPartOfWordWrap() throws Exception {
        makeMethodWithTwoArgumentsAccessible("isNotAWordWrap");
        assertEquals(true, method.invoke(WORDS_PARSER, SYMBOLS_DTO, 2));
    }

    @Test
    public void dotIsAPartOfWord() throws Exception {
        makeMethodWithTwoArgumentsAccessible("dotIsAPartOfWord");
        assertEquals(true, method.invoke(WORDS_PARSER, SYMBOLS_DTO, 20));
    }

    @Test
    public void dotIsNotAPartOfWord() throws Exception {
        makeMethodWithTwoArgumentsAccessible("dotIsAPartOfWord");
        assertEquals(false, method.invoke(WORDS_PARSER, SYMBOLS_DTO, 9));
    }

    @Test
    public void shouldReturnExpectedWords() {
        List<Word> expected = EXPECTED_WORDS;
        List<Word> actual = WORDS_PARSER.fillWords(SYMBOLS_DTO);
        assertEquals(expected, actual);
    }
}




