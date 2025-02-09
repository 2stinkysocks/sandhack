package me.travis.sandhack.setting;

import me.travis.sandhack.Sandhack;
import me.travis.sandhack.hack.Hack;

import java.util.function.Predicate;

public class Setting<T> {

    private final String name;
    private final Hack parent;
    public T value;
    public Predicate<T> shown;

    public Setting(String name, T value, Hack parent) {
        this.name = name;
        this.value = value;
        this.parent = parent;

        Sandhack.SETTINGS.addSetting(this);
    }

    public Setting(String name, T value, Hack parent, Predicate<T> shown){
        this.name = name;
        this.value = value;
        this.parent = parent;
        this.shown = shown;
        Sandhack.SETTINGS.addSetting(this);
    }

    public String getName() {
        return this.name;
    }

    public T getValue() {
        return this.value;
    }

    public String getType() {
    	return "";
    }

    public Hack getParent() {
        return this.parent;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Predicate<T> getShown() {
        return shown;
    }

    public void setShown(Predicate<T> shown) {
        this.shown = shown;
    }
}
