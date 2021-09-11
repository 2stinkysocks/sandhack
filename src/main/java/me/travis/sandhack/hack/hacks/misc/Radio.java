package me.travis.sandhack.hack.hacks.misc;

import me.travis.sandhack.Sandhack;
import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.setting.type.BooleanSetting;

@Hack.Registration(name = "Radio", description = "plays the best music", category = Hack.Category.MISC)
public class Radio extends Hack {

    BooleanSetting playButton = new BooleanSetting("Play", false, this);
    BooleanSetting stopButton = new BooleanSetting("Stop", false, this);
    BooleanSetting skipButton = new BooleanSetting("Skip", false, this);
    BooleanSetting shuffleButton = new BooleanSetting("Shuffle", false, this);

    // TODO : FIX THIS
    @Override
    public void onUpdate() {
        if (this.playButton.getValue()) {
            Sandhack.SONG_MANAGER.play();
            this.playButton.toggle();
        }
        if (this.stopButton.getValue()) {
            Sandhack.SONG_MANAGER.stop();
            this.stopButton.toggle();
        }
        if (this.skipButton.getValue()) {
            Sandhack.SONG_MANAGER.skip();
            this.skipButton.toggle();
        }
        if (this.shuffleButton.getValue()) {
            Sandhack.SONG_MANAGER.shuffle();
            this.shuffleButton.toggle();
        }
    }
}
