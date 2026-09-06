package com.baeldung.lsc.messaging;

public record TaskCompletedEvent(Long taskId, String taskName, String campaignName) {
}
