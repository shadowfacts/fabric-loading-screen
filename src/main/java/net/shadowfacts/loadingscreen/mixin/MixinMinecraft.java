package net.shadowfacts.loadingscreen.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.text.TextFormat;
import net.shadowfacts.loadingscreen.LoadingScreen;
import net.shadowfacts.loadingscreen.api.LoadingScreenAPI;
import net.shadowfacts.loadingscreen.api.element.ILoadingScreenElement;
import net.shadowfacts.loadingscreen.api.element.IProgressBarElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.Color;

@Mixin(value = Minecraft.class, remap = false)
public abstract class MixinMinecraft {

	@Overwrite
	public void a(TextureManager textureManager) {
		if (LoadingScreen.enabled) {
			ILoadingScreenElement el = LoadingScreenAPI.getElementFactory().createTextElement();
			el.setText("Initializing Minecraft");
//			el.setProgress(0.5f);
			LoadingScreenAPI.setTopLevelElement(el);

			LoadingScreen.textureManager = textureManager;
			LoadingScreen.draw(textureManager);
		}
	}

	@Inject(method = "a(Ljava/lang/String;)V", at = @At("RETURN"))
	public void onA(String s, CallbackInfo ci) {
		if ("Post startup".equals(s)) {
//			LoadingScreen.stop();
		}
	}

}
