package net.shadowfacts.loadingscreen.util;

import net.minecraft.client.GlHandler;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LSFontRenderer {

	private static final String CHARACTERS = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000";

	private static final Identifier fontTexId = new Identifier("textures/font/ascii.png");
	private static final Texture fontTex = new Texture(fontTexId);

	private static final int[] widths = new int[256];

	public static final int FONT_HEIGHT = 9;

	static {
		try (InputStream in = Utils.openStream(fontTexId)) {
			BufferedImage image = ImageIO.read(in);

			int width = image.getWidth();
			int height = image.getHeight();
			int[] colors = new int[width * height];
			image.getRGB(0, 0, width, height, colors, 0, width);
			int wid = height / 16;
			int lvt_7_1_ = width / 16;
			boolean lvt_8_1_ = true;
			float lvt_9_1_ = 8.0F / (float)lvt_7_1_;

			for (int charIndex = 0; charIndex < 256; ++charIndex)
			{
				int charX = charIndex % 16;
				int charY = charIndex / 16;

				if (charIndex == 32)
				{
					widths[charIndex] = 4;
				}

				int l1;

				for (l1 = lvt_7_1_ - 1; l1 >= 0; --l1)
				{
					int i2 = charX * lvt_7_1_ + l1;
					boolean flag1 = true;

					for (int j2 = 0; j2 < wid && flag1; ++j2)
					{
						int k2 = (charY * lvt_7_1_ + j2) * width;

						if ((colors[i2 + k2] >> 24 & 255) != 0)
						{
							flag1 = false;
						}
					}

					if (!flag1)
					{
						break;
					}
				}

				++l1;
				widths[charIndex] = (int)(0.5D + (double)((float)l1 * lvt_9_1_)) + 1;
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static int getStringWidth(String s) {
		int width = 0;
		for (char c : s.toCharArray()) {
			width += getCharWidth(c);
		}
		return width;
	}

	public static int drawString(String s, int startX, int y, Color color) {
		int x = startX;

		GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
//		GL11.glEnable(GL11.GL_TEXTURE_2D);
//		GL11.glDisable(GL11.GL_LIGHTING);
//		GL11.glDisable(GL11.GL_DEPTH_TEST);
//		GL11.glEnable(GL11.GL_BLEND);
//		GlHandler.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		fontTex.bind();

//		GL11.glBegin(GL11.GL_QUADS);
//
//		GL11.glTexCoord2f(0, 0);
//		GL11.glVertex2f(0, 0);
//
//		GL11.glTexCoord2f(0, 1);
//		GL11.glVertex2f(0, 256);
//
//		GL11.glTexCoord2f(1, 1);
//		GL11.glVertex2f(256, 256);
//
//		GL11.glTexCoord2f(1, 0);
//		GL11.glVertex2f(256, 0);
//
//		GL11.glEnd();
//
//		if (true) return x;

		for (char c : s.toCharArray()) {
			int index = CHARACTERS.indexOf(c);
			int charX = index % 16 * 8;
			int charY = index / 16 * 8;

			int charWidth = getCharWidth(c);

			float f = (float)charWidth - 0.01F;
			GL11.glBegin(GL11.GL_TRIANGLE_STRIP);

			GL11.glTexCoord2f((float)charX / 128.0F, (float)charY / 128.0F);
			GL11.glVertex3f(x, y, 0.0F);

			GL11.glTexCoord2f((float)charX / 128.0F, ((float)charY + 7.99F) / 128.0F);
			GL11.glVertex3f(x, y + 7.99F, 0.0F);

			GL11.glTexCoord2f(((float)charX + f - 1.0F) / 128.0F, (float)charY / 128.0F);
			GL11.glVertex3f(x + f - 1.0F, y, 0.0F);

			GL11.glTexCoord2f(((float)charX + f - 1.0F) / 128.0F, ((float)charY + 7.99F) / 128.0F);
			GL11.glVertex3f(x + f - 1.0F, y + 7.99F, 0.0F);

			GL11.glEnd();

			x += charWidth;
		}
		return x;
	}

	private static int getCharWidth(char c) {
		if (c == 32) {
			return 4;
		} else {
			return widths[CHARACTERS.indexOf(c)];
		}
	}

}
