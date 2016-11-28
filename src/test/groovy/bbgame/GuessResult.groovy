package bbgame

/**
 * Created by bluepoet on 2016. 11. 26..
 */
class GuessResult {
    def solved
    def strikes
    def balls

    GuessResult(solved, strikes, balls) {
        this.solved = solved
        this.strikes = strikes
        this.balls = balls
    }

    def isSolved() {
        return solved
    }

    def getStrikes() {
        return strikes
    }

    def getBalls() {
        return balls
    }

    @Override
    def String toString() {
        return strikes + 'strikes, ' + balls + 'balls'
    }
}
