package controller;

import domain.MoneyToTicket;
import domain.Lotto;
import generator.LottoGenerator;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
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
        System.out.println("구입금액을 입력해 주세요.");
        int money = inputView.getInputMoney();

        int count = MoneyToTicket.moneyToTicket(money, PRICE_PER_TICKET);
        outputView.printPurchasedTicketsMessage(count);

        List<Lotto> tickets = lottoGenerator.generateLottoTickets(count);
        List<List<Integer>> ticketsAsNumbers = toNumberLists(tickets);
        outputView.printLottoNumbers(ticketsAsNumbers);
    }

    private List<List<Integer>> toNumberLists(List<Lotto> tickets) {
        List<List<Integer>> result = new ArrayList<>();
        for (Lotto ticket : tickets) {
            result.add(ticket.getNumbers());
        }
        return result;
    }
}