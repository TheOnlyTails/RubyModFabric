package com.theonlytails.rubymod.mixins;

import com.theonlytails.rubymod.fluids.FluidInfo;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public class BiomeMixin {
    @Inject(method = "Lnet/minecraft/world/biome/Biome;getWaterFogColor()I", at = @At(value = "HEAD"), cancellable = true)
    private void getWaterFogColor(CallbackInfoReturnable<Integer> cir) {
        if (MinecraftClient.getInstance().gameRenderer.getCamera().getSubmergedFluidState().getFluid() instanceof FluidInfo) {
            FluidInfo fluidInfo = (FluidInfo) MinecraftClient.getInstance().gameRenderer.getCamera().getSubmergedFluidState().getFluid();
            cir.setReturnValue(fluidInfo.getFogColor());
        }
    }
}