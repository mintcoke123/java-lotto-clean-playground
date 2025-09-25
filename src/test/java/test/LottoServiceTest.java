package test;

import domain.Lotto;
import org.junit.jupiter.api.Test;
import service.LottoService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoServiceTest {

    @Test
    void 매칭수_세고_집계와_수익률을_계산한다() {
        // given
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        Lotto ticketAllMatch       = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));      // 6개
        Lotto ticketThreeMatchOne  = new Lotto(Arrays.asList(1, 2, 3, 7, 8, 9));      // 3개
        Lotto ticketNoMatch        = new Lotto(Arrays.asList(10, 11, 12, 13, 14, 15));// 0개
        Lotto ticketThreeMatchTwo  = new Lotto(Arrays.asList(4, 5, 6, 10, 11, 12));   // 3개

        List<Lotto> purchasedTickets = Arrays.asList(
                ticketAllMatch, ticketThreeMatchOne, ticketNoMatch, ticketThreeMatchTwo
        );
        int paidAmount = purchasedTickets.size() * 1000; // 4000원

        LottoService lottoService = new LottoService();

        // when
        LottoService.Result result =
                lottoService.calculate(purchasedTickets, winningNumbers, paidAmount);

        // then
        assertEquals(2, result.threeMatchCount);
        assertEquals(0, result.fourMatchCount);
        assertEquals(0, result.fiveMatchCount);
        assertEquals(1, result.sixMatchCount);

        // 총 상금: 2,000,000,000 + 5,000 + 5,000 = 2,000,010,000
        assertEquals(2_000_010_000L, result.totalPrizeAmount);

        // 수익률 = 2,000,010,000 / 4,000 = 500002.5
        assertEquals(500_002.5f, result.earningRate, 0.0001f);
    }

    @Test
    void correctCountToPrize_매핑() {
        LottoService lottoService = new LottoService();
        assertEquals(0,             lottoService.correctCountToPrize(0));
        assertEquals(5_000,         lottoService.correctCountToPrize(3));
        assertEquals(50_000,        lottoService.correctCountToPrize(4));
        assertEquals(1_500_000,     lottoService.correctCountToPrize(5));
        assertEquals(2_000000_000, lottoService.correctCountToPrize(6));
    }

    @Test
    void countTargetNumberContained_정상동작() {
        LottoService lottoService = new LottoService();
        Lotto ticket = new Lotto(Arrays.asList(1, 2, 3, 10, 11, 12));
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertEquals(3, lottoService.countTargetNumberContained(ticket, winningNumbers));
    }
}
