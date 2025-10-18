package calculator.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleView {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void printPrompt() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
    }

    public String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printZero() {
        System.out.println("0");
    }

    public void printResult(int total) {
        System.out.println("결과 : " + total);
    }
}