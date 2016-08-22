package net.shadowfacts.loadingscreen.api.internal;

import net.shadowfacts.loadingscreen.api.element.IElementFactory;
import net.shadowfacts.loadingscreen.api.element.ILoadingScreenElement;

import java.awt.Color;

public interface IInternalMethods {

	ILoadingScreenElement getTopLevelElement();

	void setTopLevelElement(ILoadingScreenElement element);

	Color getDefaultTextColor();

	Color getDefaultPrimaryColor();

	Color getDefaultSecondaryColor();

	IElementFactory getElementFactory();

	int getLoadingScreenWidth();
}
