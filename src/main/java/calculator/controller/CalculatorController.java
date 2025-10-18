package calculator.controller;

import calculator.service.StringCalculator;
import calculator.view.ConsoleView;

public class CalculatorController {

    private final ConsoleView view;
    private final StringCalculator calculator;

    public CalculatorController(ConsoleView view, StringCalculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void run() {
        view.printPrompt();
        String input = view.readLine();

        int result = calculator.sum(input);

        if (input == null || input.trim().isEmpty()) {
            view.printZero();
            return;
        }

        view.printResult(result);
    }
}