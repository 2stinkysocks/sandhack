package me.travis.sandhack.gui.component.components;

import me.travis.sandhack.Sandhack;
import me.travis.sandhack.gui.SandhackGuiNew;
import me.travis.sandhack.gui.component.Component;
import me.travis.sandhack.gui.component.HackButton;
import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.hack.hacks.client.Gui;
import me.travis.sandhack.setting.type.KeySetting;
import me.travis.sandhack.util.RenderUtil2D;
import org.lwjgl.input.Keyboard;

/**
 * @author Madmegsox1
 * @since 29/04/2021
 */

public class KeyBindComponent extends Component {
    private String name;
    private boolean isHovered;
    private boolean isBinding;
    private HackButton parent;
    private KeySetting setting;
    private int offset;
    private int x;
    private int y;
    private boolean normal;

    private Hack module;

    public KeyBindComponent(HackButton button, int offset) {
        this.parent = button;
        this.name = "Bind";
        this.offset = offset;
        this.normal = true;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        setShown(true);
    }

    public KeyBindComponent(KeySetting setting, HackButton button, int offset) {
        this.parent = button;
        this.setting = setting;
        this.name = setting.getName();
        this.offset = offset;
        this.normal = false;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        setShown(true);
    }

    @Override
    public void renderComponent(int mouseX, int mouseY) {
        if(!isShown())return;
        if (normal) {
            module = this.parent.mod;
            RenderUtil2D.drawRectMC(parent.parent.getX() + SandhackGuiNew.SETTING_OFFSET, parent.parent.getY() + offset + SandhackGuiNew.MODULE_OFFSET, parent.parent.getX() + parent.parent.getWidth() - SandhackGuiNew.SETTING_OFFSET, parent.parent.getY() + offset + SandhackGuiNew.HEIGHT + SandhackGuiNew.MODULE_OFFSET, this.isHovered ? SandhackGuiNew.GUI_HOVERED_COLOR() : SandhackGuiNew.GUI_COLOR());
            if (module.getBind() == -1) {
                if (Gui.INSTANCE.customFont.getValue()) {
                    Sandhack.GUI_FONT_MANAGER.drawStringWithShadow(isBinding ? "Listening..." : (name + " - " + "NONE"), parent.parent.getX() + SandhackGuiNew.SUB_FONT_SIZE, parent.parent.getY() + offset + 3 + SandhackGuiNew.MODULE_OFFSET, Gui.INSTANCE.fontColor.getValue().hashCode());
                } else {
                    mc.fontRenderer.drawStringWithShadow(isBinding ? "Listening..." : (name + " - " + "NONE"), parent.parent.getX() + SandhackGuiNew.SUB_FONT_SIZE, parent.parent.getY() + offset + 3 + SandhackGuiNew.MODULE_OFFSET, Gui.INSTANCE.fontColor.getValue().hashCode());
                }
            } else {
                if (Gui.INSTANCE.customFont.getValue()) {
                    Sandhack.GUI_FONT_MANAGER.drawStringWithShadow(isBinding ? "Listening..." : (name + " - " + getRenderKey()), parent.parent.getX() + SandhackGuiNew.SUB_FONT_SIZE, parent.parent.getY() + offset + 3 + SandhackGuiNew.MODULE_OFFSET, Gui.INSTANCE.fontColor.getValue().hashCode());
                } else {
                    mc.fontRenderer.drawStringWithShadow(isBinding ? "Listening..." : (name + " - " + getRenderKey()), parent.parent.getX() + SandhackGuiNew.SUB_FONT_SIZE, parent.parent.getY() + offset + 3 + SandhackGuiNew.MODULE_OFFSET, Gui.INSTANCE.fontColor.getValue().hashCode());
                }
            }

        } else {
            RenderUtil2D.drawRectMC(parent.parent.getX() + SandhackGuiNew.SETTING_OFFSET, parent.parent.getY() + offset + SandhackGuiNew.MODULE_OFFSET, parent.parent.getX() + parent.parent.getWidth() - SandhackGuiNew.SETTING_OFFSET, parent.parent.getY() + offset + SandhackGuiNew.HEIGHT + SandhackGuiNew.MODULE_OFFSET, this.isHovered ? SandhackGuiNew.GUI_HOVERED_COLOR() : SandhackGuiNew.GUI_COLOR());
            if (setting.getKey() == -1) {
                if (Gui.INSTANCE.customFont.getValue()) {
                    Sandhack.GUI_FONT_MANAGER.drawStringWithShadow(isBinding ? "Listening..." : (name + " - " + "NONE"), parent.parent.getX() + SandhackGuiNew.SUB_FONT_SIZE, parent.parent.getY() + offset + 3 + SandhackGuiNew.MODULE_OFFSET, Gui.INSTANCE.fontColor.getValue().hashCode());
                } else {
                    mc.fontRenderer.drawStringWithShadow(isBinding ? "Listening..." : (name + " - " + "NONE"), parent.parent.getX() + SandhackGuiNew.SUB_FONT_SIZE, parent.parent.getY() + offset + 3 + SandhackGuiNew.MODULE_OFFSET, Gui.INSTANCE.fontColor.getValue().hashCode());
                }
            } else {
                if (Gui.INSTANCE.customFont.getValue()) {
                    Sandhack.GUI_FONT_MANAGER.drawStringWithShadow(isBinding ? "Listening..." : (name + " - " + getRenderKey()), parent.parent.getX() + SandhackGuiNew.SUB_FONT_SIZE, parent.parent.getY() + offset + 3 + SandhackGuiNew.MODULE_OFFSET, Gui.INSTANCE.fontColor.getValue().hashCode());
                } else {
                    mc.fontRenderer.drawStringWithShadow(isBinding ? "Listening..." : (name + " - " + getRenderKey()), parent.parent.getX() + SandhackGuiNew.SUB_FONT_SIZE, parent.parent.getY() + offset + 3 + SandhackGuiNew.MODULE_OFFSET, Gui.INSTANCE.fontColor.getValue().hashCode());
                }
            }
        }
    }

