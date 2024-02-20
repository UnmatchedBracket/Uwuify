package io.github.unmatchedbracket.uwu.mixin.client;

import io.github.unmatchedbracket.uwu.Uwuifier;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BossBarHud.class)
public class BossBarHudMixin {
    @ModifyVariable(at = @At("STORE"), method = "render(Lnet/minecraft/client/gui/DrawContext;)V")
    private Text render(Text text) {
        return Uwuifier.uwuify(text);
    }
}