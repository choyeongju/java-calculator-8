package calculator;

import static java.util.regex.Pattern.quote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String inputLine = reader.readLine();

            if (inputLine == null || inputLine.trim().isEmpty()) {
                System.out.println("0");
                return;
            }

            String expression = inputLine;
            String delimiterPattern = "[,:]";

            if (inputLine.startsWith("//")) {
                int lineBreakIndex = inputLine.indexOf('\n');
                int literalLineBreakIndex = inputLine.indexOf("\\n");

                int splitPoint;
                int skipCount;

                if (lineBreakIndex != -1) {
                    splitPoint = lineBreakIndex;
                    skipCount = 1;
                } else if (literalLineBreakIndex != -1) {
                    splitPoint = literalLineBreakIndex;
                    skipCount = 2;
                } else {
                    throw new IllegalArgumentException("[400 error] 커스텀 구분자 형식이 올바르지 않습니다.");
                }

                String userDelimiter = inputLine.substring(2, splitPoint);
                if (userDelimiter.isEmpty()) {
                    throw new IllegalArgumentException("[400 error] 커스텀 구분자가 비어 있습니다.");
                }

                delimiterPattern = quote(userDelimiter);
                expression = inputLine.substring(splitPoint + skipCount);

                if (expression.contains(",") || expression.contains(":")) {
                    throw new IllegalArgumentException("[400 error] 정의되지 않은 구분자가 포함되었습니다.");
                }
            }

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