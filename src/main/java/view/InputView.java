package view;

import java.util.Scanner;

public class InputView {
    Scanner scanner = new Scanner(System.in);
    public int getInputMoney() {
        int inputMoney =  scanner.nextInt();
        scanner.nextLine();
        return inputMoney;
    }

    public String getInputTargetNumber() {
        String inputTargetNumber = scanner.nextLine().trim();
        return inputTargetNumber;
    }
}
