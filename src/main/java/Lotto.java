import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private static final int ALL_LOTTO_NUMBERS = 45;
    private static final int SELECTED_LOTTO_NUMBER = 6;

    public List<Integer> generateLottoNumbers() {
        List<Integer> allLottoNumbers = generateAllLottoNumbers();
        List<Integer> shuffledAllLottoNumbers = shuffleAllLottoNumbers(allLottoNumbers);
        List<Integer> pickedLottoNumbers = pickSixLottoNumbers(shuffledAllLottoNumbers);
        return sortLottoNumbers(pickedLottoNumbers);

    }

    private List<Integer> pickSixLottoNumbers(List<Integer> shuffledAllLottoNumbers) {
        return new ArrayList<>(shuffledAllLottoNumbers.subList(0, SELECTED_LOTTO_NUMBER));
    }

    private List<Integer> sortLottoNumbers(List<Integer> lottoNumbers) {
        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }

    private List<Integer> shuffleAllLottoNumbers(List<Integer> allLottoNumbers) {
        Collections.shuffle(allLottoNumbers);
        return allLottoNumbers;
    }

    private List<Integer> generateAllLottoNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= ALL_LOTTO_NUMBERS; i++) {
            lottoNumbers.add(i);
        }
        return lottoNumbers;
    }

}
