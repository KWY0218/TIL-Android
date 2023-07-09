package kwy.study.til.asap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kwy.study.til.asap.domain.Result;
import kwy.study.til.asap.domain.TimeSlot;
import kwy.study.til.asap.domain.TimeSlotInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static kwy.study.til.asap.domain.Duration.TWO_HOUR;
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
        asapUtil.setTimeTable();
        // then
        Map<String, Map<TimeSlot, TimeSlotInfo>> table = asapUtil.getTimeTable();
        Set<String> rows = table.keySet();

        for (String row : rows) {
            System.out.println(row + " ------------------------");
            Map<TimeSlot, TimeSlotInfo> col = table.get(row);
            TimeSlot[] timeSlots = TimeSlot.values();
            for(TimeSlot timeSlot : timeSlots) {
                System.out.println(timeSlot);
                System.out.println(col.get(timeSlot).toString());
            }
            System.out.print("---------------------------------------");
        }
    }

    @Test
    void collectPossibleTimeTest() {
        // given
        asapUtil.setTimeTable();

        // when
        asapUtil.collectPossibleTime();

        // then
        for (Result r : asapUtil.getResults().get(TWO_HOUR)) {
            System.out.println(r.toString());
        }
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
                () -> assertThat(asapUtil.getTimeTable().get("7.1.토").get(TimeSlot.SLOT_22_00).userNames.size()).isEqualTo(4),
                () -> assertThat(asapUtil.getTimeTable().get("7.1.토").get(TimeSlot.SLOT_22_30).userNames.size()).isEqualTo(4),
                () -> assertThat(asapUtil.getTimeTable().get("7.1.토").get(TimeSlot.SLOT_23_00).userNames.size()).isEqualTo(4),
                () -> assertThat(asapUtil.getTimeTable().get("7.1.토").get(TimeSlot.SLOT_23_30).userNames.size()).isEqualTo(4),
                () -> assertThat(asapUtil.getTimeTable().get("7.1.토").get(TimeSlot.SLOT_24_00).userNames.size()).isEqualTo(4)
        );

        assertAll("사람 3명",
                () -> assertThat(asapUtil.getTimeTable().get("7.2.일").get(TimeSlot.SLOT_18_00).userNames.size()).isEqualTo(3),
                () -> assertThat(asapUtil.getTimeTable().get("7.2.일").get(TimeSlot.SLOT_18_30).userNames.size()).isEqualTo(3),
                () -> assertThat(asapUtil.getTimeTable().get("7.2.일").get(TimeSlot.SLOT_19_00).userNames.size()).isEqualTo(3),
                () -> assertThat(asapUtil.getTimeTable().get("7.2.일").get(TimeSlot.SLOT_19_30).userNames.size()).isEqualTo(3),
                () -> assertThat(asapUtil.getTimeTable().get("7.2.일").get(TimeSlot.SLOT_20_00).userNames.size()).isEqualTo(3)
        );

        assertAll("가중치",
                () -> assertThat(asapUtil.getTimeTable().get("7.4.화").get(TimeSlot.SLOT_18_00).weight).isEqualTo(3),
                () -> assertThat(asapUtil.getTimeTable().get("7.2.일").get(TimeSlot.SLOT_22_00).weight).isEqualTo(1)
        );
    }

    @Test
    void keySetTest() {
        // given
        Set<String> expected = Set.of("7.1.토", "7.2.일", "7.3.월", "7.4.화");
        asapUtil.setTimeTable();

        // when
        Set<String> result = asapUtil.getTimeTable().keySet();

        // then
        Assertions.assertEquals(expected, result);
    }

}