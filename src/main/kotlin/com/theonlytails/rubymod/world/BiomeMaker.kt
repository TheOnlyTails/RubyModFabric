package com.theonlytails.rubymod.world

import com.theonlytails.rubymod.registries.EntityTypeRegistry
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.util.math.MathHelper
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.world.biome.*
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures
import net.minecraft.world.gen.feature.DefaultBiomeFeatures
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig

@Suppress("SameParameterValue")
object BiomeMaker {
	fun makeRubyHills(): Biome {
		val genSettings = genSettings(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG)

		BuiltinRegistries.init()

		genSettings.structureFeature(ConfiguredStructureFeatures.PILLAGER_OUTPOST)
		genSettings.structureFeature(ConfiguredStructureFeatures.VILLAGE_PLAINS)
		genSettings.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL)

		DefaultBiomeFeatures.addLandCarvers(genSettings)
		DefaultBiomeFeatures.addDefaultUndergroundStructures(genSettings)
		DefaultBiomeFeatures.addDungeons(genSettings)
		DefaultBiomeFeatures.addMineables(genSettings)
		DefaultBiomeFeatures.addDefaultOres(genSettings)
		DefaultBiomeFeatures.addDefaultDisks(genSettings)
		DefaultBiomeFeatures.addMushroomFieldsFeatures(genSettings)
		DefaultBiomeFeatures.addSprings(genSettings)
		DefaultBiomeFeatures.addDefaultLakes(genSettings)

		val spawnSettings = spawnSettings()

		spawnSettings.addSpawn(SpawnGroup.CREATURE,
			EntityTypeRegistry.RUBY_SHEEP, 12, 2, 3)

		spawnSettings.addSpawn(SpawnGroup.CREATURE,
			EntityType.MULE, 5, 1, 3)

		DefaultBiomeFeatures.addFarmAnimals(spawnSettings)
		DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings)

		return biome(
			precipitation = Biome.Precipitation.NONE,
			category = Biome.Category.EXTREME_HILLS,
			depth = 0.13f,
			scale = 0.5f,
			temperature = 0.5f,
			downfall = 0.3f,
			effects(
				grassColor = 0xe80a0a,
				skyColor = getSkyForTemp(0.5f)
			),
			genSettings,
			spawnSettings.build()
		)
	}

	/**
	 * Base biome function
	 * Sky color is not generated
	 */
	private fun biome(
		precipitation: Biome.Precipitation,
		category: Biome.Category,
		depth: Float,
		scale: Float,
		temperature: Float,
		downfall: Float,
		effects: BiomeEffects.Builder,
		genSettings: GenerationSettings.Builder,
		spawnSettings: SpawnSettings = SpawnSettings.INSTANCE,
	): Biome {
		return Biome.Builder()
			.precipitation(precipitation)
			.category(category)
			.depth(depth)
			.scale(scale)
			.temperature(temperature)
			.downfall(downfall)
			.effects(effects.build())
			.generationSettings(genSettings.build())
			.spawnSettings(spawnSettings)
			.build()
	}

	/**
	 * Biome ambience with default parameters and enforced the required ones.
	 * Should prevent slip ups on my part :)
	 */
	private fun effects(
		waterColor: Int = 0x3f76e4,
		waterFogColor: Int = 0x50533,
		grassColor: Int = 0x91bd59,
		foliageColor: Int = 0x77ab2f,
		skyColor: Int,
		skyFogColor: Int = 12638463,
	): BiomeEffects.Builder {
		return BiomeEffects.Builder()
			.waterColor(waterColor)
			.waterFogColor(waterFogColor)
			.grassColor(grassColor)
			.foliageColor(foliageColor)
			.skyColor(skyColor)
			.fogColor(skyFogColor)
	}

	/** Shortcut function and enforces surface builder */
	private fun <C : SurfaceConfig> genSettings(
		surfaceBuilder: SurfaceBuilder<C>,
		config: C,
	): GenerationSettings.Builder {
		return GenerationSettings.Builder().surfaceBuilder(surfaceBuilder.withConfig(config))
	}

	/** Shortcut function */
	private fun spawnSettings(): SpawnSettings.Builder {
		return SpawnSettings.Builder()
	}

	/** Shortcut function */
	private fun SpawnSettings.Builder.addSpawn(
		classification: SpawnGroup,
		entityType: EntityType<*>,
		weight: Int,
		min: Int,
		max: Int,
	): SpawnSettings.Builder {
		return spawn(classification, SpawnSettings.SpawnEntry(entityType, weight, min, max))
	}

	private fun getSkyForTemp(temperature: Float): Int {
		val a = MathHelper.clamp(temperature / 3.0f, -1.0f, 1.0f)
		return MathHelper.hsvToRgb(0.62222224f - a * 0.05f, 0.5f + a * 0.1f, 1.0f)
	}
}