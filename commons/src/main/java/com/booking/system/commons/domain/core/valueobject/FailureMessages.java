package com.booking.system.commons.domain.core.valueobject;

import com.booking.system.commons.domain.core.AbstractDomainList;

import java.util.ArrayList;
import java.util.List;

public class FailureMessages extends AbstractDomainList<String> {

    private FailureMessages(final List<String> data) {
        super(data);
    }

    public static FailureMessages empty() {
        return new FailureMessages(new ArrayList<>());
    }

    public static FailureMessages newInstance(final List<String> messages) {
        return new FailureMessages(messages);
    }

    public static FailureMessages of(final String... messages) {
        return new FailureMessages(List.of(messages));
    }


    public boolean isNotEmpty() {
        return !this.isEmpty();
    }
}
