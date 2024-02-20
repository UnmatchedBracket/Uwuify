package io.github.unmatchedbracket.uwu.mixin.client;

import io.github.unmatchedbracket.uwu.Uwuifier;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {
    @Inject(
        method = "renderLabelIfPresent(Lnet/minecraft/entity/Entity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
        // at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V")
        at = @At(value = "HEAD")
    )
    // inject *after* the deadmau5 check
    private void renderLabelIfPresent(CallbackInfo ci, @Local LocalRef<Text> lr) {
        System.out.println("injecty nametag");
        lr.set(Uwuifier.uwuify(lr.get()));
    }
}