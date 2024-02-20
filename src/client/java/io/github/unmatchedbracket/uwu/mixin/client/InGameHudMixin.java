package io.github.unmatchedbracket.uwu.mixin.client;

import io.github.unmatchedbracket.uwu.Uwuifier;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @ModifyVariable(at = @At("HEAD"), method = "setTitle(Lnet/minecraft/text/Text;)V")
    private Text setTitle(Text text) {
        return Uwuifier.uwuify(text);
    }

    @ModifyVariable(at = @At("HEAD"), method = "setSubtitle(Lnet/minecraft/text/Text;)V")
    private Text setSubtitle(Text text) {
        return Uwuifier.uwuify(text);
    }

    @ModifyVariable(at = @At("HEAD"), method = "setOverlayMessage(Lnet/minecraft/text/Text;Z)V")
    private Text setActionBar(Text text) {
        return Uwuifier.uwuify(text);
    }

    @ModifyVariable(at = @At("STORE"), ordinal = 0, method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V")
    private Text renderScoreboardSidebar(Text text) {
        return Uwuifier.uwuify(text);
    }

    // theese needa be static like the func they mixin
    @ModifyVariable(at = @At("STORE"), ordinal = 1, method = "method_55439(Lnet/minecraft/scoreboard/Scoreboard;Lnet/minecraft/scoreboard/number/NumberFormat;Lnet/minecraft/scoreboard/ScoreboardEntry;)Lnet/minecraft/client/gui/hud/InGameHud$SidebarEntry;")
    private Text renderScoreboardSidebar_lambda(Text text) {
        return Uwuifier.uwuify(text);
    }

    // only the first one needs to be uwu'd, the second one is just a number
    //adding Z to sig failed with same error; may be faulty sig
    // @ModifyVariable(at = @At("STORE"), ordinal = 1, method = "method_55439(Lnet/minecraft/scoreboard/Scoreboard;Lnet/minecraft/scoreboard/number/NumberFormat;Lnet/minecraft/scoreboard/ScoreboardEntry;)Lnet/minecraft/client/gui/hud/InGameHud$SidebarEntry;")
    // private MutableText renderScoreboardSidebar3(MutableText text) {
    //     return Uwuifier.uwuify(text).copy();
    // }
}