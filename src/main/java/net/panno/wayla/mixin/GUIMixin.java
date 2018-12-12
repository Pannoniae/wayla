package net.panno.wayla.mixin;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HitResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.FluidRayTraceMode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.panno.wayla.util.Config.*;
import static net.panno.wayla.util.RenderUtil.drawBeveledBox;
import static net.panno.wayla.util.RenderUtil.renderItemStack;


@Mixin(InGameHud.class)
public abstract class GUIMixin {

    @Shadow @Final private MinecraftClient client;



    @Inject(at = @At(value = "RETURN"), method = "draw(F)V")
    private void drawWAILAInfo(CallbackInfo info) {

        MinecraftClient mc = MinecraftClient.getInstance();
        HitResult result = this.client.getCameraEntity().rayTrace(mc.interactionManager.getReachDistance(), 0.0F, FluidRayTraceMode.NONE);

        if (result != null && result.type != HitResult.Type.NONE) {

            if (result.type == HitResult.Type.BLOCK) {

                Block block = mc.world.getBlockState(result.getBlockPos()).getBlock();
                ItemStack stack = block.getItem().getDefaultStack();

                String name = stack.getDisplayName().getText();
                String modName = Registry.BLOCK.getId(block).getNamespace();

                int x = (int)(mc.window.getScaledWidth() / 2.0F);

                 int overlayTop = marginY,
                         blockTop = overlayTop + marginY,
                         blockNameTop = blockTop + blockSize + innerMargin,
                         modNameTop = blockNameTop + mc.fontRenderer.FONT_HEIGHT + innerMargin,
                         overlayBottom = modNameTop + mc.fontRenderer.FONT_HEIGHT + marginY;

                // more precise X
                float x_ = mc.window.getScaledWidth() / 2.0F;

                int textWidth = mc.fontRenderer.getStringWidth(name);
                int modNameWidth = mc.fontRenderer.getStringWidth(modName);

                int minWidth = textWidth > 16 && modNameWidth > 16 ? Math.max(textWidth, modNameWidth) : 16;

                drawBeveledBox(x - (minWidth / 2) - marginX, overlayTop, x + (minWidth / 2) + marginX, overlayBottom,
                        SOLID_COLOR, 0xFFFF5555, BACKGROUND_COLOR);

                renderItemStack(stack, x - (blockSize / 2), blockTop);

                mc.fontRenderer.drawWithShadow(name, x_ - ((float)textWidth / 2), blockNameTop, 0xFFFF5555);

                mc.fontRenderer.drawWithShadow(modName, x_ - ((float)modNameWidth / 2), modNameTop, 0xFFFF5555);



                // Minimum width of box


            }
        }
    }

}
