package pvpclient.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pvpclient.client.HudRenderer;

@Mixin(MouseHandler.class)
public class ExampleClientMixin {
	@Inject(at = @At("HEAD"), method = "onPress")
	private void onMouseClick(long window, int button, int action, int mods, CallbackInfo info) {
		if (action == 1) {
			HudRenderer.registerClick();
		}
	}
}