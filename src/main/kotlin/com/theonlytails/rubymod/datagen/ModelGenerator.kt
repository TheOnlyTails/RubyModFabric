package com.theonlytails.rubymod.datagen

import com.google.gson.JsonObject
import com.theonlytails.rubymod.registries.BlockRegistry
import com.theonlytails.rubymod.registries.ItemRegistry
import me.shedaniel.cloth.api.datagen.v1.DataGeneratorHandler
import me.shedaniel.cloth.api.datagen.v1.ModelStateData
import net.minecraft.block.Block
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModelGenerator {
	fun generate(handler: DataGeneratorHandler) {
		val modelStates = handler.modelStates

		generateBlocks(modelStates)
		generateItems(modelStates)
	}

	private fun generateItems(modelStates: ModelStateData) {
		// Items
		modelStates.addGeneratedItemModel(ItemRegistry.poisonedApple)

		modelStates.addGeneratedItemModel(ItemRegistry.ruby)

		modelStates.addSimpleItemModel(ItemRegistry.rubySheepSpawnEgg,
			Identifier("minecraft:item/template_spawn_egg"))

		// Armor
		modelStates.addGeneratedItemModel(ItemRegistry.rubyHelmet)
		modelStates.addGeneratedItemModel(ItemRegistry.rubyChestplate)
		modelStates.addGeneratedItemModel(ItemRegistry.rubyLeggings)
		modelStates.addGeneratedItemModel(ItemRegistry.rubyBoots)

		// Tools
		modelStates.addHandheldItemModel(ItemRegistry.rubyPickaxe)
		modelStates.addHandheldItemModel(ItemRegistry.rubySword)
		modelStates.addHandheldItemModel(ItemRegistry.rubyAxe)
		modelStates.addHandheldItemModel(ItemRegistry.rubyShovel)
		modelStates.addHandheldItemModel(ItemRegistry.rubyHoe)

		// Block items
		modelStates.addSimpleBlockItemModel(BlockRegistry.rubyBlock)
		modelStates.addSimpleBlockItemModel(BlockRegistry.rubyOre)
		modelStates.addSimpleBlockItemModel(BlockRegistry.rubyWool)
		modelStates.addSimpleBlockItemModel(BlockRegistry.rubyCarpet)
		modelStates.addGeneratedItemModel(BlockRegistry.logicGate.asItem())
	}

	private fun generateBlocks(modelStates: ModelStateData) {
		modelStates.addSingletonCubeAll(BlockRegistry.rubyBlock)
		modelStates.addSingletonCubeAll(BlockRegistry.rubyWool)
		modelStates.addSingletonCubeAll(BlockRegistry.rubyOre)
		modelStates.registerCarpet(BlockRegistry.rubyCarpet, BlockRegistry.rubyWool)
	}
}

private fun ModelStateData.registerCarpet(carpet: Block, wool: Block) {
	addState(carpet) {
		val finalJson = JsonObject()

		val variants = JsonObject()
		val defaultVariant = JsonObject()
		val carpetId = Registry.BLOCK.getId(carpet)
		defaultVariant.addProperty("model", "${carpetId.namespace}:block/${carpetId.path}")
		variants.add("", defaultVariant)

		finalJson.add("variants", variants)

		finalJson
	}

	addBlockModel(carpet) {
		val finalJson = JsonObject()

		finalJson.addProperty("parent", Identifier("minecraft:block/carpet").toString())

		val textures = JsonObject()
		val woolId = Registry.BLOCK.getId(wool)
		textures.addProperty("wool", "${woolId.namespace}:block/${woolId.path}")

		finalJson.add("textures", textures)

		finalJson
	}
}