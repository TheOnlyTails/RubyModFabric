package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.id
import com.theonlytails.rubymod.world.BiomeMaker
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes
import net.fabricmc.fabric.api.biome.v1.OverworldClimate
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.biome.Biome

object BiomeRegistry {
	private fun register(id: String, biome: Biome) =
		Registry.register(BuiltinRegistries.BIOME, id(id), biome)

	val rubyHills = register("ruby_hills", BiomeMaker.makeRubyHills())

	private val rubyHillsKey: RegistryKey<Biome> = RegistryKey.of(Registry.BIOME_KEY, id("ruby_hills"))

	@Suppress("DEPRECATION")
	fun addBiomesToGeneration() {
		OverworldBiomes.addContinentalBiome(rubyHillsKey, OverworldClimate.TEMPERATE, 6.0)
	}
}