package me.travis.sandhack.setting.type;

import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.setting.Setting;
import org.lwjgl.input.Keyboard;

import java.util.function.Predicate;

/**
 * @author Madmegsox1
 * @since 30/04/2021
 */

public class KeySetting extends Setting<Integer> {

    public KeySetting(String name, int value, Hack parent) {
        super(name, value, parent);
    }

    public KeySetting(String name, int value, Hack parent, Predicate<Integer> shown) {
        super(name, value, parent, shown);
    }

    public int getKey() {
        return this.value;
    }

    public void setKey(int key) {
        this.value = key;
    }

    public boolean isDown() {
        if (this.value <= 0) return false;
        return Keyboard.isKeyDown(value);
    }

    public String getKeyName() {
        return Keyboard.getKeyName(this.getKey());
    }

    public boolean isShown(){
        if(shown == null){
            return true;
        }
        return shown.test(this.getValue());
    }

    @Override
    public String getType() {
        return "key";
    }
}
