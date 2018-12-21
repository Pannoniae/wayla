package net.panno.wayla.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import net.panno.wayla.HUD;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class GUIMixin {

    private HUD waylaHUD;

    @Inject(at = @At(value = "RETURN"), method = "draw(F)V")
    private void drawWAYLAInfo(float v, CallbackInfo ci) {
        if (waylaHUD == null) {
            waylaHUD = new HUD();
        }
        waylaHUD.draw();
    }
}