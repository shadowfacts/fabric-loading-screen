package net.shadowfacts.loadingscreen.mixin;

import net.minecraft.client.util.FontRenderer;
import net.minecraft.util.Identifier;
import net.shadowfacts.loadingscreen.util.LSMinecraftFontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = FontRenderer.class, remap = false)
public abstract class MixinFontRenderer {

	@Shadow
	public Identifier g;

	@Shadow
	public abstract Identifier a(int a1);

//	@Inject(method = "<init>*", at = @At(value = "RETURN"))
//	public void onConstruction(CallbackInfo ci) {
//		if (LSMinecraftFontRenderer.class.isAssignableFrom(getClass())) {
//			LSMinecraftFontRenderer.bindTexture(this.g);
//		}
//	}

	@Inject(method = "a(IZ)F", at = @At(value = "HEAD", shift = At.Shift.BY, by = 28))
	public void onA(int a1, boolean a2, CallbackInfoReturnable<Float> ci) {
		if (LSMinecraftFontRenderer.class.isAssignableFrom(getClass())) {
			LSMinecraftFontRenderer.bindTexture(this.g);
		}
	}

	@Inject(method = "b(I)V", at = @At("HEAD"), cancellable = true)
	public void onB(int a1, CallbackInfo ci) {
		if (LSMinecraftFontRenderer.class.isAssignableFrom(getClass())) {
			LSMinecraftFontRenderer.bindTexture(this.a(a1));
			ci.cancel();
		}
	}

}
