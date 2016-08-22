package net.shadowfacts.loadingscreen;

import net.fabricmc.base.loader.Init;
import net.minecraft.client.GlHandler;
import net.minecraft.client.texture.TextureManager;
import net.shadowfacts.loadingscreen.api.LoadingScreenAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.DisplayScale;
import net.shadowfacts.loadingscreen.api.element.ILoadingScreenElement;
import net.shadowfacts.loadingscreen.util.LSFontRenderer;
import net.shadowfacts.loadingscreen.util.Utils;
import none.bpd;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.awt.Color;

public class LoadingScreen {

	public static boolean enabled;
	public static TextureManager textureManager;

	private static DisplayScale scale;
	private static bpd bpd;

	@Init
	public void init() {
		LoadingScreenAPI.setInternalMethods(new InternalMethods());
		InternalMethods.loadFromConfig(); // TODO: config stuff
		enabled = true;
	}

	public static void draw(TextureManager textureManager) {
		setupGLState();

		ILoadingScreenElement el = LoadingScreenAPI.getTopLevelElement();
		if (el != null) {
			el.draw(textureManager, new DisplayScale(Minecraft.getInstance()).getScaledHeight() / 2);
		}
//		Utils.drawRect(0, 0, 100, 100, Color.RED);
//		LSFontRenderer.drawString("Hello, World!", 0, 0, Color.BLACK);


		Minecraft.getInstance().a("Loading Screen");

		tearDownGLState();
	}

	private static void setupGLState() {
		scale = new DisplayScale(Minecraft.getInstance());
		int v2 = scale.e();
		bpd = new bpd(scale.getScaledWidth() * v2, scale.getScaledHeight() * v2, true);
		bpd.a(false);
		GlHandler.matrixMode(GL11.GL_PROJECTION);
		GlHandler.loadIdentity();
		GlHandler.ortho(0, (double)scale.getScaledWidth(), (double)scale.getScaledHeight(), 0, 1000, 3000);
		GlHandler.matrixMode(GL11.GL_MODELVIEW);
		GlHandler.loadIdentity();
		GlHandler.translate(0, 0, -2000);
		GlHandler.g();
		GlHandler.p();
		GlHandler.j();
		GlHandler.y();
	}

	private static void tearDownGLState() {
		GlHandler.g();
		GlHandler.p();
		bpd.e();
		bpd.c(scale.getScaledWidth() * scale.e(), scale.getScaledHeight() * scale.e());
		GlHandler.e();
		GlHandler.alphaFunc(516, 0.1f);
		Display.update();
	}

}
