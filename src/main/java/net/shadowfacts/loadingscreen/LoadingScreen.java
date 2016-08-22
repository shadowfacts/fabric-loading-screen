package net.shadowfacts.loadingscreen;

import net.fabricmc.base.loader.Init;
import net.minecraft.client.GlHandler;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexBuffer;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;
import net.shadowfacts.loadingscreen.api.LoadingScreenAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.DisplayScale;
import net.shadowfacts.loadingscreen.api.element.ILoadingScreenElement;
import net.shadowfacts.loadingscreen.util.LSFontRenderer;
import net.shadowfacts.loadingscreen.util.Texture;
import net.shadowfacts.loadingscreen.util.Utils;
import none.bpd;
import none.bwr;
import none.bxj;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;

public class LoadingScreen {

	public static boolean enabled;
	public static TextureManager textureManager;

	private static DisplayScale scale;
	private static bpd bpd;

	private static Texture mojangLogo;

	@Init
	public void init() {
		LoadingScreenAPI.setInternalMethods(new InternalMethods());
		InternalMethods.loadFromConfig(); // TODO: config stuff
		enabled = true;
	}

	public static void draw(TextureManager textureManager) {
		setupGLState();

		drawMojangLogo();

		ILoadingScreenElement el = LoadingScreenAPI.getTopLevelElement();
		if (el != null) {
			el.draw(textureManager, new DisplayScale(Minecraft.getInstance()).getScaledHeight() - el.getHeight());
		}

		Minecraft.getInstance().a("Loading Screen");

		tearDownGLState();
	}

	private static void drawMojangLogo() {
		if (mojangLogo == null) {
			mojangLogo = new Texture(Minecraft.RESOURCE_LOGO);
		}

		mojangLogo.bind();

		DisplayScale scale = new DisplayScale(Minecraft.getInstance());

		int width = 256;
//		int height = 38;
		int height = 256;
		int x = (scale.getScaledWidth() - width) / 2;
		int y = (scale.getScaledHeight() - height) / 2;

		int texX = 0;
//		int texY = 109;
		int texY = 0;

		GL11.glColor4f(1, 1, 1, 1);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBegin(GL11.GL_QUADS);

		GL11.glTexCoord2f(texX, texY);
		GL11.glVertex3f(x, y, 0);

		GL11.glTexCoord2f(texX, texY + height);
		GL11.glVertex3f(x, y + height, 0);

		GL11.glTexCoord2f(texX + width, texY + height);
		GL11.glVertex3f(x + width, y + height, 0);

		GL11.glTexCoord2f(texX + width, texY);
		GL11.glVertex3f(x + width, y, 0);

		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
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
