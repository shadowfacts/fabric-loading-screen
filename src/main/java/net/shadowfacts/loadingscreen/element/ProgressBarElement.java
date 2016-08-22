package net.shadowfacts.loadingscreen.element;

import net.minecraft.client.Minecraft;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.DisplayScale;
import net.shadowfacts.loadingscreen.api.LoadingScreenAPI;
import net.shadowfacts.loadingscreen.api.element.IProgressBarElement;
import net.shadowfacts.loadingscreen.util.Utils;

import java.awt.Color;

public class ProgressBarElement extends TextElement implements IProgressBarElement {

	private float progress = 0;

	private Integer maxStage;
	private int stage = 0;

	private Color primaryColor = LoadingScreenAPI.getDefaultPrimaryColor();
	private Color secondaryColor = LoadingScreenAPI.getDefaultSecondaryColor();

	@Override
	public float getProgress() {
		return progress;
	}

	@Override
	public void setProgress(float progress) {
		this.progress = progress;
	}

	@Override
	public void setStages(int stages) {
		this.maxStage = stages;
	}

	@Override
	public void incrementStage() {
		if (maxStage == null) throw new IllegalStateException("Cannot increment stages when max stages hasn't been set");
		stage++;
		progress = stage / maxStage;
	}

	@Override
	public Color getPrimaryColor() {
		return primaryColor;
	}

	@Override
	public void setPrimaryColor(Color color) {
		primaryColor = color;
	}

	@Override
	public Color getSecondaryColor() {
		return secondaryColor;
	}

	@Override
	public void setSecondaryColor(Color color) {
		secondaryColor = color;
	}

	@Override
	public void draw(TextureManager textureManager, int y) {
		super.draw(textureManager, y);

		int left = (new DisplayScale(Minecraft.getInstance()).getScaledWidth() - LoadingScreenAPI.getLoadingScreenWidth()) / 2;
		int filled = (int)(progress * LoadingScreenAPI.getLoadingScreenWidth());
		int empty = LoadingScreenAPI.getLoadingScreenWidth() - filled;

		Utils.drawRect(left, y, filled, 20, getPrimaryColor());
		Utils.drawRect(left + filled, y, empty, 20, getSecondaryColor());

	}
}
