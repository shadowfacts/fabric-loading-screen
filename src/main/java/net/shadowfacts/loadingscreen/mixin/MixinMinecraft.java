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
			IProgressBarElement el = LoadingScreenAPI.getElementFactory().createProgressElement();
			el.setStages(100);
			el.setText("Loading mods");
			LoadingScreenAPI.setTopLevelElement(el);

			LoadingScreen.textureManager = textureManager;
			LoadingScreen.draw(textureManager);

			while (el.getProgress() < 1) {
				el.incrementStage();
				LoadingScreen.draw(textureManager);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

//			for (int i = 0; i < 3; i++) {
//				IProgressBarElement child = LoadingScreenAPI.getElementFactory().createProgressElement();
//				child.setText("Mod " + i);
//				child.setProgress(0);
//				el.setChild(child);
//				while (child.getProgress() < 1) {
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//					child.setProgress(child.getProgress() + 0.25f);
//					LoadingScreen.draw(textureManager);
//				}
//				el.incrementStage();
//				el.setChild(null);
//				LoadingScreen.draw(textureManager);
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
		}
	}

	@Inject(method = "a(Ljava/lang/String;)V", at = @At("RETURN"))
	public void onA(String s, CallbackInfo ci) {
		if ("Post startup".equals(s)) {
//			LoadingScreen.stop();
		}
	}

}
