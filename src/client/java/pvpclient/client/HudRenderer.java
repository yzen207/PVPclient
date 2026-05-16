package pvpclient.client;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;

public class HudRenderer {
    private static int cps = 0;
    private static int clicks = 0;
    private static long lastSecond = System.currentTimeMillis();

    public static void register() {
        HudRenderCallback.EVENT.register((guiGraphics, tickDelta) -> {
            Minecraft client = Minecraft.getInstance();
            if (client.player == null) return;

            long now = System.currentTimeMillis();
            if (now - lastSecond >= 1000) {
                cps = clicks;
                clicks = 0;
                lastSecond = now;
            }

            guiGraphics.drawString(client.font, "PVPClient", 2, 2, 0xFF0000, true);
            guiGraphics.drawString(client.font, "FPS: " + client.getFps(), 2, 12, 0xFFFFFF, true);
            guiGraphics.drawString(client.font, "CPS: " + cps, 2, 22, 0xFFFFFF, true);
            guiGraphics.drawString(client.font, "Combo: 0", 2, 32, 0xFFFFFF, true);
        });
    }

    public static void registerClick() {
        clicks++;
    }
}