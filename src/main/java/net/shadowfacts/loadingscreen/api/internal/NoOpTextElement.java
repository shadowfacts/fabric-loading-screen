package net.shadowfacts.loadingscreen.api.internal;

import net.minecraft.client.texture.TextureManager;
import net.shadowfacts.loadingscreen.api.LoadingScreenAPI;
import net.shadowfacts.loadingscreen.api.element.ILoadingScreenElement;

import javax.annotation.Nullable;
import java.awt.Color;

public class NoOpTextElement implements ILoadingScreenElement {

	@Override
	public String getText() {
		return "";
	}

	@Override
	public void setText(String text) {

	}

	@Override
	public Color getTextColor() {
		return LoadingScreenAPI.getDefaultTextColor();
	}

	@Override
	public void setTextColor(Color color) {

	}

	@Nullable
	@Override
	public ILoadingScreenElement getChild() {
		return null;
	}

	@Override
	public void setChild(ILoadingScreenElement element) {

	}

	@Override
	public int getHeight() {
		return 10;
	}

	@Override
	public void draw(TextureManager textureManager, int y) {

	}

}
