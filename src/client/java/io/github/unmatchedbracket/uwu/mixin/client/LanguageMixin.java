package io.github.unmatchedbracket.uwu.mixin.client;

import io.github.unmatchedbracket.uwu.Uwuifier;
import net.minecraft.util.Language;

import java.util.function.BiConsumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Language.class)
public class LanguageMixin {
    @ModifyVariable(at = @At("STORE"), method = "create()Lnet/minecraft/util/Language;")
    private static BiConsumer<String, String> create(BiConsumer<String, String> original) {
        return (String a, String b) -> {
            // System.out.println("it's `" + a + "` and `" + b + "`");
            original.accept(a, Uwuifier.uwustring(b));
        };
    }
}