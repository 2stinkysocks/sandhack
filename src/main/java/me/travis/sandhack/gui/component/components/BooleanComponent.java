package me.travis.sandhack.gui.component.components;

import me.travis.sandhack.Sandhack;
import me.travis.sandhack.gui.SandhackGuiNew;
import me.travis.sandhack.gui.component.Component;
import me.travis.sandhack.gui.component.HackButton;
import me.travis.sandhack.hack.hacks.client.Gui;
import me.travis.sandhack.setting.type.BooleanSetting;
import me.travis.sandhack.setting.type.ColourSetting;
import me.travis.sandhack.util.RenderUtil2D;

import java.awt.*;

/**
 * @author Madmegsox1
 * @since 29/04/2021
 */

public class BooleanComponent extends Component {
    private ColorComponent p;
    private boolean hovered;
    private BooleanSetting option;
    private final HackButton parent;
    private ColourSetting coption;
    private int offset;
    private int x;
    private int y;

    public BooleanComponent(BooleanSetting option, HackButton button, int offset) {
        this.option = option;
        this.coption = null;
        this.parent = button;
        this.offset = offset;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        setShown(true);
    }


    @Override
    public void renderComponent(int mouseX, int mouseY) {
        if(!isShown())return;
        SandhackGuiNew.drawRect(parent.parent.getX() + SandhackGuiNew.SETTING_OFFSET, parent.parent.getY() + offset + SandhackGuiNew.MODULE_OFFSET, parent.parent.getX() + parent.parent.getWidth() - SandhackGuiNew.SETTING_OFFSET, parent.parent.getY() + offset + SandhackGuiNew.HEIGHT + SandhackGuiNew.MODULE_OFFSET, this.hovered ? SandhackGuiNew.GUI_HOVERED_COLOR() : SandhackGuiNew.GUI_COLOR());
        RenderUtil2D.drawBorderedRect(parent.parent.getX() + SandhackGuiNew.SETTING_OFFSET + 85, parent.parent.getY() + offset + 3 + SandhackGuiNew.MODULE_OFFSET, parent.parent.getX() + 115 - SandhackGuiNew.SETTING_OFFSET, parent.parent.getY() + offset + SandhackGuiNew.HEIGHT + SandhackGuiNew.MODULE_OFFSET - 3, 1, this.coption == null ? !this.option.getValue() ? SandhackGuiNew.GUI_COLOR() : Gui.INSTANCE.buttonColor.getValue().hashCode() : coption.getRainbow() ? new Color(coption.getValue().getRed(), coption.getValue().getGreen(), coption.getValue().getBlue(), 255).hashCode() : SandhackGuiNew.GUI_COLOR(), new Color(0, 0, 0, 200).hashCode(), this.hovered);
        RenderUtil2D.drawRectMC(parent.parent.getX() + SandhackGuiNew.SETTING_OFFSET + (this.coption == null ? this.option.getValue() ? 95 : 88 : this.coption.getRainbow() ? 95 : 88), parent.parent.getY() + offset + 5 + SandhackGuiNew.MODULE_OFFSET, parent.parent.getX() + (this.coption == null ? this.option.getValue() ? 112 : 105 : this.coption.getRainbow() ? 112 : 105) - SandhackGuiNew.SETTING_OFFSET, parent.parent.getY() + offset + SandhackGuiNew.HEIGHT + SandhackGuiNew.MODULE_OFFSET - 5, new Color(50, 50, 50, 255).hashCode());
        if (Gui.INSTANCE.customFont.getValue()) {
            Sandhack.GUI_FONT_MANAGER.drawStringWithShadow(this.coption == null ? this.option.getName() : "Rainbow", parent.parent.getX() + SandhackGuiNew.SUB_FONT_SIZE, parent.parent.getY() + offset + 3 + SandhackGuiNew.MODULE_OFFSET, Gui.INSTANCE.fontColor.getValue().hashCode());
        } else {
            mc.fontRenderer.drawStringWithShadow(this.coption == null ? this.option.getName() : "Rainbow", parent.parent.getX() + SandhackGuiNew.SUB_FONT_SIZE, parent.parent.getY() + offset + 3 + SandhackGuiNew.MODULE_OFFSET, Gui.INSTANCE.fontColor.getValue().hashCode());
        }
    }

    @Override
    public void setOff(int newOff) {
        offset = newOff;
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.hovered = isMouseOnButton(mouseX, mouseY);
        this.y = parent.parent.getY() + offset;
        this.x = parent.parent.getX();
        boolean old = isShown();
        setShown(this.option.isShown());
        if(old != isShown()){
            this.parent.init(parent.mod, parent.parent, parent.offset, true);
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if(!isShown())return;
        if (coption == null) {
            if (isMouseOnButton(mouseX, mouseY) && button == 0 && this.parent.isOpen) {
                this.option.setValue(!option.getValue());
            }
        } else {
            if (isMouseOnButton(mouseX, mouseY) && button == 0 && this.parent.isOpen && p.isOpen()) {
                this.coption.setRainbow(!coption.getRainbow());
            }
        }

    }

    public boolean isMouseOnButton(int x, int y) {
        return x > this.parent.parent.getX() + SandhackGuiNew.SETTING_OFFSET && x < this.parent.parent.getX() + SandhackGuiNew.WIDTH - SandhackGuiNew.SETTING_OFFSET && y > this.parent.parent.getY() + offset + SandhackGuiNew.MODULE_OFFSET && y < this.parent.parent.getY() + offset + SandhackGuiNew.HEIGHT + SandhackGuiNew.MODULE_OFFSET;
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

