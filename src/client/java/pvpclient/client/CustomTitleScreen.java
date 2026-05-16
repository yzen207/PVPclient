package pvpclient.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class CustomTitleScreen extends TitleScreen {

    private static final ResourceLocation BACKGROUND =
            ResourceLocation.fromNamespaceAndPath("pvpclient", "textures/gui/background.png");

    private static class DarkButton extends Button {
        public DarkButton(int x, int y, int width, int height, Component label, OnPress onPress) {
            super(x, y, width, height, label, onPress, DEFAULT_NARRATION);
        }

        @Override
        public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
            int x = this.getX();
            int y = this.getY();
            int w = this.getWidth();
            int h = this.getHeight();

            int bgColor = this.isHovered() ? 0xCC9933AA : 0xAA220033;
            guiGraphics.fill(x, y, x + w, y + h, bgColor);

            int borderColor = this.isHovered() ? 0xFFCC88FF : 0x88884499;
            guiGraphics.fill(x,         y,         x + w,     y + 1,     borderColor);
            guiGraphics.fill(x,         y + h - 1, x + w,     y + h,     borderColor);
            guiGraphics.fill(x,         y,         x + 1,     y + h,     borderColor);
            guiGraphics.fill(x + w - 1, y,         x + w,     y + h,     borderColor);

            int textColor = this.isHovered() ? 0xFFFFFFFF : 0xCCDDAAFF;
            guiGraphics.drawCenteredString(
                    Minecraft.getInstance().font,
                    this.getMessage(),
                    x + w / 2,
                    y + (h - 8) / 2,
                    textColor
            );
        }
    }

    @Override
    public void init() {
        super.init();
        this.clearWidgets();

        int centerX = this.width / 2;
        int btnW = 200;
        int btnH = 14;
        int startY = (int)(this.height * 0.75f);
        int gap = 18;

        this.addRenderableWidget(new DarkButton(
                centerX - btnW / 2, startY, btnW, btnH,
                Component.literal("Singleplayer"),
                btn -> this.minecraft.setScreen(
                        new net.minecraft.client.gui.screens.worldselection.SelectWorldScreen(this))
        ));

        this.addRenderableWidget(new DarkButton(
                centerX - btnW / 2, startY + gap, btnW, btnH,
                Component.literal("Multiplayer"),
                btn -> this.minecraft.setScreen(
                        new net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen(this))
        ));

        this.addRenderableWidget(new DarkButton(
                centerX - btnW / 2, startY + gap * 2, btnW, btnH,
                Component.literal("Quit"),
                btn -> this.minecraft.stop()
        ));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.blit(RenderType::guiTextured, BACKGROUND,
                0, 0, 0f, 0f, this.width, this.height, this.width, this.height);

        guiGraphics.fill(0, 0, this.width, this.height, 0x44110022);

        guiGraphics.drawString(this.font, "PVPClient", this.width - 72, 8,  0xFFCC88FF, true);
        guiGraphics.drawString(this.font, "by Jazz",   this.width - 72, 20, 0xAA9999FF, true);

        for (var widget : this.children()) {
            if (widget instanceof DarkButton btn) {
                btn.render(guiGraphics, mouseX, mouseY, partialTick);
            }
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}