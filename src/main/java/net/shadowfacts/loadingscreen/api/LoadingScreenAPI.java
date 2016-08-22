package net.shadowfacts.loadingscreen.api;

import net.shadowfacts.loadingscreen.InternalMethods;
import net.shadowfacts.loadingscreen.api.element.IElementFactory;
import net.shadowfacts.loadingscreen.api.element.ILoadingScreenElement;
import net.shadowfacts.loadingscreen.api.internal.IInternalMethods;
import net.shadowfacts.loadingscreen.api.internal.NoOpInteralMethods;

import javax.annotation.Nullable;
import java.awt.Color;

public class LoadingScreenAPI {

	private static IInternalMethods internalMethods = new NoOpInteralMethods();

//	INTERNAL: DO NOT USE
	public static void setInternalMethods(InternalMethods internalMethods) {
		LoadingScreenAPI.internalMethods = internalMethods;
	}

	@Nullable
	public static ILoadingScreenElement getTopLevelElement() {
		return internalMethods.getTopLevelElement();
	}

	public static void setTopLevelElement(ILoadingScreenElement element) {
		internalMethods.setTopLevelElement(element);
	}

	public static Color getDefaultTextColor() {
		return internalMethods.getDefaultTextColor();
	}

	public static Color getDefaultPrimaryColor() {
		return internalMethods.getDefaultPrimaryColor();
	}

	public static Color getDefaultSecondaryColor() {
		return internalMethods.getDefaultSecondaryColor();
	}

	public static IElementFactory getElementFactory() {
		return internalMethods.getElementFactory();
	}

	public static int getLoadingScreenWidth() {
		return internalMethods.getLoadingScreenWidth();
	}

}
