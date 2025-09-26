package view;

import domain.Lotto;

import java.util.List;

public class OutputView {
    public void printPurchasedTicketsMessage(int tickets){
        System.out.println();
        System.out.printf("%d개를 구매했습니다.",tickets);
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
        System.out.printf("%d개 일치 (%d원) - %d개%n", correctNumber, price, count);
    }

    public void printTotalBenefitResultMessage(double totalBenefit) {
        System.out.printf("총 수익률은 %.2f입니다.", totalBenefit);
    }

    public void printLottoNumbers(List<Lotto> lottoTickets) {
        for (Lotto ticket : lottoTickets){
            System.out.println(ticket.getNumbers());
        }
        System.out.println();
    }

}
