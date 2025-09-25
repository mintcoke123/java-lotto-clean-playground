package view;

import java.util.Scanner;

public class InputView {
    Scanner scanner = new Scanner(System.in);
    public int getInputMoney() {
        int inputMoney =  scanner.nextInt();
        return inputMoney;
    }
}
