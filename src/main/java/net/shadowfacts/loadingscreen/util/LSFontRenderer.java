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

	public static final int[] widths = new int[256];

	static {
		try (InputStream in = Utils.openStream(fontTexId)) {
			BufferedImage image = ImageIO.read(in);

			int lvt_3_1_ = image.getWidth();
			int lvt_4_1_ = image.getHeight();
			int[] lvt_5_1_ = new int[lvt_3_1_ * lvt_4_1_];
			image.getRGB(0, 0, lvt_3_1_, lvt_4_1_, lvt_5_1_, 0, lvt_3_1_);
			int lvt_6_1_ = lvt_4_1_ / 16;
			int lvt_7_1_ = lvt_3_1_ / 16;
			boolean lvt_8_1_ = true;
			float lvt_9_1_ = 8.0F / (float)lvt_7_1_;

			for (int lvt_10_1_ = 0; lvt_10_1_ < 256; ++lvt_10_1_)
			{
				int j1 = lvt_10_1_ % 16;
				int k1 = lvt_10_1_ / 16;

				if (lvt_10_1_ == 32)
				{
					widths[lvt_10_1_] = 4;
				}

				int l1;

				for (l1 = lvt_7_1_ - 1; l1 >= 0; --l1)
				{
					int i2 = j1 * lvt_7_1_ + l1;
					boolean flag1 = true;

					for (int j2 = 0; j2 < lvt_6_1_ && flag1; ++j2)
					{
						int k2 = (k1 * lvt_7_1_ + j2) * lvt_3_1_;

						if ((lvt_5_1_[i2 + k2] >> 24 & 255) != 0)
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
				widths[lvt_10_1_] = (int)(0.5D + (double)((float)l1 * lvt_9_1_)) + 1;
			}

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static int getStringWidth(String s) {
		int width = 0;
		for (char c : s.toCharArray()) {
			width += getCharWidth(c, CHARACTERS.indexOf(c));
		}
		return width;
	}

	public static int drawString(String s, int startX, int y, Color color) {
		int x = startX;

		GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		fontTex.bind();

		for (char c : s.toCharArray()) {
			int index = CHARACTERS.indexOf(c);
			int charX = index % 16 * 8;
			int charY = index / 16 * 8;

			int charWidth = getCharWidth(c, index);

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

	private static int getCharWidth(char c, int index) {
		if (c == 32) {
			return 4;
		} else {
			return widths[index];
		}
	}

}
