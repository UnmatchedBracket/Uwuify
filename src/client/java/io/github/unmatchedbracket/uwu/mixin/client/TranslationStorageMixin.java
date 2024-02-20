package io.github.unmatchedbracket.uwu.mixin.client;

import io.github.unmatchedbracket.uwu.Uwuifier;
import net.minecraft.client.resource.language.TranslationStorage;

import java.util.function.BiConsumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(TranslationStorage.class)
public class TranslationStorageMixin {
    @ModifyArg(
        method = "load(Ljava/lang/String;Ljava/util/List;Ljava/util/Map;)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Language;load(Ljava/io/InputStream;Ljava/util/function/BiConsumer;)V")
    )
    private static BiConsumer<String, String> load(BiConsumer<String, String> original) {
        return (String a, String b) -> {
            // System.out.println("it's `" + a + "` and `" + b + "`");
            original.accept(a, Uwuifier.uwustring(b));
        };
    }

    // @ModifyVariable(at = @At("STORE"), name = "biConsumer", method = "create()Lnet/minecraft/util/Language;")
    // private static BiConsumer<String, String> create(BiConsumer<String, String> original) {
    //     return (String a, String b) -> {
    //         System.out.println("it's `" + a + "` and `" + b + "`");
    //         original.accept(a, Uwuifier.uwustring(b));
    //     };
    // }
}