package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String inputLine = reader.readLine();

            String expression = inputLine;
            String delimiterPattern = "[,:]";
            String[] tokens = expression.split(delimiterPattern);
            int total = 0;

            for (String token : tokens) {
                total += Integer.parseInt(token);
            }

            System.out.println("결과 : " + total);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}