package io.github.unmatchedbracket.uwu.mixin.client;

import io.github.unmatchedbracket.uwu.Uwuifier;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ChatHud.class)
public class ChatHudMixin {
    // Text message, @Nullable MessageSignatureData signature, @Nullable MessageIndicator indicator
    @ModifyVariable(at = @At("HEAD"), method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V")
    private Text addMessage(Text text) {
        return Uwuifier.uwuify(text);
    }
}