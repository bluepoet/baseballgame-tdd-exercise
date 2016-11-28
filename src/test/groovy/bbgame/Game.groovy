package bbgame
/**
 * Created by bluepoet on 2016. 11. 26..
 */
class Game {
    String number
    GameNumberGenerator gameNumberGenerator

    def generateNumber() {
        this.number = gameNumberGenerator.generate()
    }

    void setGameNumberGenerator(GameNumberGenerator gameNumberGenerator) {
        this.gameNumberGenerator = gameNumberGenerator
    }

    def guess(String guessNum) {
        assertGuessNumberValid(guessNum)
        if(number.equals(guessNum)) {
            return createSolvedResult()
        }else{
            return createNonSolvedResult(guessNum)
        }
    }

    private void assertGuessNumberValid(String guessNum) {
        if (guessNum == null) {
            throw new IllegalArgumentException()
        }

        if (guessNum.length() != 3) {
            throw new IllegalArgumentException()
        }
        for (char ch : guessNum.toCharArray()) {
            if (ch < '0' || ch > '9') {
                throw new IllegalArgumentException()
            }
        }
        if (guessNum.charAt(0) == guessNum.charAt(1) || guessNum.charAt(0) == guessNum.charAt(2)
                || guessNum.charAt(1) == guessNum.charAt(2)) {
            throw new IllegalArgumentException()
        }
    }

    private GuessResult createSolvedResult() {
        new GuessResult(true, 3, 0)
    }

    private GuessResult createNonSolvedResult(String guessNum) {
        int strikes = 0;
        int balls = 0;
        for (int i = 0; i < number.length(); i++) {
            int idx = number.indexOf(guessNum.charAt(i) as int)
            if (idx == i) strikes++;
            else if (idx > -1) balls++;
        }
        return new GuessResult(false, strikes, balls)
    }
}