package view;

import domain.Lotto;

import java.util.List;

public class OutputView {
    public void printPurchasedTicketsMessage(int tickets){
        System.out.println();
        System.out.println(tickets + "개를 구매했습니다.");
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

    public void printResultMessage(int correctNumber, int price, int count){
        System.out.println(correctNumber + "개 일치 (" + price + "원)- " + count + "개";
    }

    public void printTotalBenefitResultMessage(float totalBenefit){
        System.out.println("총 수익률은 "+totalBenefit+"입니다.");
    }

    public void printLottoNumbers(List<Lotto> lottoTickets) {
        for (Lotto ticket : lottoTickets){
            System.out.println(ticket.getNumbers());
        }
        System.out.println();
    }

}
