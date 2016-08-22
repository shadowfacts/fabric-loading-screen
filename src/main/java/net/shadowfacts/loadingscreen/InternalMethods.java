package net.shadowfacts.loadingscreen;

import net.shadowfacts.loadingscreen.api.element.IElementFactory;
import net.shadowfacts.loadingscreen.api.element.ILoadingScreenElement;
import net.shadowfacts.loadingscreen.api.internal.IInternalMethods;
import net.shadowfacts.loadingscreen.element.ElementFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.DisplayScale;

import java.awt.Color;

public class InternalMethods implements IInternalMethods {

	private static ILoadingScreenElement topElement;
	private static Color defaultTextColor;
	private static Color defaultPrimaryColor;
	private static Color defaultSecondaryColor;
	private static IElementFactory elementFactory = new ElementFactory();

	static void loadFromConfig() {
		defaultTextColor = Color.BLACK;
		defaultPrimaryColor = Color.RED;
		defaultSecondaryColor = Color.WHITE;
	}

	@Override
	public ILoadingScreenElement getTopLevelElement() {
		return topElement;
	}

	@Override
	public void setTopLevelElement(ILoadingScreenElement element) {
		topElement = element;
	}

	@Override
	public Color getDefaultTextColor() {
		return defaultTextColor;
	}

	@Override
	public Color getDefaultPrimaryColor() {
		return defaultPrimaryColor;
	}

	@Override
	public Color getDefaultSecondaryColor() {
		return defaultSecondaryColor;
	}

	@Override
	public IElementFactory getElementFactory() {
		return elementFactory;
	}

	@Override
	public int getLoadingScreenWidth() {
		return (int)(new DisplayScale(Minecraft.getInstance()).getScaledWidth() * 2/3f);
	}

}
