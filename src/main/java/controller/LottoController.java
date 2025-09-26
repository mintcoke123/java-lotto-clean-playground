package controller;

import domain.CalculateTicketCount;
import domain.Lotto;
import domain.MatchReward;
import domain.ParseInputStringToNumbers;
import generator.LottoGenerator;
import service.LottoService;
import view.InputView;
import view.OutputView;

import java.util.List;

import static constants.LottoConstants.*;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator lottoGenerator;

    public LottoController(InputView inputView, OutputView outputView, LottoGenerator lottoGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        // 1) 금액 입력
        outputView.printPurchaseMessage();
        int money = inputView.getInputMoney();

        // 2) 티켓 생성 및 출력
        int count = CalculateTicketCount.calculateTicketCount(money, PRICE_PER_TICKET);
        outputView.printPurchasedTicketsMessage(count);

        List<Lotto> tickets = lottoGenerator.generateLottoTickets(count);
        outputView.printLottoNumbers(tickets);

        // 3) 당첨 번호 입력
        outputView.printTargetNumberMessage();
        String inputTargetNumbers = inputView.getInputTargetNumber();
        List<Integer> targetNumbers = ParseInputStringToNumbers.parse(inputTargetNumbers);

        // 4) 수익률
        LottoService lottoService = new LottoService();
        LottoService.Result result = lottoService.calculate(tickets, targetNumbers, money);

        // 5) 출력
        outputView.printStatusMessage();
        outputView.printResultMessage(3, MatchReward.THREE.getPrize(), result.threeMatchCount);
        outputView.printResultMessage(4, MatchReward.FOUR.getPrize(),  result.fourMatchCount);
        outputView.printResultMessage(5, MatchReward.FIVE.getPrize(),  result.fiveMatchCount);
        outputView.printResultMessage(6, MatchReward.SIX.getPrize(),   result.sixMatchCount);
        outputView.printTotalBenefitResultMessage(result.returnRate);
    }
}
