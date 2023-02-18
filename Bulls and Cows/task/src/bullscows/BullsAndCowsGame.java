package bullscows;

import java.util.Random;

public class BullsAndCowsGame {
    private final String secretCode;
    private boolean isGameSolvedField;

    public BullsAndCowsGame(int inputDifficulty, int numberOfSymbols) {
        this.secretCode = createAndSetSecretCode(inputDifficulty, numberOfSymbols);
        isGameSolvedField = false;
    }
    public String createAndSetSecretCode(int inputDifficulty, int numberOfSymbols) {
        String code = "";
        if (numberOfSymbols < 11) {
            code = createRandomNumberOnlyIntegers(inputDifficulty);
        } else {
            code = createRandomNumberWithLetters(inputDifficulty, numberOfSymbols);
        }
        return code;
    }
    public String getSecretCode() {
        return secretCode;
    }
    public boolean isGameSolved() { return isGameSolvedField;};

    public String gradeAnswer(String inputCode) {
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < inputCode.length(); i++) {
            char inputChar = inputCode.charAt(i);

            for (int j = 0; j < secretCode.length(); j++) {
                char secretChar = secretCode.charAt(j);

                //Check for bulls
                if ((i == j) && (inputChar == secretChar)) {
                    bulls++;
                    continue;
                }

                //Check for cows
                if (inputChar == secretChar) cows++;
            }
        }
        checkIfGameIsSolved(bulls);

        String output = createOutputText(bulls, cows);
        return output;
    }

    private void checkIfGameIsSolved(int bulls) {
        if (bulls == secretCode.length()) isGameSolvedField = true;
    }

    private String createOutputText(int bulls, int cows){

        if (bulls == 0 && cows == 0) {
            return String.format("Grade: None.");
        }

        if (bulls == 0){
            return String.format("Grade: %s cow(s)", cows);
        }

        if (cows == 0){
            return String.format("Grade: %s bull(s)", bulls);
        }

        return String.format("Grade: %s bull(s) and %s cow(s)", bulls, cows);
    }

    private String createRandomNumberOnlyIntegers(int inputLength) {
        StringBuilder randomNumber = new StringBuilder();
        Random random = new Random();
        int randomNumberTemp;

        //get other chars. Length of randomNumber is value if inputLength String. Each value may only be used once
        for (int i = 0; i < inputLength; i++) {
            do {
                randomNumberTemp = random.nextInt(10);
            } while (randomNumber.toString().contains(String.valueOf(randomNumberTemp)));
            randomNumber.append(randomNumberTemp);
        }
        System.out.println(inputLength);
        System.out.println(randomNumber.toString());
        return randomNumber.toString();
    }

    private String createRandomNumberWithLetters(int inputLength, int numberOfSymbols) {
        StringBuilder randomNumber = new StringBuilder();
        Random random = new Random();
        int randomNumberIntTemp;
        String randomNumberStringTemp = "";

        //get other chars. Length of randomNumber is value if inputLength String. Each value may only be used once
        for (int i = 0; i < inputLength; i++) {
            do {
                randomNumberIntTemp = random.nextInt(numberOfSymbols);
                if (randomNumberIntTemp < 10) {
                    randomNumberStringTemp = String.valueOf(randomNumberIntTemp);
                }
                if (randomNumberIntTemp > 10) {
                    int charValue = 97 - 11 + randomNumberIntTemp;
                    System.out.println((char) charValue);
                    randomNumberStringTemp = String.valueOf((char) charValue);
                }
            } while (randomNumber.toString().contains(randomNumberStringTemp));

            randomNumber.append(randomNumberStringTemp);
        }
        System.out.println(inputLength);
        System.out.println(randomNumber.toString());
        return randomNumber.toString();
    }

    public static String getSymbolsToBeUsedAsText(int numberOfSymbols) {
        if (numberOfSymbols < 11) {
            return ("0-" + (numberOfSymbols - 1));
        }
        int charValue = 97 - 11 + numberOfSymbols;
        return ("0-9, a-" + (char) charValue);
    }
}
