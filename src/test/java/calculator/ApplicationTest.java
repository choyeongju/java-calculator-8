package calculator;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {

    @Test
    void 빈_문자열() {
        assertSimpleTest(() -> {
            run("");
            assertThat(output()).contains("0");
        });
    }

    @Test
    void 기본_구분자_쉼표() {
        assertSimpleTest(() -> {
            run("1,2,3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 기본_구분자_쉼표_콜론() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_단일문자_구분자() {
        assertSimpleTest(() -> {
            run("//;\\n1;2;3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_영어_다글자_구분자() {
        assertSimpleTest(() -> {
            run("//av\\n1av2av3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_한글_다글자_구분자() {
        assertSimpleTest(() -> {
            run("//ㅇㄴ\\n1ㅇㄴ2ㅇㄴ3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_정규식특수문자_구분자_별표() {
        assertSimpleTest(() -> {
            run("//*\\n1*2*3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_정규식특수문자_구분자_파이프() {
        assertSimpleTest(() -> {
            run("//|\\n1|2|3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_정규식특수문자_구분자_물음표() {
        assertSimpleTest(() -> {
            run("//?\\n1?2?3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    void 커스텀_구분자_단일값() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 음수_포함시_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 숫자외_포함시_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,a,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 연속_구분자_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,,2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 선행_구분자_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(",1,2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 후행_구분자_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,2,"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_구분자_부분_없음_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//\n1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀구분자_기본구분자_혼용_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n1;2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_형식오류_개행없음_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;1;2;3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_형식오류_숫자없음_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 커스텀_연속구분자_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//av\\n1avav2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 합계_int_범위_초과_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("2147483647,1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}