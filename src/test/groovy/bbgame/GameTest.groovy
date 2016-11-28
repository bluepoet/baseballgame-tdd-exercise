package bbgame

import spock.lang.Specification

import java.util.concurrent.ThreadLocalRandom

/**
 * Created by bluepoet on 2016. 11. 25..
 */
class GameTest extends Specification {
    def game

    void setup() {
        game = new Game()
    }

    def "givenInvalidGuessNumber_throwArgEx"() {
        when:
        game.guess(guessNum)

        then:
        thrown expectedException

        where:
        guessNum | expectedException
        null | IllegalArgumentException
        "12" | IllegalArgumentException
        "12a" | IllegalArgumentException
        "a45" | IllegalArgumentException
        "113" | IllegalArgumentException
        "141" | IllegalArgumentException
        "011" | IllegalArgumentException
    }

    def "givenExactWatchingGuessNum_returnSolvedResult"() {
        when:
        generateGameNumber(gameNumber)
        GuessResult guessResult = game.guess(guessNumber)

        then:
        guessResult.isSolved() == isSolved
        guessResult.getStrikes() == strikes
        guessResult.getBalls() == balls

        where:
        gameNumber | guessNumber | isSolved | strikes | balls
        "123"      | "123"       | true     | 3       | 0
        "456"      | "456"       | true     | 3       | 0
        "123"      | "789"       | false    | 0       | 0
        "123"      | "024"       | false    | 1       | 0
        "123"      | "103"       | false    | 2       | 0
        "123"      | "023"       | false    | 2       | 0
        "123"      | "124"       | false    | 2       | 0
        "123"      | "204"       | false    | 0       | 1
        "123"      | "035"       | false    | 0       | 1
        "123"      | "314"       | false    | 0       | 2
        "123"      | "132"       | false    | 1       | 2
        "123"      | "134"       | false    | 1       | 1
        "123"      | "321"       | false    | 1       | 2
    }

    def generateGameNumber(gameNumber) {
        game.setGameNumberGenerator(new GameNumberGenerator() {
            String generate() {
                return gameNumber
            }
        });
        game.generateNumber()
    }
}
