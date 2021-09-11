package me.travis.sandhack.event.events;

import me.travis.sandhack.event.EventStage;
import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.setting.Setting;


public class ClientEvent extends EventStage {
    private Hack hack;
    private Setting setting;

    public ClientEvent(int stage, Hack hack) {
        super(stage);
        this.hack = hack;
    }

    public ClientEvent(Setting setting) {
        super(2);
        this.setting = setting;
    }

    public Hack getFeature() {
        return this.hack;
    }

    public Setting getSetting() {
        return this.setting;
    }
}

