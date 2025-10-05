package test;

import domain.Lotto;
import generator.ManualLottoTicketsGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManualLottoTicketsGeneratorTest {

    @Test
    void 문자열리스트를_전달하면_수동로또티켓리스트가_생성된다() {
        // given
        List<String> lottoNumberLines = List.of(
                "1,2,3,4,5,6",
                "7,8,9,10,11,12"
        );

        // when
        List<Lotto> manualTickets = ManualLottoTicketsGenerator.createManualTickets(lottoNumberLines);

        // then
        assertEquals(2, manualTickets.size());
        assertEquals(List.of(1, 2, 3, 4, 5, 6), manualTickets.get(0).getNumbers());
        assertEquals(List.of(7, 8, 9, 10, 11, 12), manualTickets.get(1).getNumbers());
    }


    @Test
    void 잘못된형식의문자열이면_예외가발생한다() {
        // given
        List<String> lottoNumberLines = List.of("1,2,a,b,3,4");

        // when & then
        assertThrows(NumberFormatException.class,
                () -> ManualLottoTicketsGenerator.createManualTickets(lottoNumberLines));
    }
}
