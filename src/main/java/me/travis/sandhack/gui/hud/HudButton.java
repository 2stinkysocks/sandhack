package me.travis.sandhack.gui.hud;

import me.travis.sandhack.Sandhack;
import me.travis.sandhack.gui.SandhackGuiNew;
import me.travis.sandhack.gui.component.CategoryComponent;
import me.travis.sandhack.gui.component.Component;
import me.travis.sandhack.gui.hud.element.HudElement;
import me.travis.sandhack.hack.hacks.client.Gui;
import me.travis.sandhack.util.RenderUtil2D;

/**
 * @author Madmegsox1
 * @since 17/06/2021
 */

public class HudButton extends Component {

    public final HudElement element;
    public final CategoryComponent parent;
    public int offset;
    private boolean isHovered;

    public HudButton(HudElement element, CategoryComponent parent, int offset){
        this.element = element;
        this.parent = parent;
        this.offset = offset;
    }

    @Override
    public void renderComponent(int MouseX, int MouseY) {
        if (element.isEnabled()) {
            RenderUtil2D.drawGradientRect(parent.getX() + SandhackGuiNew.MODULE_WIDTH, this.parent.getY() + this.offset + SandhackGuiNew.MODULE_OFFSET,
                    parent.getX() + parent.getWidth() - SandhackGuiNew.MODULE_WIDTH, this.parent.getY() + SandhackGuiNew.HEIGHT + this.offset + SandhackGuiNew.MODULE_OFFSET,
                    (Gui.INSTANCE.buttonColor.getValue().hashCode()),
                    (Gui.INSTANCE.buttonColor.getValue().hashCode()), isHovered);
        } else {
            RenderUtil2D.drawRectMC(parent.getX() + SandhackGuiNew.MODULE_WIDTH, this.parent.getY() + this.offset + SandhackGuiNew.MODULE_OFFSET, parent.getX() + parent.getWidth() - SandhackGuiNew.MODULE_WIDTH, this.parent.getY() + SandhackGuiNew.HEIGHT + this.offset + SandhackGuiNew.MODULE_OFFSET, this.isHovered ? SandhackGuiNew.GUI_HOVERED_COLOR() : SandhackGuiNew.GUI_MODULECOLOR());
        }
        if (Gui.INSTANCE.customFont.getValue()) {
            Sandhack.GUI_FONT_MANAGER.drawStringWithShadow(this.element.getName(), parent.getX() + SandhackGuiNew.MODULE_FONT_SIZE, parent.getY() + this.offset + SandhackGuiNew.MODULE_OFFSET + SandhackGuiNew.HEIGHT / 2f - SandhackGuiNew.FONT_HEIGHT, Gui.INSTANCE.fontColor.getValue().hashCode());
        } else {
            mc.fontRenderer.drawStringWithShadow(this.element.getName(), parent.getX() + SandhackGuiNew.MODULE_FONT_SIZE, parent.getY() + this.offset + SandhackGuiNew.MODULE_OFFSET + SandhackGuiNew.HEIGHT / 2f - SandhackGuiNew.FONT_HEIGHT, Gui.INSTANCE.fontColor.getValue().hashCode());
        }
    }

    @Override
    public void updateComponent(int mouseX, int mouseY) {
        this.isHovered = isMouseOnButton(mouseX, mouseY);
    }

    public boolean isMouseOnButton(int x, int y) {
        if (x > parent.getX() + SandhackGuiNew.MODULE_WIDTH && x < parent.getX() + parent.getWidth() - SandhackGuiNew.MODULE_WIDTH && y > this.parent.getY() + this.offset && y < this.parent.getY() + SandhackGuiNew.HEIGHT + SandhackGuiNew.MODULE_OFFSET + this.offset) {
            return true;
        }
        return false;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (isMouseOnButton(mouseX, mouseY) && button == 0) {
            this.element.toggle();
        }
    }

}
