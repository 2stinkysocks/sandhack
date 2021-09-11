package me.travis.sandhack.setting.type;

import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.setting.Setting;

import java.util.function.Predicate;

public class BooleanSetting extends Setting<Boolean> {

    public BooleanSetting(String name, Boolean value, Hack parent) {
        super(name, value, parent);
    }

    public BooleanSetting(String name, boolean value, Hack parent, Predicate<Boolean> shown) {
        super(name, value, parent, shown);
    }

    public void toggle() {
        value = !value;
    }

    public Boolean getValue() {
        return value;
    }

    public boolean isShown(){
        if(shown == null){
            return true;
        }
        return shown.test(this.getValue());
    }

    @Override
    public String getType() {
        return "boolean";
    }
}
