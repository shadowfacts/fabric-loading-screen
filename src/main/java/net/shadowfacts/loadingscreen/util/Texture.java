package net.shadowfacts.loadingscreen.util;

import net.minecraft.util.Identifier;
import net.shadowfacts.loadingscreen.LoadingScreen;
import none.bwr;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.IntBuffer;

public class Texture {

	private static final IntBuffer buf = BufferUtils.createIntBuffer(4 * 1024 * 1024);

	private Identifier id;
//	private int glId;

	public Texture(Identifier id) {
//		this.id = id;

		try (InputStream in = Utils.openStream(id)) {
			this.id = LoadingScreen.textureManager.a(id.toString(), new bwr(ImageIO.read(in)));

//			BufferedImage image = ImageIO.read(in);
//			in.close();
//
//			int size = 1;
//			while ((size / image.getWidth()) * (size / image.getHeight()) < 1) size *= 2;
//
//			glId = GL11.glGenTextures();
//			GL11.glBindTexture(GL11.GL_TEXTURE_2D, glId);
//			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//
//			buf.clear();
//			int[] array = new int[128 * 128];
//			image.getRGB(0, 0, 128, 128, array, 0, 128);
//			buf.put(array);
//			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, size, size, 0, GL12.GL_BGRA, GL12.GL_UNSIGNED_INT_8_8_8_8_REV, buf);
//
//
//			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Identifier getId() {
		return id;
	}

	public void bind() {
		LoadingScreen.textureManager.bindTexture(id);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, glId);
	}

}
