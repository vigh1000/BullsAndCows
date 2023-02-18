package bullscows;

import javax.print.DocFlavor;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        p("Input the length of the secret code:");
        int inputLength = getInputAsInt();
        if (inputLength == 0) {
            p("Error:");
            return;
        }

        p("Input the number of possible symbols in the code:");
        int inputNumberOfSymbols = getInputAsInt();
        if (inputNumberOfSymbols == 0) {
            p("Error:");
            return;
        }

        String inputCheck = checkIfBeginningInputIsCorrect( inputLength, inputNumberOfSymbols);
        if (inputCheck != "") {
            p(inputCheck);
            return;
        }

        BullsAndCowsGame game = new BullsAndCowsGame(inputLength, inputNumberOfSymbols);
        String hiddenSecretCode = "*".repeat(inputLength);
        p(String.format("The secret is prepared: %s %s.", hiddenSecretCode, game.getSymbolsToBeUsedAsText(inputNumberOfSymbols)));

        p("Okay, let's start a game!");
        int turnCounter = 1;
        String inputSolution;
        do {
            p(String.format("Turn %s:", turnCounter));
            inputSolution = getInput();
            p(game.gradeAnswer(inputSolution));

        } while (!game.isGameSolved());

        p("Congratulations! You guessed the secret code.");

    }

    private static String checkIfBeginningInputIsCorrect(int inputLength, int inputNumberOfSymbols) {
        if (inputLength > inputNumberOfSymbols) {
            return String.format("Error: it's not possible to generate a code with a length of %s with %s unique symbols.", inputLength, inputNumberOfSymbols);
        }

        if (inputLength > 36) {
            return "Error: can't generate a secret number with a length of 37 because not even DOJO will be able to solve it.";
        }

        if (inputNumberOfSymbols > 36) {
            return "Error: maximum number of possible symbols in the code is 36 (0-9, a-z).";
        }

        if (inputLength == 0) {
            return "Error: Can't create a secret code with 0 length.";
        }

        return "";
    }

    private static String getInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        return input;
    }

    private static int getInputAsInt() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        int inputInt = 0;
        try {
            inputInt = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            p(String.format("Error: %s isn't a valid number.", input));
        }
        return inputInt;
    }

    public static void p(String output) {
        System.out.println(output);
    }
}
