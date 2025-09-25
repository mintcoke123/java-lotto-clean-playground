package view;

import domain.Lotto;

import java.util.List;

public class OutputView {
    public void printPurchasedTicketsMessage(int tickets){
        System.out.println(tickets + "개를 구매했습니다.");
    }



    public void printLottoNumbers(List<Lotto> lottoTickets) {
        for (Lotto ticket : lottoTickets) System.out.println(ticket);
    }
}
