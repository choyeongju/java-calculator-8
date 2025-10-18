package calculator;

import calculator.controller.CalculatorController;
import calculator.service.StringCalculator;
import calculator.view.ConsoleView;

public class Application {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        StringCalculator calculator = new StringCalculator();
        CalculatorController controller = new CalculatorController(view, calculator);

        controller.run();
    }
}