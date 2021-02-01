package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.fluids.GhostWater
import com.theonlytails.rubymod.id
import net.minecraft.fluid.FlowableFluid
import net.minecraft.util.registry.Registry

object FluidRegistry {
	fun <T : FlowableFluid> register(id: String, fluid: T): T = Registry.register(Registry.FLUID, id(id), fluid)

	val stillGhostWater = register("ghost_water", GhostWater.GhostWaterStill())
	val flowingGhostWater = register("flowing_ghost_water", GhostWater.GhostWaterFlowing())
}