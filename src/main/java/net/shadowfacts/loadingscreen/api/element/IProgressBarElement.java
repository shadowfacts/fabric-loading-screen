package net.shadowfacts.loadingscreen.api.element;

import java.awt.Color;

public interface IProgressBarElement extends ILoadingScreenElement {

	float getProgress();

	void setProgress(float progress);

	void setStages(int stages);

	void incrementStage();

	Color getPrimaryColor();

	void setPrimaryColor(Color color);

	Color getSecondaryColor();

	void setSecondaryColor(Color color);

}
