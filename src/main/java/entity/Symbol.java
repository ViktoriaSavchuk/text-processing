package entity;

import java.util.Objects;

public class Symbol {

    private Character symbol;

    public Symbol(Character symbol) {
        this.symbol = symbol;
    }

    public Symbol() {
    }

    public Character getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol + "";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Symbol symbol1 = (Symbol) o;
        return Objects.equals(symbol, symbol1.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }
}
