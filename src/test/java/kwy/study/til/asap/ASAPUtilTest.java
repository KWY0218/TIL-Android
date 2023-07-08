package kwy.study.til.asap;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ASAPUtilTest {
    ASAPUtil asapUtil;

    @BeforeEach
    void setUp() {
        asapUtil = new ASAPUtil();
    }

    @Test
    void initTest() {
        // given
        // when
        // then
        assertThat(asapUtil.getMeeting().getDuration()).isEqualTo("2HOUR");

    }

    @Test
    void setTimeTableTest() {
        // given
        List<String> expected = Arrays.asList("7.1.토", "7.2.일", "7.3.월", "7.4.화");

        // when
        asapUtil.setTimeTable();

        // then
        for (String e : expected) {
            assertThat(asapUtil.getTimeTable().get(e)).isNotNull();
        }
    }
}