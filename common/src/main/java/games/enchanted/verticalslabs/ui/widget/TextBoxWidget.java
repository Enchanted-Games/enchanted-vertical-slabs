package games.enchanted.verticalslabs.ui.widget;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.MultiLineTextWidget;
import net.minecraft.network.chat.Component;

public class TextBoxWidget extends MultiLineTextWidget {
    public TextBoxWidget(int maxWidth, Component message, Font font) {
        this(maxWidth, message, font, true);
    }

    public TextBoxWidget(int maxWidth, Component message, Font font, boolean centered) {
        super(message, font);
        this.setMaxWidth(maxWidth);
        this.setCentered(centered);
        this.active = true;
    }
}
