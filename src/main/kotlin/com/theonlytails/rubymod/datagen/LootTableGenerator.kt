package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.mixins.BlockLootTableGeneratorInvoker
import com.theonlytails.rubymod.mixins.EntityLootTableGeneratorInvoker
import com.theonlytails.rubymod.registries.BlockRegistry
import com.theonlytails.rubymod.registries.EntityTypeRegistry
import com.theonlytails.rubymod.registries.ItemRegistry
import me.shedaniel.cloth.api.datagen.v1.DataGeneratorHandler
import me.shedaniel.cloth.api.datagen.v1.LootTableData

object LootTableGenerator {
	fun generate(handler: DataGeneratorHandler) {
		val lootTableData = handler.lootTables

		generateBlocks(lootTableData)
		generateEntities(lootTableData)
	}

	private fun generateBlocks(lootTableData: LootTableData) {
		lootTableData.registerBlockDropSelf(BlockRegistry.logicGate)

		lootTableData.registerBlockDropSelf(BlockRegistry.rubyBlock)

		lootTableData.registerBlockDropSelf(BlockRegistry.rubyWool)

		lootTableData.register(BlockRegistry.rubyOre,
			BlockLootTableGeneratorInvoker.oreDrops(BlockRegistry.rubyOre, ItemRegistry.ruby))
	}

	private fun generateEntities(lootTableData: LootTableData) {
		lootTableData.register(EntityTypeRegistry.RUBY_SHEEP,
			EntityLootTableGeneratorInvoker.createForSheep(BlockRegistry.rubyWool))
	}
}