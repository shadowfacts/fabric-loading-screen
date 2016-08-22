package net.shadowfacts.loadingscreen.api.internal;

import net.shadowfacts.loadingscreen.api.element.IElementFactory;
import net.shadowfacts.loadingscreen.api.element.ILoadingScreenElement;
import net.shadowfacts.loadingscreen.api.element.IProgressBarElement;

public class NoOpElementFactory implements IElementFactory {

	@Override
	public ILoadingScreenElement createTextElement() {
		return new NoOpTextElement();
	}

	@Override
	public IProgressBarElement createProgressElement() {
		return new NoOpProgressBarElement();
	}

}
