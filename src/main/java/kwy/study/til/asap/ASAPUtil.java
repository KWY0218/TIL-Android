package kwy.study.til.asap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kwy.study.til.asap.domain.DateAvailability;
import kwy.study.til.asap.domain.PreferTime;

public class ASAPUtil {
    private Map<String, Map<String, List<String>>> timeTable = new HashMap<>();
    private final List<DateAvailability> ads = DateAvailability.getDummy();
    private final List<PreferTime> pt = PreferTime.getDummy();

    public void setTimeTable() {
        for(DateAvailability ad  :ads) {
            Map<String, List<String>> rowTable = new HashMap<>();

        }
    }

}
