package view;

import domain.Lotto;

import java.util.List;

public class OutputView {
    public void printPurchasedTicketsMessage(int tickets){
        System.out.println();
        String message = String.format("%d개를 구매했습니다.",tickets);
        System.out.println(message);
    }

    public void printPurchaseMessage(){
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printTargetNumberMessage(){
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printStatusMessage(){
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public void printResultMessage(int correctNumber, int price, int count) {
        String message = String.format("%d개 일치 (%d원) - %d개%n", correctNumber, price, count);
        System.out.println(message);
    }

    public void printTotalBenefitResultMessage(double totalBenefit) {
        String message = String.format("총 수익률은 %.2f입니다.", totalBenefit);
        System.out.println(message);
    }

    public void printLottoNumbers(List<Lotto> lottoTickets) {
        for (Lotto ticket : lottoTickets){
            System.out.println(ticket.getNumbers());
        }
        System.out.println();
    }

}
