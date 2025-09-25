package generator;

import domain.Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {

    private static final int ALL_LOTTO_NUMBER_SIZE = 45;
    private static final int LOTTO_NUMBER_SIZE = 6;

    public Lotto generateLottoNumbers() {
        List<Integer> allLottoNumbers = generateAllLottoNumbers();
        List<Integer> shuffledAllLottoNumbers = shuffleAllLottoNumbers(allLottoNumbers);
        List<Integer> pickedLottoNumbers = pickSixLottoNumbers(shuffledAllLottoNumbers);
        List<Integer> sortedLottoNumbers = sortLottoNumbers(pickedLottoNumbers);
        return new Lotto(sortedLottoNumbers);
    }

    public List<Lotto> generateLottoTickets(int count) {
        List<Lotto> tickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            tickets.add(generateLottoNumbers());
        }
        return tickets;
    }


    private List<Integer> pickSixLottoNumbers(List<Integer> shuffledAllLottoNumbers) {
        return new ArrayList<>(shuffledAllLottoNumbers.subList(0, LOTTO_NUMBER_SIZE));
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
        for (int i = 1; i <= ALL_LOTTO_NUMBER_SIZE; i++) {
            lottoNumbers.add(i);
        }
        return lottoNumbers;
    }
}
