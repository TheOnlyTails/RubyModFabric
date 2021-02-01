@file:Suppress("DEPRECATION")

package com.theonlytails.rubymod.world

import com.theonlytails.rubymod.id
import com.theonlytails.rubymod.registries.BlockRegistry
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.decorator.RangeDecoratorConfig
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreFeatureConfig

object FeatureGen {
	private const val veinSize = 2
	private const val maxHeight = 30
	private const val minHeight = 23
	private const val veinsPerChunk = 10

	private val ORE_RUBY = Feature.NO_SURFACE_ORE
		.configure(OreFeatureConfig(
			OreFeatureConfig.Rules.BASE_STONE_NETHER,
			BlockRegistry.rubyOre.defaultState,
			veinSize
		))
		.decorate(Decorator.RANGE.configure(RangeDecoratorConfig(minHeight, 0, maxHeight)))
		.spreadHorizontally()
		.repeat(veinsPerChunk)

	private val ORE_RUBY_KEY =
		RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, id("ore_ruby"))

	fun addFeaturesToBiomes() {
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ORE_RUBY_KEY.value, ORE_RUBY)
		BiomeModifications.addFeature(
			BiomeSelectors.foundInTheNether(),
			GenerationStep.Feature.UNDERGROUND_ORES,
			ORE_RUBY_KEY)
	}
}