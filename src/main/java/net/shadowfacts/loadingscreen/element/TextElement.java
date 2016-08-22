package net.shadowfacts.loadingscreen.element;

import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.DisplayScale;
import net.shadowfacts.loadingscreen.api.LoadingScreenAPI;
import net.shadowfacts.loadingscreen.api.element.ILoadingScreenElement;
import net.shadowfacts.loadingscreen.util.LSFontRenderer;
import net.shadowfacts.loadingscreen.util.LSMinecraftFontRenderer;
import net.shadowfacts.loadingscreen.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.FontRenderer;

import javax.annotation.Nullable;
import java.awt.Color;

public class TextElement implements ILoadingScreenElement {

	private String text = "";
	private Color color = LoadingScreenAPI.getDefaultTextColor();
	private ILoadingScreenElement child;

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public Color getTextColor() {
		return color;
	}

	@Override
	public void setTextColor(Color color) {
		this.color = color;
	}

	@Nullable
	@Override
	public ILoadingScreenElement getChild() {
		return child;
	}

	@Override
	public void setChild(ILoadingScreenElement element) {
		this.child = element;
	}

	@Override
	public void draw(TextureManager textureManager, int y) {
		if (text != null && !text.isEmpty()) {
			Minecraft mc = Minecraft.getInstance();
			DisplayScale scale = new DisplayScale(mc);
			int x = (scale.getScaledWidth() - LSFontRenderer.getStringWidth(text)) / 2;
			LSFontRenderer.drawString(text, x, y, color);
		}

		if (hasChild()) {
			child.draw(textureManager, y + 20);
		}
	}

}
