package me.travis.sandhack.event;

import me.travis.sandhack.event.processor.Event;

public class EventStage extends Event {
    private int stage;

    public EventStage() {
    }

    public EventStage(int stage) {
        this.stage = stage;
    }

    public int getStage() {
        return this.stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }
}

