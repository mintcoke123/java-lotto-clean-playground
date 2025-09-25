package controller;

import domain.MoneyToTicket;
import domain.Lotto;
import domain.ParseInputStringToNumbers;
import generator.LottoGenerator;
import service.LottoService;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {
    private static final int PRICE_PER_TICKET = 1000;

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
        int count = MoneyToTicket.moneyToTicket(money, PRICE_PER_TICKET);
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
        outputView.printResultMessage(3,  5000,       result.threeMatchCount);
        outputView.printResultMessage(4,  50000,      result.fourMatchCount);
        outputView.printResultMessage(5,  1500000,    result.fiveMatchCount);
        outputView.printResultMessage(6,  2000000000, result.sixMatchCount);
        outputView.printTotalBenefitResultMessage(result.earningRate);
    }
}
