package calculator;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

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

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}