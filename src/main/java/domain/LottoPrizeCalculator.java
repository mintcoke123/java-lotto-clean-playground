package domain;

import java.util.List;

public class LottoPrizeCalculator {
    public static class Result {
        public int threeMatchCount;
        public int fourMatchCount;
        public int fiveMatchCount;
        public int sixMatchCount;
        public long totalPrizeAmount;
        public float returnRate;
    }

    public Result calculate(List<Lotto> tickets, List<Integer> targetNumbers, int paidAmount) {
        Result result = new Result();

        for (Lotto ticket : tickets) {
            int matched = ticket.countMatches(targetNumbers);

            MatchReward reward = MatchReward.of(matched);
            if (reward == MatchReward.THREE) result.threeMatchCount++;
            if (reward == MatchReward.FOUR)  result.fourMatchCount++;
            if (reward == MatchReward.FIVE)  result.fiveMatchCount++;
            if (reward == MatchReward.SIX)   result.sixMatchCount++;

            result.totalPrizeAmount += rewardToPrize(reward);
        }

        result.returnRate = returnRate(paidAmount, (int) result.totalPrizeAmount);
        return result;
    }




    public int rewardToPrize(MatchReward reward) {
        return reward.getPrize();
    }



    public float returnRate(int paidAmount, int prizeAmount) {
        return (float) prizeAmount/paidAmount;
    }


}
