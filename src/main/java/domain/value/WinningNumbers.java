package domain.value;

import domain.Lotto;

import java.util.List;
public record WinningNumbers(List<Integer> values) {
    public WinningNumbers {
        new Lotto(values);
    }
}
