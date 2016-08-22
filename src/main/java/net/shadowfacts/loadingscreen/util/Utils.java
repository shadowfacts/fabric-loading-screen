package net.shadowfacts.loadingscreen.util;

import net.minecraft.client.GlHandler;
import net.minecraft.client.GlHandler.r;
import net.minecraft.client.GlHandler.l;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexBuffer;
import net.minecraft.util.Identifier;
import none.bxj;
import none.byc;
import org.lwjgl.opengl.GL11;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

	public static int toARGB(Color color) {
		return ((color.getAlpha() & 255) << 24) | ((color.getRed() & 255) << 16) | ((color.getGreen() & 255) << 8) | (color.getBlue() & 255);
	}

	public static void drawRect(int x, int y, int width, int height, Color color) {

//		int r = color.getRed();
//		int g = color.getGreen();
//		int b = color.getBlue();
//		int a = color.getAlpha();
//
//		Tessellator tessellator = Tessellator.getInstance();
//		VertexBuffer buffer = tessellator.getVertexBuffer();
//		buffer.begin(GL11.GL_QUADS, bxj.e);
//
//		buffer.pos(x, y, 0).color(r, g, b, a).end();
//		buffer.pos(x, y + height, 0).color(r, g, b, a).end();
//		buffer.pos(x + width, y + height, 0).color(r, g, b, a).end();
//		buffer.pos(x + width, y, 0).color(r, g, b, a).end();
//
//		tessellator.b();

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
		GL11.glVertex2f(x, y);
		GL11.glVertex2f(x, y + height);
		GL11.glVertex2f(x + width, y + height);
		GL11.glVertex2f(x + width, y);
		GL11.glEnd();

//		float zLevel = 0;
//		Tessellator tessellator = Tessellator.getInstance();
//		VertexBuffer buffer = tessellator.getVertexBuffer();
//		GlHandler.m();
//		GlHandler.z();
//		GlHandler.a(r.l, l.j, r.e, l.n);
//		GlHandler.color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
//		buffer.begin(GL11.GL_QUADS, bxj.e);
//		buffer.pos((double)x, (double)y + height, zLevel).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).end();
//		buffer.pos((double)x + width, (double)y + height, zLevel).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).end();
//		buffer.pos((double)x + width, (double)y, zLevel).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).end();
//		buffer.pos((double)x, (double)y, zLevel).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).end();
//		tessellator.b();
//		GlHandler.y();
//		GlHandler.l();
	}

	public static InputStream openStream(Identifier id) throws IOException {
		return Utils.class.getResourceAsStream(String.format("/assets/%s/%s", id.getOwner(), id.getName()));
//		for (byc pack : Minecraft.getInstance().aB) {
//			if (pack.b(id)) {
//				return pack.a(id);
//			}
//		}
//		throw new RuntimeException("Couldn't find resource pack to handle " + id);
	}

	public static void drawOutline(int x, int y, int width, int height) {
		drawRect(x, y, width, height, Color.BLACK);
		drawRect(x + 1, y + 1, width - 2, height - 2, Color.WHITE);
	}
}
