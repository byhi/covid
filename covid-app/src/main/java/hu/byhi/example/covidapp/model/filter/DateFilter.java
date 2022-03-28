package hu.byhi.example.covidapp.model.filter;

import java.time.LocalDateTime;

public class DateFilter {
    LocalDateTime intervalStart;
    LocalDateTime intervalEnd;

    public LocalDateTime getIntervalStart() {
        return intervalStart;
    }

    public void setIntervalStart(LocalDateTime intervalStart) {
        this.intervalStart = intervalStart;
    }

    public LocalDateTime getIntervalEnd() {
        return intervalEnd;
    }

    public void setIntervalEnd(LocalDateTime intervalEnd) {
        this.intervalEnd = intervalEnd;
    }
}
