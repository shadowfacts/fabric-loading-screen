package net.shadowfacts.loadingscreen.api.internal;

import net.shadowfacts.loadingscreen.api.element.IElementFactory;
import net.shadowfacts.loadingscreen.api.element.ILoadingScreenElement;

import java.awt.Color;

public class NoOpInteralMethods implements IInternalMethods {

	@Override
	public ILoadingScreenElement getTopLevelElement() {
		return null;
	}

	@Override
	public void setTopLevelElement(ILoadingScreenElement element) {

	}

	@Override
	public Color getDefaultTextColor() {
		return Color.BLACK;
	}

	@Override
	public Color getDefaultPrimaryColor() {
		return Color.RED;
	}

	@Override
	public Color getDefaultSecondaryColor() {
		return Color.WHITE;
	}

	@Override
	public IElementFactory getElementFactory() {
		return new NoOpElementFactory();
	}

	@Override
	public int getLoadingScreenWidth() {
		return 0;
	}

}
