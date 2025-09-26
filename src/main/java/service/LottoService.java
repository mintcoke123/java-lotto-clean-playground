package service;

import domain.Lotto;
import domain.MatchReward;
import static constants.LottoConstants.*;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
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
            int matched = countTargetNumberContained(ticket, targetNumbers);

            MatchReward reward = MatchReward.of(matched);
            if (reward == MatchReward.THREE) result.threeMatchCount++;
            if (reward == MatchReward.FOUR) result.fourMatchCount++;
            if (reward == MatchReward.FIVE) result.fiveMatchCount++;
            if (reward == MatchReward.SIX) result.sixMatchCount++;

            result.totalPrizeAmount += rewardToPrize(reward);
        }

        result.returnRate = returnRate(paidAmount, (int) result.totalPrizeAmount);
        return result;
    }



    public int plusIfTargetNumberContained(List<Integer> lottoNumbers, int targetNumber) {
        if(lottoNumbers.contains(targetNumber)) {
            return 1;
        }
        return 0;
    }

    public int countTargetNumberContained(Lotto lotto, List<Integer> targetNumbers) {
        List<Integer> lottoNumbers = lotto.getNumbers();
        int count = 0;
        for(Integer targetNumber :targetNumbers){
            count += plusIfTargetNumberContained(lottoNumbers, targetNumber);
        }
        return count;
    }


    public int correctCountToPrize(int correctCount) {
        if (correctCount == 3) return 5000;
        if (correctCount == 4) return 50000;
        if (correctCount == 5) return 1500000;
        if (correctCount == 6) return 2000000000;
        return 0;
    }

    public int rewardToPrize(MatchReward reward) {
        if (reward == MatchReward.THREE) return MATCH_THREE_PRIZE;
        if (reward == MatchReward.FOUR) return MATCH_FOUR_PRIZE;
        if (reward == MatchReward.FIVE) return MATCH_FIVE_PRIZE;
        if (reward == MatchReward.SIX) return MATCH_SIX_PRIZE;
        return 0;
    }

    public float returnRate(int paidAmount, int prizeAmount) {
        return (float) paidAmount / prizeAmount;
    }


}