    @Override
    public void setOff(int newOff) {
        offset = newOff;
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.isHovered = isMouseOnButton(mouseX, mouseY);
        this.y = parent.parent.getY() + offset;
        this.x = parent.parent.getX();
        boolean old = isShown();
        setShown(this.setting.isShown());
        if(old != isShown()){
            this.parent.init(parent.mod, parent.parent, parent.offset, true);
        }
    }

    public boolean isMouseOnButton(int x, int y) {
        return x > this.parent.parent.getX() + SandhackGuiNew.SETTING_OFFSET && x < this.parent.parent.getX() + SandhackGuiNew.WIDTH - SandhackGuiNew.SETTING_OFFSET && y > this.parent.parent.getY() + offset + SandhackGuiNew.MODULE_OFFSET && y < this.parent.parent.getY() + offset + SandhackGuiNew.HEIGHT + SandhackGuiNew.MODULE_OFFSET;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if(!isShown())return;
        if (this.isBinding) {
            this.isBinding = false;
            if (normal) {
                switch (button) {
                    case 0:
                        module.setBind(-2);
                        break;
                    case 1:
                        module.setBind(-3);
                        break;
                    case 2:
                        module.setBind(-4);
                        break;
                    case 3:
                        module.setBind(-5);
                        break;
                    case 4:
                        module.setBind(-6);
                        break;

                }
            } else {
                Sandhack.LOGGER.info(button);
                switch (button) {
                    case 0:
                        setting.setKey(-2);
                        break;
                    case 1:
                        setting.setKey(-3);
                        break;
                    case 2:
                        setting.setKey(-4);
                        break;
                    case 3:
                        setting.setKey(-5);
                        break;
                    case 4:
                        setting.setKey(-6);
                        break;
                }
            }
            return;
        }
        if (isMouseOnButton(mouseX, mouseY) && button == 0 && this.parent.isOpen) {
            this.isBinding = !this.isBinding;
        }
    }

    @Override
    public void keyTyped(char typedChar, int key) {
        if(!isShown())return;
        if (this.isBinding) {
            if (this.normal) {
                module.setBind(key);
                if (key == Keyboard.KEY_DELETE) {
                    module.setBind(-1);
                }
            } else {
                setting.setKey(key);
                if (key == Keyboard.KEY_DELETE) {
                    setting.setKey(-1);
                }
            }
            this.isBinding = false;
        }
    }

    @Override
    public HackButton getParent() {
        return parent;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    private String getRenderKey() {
        if (normal) {
            if (module == null) return "NONE";
            switch (module.getBind()) {
                case -2:
                    return "M0";
                case -3:
                    return "M1";
                case -4:
                    return "M2";
                case -5:
                    return "M3";
                case -6:
                    return "M4";
                default:
                    return Keyboard.getKeyName(module.getBind());
            }
        } else {
            switch (setting.getKey()) {
                case -2:
                    return "M0";
                case -3:
                    return "M1";
                case -4:
                    return "M2";
                case -5:
                    return "M3";
                case -6:
                    return "M4";
                default:
                    return Keyboard.getKeyName(setting.getKey());
            }
        }
    }
}
