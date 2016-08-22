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

//	private static Thread thread;
//	private static boolean running;
//	private static Lock lock = new ReentrantLock(true);

	private static DisplayScale scale;
	private static bpd bpd;

	@Init
	public void init() {
		LoadingScreenAPI.setInternalMethods(new InternalMethods());
		InternalMethods.loadFromConfig(); // TODO: config stuff
		enabled = true;
	}

//	public static void start() {
//		running = true;
//
////		try {
////			Display.getDrawable().releaseContext();
////		} catch (LWJGLException e) {
////			throw new RuntimeException(e);
////		}
//
//		SharedDrawable shared;
//
//		try {
//			shared = new SharedDrawable(Display.getDrawable());
//			Display.getDrawable().releaseContext();
//			shared.makeCurrent();
////			shared.makeCurrent();
//		} catch (LWJGLException e) {
//			throw new RuntimeException(e);
//		}
//
//		thread = new Thread(() -> {
////			try {
////				shared.makeCurrent();
////			} catch (LWJGLException e) {
////				throw new RuntimeException(e);
////			}
//
//			setupGLState();
//
//			while (running) {
//				draw(textureManager);
//			}
//
//			tearDownGLState();
//		});
//
//		thread.start();
//	}

//	public static void stop() {
//		running = false;
//
////		try {
////			Display.getDrawable().makeCurrent();
////		} catch (LWJGLException e) {
////			throw new RuntimeException(e);
////		}
//	}

	public static void draw(TextureManager textureManager) {
		setupGLState();

//		ILoadingScreenElement el = LoadingScreenAPI.getTopLevelElement();
//		if (el != null) {
//			el.draw(textureManager, new DisplayScale(Minecraft.getInstance()).getScaledHeight() / 2);
//		}
//		Utils.drawRect(0, 0, 100, 100, Color.RED);
		LSFontRenderer.drawString("Hello, World!", 0, 0, Color.BLACK);

		Minecraft.getInstance().a("Loading Screen");

		tearDownGLState();
	}

	private static void setupGLState() {
//		lock.lock();
//		try {
//			Display.getDrawable().makeCurrent();
//		} catch (LWJGLException e) {
//			throw new RuntimeException(e);
//		}

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

//		try {
//			Display.getDrawable().releaseContext();
//		} catch (LWJGLException e) {
//			throw new RuntimeException(e);
//		} finally {
//			lock.unlock();
//		}
	}

}
