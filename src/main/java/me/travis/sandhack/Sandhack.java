package me.travis.sandhack;

import me.travis.sandhack.command.Commands;
import me.travis.sandhack.event.Events;
import me.travis.sandhack.event.processor.EventProcessor;
import me.travis.sandhack.gui.SandhackGuiNew;
import me.travis.sandhack.gui.hud.element.HudManager;
import me.travis.sandhack.hack.Hacks;
import me.travis.sandhack.manager.*;
import me.travis.sandhack.manager.fonts.DonatorFont;
import me.travis.sandhack.manager.fonts.GuiFont;
import me.travis.sandhack.manager.fonts.MenuFont;
import me.travis.sandhack.networking.handler.ChatHandling;
import me.travis.sandhack.networking.handler.ClientHandling;
import me.travis.sandhack.setting.Settings;
import me.travis.sandhack.util.RenderUtil2D;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

/**
 * @author travis - began work on 8th april 2021
 */
@Mod(modid = Sandhack.MODID, name = Sandhack.MODNAME, version = Sandhack.MODVER)
public class Sandhack {

    public static final String MODID = "sandhack";
    public static final String MODNAME = "Sandhack";
    public static final String MODVER = "0.6.1";

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    // events
    public static Events EVENTS;
    public static EventProcessor EVENT_PROCESSOR;

    // commands
    public static Commands COMMANDS;

    // hacks
    public static Hacks HACKS;

    // settings
    public static Settings SETTINGS;

    // gui
    public static SandhackGuiNew GUI2;

    // managers
    public static MenuFont MENU_FONT_MANAGER;
    public static GuiFont GUI_FONT_MANAGER;
    public static DonatorFont DONATOR_FONT_MANAGER;
    public static FriendManager FRIEND_MANAGER;
    public static EnemyManager ENEMY_MANAGER;
    public static PopManager POP_MANAGER;
    public static ServerManager SERVER_MANAGER;
    public static PositionManager POS_MANAGER;
    public static RotationManager ROTATION_MANAGER;
    public static ConfigManager CONFIG_MANAGER;
    public static SongManager SONG_MANAGER;
    public static CapeManager CAPE_MANAGER;
    public static CosmeticManager COSMETIC_MANAGER;
    public static AltManager ALT_MANAGER;
    public static ClientHandling CLIENT_HANDLING;
    public static ChatHandling CHAT_HANDLING;
    public static HudManager HUD_MANAGER;

    // megs weird thingy
    public static RenderUtil2D RENDER_UTIL_2D;

    @Mod.Instance
    public static Sandhack INSTANCE;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER.info("loading " + MODNAME);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        this.load();
        LOGGER.info(MODNAME + " : " + MODVER + " has been loaded");
        Display.setTitle("Sandhack | v" + MODVER);
    }

    public void load() {
        EVENT_PROCESSOR = new EventProcessor();
        EVENTS = new Events();
        SETTINGS = new Settings();
        RENDER_UTIL_2D = new RenderUtil2D();
        COMMANDS = new Commands();
        HACKS = new Hacks();
        HUD_MANAGER = new HudManager();
        this.loadManagers();
        CLIENT_HANDLING = new ClientHandling();
        CHAT_HANDLING = new ChatHandling();
        CONFIG_MANAGER.loadConfig();
        GUI2 = new SandhackGuiNew();
    }

    public static void unLoad() {
        CONFIG_MANAGER.saveConfig();
    }

    public void loadManagers() {
        MENU_FONT_MANAGER = new MenuFont();
        GUI_FONT_MANAGER = new GuiFont();
        FRIEND_MANAGER = new FriendManager();
        ENEMY_MANAGER = new EnemyManager();
        POP_MANAGER = new PopManager();
        SERVER_MANAGER = new ServerManager();
        POS_MANAGER = new PositionManager();
        ROTATION_MANAGER = new RotationManager();
        CONFIG_MANAGER = new ConfigManager();
        SONG_MANAGER = new SongManager();
        CAPE_MANAGER = new CapeManager();
        DONATOR_FONT_MANAGER = new DonatorFont();
        COSMETIC_MANAGER = new CosmeticManager();
        ALT_MANAGER = new AltManager();
    }

}

