package domain;

import java.util.*;

public class Lotto {
    private static final int LOTTO_NUMBER_SIZE = 6;
    private static final int MAX = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
        if (new HashSet<>(numbers).size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("중복된 번호가 있습니다.");
        }
        for (int n : numbers) {
            if (n < 1 || n > MAX) {
                throw new IllegalArgumentException("번호는 1이상 45이하 범위여야 합니다.");
            }
        }
    }


    public List<Integer> getNumbers() {
        return numbers;
    }


}