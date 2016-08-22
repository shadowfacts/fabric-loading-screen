package net.shadowfacts.loadingscreen.util;

import net.minecraft.client.GameSettings;
import net.minecraft.client.GlHandler;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexBuffer;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.client.util.FontRenderer;
import net.minecraft.util.Identifier;
import none.bxj;

import java.util.Locale;

/**
 * @author shadowfacts
 */
public class LSMinecraftFontRenderer extends FontRenderer {

	private static final Identifier FONT_TEX_ID = new Identifier("textures/font/ascii.png");
	private static final Texture FONT_TEX = new Texture(FONT_TEX_ID);

	private static LSMinecraftFontRenderer instance;

	public LSMinecraftFontRenderer(GameSettings settings, TextureManager textureManager) {
		super(settings, FONT_TEX_ID, textureManager, false);
		bindTexture(this.g);
		super.a(null);
		this.v = true;
	}

	public int drawString(String str, float x, float y, int color) {
		return this.drawString(str, x, y, color, true);
	}

	public int drawStringWithoutShadow(String str, int x, int y, int color) {
		return this.drawString(str, (float)x, (float)y, color, false);
	}

	public int drawString(String str, float x, float y, int color, boolean shadow) {
		GlHandler.e();
		this.e();
		int v1;
		if(shadow) {
			v1 = this.b(str, x + 1.0F, y + 1.0F, color, true);
			v1 = Math.max(v1, this.b(str, x, y, color, false));
		} else {
			v1 = this.b(str, x, y, color, false);
		}

		return v1;
	}

	public int b(String a1, float a2, float a3, int a4, boolean a5) {
		if(a1 == null) {
			return 0;
		} else {
			if(this.l) {
				a1 = this.c(a1);
			}

			if((a4 & -67108864) == 0) {
				a4 |= -16777216;
			}

			if(a5) {
				a4 = (a4 & 16579836) >> 2 | a4 & -16777216;
			}

			this.m = (float)(a4 >> 16 & 255) / 255.0F;
			this.n = (float)(a4 >> 8 & 255) / 255.0F;
			this.o = (float)(a4 & 255) / 255.0F;
			this.p = (float)(a4 >> 24 & 255) / 255.0F;
			GlHandler.color(this.m, this.n, this.o, this.p);
			this.i = a2;
			this.j = a3;
			this.a(a1, a5);
			return (int)this.i;
		}
	}

