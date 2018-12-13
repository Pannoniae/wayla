package net.panno.wayla.mixin;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HitResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.FluidRayTraceMode;
import net.panno.wayla.HUD;
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

    private HUD hud;



    @Inject(at = @At(value = "RETURN"), method = "draw(F)V")
    private void drawWAYLAInfo(CallbackInfo info) {
        if (hud == null) {
            hud = new HUD();
        }
        hud.draw();
    }
}