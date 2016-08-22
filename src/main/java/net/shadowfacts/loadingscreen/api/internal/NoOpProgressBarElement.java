package net.shadowfacts.loadingscreen.api.internal;

import net.shadowfacts.loadingscreen.api.LoadingScreenAPI;
import net.shadowfacts.loadingscreen.api.element.IProgressBarElement;

import java.awt.Color;

public class NoOpProgressBarElement extends NoOpTextElement implements IProgressBarElement {

	@Override
	public float getProgress() {
		return 0;
	}

	@Override
	public void setProgress(float progress) {

	}

	@Override
	public void setStages(int stages) {

	}

	@Override
	public void incrementStage() {

	}

	@Override
	public Color getPrimaryColor() {
		return LoadingScreenAPI.getDefaultPrimaryColor();
	}

	@Override
	public void setPrimaryColor(Color color) {

	}

	@Override
	public Color getSecondaryColor() {
		return LoadingScreenAPI.getDefaultSecondaryColor();
	}

	@Override
	public void setSecondaryColor(Color color) {

	}

}