	public void a(String a1, boolean a2) {
		for(int v1 = 0; v1 < a1.length(); ++v1) {
			char v2 = a1.charAt(v1);
			int v3;
			int v4;
			if(v2 == 167 && v1 + 1 < a1.length()) {
				v3 = "0123456789abcdefklmnor".indexOf(a1.toLowerCase(Locale.ENGLISH).charAt(v1 + 1));
				if(v3 < 16) {
					this.r = false;
					this.s = false;
					this.v = false;
					this.u = false;
					this.t = false;
					if(v3 < 0 || v3 > 15) {
						v3 = 15;
					}

					if(a2) {
						v3 += 16;
					}

					v4 = this.f[v3];
					this.q = v4;
					GlHandler.color((float)(v4 >> 16) / 255.0F, (float)(v4 >> 8 & 255) / 255.0F, (float)(v4 & 255) / 255.0F, this.p);
				} else if(v3 == 16) {
					this.r = true;
				} else if(v3 == 17) {
					this.s = true;
				} else if(v3 == 18) {
					this.v = true;
				} else if(v3 == 19) {
					this.u = true;
				} else if(v3 == 20) {
					this.t = true;
				} else if(v3 == 21) {
					this.r = false;
					this.s = false;
					this.v = false;
					this.u = false;
					this.t = false;
					GlHandler.color(this.m, this.n, this.o, this.p);
				}

				++v1;
			} else {
				v3 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000".indexOf(v2);
				if(this.r && v3 != -1) {
					v4 = this.a(v2);

					char v5;
					do {
						v3 = this.rand.nextInt("ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000".length());
						v5 = "ÀÁÂÈÊËÍÓÔÕÚßãõğİıŒœŞşŴŵžȇ\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»░▒▓│┤╡╢╖╕╣║╗╝╜╛┐└┴┬├─┼╞╟╚╔╩╦╠═╬╧╨╤╥╙╘╒╓╫╪┘┌█▄▌▐▀αβΓπΣσμτΦΘΩδ∞∅∈∩≡±≥≤⌠⌡÷≈°∙·√ⁿ²■\u0000".charAt(v3);
					} while(v4 != this.a(v5));

					v2 = v5;
				}

				float var12 = this.k?0.5F:1.0F;
				boolean var13 = (v2 == 0 || v3 == -1 || this.k) && a2;
				if(var13) {
					this.i -= var12;
					this.j -= var12;
				}

				float v6 = this.a(v2, this.t);
				if(var13) {
					this.i += var12;
					this.j += var12;
				}

				if(this.s) {
					this.i += var12;
					if(var13) {
						this.i -= var12;
						this.j -= var12;
					}

					this.a(v2, this.t);
					this.i -= var12;
					if(var13) {
						this.i += var12;
						this.j += var12;
					}

					++v6;
				}

				Tessellator v7;
				VertexBuffer v8;
				if(this.v) {
					v7 = Tessellator.getInstance();
					v8 = v7.getVertexBuffer();
					GlHandler.z();

//					GL11.glBegin(GL11.GL_QUADS);
//
//					GL11.glVertex2f(this.i, this.j + FONT_HEIGHT / 2);
//					GL11.glTexCoord2f(this.i, this.j + FONT_HEIGHT / 2);
//
//					GL11.glVertex2f(this.i + v6, this.j + FONT_HEIGHT / 2);
//					GL11.glTexCoord2f(this.i + v6, this.j + FONT_HEIGHT / 2);
//
//					GL11.glVertex2f(this.i + v6, this.j + FONT_HEIGHT / 2 - 1);
//					GL11.glTexCoord2f(this.i + v6, this.j + FONT_HEIGHT / 2 - 1);
//
//					GL11.glVertex2f(this.i, this.j + FONT_HEIGHT / 2 - 1);
//					GL11.glTexCoord2f(this.i, this.j + FONT_HEIGHT / 2 - 1);
//					GL11.glEnd();

					v8.begin(7, bxj.e);
					v8.pos((double)this.i, (double)(this.j + (float)(this.FONT_HEIGHT / 2)), 0.0D).end();
					v8.pos((double)(this.i + v6), (double)(this.j + (float)(this.FONT_HEIGHT / 2)), 0.0D).end();
					v8.pos((double)(this.i + v6), (double)(this.j + (float)(this.FONT_HEIGHT / 2) - 1.0F), 0.0D).end();
					v8.pos((double)this.i, (double)(this.j + (float)(this.FONT_HEIGHT / 2) - 1.0F), 0.0D).end();
					v7.b();

					GlHandler.y();
				}

				if(this.u) {
					v7 = Tessellator.getInstance();
					v8 = v7.getVertexBuffer();
					GlHandler.z();
					int v9 = this.u?-1:0;

//					GL11.glBegin(GL11.GL_QUADS);
//
//					GL11.glVertex2f(this.i + v9, this.j + FONT_HEIGHT);
//					GL11.glTexCoord2f(this.i + v9, this.j + FONT_HEIGHT);
//
//					GL11.glVertex2f(this.i + v6, this.j + FONT_HEIGHT);
//					GL11.glTexCoord2f(this.i + v6, this.j + FONT_HEIGHT);
//
//					GL11.glVertex2f(this.i + v6, j + FONT_HEIGHT - 1);
//					GL11.glTexCoord2f(this.i + v6, j + FONT_HEIGHT - 1);
//
//					GL11.glVertex2f(this.i + v9, this.j + FONT_HEIGHT - 1);
//					GL11.glTexCoord2f(this.i + v9, this.j + FONT_HEIGHT - 1);
//
//					GL11.glEnd();

					v8.begin(7, bxj.e);
					v8.pos((double)(this.i + (float)v9), (double)(this.j + (float)this.FONT_HEIGHT), 0.0D).end();
					v8.pos((double)(this.i + v6), (double)(this.j + (float)this.FONT_HEIGHT), 0.0D).end();
					v8.pos((double)(this.i + v6), (double)(this.j + (float)this.FONT_HEIGHT - 1.0F), 0.0D).end();
					v8.pos((double)(this.i + (float)v9), (double)(this.j + (float)this.FONT_HEIGHT - 1.0F), 0.0D).end();
					v7.b();

					GlHandler.y();
				}

				this.i += (float)((int)v6);
			}
		}

	}

	public static void bindTexture(Identifier id) {
		if (id.equals(FONT_TEX_ID)) {
			FONT_TEX.bind();
		} else {
			throw new IllegalArgumentException("LSMinecraftFontRenderer attempted to bind invalid texture: " + id);
		}
	}

	public static LSMinecraftFontRenderer getInstance(GameSettings settings, TextureManager textureManager) {
		if (instance == null) {
			instance = new LSMinecraftFontRenderer(settings, textureManager);
		}
		return instance;
	}

}
