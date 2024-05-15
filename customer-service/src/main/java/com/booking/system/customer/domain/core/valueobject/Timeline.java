package com.booking.system.customer.domain.core.valueobject;

import com.booking.system.commons.domain.core.AbstractDomainList;
import com.booking.system.customer.domain.core.entity.ReservationOrderTimeline;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Timeline extends AbstractDomainList<ReservationOrderTimeline> {

    protected Timeline(final List<ReservationOrderTimeline> data) {
        super(data);
    }

    public static Timeline empty() {
        return new Timeline(new ArrayList<>());
    }

    public static Timeline of(final List<ReservationOrderTimeline> items) {
        return new Timeline(items);
    }

    public <R> List<R> mapToListOf(final Function<? super ReservationOrderTimeline, R> mapper) {
        return this.data().stream()
                .sorted(Comparator.comparing(ReservationOrderTimeline::getOccurredAt).reversed())
                .map(mapper)
                .toList();
    }
}
