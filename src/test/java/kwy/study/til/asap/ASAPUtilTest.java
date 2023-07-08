package kwy.study.til.asap;

import java.util.Arrays;
import java.util.List;
import kwy.study.til.asap.domain.TimeSlot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ASAPUtilTest {
    ASAPUtil asapUtil;

    @BeforeEach
    void setUp() {
        asapUtil = new ASAPUtil();
        asapUtil.initTimeTable();
    }

    @Test
    void initTest() {
        // given
        // when
        // then
        assertThat(asapUtil.getMeeting().getDuration()).isEqualTo("2HOUR");

    }

    @Test
    void initTimeTableTest() {
        // given
        List<String> expected = Arrays.asList("7.1.토", "7.2.일", "7.3.월", "7.4.화");

        // when
        asapUtil.initTimeTable();

        // then
        for (String e : expected) {
            assertThat(asapUtil.getTimeTable().get(e)).isNotNull();
        }
    }

    @Test
    void setTimeTableTest() {
        // given

        // when
        asapUtil.setTimeTable();

        // then
        assertAll("사람 4명",
                () -> assertThat(asapUtil.getTimeTable().get("7.1.토").get(TimeSlot.SLOT_22_00).size()).isEqualTo(4),
                () -> assertThat(asapUtil.getTimeTable().get("7.1.토").get(TimeSlot.SLOT_22_30).size()).isEqualTo(4),
                () -> assertThat(asapUtil.getTimeTable().get("7.1.토").get(TimeSlot.SLOT_23_00).size()).isEqualTo(4),
                () -> assertThat(asapUtil.getTimeTable().get("7.1.토").get(TimeSlot.SLOT_23_30).size()).isEqualTo(4),
                () -> assertThat(asapUtil.getTimeTable().get("7.1.토").get(TimeSlot.SLOT_24_00).size()).isEqualTo(4)
        );

        assertAll("사람 3명",
                () -> assertThat(asapUtil.getTimeTable().get("7.2.일").get(TimeSlot.SLOT_18_00).size()).isEqualTo(3),
                () -> assertThat(asapUtil.getTimeTable().get("7.2.일").get(TimeSlot.SLOT_18_30).size()).isEqualTo(3),
                () -> assertThat(asapUtil.getTimeTable().get("7.2.일").get(TimeSlot.SLOT_19_00).size()).isEqualTo(3),
                () -> assertThat(asapUtil.getTimeTable().get("7.2.일").get(TimeSlot.SLOT_19_30).size()).isEqualTo(3),
                () -> assertThat(asapUtil.getTimeTable().get("7.2.일").get(TimeSlot.SLOT_20_00).size()).isEqualTo(3)
        );
    }
}