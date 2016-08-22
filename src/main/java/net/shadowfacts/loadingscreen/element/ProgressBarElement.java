package net.shadowfacts.loadingscreen.element;

import net.minecraft.client.Minecraft;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.DisplayScale;
import net.shadowfacts.loadingscreen.api.LoadingScreenAPI;
import net.shadowfacts.loadingscreen.api.element.IProgressBarElement;
import net.shadowfacts.loadingscreen.util.Utils;
import org.lwjgl.opengl.GL11;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ProgressBarElement extends TextElement implements IProgressBarElement {

	private static final NumberFormat PERCENT_FORMAT = new DecimalFormat("##.#%");

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
		progress = stage / (float)maxStage;
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
	public String getText() {
		String text = super.getText();
		String progress;
		if (maxStage != null) {
			progress = stage + " / " + maxStage;
		} else {
			progress = PERCENT_FORMAT.format(this.progress);
		}
		if (text == null || text.isEmpty()) {
			return progress;
		} else {
			return text + ": " + progress;
		}
	}

	@Override
	public void draw(TextureManager textureManager, int y) {
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		int left = (new DisplayScale(Minecraft.getInstance()).getScaledWidth() - LoadingScreenAPI.getLoadingScreenWidth()) / 2;
		int filled = (int)(progress * LoadingScreenAPI.getLoadingScreenWidth());
		int empty = LoadingScreenAPI.getLoadingScreenWidth() - filled;

		Utils.drawRect(left, y, filled, getHeight(), getPrimaryColor());
		Utils.drawRect(left + filled, y, empty, getHeight(), getSecondaryColor());

		GL11.glEnable(GL11.GL_TEXTURE_2D);

		super.draw(textureManager, y);

	}
}
