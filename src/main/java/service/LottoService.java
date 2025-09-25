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
        public float earningRate;   // 출력에서 double로 받아도 자동 승격됩니다.
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

        result.earningRate = earningRate(paidAmount, (int) result.totalPrizeAmount);
        return result;
    }

    public boolean isTargetNumberContained(Lotto lotto, int targetNumber) {
        List<Integer> lottoNumbers = lotto.getNumbers();
        if(lottoNumbers.contains(targetNumber)) {
            return true;
        }
        return false;
    }

    public int countTargetNumberContained(Lotto lotto, List<Integer> targetNumbers) {
        List<Integer> lottoNumbers = lotto.getNumbers();
        int count = 0;
        for(Integer targetNumber :targetNumbers){
            count += lottoNumbers.contains(targetNumber) ? 1 : 0;
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

    public float earningRate(int inputmoney, int outputmoney) {
        return (float) outputmoney / inputmoney;
    }


}
