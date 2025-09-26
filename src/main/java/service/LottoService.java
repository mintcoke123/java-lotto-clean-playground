package service;

import domain.Lotto;

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

            if (matched == 3) result.threeMatchCount++;
            if (matched == 4) result.fourMatchCount++;
            if (matched == 5) result.fiveMatchCount++;
            if (matched == 6) result.sixMatchCount++;

            result.totalPrizeAmount += correctCountToPrize(matched);
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

    public float returnRate(int paidAmount, int prizeAmount) {
        return (float) paidAmount / prizeAmount;
    }


}
