package controller;

import domain.CalculateTicketCount;
import domain.Lotto;
import domain.MatchReward;
import domain.ParseInputStringToNumbers;
import generator.AutoLottoTicketsGenerator;
import domain.LottoPrizeCalculator;
import generator.ManualLottoTicketsGenerator;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private static final int PRICE_PER_TICKET = 1000;


    private final InputView inputView;
    private final OutputView outputView;
    private final AutoLottoTicketsGenerator lottoGenerator;

    public LottoController(InputView inputView, OutputView outputView, AutoLottoTicketsGenerator lottoGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        int purchaseAmount = getValidPurchaseAmount();
        List<Lotto> purchasedTickets = getValidPurchasedTickets(purchaseAmount);
        List<Integer> winningNumbers = getValidWinningNumbers();
        int bonusNumber = getValidBonusNumber(winningNumbers);

        LottoPrizeCalculator lottoPrizeCalculator = new LottoPrizeCalculator();
        LottoPrizeCalculator.Result result = lottoPrizeCalculator.calculate(purchasedTickets, winningNumbers, purchaseAmount,bonusNumber);

        printResult(result);
    }

    private void printResult(LottoPrizeCalculator.Result result) {
        outputView.printStatusMessage();
        outputView.printResultMessage(3, MatchReward.THREE.getPrize(), result.threeMatchCount);
        outputView.printResultMessage(4, MatchReward.FOUR.getPrize(), result.fourMatchCount);
        outputView.printResultMessage(5, MatchReward.FIVE.getPrize(), result.fiveMatchCount);
        outputView.printBonusResultMessage(5, MatchReward.BONUSFIVE.getPrize(), result.fiveBonusMatchCount);
        outputView.printResultMessage(6, MatchReward.SIX.getPrize(), result.sixMatchCount);
        outputView.printTotalBenefitResultMessage(result.returnRate);
    }

    private int getValidBonusNumber(List<Integer> winningNumbers) {
        while (true) {
            outputView.printBonusNumberMessage();
            int bonus = inputView.getInputBonusNumber();
            if (bonus < 1 || bonus > Lotto.ALL_LOTTO_NUMBER_SIZE) {
                outputView.printOutOfRangeLottoNumberMessage();
                continue;
            }
            if (winningNumbers.contains(bonus)) {
                outputView.printDuplicateLottoNumberMessage();
                continue;
            }
            return bonus;
        }
    }

    private List<Lotto> getValidPurchasedTickets(int purchaseAmount) {
        int total = CalculateTicketCount.calculateTicketCount(purchaseAmount, PRICE_PER_TICKET);

        outputView.printManualCountMessage();
        int manual = getValidLottoTicketCount(total);
        int auto = total - manual;

        outputView.printPurchasedTicketsMessage(manual, auto);

        List<Lotto> manualLottoTickets = getValidManualTickets(manual);
        List<Lotto> autoLottoTickets = lottoGenerator.generateAutoLottoTickets(auto);

        List<Lotto> purchasedTickets = new ArrayList<>(manualLottoTickets);
        purchasedTickets.addAll(autoLottoTickets);

        outputView.printLottoNumbers(purchasedTickets);
        return purchasedTickets;
    }

    private int getValidLottoTicketCount(int purchasedTicketCount) {
        int manualCount;
        do {
            manualCount = inputView.getInputManualLottoCount();
            if (manualCount < 0 || manualCount > purchasedTicketCount) {
                outputView.printInvalidManualCountMessage();
            }
        } while (manualCount < 0 || manualCount > purchasedTicketCount);
        return manualCount;
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

    private List<Lotto> getValidManualTickets(int manualCount) {
        if (manualCount == 0) return List.of();

        while (true) {
            outputView.printManualNumberMessage();
            List<String> lines = inputView.getInputManualLottoNumbers(manualCount);
            try {
                return ManualLottoTicketsGenerator.createManualTickets(lines);
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
}
