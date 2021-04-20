package calculator;

public class Calculator {
    private static boolean arabicNumbers = true;
    private final Output out;

     Calculator(Output out) {
        this.out = out;
    }

    private static String[] pars(String input) {
        String[] parsedInput = input.split(" ");
        if (parsedInput.length != 3) {
            Input inputSymbolAgain = new ConsoleInput();
            input = inputSymbolAgain.askStr("Invalid data input format. Enter the expression, separating each character" +
                    "with a space.");
            return pars(input);
        } else {
            return parsedInput;
        }
    }

    public void init(Input inputSymbol, Output output) {
        String input = inputSymbol.askStr("Enter expression: ");
        while (!input.isEmpty()) {
            String[] parsedInput = calculator.Calculator.pars(input);
            String operation = parsedInput[1];
            Operations values;
            int value1 = 0;
            int value2 = 0;
            try {
                value1 = Integer.parseInt(parsedInput[0]);
                value2 = Integer.parseInt(parsedInput[2]);
            } catch (NumberFormatException e) {
                arabicNumbers = false;
                out.println("Introduced the Roman numerals");
            }

            if (arabicNumbers) {
                values = new ArabChar(value1, value2, output);
            } else {
                values = new RomChar(parsedInput[0], parsedInput[2], output);
            }

            try {
            switch (operation) {
                case "+":
                    values.sum();
                    break;
                case "-":
                    values.sub();
                    break;
                case "/":
                    values.div();
                    break;
                case "*":
                    values.mul();
                    break;
                default:
                    throw new Exception("You have selected an invalid operation. Available operations:"
                        + "\n" +
                        "summation: +" + "\n" +
                        "difference: -" + "\n" +
                        "division: /" + "\n" +
                        "multiplication: *");
            }
            } catch (Exception ex) {
                out.println(ex.getMessage());
                break;
            }

            if (arabicNumbers) {
                out.println("Answer: " + values.getResult());
            } else {
                out.println("Answer: " + values.getStringResult());
            }
            input = inputSymbol.askStr("Enter the following expression: ");
        }
        out.println("You are logged out of the calculator");
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input inputSymbol = new ConsoleInput();
        new Calculator(output).init(inputSymbol, output);
     }
}