package me.travis.sandhack.util.elements;

import me.travis.sandhack.util.Globals;
import me.travis.sandhack.util.PlayerUtil;

import java.util.UUID;

public class WurstplusPlayer implements Globals {

    private final String name;
    private String nickName;

    public WurstplusPlayer(String name) {
        this.name = name;
        PlayerUtil.getUUIDFromName(name);
    }

    public WurstplusPlayer(String name, UUID uuid) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String name) {
        this.nickName = name;
    }

}
