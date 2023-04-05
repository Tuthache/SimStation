package casino;
import mvc.*;

public class Casino extends Model {
    private int die1 = 1, die2 = 1;
    private int wins = 0, losses = 0;
    public void roll() {
        die1 = Utilities.rng.nextInt(5) + 1;
        die2 = Utilities.rng.nextInt(5) + 1;
        int sum = die1 + die2;
        if (sum == 3) losses++;
        if (sum == 7) wins++;
        changed();
    }
    public int getDie1() {
        return die1;
    }
    public int getDie2() {
        return die2;
    }
    public int getWins() {
        return wins;
    }
    public int getLosses() {
        return losses;
    }
}