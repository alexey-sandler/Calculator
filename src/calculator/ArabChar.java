package calculator;

public class ArabChar implements Operations {
    private int symbol1;
    private int symbol2;
    private int rsl;
    private final Output out;

    ArabChar(int symbol1, int symbol2, Output out) {
        this.symbol1 = symbol1;
        this.symbol2 = symbol2;
        this.out = out;
    }

    public void sum() {
        this.rsl = symbol1 + symbol2;
    }

    public void sub() {
        this.rsl = symbol1 - symbol2;
    }

    public void div() {
        try{
            this.rsl = symbol1 / symbol2;
        } catch (ArithmeticException e) {
            out.print("Division by zero is not allowed! ");
        }
    }

    public void mul() {
        this.rsl = symbol1 * symbol2;
    }

    @Override
    public int getResult() {
        return rsl;
    }

    @Override
    public String getStringResult() {
        return "" + rsl;
    }

    public int getSymbol1() {
        return symbol1;
    }

    public int getSymbol2() {
        return symbol2;
    }

    public void setRsl(int result) {
        this.rsl = result;
    }

    public void setSymbol1(int value1) {
        this.symbol1 = value1;
    }

    public void setSymbol2(int value2) {
        this.symbol2 = value2;
    }
}
