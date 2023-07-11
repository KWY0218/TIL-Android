package kwy.study.til.asap.domain;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TimeSlotTest {

    @Test
    void getTimeSlotsTest() {
        // given
        List<TimeSlot> expected = Arrays.asList(TimeSlot.SLOT_10_00, TimeSlot.SLOT_10_30, TimeSlot.SLOT_11_00, TimeSlot.SLOT_11_30);

        // when
        List<TimeSlot> result = TimeSlot.getTimeSlots(8, 11);

        // then
        Assertions.assertIterableEquals(expected, result);
    }
}