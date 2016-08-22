package net.shadowfacts.loadingscreen.util;

import net.minecraft.util.Identifier;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.IntBuffer;

public class Texture {

	private static final IntBuffer buf = BufferUtils.createIntBuffer(4 * 1024 * 1024);

	private Identifier id;
	private int glId;

	public Texture(Identifier id) {
		this.id = id;
		try {
			InputStream in = Utils.openStream(id);
			BufferedImage image = ImageIO.read(in);
			in.close();

			int size = 1;
			while ((size / image.getWidth()) * (size / image.getHeight()) < 1) size *= 2;

			glId = GL11.glGenTextures();
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, glId);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL12.GL_TEXTURE_BASE_LEVEL, 0);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL12.GL_TEXTURE_MAX_LEVEL, 0);

			buf.clear();
			for (int y = 0; y < image.getHeight(); y++) {
				for (int x = 0; x < image.getWidth(); x++) {
					buf.put(image.getRGB(x, y));
				}
			}
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, size, size, 0, GL12.GL_BGRA, GL12.GL_UNSIGNED_INT_8_8_8_8_REV, buf);


			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
			GL11.glDisable(GL11.GL_TEXTURE_2D);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Identifier getId() {
		return id;
	}

	public void bind() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, glId);
	}

}
