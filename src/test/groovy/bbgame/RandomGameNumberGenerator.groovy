package bbgame

import java.util.concurrent.ThreadLocalRandom

/**
 * Created by bluepoet on 2016. 11. 27..
 */
class RandomGameNumberGenerator implements GameNumberGenerator {
    @Override
    String generate() {
        def gameNumber = ''
        3.times {
            def randomNumber = ThreadLocalRandom.current().nextInt(0, 10) as String
            gameNumber += randomNumber
        }
        return gameNumber
    }
}
