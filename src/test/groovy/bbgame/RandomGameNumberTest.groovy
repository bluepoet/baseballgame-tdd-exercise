package bbgame

import spock.lang.Specification

/**
 * Created by bluepoet on 2016. 11. 27..
 */
class RandomGameNumberTest extends Specification {
    def "랜덤으로 생성한 야구숫자를 몇번만에 맞춰는지 테스트한다.(10000번 이상 실행시 강제종료함)"() {
        given:
        def gameNumberGenerator = new RandomGameNumberGenerator()
        def game = new Game()
        game.setGameNumberGenerator(gameNumberGenerator)
        GuessResult guessResult
        def guessNumber
        def guessCount = 0

        when:
        println '[Game Number] : ' + game.generateNumber()
        while (true) {
            try {
                guessNumber = gameNumberGenerator.generate()
                guessResult = game.guess(guessNumber)
                if (guessResult.isSolved()) {
                    println '[' + guessCount + ']matching number : ' + guessNumber + ' : ' + guessResult.toString()
                    break
                }
                println '[' + guessCount + ']no matching number : ' + guessNumber + ' : ' + guessResult.toString()
            } catch (IllegalArgumentException e) {
                println '[' + guessCount + ']invalid number : ' + guessNumber
            }
            guessCount++

            if(guessCount >= 10000) break
        }

        then:
        println 'TotalGuessCount : ' + guessCount
        assert guessCount < 10000
    }
}