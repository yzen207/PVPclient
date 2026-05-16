package pvpclient.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.screens.TitleScreen;

public class PVPclientClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		HudRenderer.register();
		ScreenEvents.BEFORE_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
			if (screen instanceof TitleScreen && !(screen instanceof CustomTitleScreen)) {
				client.setScreen(new CustomTitleScreen());
			}
		});
	}
}