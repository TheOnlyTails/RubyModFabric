package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.mixins.BlockLootTableGeneratorInvoker
import com.theonlytails.rubymod.mixins.EntityLootTableGeneratorInvoker
import com.theonlytails.rubymod.registries.BlockRegistry
import com.theonlytails.rubymod.registries.EntityTypeRegistry
import com.theonlytails.rubymod.registries.ItemRegistry
import me.shedaniel.cloth.api.datagen.v1.DataGeneratorHandler
import me.shedaniel.cloth.api.datagen.v1.LootTableData
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.SurvivesExplosionLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.CopyNameLootFunction

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

		lootTableData.registerBlockDropSelf(BlockRegistry.rubyCarpet)

		lootTableData.register(BlockRegistry.rubyBarrel, LootTable.builder()
			.pool(
				LootPool.builder()
					.with(ItemEntry.builder(BlockRegistry.rubyBarrel)
						.apply(CopyNameLootFunction.builder(CopyNameLootFunction.Source.BLOCK_ENTITY))
					)
					.conditionally(SurvivesExplosionLootCondition.builder())
			))
	}

	private fun generateEntities(lootTableData: LootTableData) {
		lootTableData.register(EntityTypeRegistry.RUBY_SHEEP,
			EntityLootTableGeneratorInvoker.createForSheep(BlockRegistry.rubyWool))
	}
}