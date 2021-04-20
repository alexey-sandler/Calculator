package calculator;

public class RomChar implements Operations {
    private final String[] roman9Symbols = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    private String romSymbol1;
    private String romSymbol2;
    private int romIntSymbol1;
    private int romIntSymbol2;
    private int resultInt;
    private String sign = "";
    private String resultString;
    private final Output out;

    RomChar(String symbol1, String symbol2, Output out) {
        this.romSymbol1 = symbol1;
        this.romSymbol2 = symbol2;
        this.romIntSymbol1 = convertToInt(romSymbol1);
        this.romIntSymbol2 = convertToInt(romSymbol2);
        this.out = out;
    }
    private String convertResultToRomes(int n, int remainder) {
        remainder = n % 10;
        if (remainder != 0) {
            try {
                return convertResultToRomes(n - remainder, 0) + roman9Symbols[remainder - 1];
            } catch (ArrayIndexOutOfBoundsException e) {
                sign = "-";
                return convertResultToRomes(n - remainder, 0) + roman9Symbols[(remainder + 1) * -1];
            }
        }
        //Возможность вывести отрицательное римское число
        if (n > 0) {
            n = n - 10;
            return convertResultToRomes(n,0) + "X";
        } else if (n < 0) {
            n = n + 10;
            return convertResultToRomes(n,0) + "X";
        }   else {
            return sign;
        }
    }

    @Override
    public void sum() {
        resultInt = romIntSymbol1 + romIntSymbol2;
        resultString = convertResultToRomes(resultInt, resultInt);
    }

    @Override
    public void sub() {
        resultInt = romIntSymbol1 - romIntSymbol2;
        resultString = convertResultToRomes(resultInt, resultInt);
    }

    @Override
    public void div() {
        try {
            resultInt = romIntSymbol1 / romIntSymbol2;
            resultString = convertResultToRomes(resultInt, resultInt);
        } catch (ArithmeticException e) {
            out.print("Check if the Roman numerals are entered correctly. Both Arabic and Roman were" +
                    "probably introduced at the same time. ");
        }

    }

    @Override
    public void mul() {
        resultInt = romIntSymbol1 * romIntSymbol2;
        resultString = convertResultToRomes(resultInt, resultInt);
    }

    @Override
    public int getResult() {
        return resultInt;
    }
    public String getStringResult() {
        return resultString;
    }

    private int convertToInt(String romesSymbol){
        char[] valueChar = romesSymbol.toCharArray();
        int[] valuesInt = new int[valueChar.length];
        for (int i = 0; i < valueChar.length; i++) {
            switch (valueChar[i]) {
                case 'I':
                    valuesInt[i] = 1;
                    break;
                case 'V':
                    valuesInt[i] = 5;
                    break;
                case 'X':
                    valuesInt[i] = 10;
                    break;
                default:
                    out.println("Invalid character contained. Correct entry of Roman numerals:"
                            + "\n" +
                            "I = 1" + "\n" +
                            "V = 5" + "\n" +
                            "X = 10");
                    break;
            }
        }
        int result = valuesInt[0];
        for (int i = 0; i < valuesInt.length && valuesInt.length > i + 1; i++) {
            if (valuesInt[i] >= valuesInt[i+1]) {
                result += valuesInt[i+1];
            } else {
                result += (valuesInt[i+1] - 2);
            }
        }
        return result;
    }

    public String getRomSymbol1() {
        return romSymbol1;
    }

    public String getRomSymbol2() {
        return romSymbol2;
    }

    public void setRomSymbol1(String romSymbol1) {
        this.romSymbol1 = romSymbol1;
    }

    public void setRomSymbol2(String romSymbol2) {
        this.romSymbol2 = romSymbol2;
    }

    public int getRomIntSymbol1() {
        return romIntSymbol1;
    }

    public int getRomIntSymbol2() {
        return romIntSymbol2;
    }

    public void setRomIntSymbol1(int romIntSymbol1) {
        this.romIntSymbol1 = romIntSymbol1;
    }

    public void setRomIntSymbol2(int romIntSymbol2) {
        this.romIntSymbol2 = romIntSymbol2;
    }
}