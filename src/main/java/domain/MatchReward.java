package domain;

import static constants.LottoConstants.*;

public enum MatchReward {
    THREE(3,5000),
    FOUR(4,50000),
    FIVE(5,1500000),
    SIX(6,2000000000),
    NONE(0,0);


    private final int matchCount;
    private final int prize;

    MatchReward(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public static MatchReward of(int matchCount) {
        if(matchCount == 3) {return THREE;}
        if(matchCount ==4){return FOUR;}
        if(matchCount == 5){return FIVE;}
        if(matchCount == 6){return SIX;}
        return NONE;
    }

}
