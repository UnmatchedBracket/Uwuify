package io.github.unmatchedbracket.uwu.mixin.client;

import io.github.unmatchedbracket.uwu.Uwuifier;
import net.minecraft.client.resource.language.I18n;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(I18n.class)
public class I18nMixin {
    @ModifyVariable(at = @At("STORE"), ordinal = 1, method = "translate(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;")
    private static String translate(String text) {
        // System.out.println(text);
        return Uwuifier.uwustring(text);
    }
}