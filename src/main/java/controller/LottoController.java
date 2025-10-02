package controller;

import domain.CalculateTicketCount;
import domain.Lotto;
import domain.MatchReward;
import domain.ParseInputStringToNumbers;
import generator.LottoGenerator;
import domain.LottoPrizeCalculator;
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
        // 1) 금액 입력
        int purchaseAmount = getValidPurchaseAmount();

        // 2) 티켓 생성 및 출력
        int purchasedTicketCount = CalculateTicketCount.calculateTicketCount(purchaseAmount, PRICE_PER_TICKET);
        outputView.printManualCountMessage();
        int manualLottoTicketCount = inputView.getInputManualLottoCount();
        int autoLottoTicketCount = purchasedTicketCount - manualLottoTicketCount;
        outputView.printPurchasedTicketsMessage(manualLottoTicketCount, autoLottoTicketCount);
        List<Lotto> manualLottoTickets = getManualTickets(manualLottoTicketCount);
        List<Lotto> autoLottoTickets = lottoGenerator.generateLottoTickets(autoLottoTicketCount);
        List<Lotto> purchasedTickets = new ArrayList<>(manualLottoTickets);
        purchasedTickets.addAll(autoLottoTickets);
        outputView.printLottoNumbers(purchasedTickets);

        // 3-1) 당첨 번호 입력
        List<Integer> winningNumbers = getValidWinningNumbers();

        // 3-2) 보너스 번호 입력
        outputView.printBonusNumberMessage();
        int bonusNumber = inputView.getInputBonusNumber();

        // 4) 수익률
        LottoPrizeCalculator lottoPrizeCalculator = new LottoPrizeCalculator();
        LottoPrizeCalculator.Result result = lottoPrizeCalculator.calculate(purchasedTickets, winningNumbers, purchaseAmount,bonusNumber);

        outputView.printStatusMessage();
        outputView.printResultMessage(3, MatchReward.THREE.getPrize(), result.threeMatchCount);
        outputView.printResultMessage(4, MatchReward.FOUR.getPrize(), result.fourMatchCount);
        outputView.printResultMessage(5, MatchReward.FIVE.getPrize(), result.fiveMatchCount);
        outputView.printBonusResultMessage(5,MatchReward.BONUSFIVE.getPrize(), result.fiveBonusMatchCount);
        outputView.printResultMessage(6, MatchReward.SIX.getPrize(), result.sixMatchCount);
        outputView.printTotalBenefitResultMessage(result.returnRate);
    }

    private int getValidPurchaseAmount() {
        int purchaseAmount;
        do {
            outputView.printPurchaseMessage();
            purchaseAmount = inputView.getInputMoney();
            if (purchaseAmount <= 0) {
                outputView.printInvalidPurchaseAmountMessage();
            }
        } while (purchaseAmount <= 0);
        return purchaseAmount;
    }

    private List<Integer> getValidWinningNumbers() {
        while (true) {
            try {
                outputView.printTargetNumberMessage();
                String rawInput = inputView.getInputTargetNumber();
                List<Integer> parsedNumbers = ParseInputStringToNumbers.parse(rawInput);
                new Lotto(parsedNumbers);
                return parsedNumbers;
            } catch (IllegalArgumentException e) {
                String message = e.getMessage();
                if (message.contains("6개")) {
                    outputView.printInvalidLottoSizeMessage();
                } else if (message.contains("중복")) {
                    outputView.printDuplicateLottoNumberMessage();
                } else if (message.contains("1이상 45이하")) {
                    outputView.printOutOfRangeLottoNumberMessage();
                } else {
                    System.out.println("[ERROR] " + message);
                }
            }
        }
    }

    private List<Lotto> getManualTickets(int manualCount) {
        List<Lotto> manualTickets = new ArrayList<>();
        if (manualCount == 0) return manualTickets;

        outputView.printManualNumberMessage();
        List<String> lottoNumberLines = inputView.getInputManualLottoNumbers(manualCount);

        for (String lottoNumberLine : lottoNumberLines) {
            List<Integer> manualLottoNumbers = ParseInputStringToNumbers.parse(lottoNumberLine);
            manualTickets.add(new Lotto(manualLottoNumbers));
        }
        return manualTickets;
    }
}
