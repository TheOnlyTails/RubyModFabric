package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.registries.BlockRegistry
import com.theonlytails.rubymod.registries.ItemRegistry
import me.shedaniel.cloth.api.datagen.v1.DataGeneratorHandler
import me.shedaniel.cloth.api.datagen.v1.ModelStateData
import net.minecraft.util.Identifier

object ModelGenerator {
	fun generate(handler: DataGeneratorHandler) {
		val modelStates = handler.modelStates

		generateBlocks(modelStates)
		generateItems(modelStates)
	}

	private fun generateItems(modelStates: ModelStateData) {
		// Items
		modelStates.addGeneratedItemModel(ItemRegistry.POISONED_APPLE)

		modelStates.addGeneratedItemModel(ItemRegistry.RUBY)

		modelStates.addSimpleItemModel(ItemRegistry.RUBY_SHEEP_SPAWN_EGG,
			Identifier("minecraft:item/template_spawn_egg"))

		// Armor
		modelStates.addGeneratedItemModel(ItemRegistry.RUBY_HELMET)
		modelStates.addGeneratedItemModel(ItemRegistry.RUBY_CHESTPLATE)
		modelStates.addGeneratedItemModel(ItemRegistry.RUBY_LEGGINGS)
		modelStates.addGeneratedItemModel(ItemRegistry.RUBY_BOOTS)

		// Tools
		modelStates.addHandheldItemModel(ItemRegistry.RUBY_PICKAXE)
		modelStates.addHandheldItemModel(ItemRegistry.RUBY_SWORD)
		modelStates.addHandheldItemModel(ItemRegistry.RUBY_AXE)
		modelStates.addHandheldItemModel(ItemRegistry.RUBY_SHOVEL)
		modelStates.addHandheldItemModel(ItemRegistry.RUBY_HOE)

		// Block items
		modelStates.addSimpleBlockItemModel(BlockRegistry.RUBY_BLOCK)
		modelStates.addSimpleBlockItemModel(BlockRegistry.RUBY_ORE)
		modelStates.addSimpleBlockItemModel(BlockRegistry.RUBY_WOOL)
		modelStates.addSimpleBlockItemModel(BlockRegistry.RUBY_CARPET)
		modelStates.addSimpleBlockItemModel(BlockRegistry.LOGIC_GATE)
	}

	private fun generateBlocks(modelStates: ModelStateData) {
		modelStates.addSingletonCubeAll(BlockRegistry.RUBY_BLOCK)
		modelStates.addSingletonCubeAll(BlockRegistry.RUBY_WOOL)
		modelStates.addSingletonCubeAll(BlockRegistry.RUBY_ORE)
		modelStates.addSimpleBlockModel(BlockRegistry.RUBY_CARPET, Identifier("minecraft:block/carpet"))
	}
}
