package me.travis.sandhack.gui.component.components;

import me.travis.sandhack.Sandhack;
import me.travis.sandhack.gui.SandhackGuiNew;
import me.travis.sandhack.gui.component.Component;
import me.travis.sandhack.gui.component.HackButton;
import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.hack.hacks.client.Gui;
import me.travis.sandhack.util.RenderUtil2D;

/**
 * @author BrownZombie
 * @since 29/04/2021
 */

public class ShownComponent extends Component {
    private String name;
    private boolean isHovered;
    private HackButton parent;
    private int offset;
    private int x;
    private int y;

    private Hack module;

    public ShownComponent(HackButton button, int offset) {
        this.parent = button;
        this.name = "Shown";
        this.offset = offset;

        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        setShown(true);
    }

    @Override
    public void renderComponent(int mouseX, int mouseY) {
        module = this.parent.mod;
        RenderUtil2D.drawRectMC(parent.parent.getX() + SandhackGuiNew.SETTING_OFFSET, parent.parent.getY() + offset + SandhackGuiNew.MODULE_OFFSET, parent.parent.getX() + parent.parent.getWidth() - SandhackGuiNew.SETTING_OFFSET, parent.parent.getY() + offset + SandhackGuiNew.HEIGHT + SandhackGuiNew.MODULE_OFFSET, this.isHovered ? SandhackGuiNew.GUI_HOVERED_COLOR() : SandhackGuiNew.GUI_COLOR());
        if (Gui.INSTANCE.customFont.getValue()) {
            Sandhack.GUI_FONT_MANAGER.drawStringWithShadow("Shown: " + (this.module.isNotification() ? "True" : "False"), parent.parent.getX() + SandhackGuiNew.SUB_FONT_SIZE, parent.parent.getY() + offset + 3 + SandhackGuiNew.MODULE_OFFSET, Gui.INSTANCE.fontColor.getValue().hashCode());
        } else {
            mc.fontRenderer.drawStringWithShadow("Shown: " + (this.module.isNotification() ? "True" : "False"), parent.parent.getX() + SandhackGuiNew.SUB_FONT_SIZE, parent.parent.getY() + offset + 3 + SandhackGuiNew.MODULE_OFFSET, Gui.INSTANCE.fontColor.getValue().hashCode());
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
    }

    public boolean isMouseOnButton(int x, int y) {
        return x > this.parent.parent.getX() + SandhackGuiNew.SETTING_OFFSET && x < this.parent.parent.getX() + SandhackGuiNew.WIDTH - SandhackGuiNew.SETTING_OFFSET && y > this.parent.parent.getY() + offset + SandhackGuiNew.MODULE_OFFSET && y < this.parent.parent.getY() + offset + SandhackGuiNew.HEIGHT + SandhackGuiNew.MODULE_OFFSET;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (isMouseOnButton(mouseX, mouseY) && button == 0 && this.parent.isOpen) {
            this.module.setNotification(!this.module.isNotification());
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
}
