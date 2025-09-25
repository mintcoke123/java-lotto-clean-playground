package test;

import domain.Lotto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LottoTest {

    @Test
    void 숫자_6개_중복없고_범위내면_생성된다() {
        // given
        List<Integer> numbers = Arrays.asList(1, 7, 15, 23, 34, 45);
        // when & then
        assertDoesNotThrow(() -> new Lotto(numbers));
    }

    @Test
    void 숫자_개수가_6개가_아니면_예외가_발생한다() {
        // given
        List<Integer> fiveList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> sevenList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        // when & then
        assertThrows(IllegalArgumentException.class, () -> new Lotto(fiveList));
        assertThrows(IllegalArgumentException.class, () -> new Lotto(sevenList));
    }

    @Test
    void 중복된_숫자가_있으면_예외가_발생한다() {
        // given
        List<Integer> duplication = Arrays.asList(1, 2, 3, 4, 5, 5);
        // when & then
        assertThrows(IllegalArgumentException.class, () -> new Lotto(duplication));
    }



}
