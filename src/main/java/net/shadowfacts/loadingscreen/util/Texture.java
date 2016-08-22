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

	private Identifier id;

	public Texture(Identifier id) {
		try (InputStream in = Utils.openStream(id)) {
			this.id = LoadingScreen.textureManager.a(id.toString(), new bwr(ImageIO.read(in)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Identifier getId() {
		return id;
	}

	public void bind() {
		LoadingScreen.textureManager.bindTexture(id);
	}

}
