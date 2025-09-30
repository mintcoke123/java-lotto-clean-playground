import controller.LottoController;
import generator.LottoGenerator;
import view.InputView;
import view.OutputView;

public class Main {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoGenerator lottoGenerator = new LottoGenerator();

        LottoController controller = new LottoController(inputView, outputView, lottoGenerator);
        controller.run();
    }
}
