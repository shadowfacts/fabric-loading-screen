package net.shadowfacts.loadingscreen.element;

import net.shadowfacts.loadingscreen.api.element.IElementFactory;
import net.shadowfacts.loadingscreen.api.element.ILoadingScreenElement;
import net.shadowfacts.loadingscreen.api.element.IProgressBarElement;

public class ElementFactory implements IElementFactory {

	@Override
	public ILoadingScreenElement createTextElement() {
		return new TextElement();
	}

	@Override
	public IProgressBarElement createProgressElement() {
		return new ProgressBarElement();
	}

}
