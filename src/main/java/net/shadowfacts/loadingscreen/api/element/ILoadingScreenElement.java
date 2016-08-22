package net.shadowfacts.loadingscreen.api.element;

import net.minecraft.client.texture.TextureManager;

import javax.annotation.Nullable;
import java.awt.Color;

public interface ILoadingScreenElement {

	String getText();

	void setText(String text);

	Color getTextColor();

	void setTextColor(Color color);

	default boolean hasChild() {
		return getChild() != null;
	}

	@Nullable
	ILoadingScreenElement getChild();

	void setChild(ILoadingScreenElement element);

	void draw(TextureManager textureManager, int y);

}
