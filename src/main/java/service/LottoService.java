package service;

import domain.Lotto;

import java.util.List;

public class LottoService {
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

}
