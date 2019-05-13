package dto;

import dto.impl.SymbolsDtoImpl;
import entity.Symbol;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SymbolsDtoImplTest {
    private static final List<Symbol> SYMBOLS = new ArrayList<>();

    static {
        SYMBOLS.add(new Symbol('P'));
        SYMBOLS.add(new Symbol('r'));
        SYMBOLS.add(new Symbol('e'));
        SYMBOLS.add(new Symbol('-'));
        SYMBOLS.add(new Symbol('\n'));
        SYMBOLS.add(new Symbol('f'));
        SYMBOLS.add(new Symbol('.'));
        SYMBOLS.add(new Symbol('A'));
        SYMBOLS.add(new Symbol('c'));
        SYMBOLS.add(new Symbol('e'));
        SYMBOLS.add(new Symbol('.'));
        SYMBOLS.add(new Symbol('.'));
        SYMBOLS.add(new Symbol('.'));
        SYMBOLS.add(new Symbol('\n'));
        SYMBOLS.add(new Symbol('J'));
        SYMBOLS.add(new Symbol('a'));

    }
    private SymbolsDtoImpl symbolsDtoImpl =new SymbolsDtoImpl(SYMBOLS);

    @Test
    public void getAllSymbols() {
    }

    @Test
    public void shouldReturnA() {


    }

    @Test
    public void getPrevious() {
    }
}