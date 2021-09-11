package me.travis.sandhack.gui.component.components;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.travis.sandhack.Sandhack;
import me.travis.sandhack.gui.SandhackGuiNew;
import me.travis.sandhack.gui.component.Component;
import me.travis.sandhack.gui.component.HackButton;
import me.travis.sandhack.hack.hacks.client.Gui;
import me.travis.sandhack.setting.type.DoubleSetting;
import me.travis.sandhack.setting.type.IntSetting;
import me.travis.sandhack.util.MathsUtil;
import me.travis.sandhack.util.RenderUtil2D;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Madmegsox1
 * @since 29/04/2021
 * fat cock
 */

public class SliderComponent extends Component {
    private boolean hovered;

    private final HackButton parent;
    private DoubleSetting setD = null;
    private IntSetting setI = null;
    private int offset;
    private int x;
    private int y;
    private boolean dragging = false;

    private double renderWidth;

    public SliderComponent(DoubleSetting value, HackButton button, int offset) {
        this.setD = value;
        this.parent = button;
        this.offset = offset;

        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        setShown(true);
    }

    public SliderComponent(IntSetting value, HackButton button, int offset) {
        this.setI = value;
        this.parent = button;
        this.offset = offset;

        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        setShown(true);
    }

    @Override
    public void renderComponent(int mouseX, int mouseY) {
        if(!isShown())return;
        net.minecraft.client.gui.Gui.drawRect(parent.parent.getX() + SandhackGuiNew.SETTING_OFFSET, parent.parent.getY() + offset + SandhackGuiNew.MODULE_OFFSET, parent.parent.getX() + parent.parent.getWidth() - SandhackGuiNew.SETTING_OFFSET, parent.parent.getY() + offset + SandhackGuiNew.HEIGHT + SandhackGuiNew.MODULE_OFFSET, this.hovered ? SandhackGuiNew.GUI_HOVERED_COLOR() : SandhackGuiNew.GUI_COLOR());
        RenderUtil2D.drawGradientRect(parent.parent.getX() + SandhackGuiNew.SETTING_OFFSET, parent.parent.getY() + offset + SandhackGuiNew.MODULE_OFFSET, parent.parent.getX() + (int) renderWidth, parent.parent.getY() + offset + SandhackGuiNew.HEIGHT + SandhackGuiNew.MODULE_OFFSET,
                (Gui.INSTANCE.buttonColor.getValue().hashCode()),
                (Gui.INSTANCE.buttonColor.getValue().hashCode()), hovered);
        if (Gui.INSTANCE.customFont.getValue()) {
            Sandhack.GUI_FONT_MANAGER.drawStringWithShadow(isInt() ? this.setI.getName() + " " + ChatFormatting.GRAY + MathsUtil.round(this.setI.getValue(), 2) : this.setD.getName() + " " + ChatFormatting.GRAY + this.setD.getValue(), parent.parent.getX() + SandhackGuiNew.SUB_FONT_SIZE, parent.parent.getY() + offset + 3 + SandhackGuiNew.MODULE_OFFSET, Gui.INSTANCE.fontColor.getValue().hashCode());
        } else {
            mc.fontRenderer.drawStringWithShadow(isInt() ? this.setI.getName() + " " + ChatFormatting.GRAY + MathsUtil.round(this.setI.getValue(), 2) : this.setD.getName() + " " + ChatFormatting.GRAY + this.setD.getValue(), parent.parent.getX() + SandhackGuiNew.SUB_FONT_SIZE, parent.parent.getY() + offset + 3 + SandhackGuiNew.MODULE_OFFSET, Gui.INSTANCE.fontColor.getValue().hashCode());
        }
    }

    @Override
    public void setOff(int newOff) {
        offset = newOff;
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        boolean old = isShown();
        if(setD == null){
            setShown(setI.isShown());
        }else {
            setShown(setD.isShown());
        }
        if(old != isShown()){
            this.parent.init(parent.mod, parent.parent, parent.offset, true);
        }
        this.hovered = isMouseOnButton(mouseX, mouseY);
        this.y = parent.parent.getY() + offset;
        this.x = parent.parent.getX();

        int widthTest = SandhackGuiNew.WIDTH - (SandhackGuiNew.SETTING_OFFSET * 2);
        double diff = Math.min(widthTest, Math.max(0, mouseX - this.x));
        if (isInt()) {
            int min = setI.getMin();
            int max = setI.getMax();

            renderWidth = (widthTest) * (float)(setI.getValue() - min) / (max - min) + SandhackGuiNew.SETTING_OFFSET;

            if (dragging) {
                if (diff == 0) {
                    setI.setValue(setI.getMin());
                } else {
                    int newValue = (int) roundToPlace(((diff / widthTest) * (max - min) + min), 2);
                    setI.setValue(newValue);
                }
            }
        } else {

            double min = setD.getMin();
            double max = setD.getMax();

            renderWidth = (widthTest) * (setD.getValue() - min) / (max - min) + SandhackGuiNew.SETTING_OFFSET;

            if (dragging) {
                if (diff == 0) {
                    setD.setValue(setD.getMin());
                } else {
                    double newValue = roundToPlace(((diff / widthTest) * (max - min) + min), 2);
                    setD.setValue(newValue);
                }
            }
        }
    }
    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if(!isShown())return;
        if (isMouseOnButtonD(mouseX, mouseY) && button == 0 && this.parent.isOpen) {
            dragging = true;
        }
        if (isMouseOnButtonI(mouseX, mouseY) && button == 0 && this.parent.isOpen) {
            dragging = true;
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
        dragging = false;
    }


    private static double roundToPlace(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }



    public boolean isMouseOnButtonD(int x, int y) {
        return x > this.x + SandhackGuiNew.SETTING_OFFSET && x < this.x + (parent.parent.getWidth() / 2 + 1) - SandhackGuiNew.SETTING_OFFSET && y > this.y && y < this.y + SandhackGuiNew.HEIGHT;
    }

    public boolean isMouseOnButtonI(int x, int y) {
        return x > this.x + parent.parent.getWidth() / 2 + SandhackGuiNew.SETTING_OFFSET && x < this.x + parent.parent.getWidth() - SandhackGuiNew.SETTING_OFFSET && y > this.y && y < this.y + SandhackGuiNew.HEIGHT;
    }

    public boolean isMouseOnButton(int x, int y) {
        return x > this.parent.parent.getX() + SandhackGuiNew.SETTING_OFFSET && x < this.parent.parent.getX() + SandhackGuiNew.WIDTH - SandhackGuiNew.SETTING_OFFSET && y > this.parent.parent.getY() + offset + SandhackGuiNew.MODULE_OFFSET && y < this.parent.parent.getY() + offset + SandhackGuiNew.HEIGHT + SandhackGuiNew.MODULE_OFFSET;
    }

    public boolean isInt() {
        return setI != null;
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
