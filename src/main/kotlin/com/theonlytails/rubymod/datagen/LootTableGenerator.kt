package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.id
import com.theonlytails.rubymod.registries.BlockRegistry
import com.theonlytails.rubymod.registries.EntityTypeRegistry
import com.theonlytails.rubymod.registries.ItemRegistry
import me.shedaniel.cloth.api.datagen.v1.DataGeneratorHandler
import me.shedaniel.cloth.api.datagen.v1.LootTableData
import net.minecraft.block.Block
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.EntityType
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.loot.ConstantLootTableRange
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.LootCondition
import net.minecraft.loot.condition.MatchToolLootCondition
import net.minecraft.loot.condition.SurvivesExplosionLootCondition
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.loot.entry.AlternativeEntry
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.entry.LootTableEntry
import net.minecraft.loot.function.ApplyBonusLootFunction
import net.minecraft.loot.function.CopyNameLootFunction
import net.minecraft.predicate.NumberRange
import net.minecraft.predicate.item.EnchantmentPredicate
import net.minecraft.predicate.item.ItemPredicate

object LootTableGenerator {
	fun generate(handler: DataGeneratorHandler) {
		val lootTableData = handler.lootTables

		generateBlocks(lootTableData)
		generateEntities(lootTableData)
		generateGifts(lootTableData)
	}

	private fun generateBlocks(lootTableData: LootTableData) {
		lootTableData.registerBlockDropSelf(BlockRegistry.logicGate)

		lootTableData.registerBlockDropSelf(BlockRegistry.rubyBlock)

		lootTableData.registerBlockDropSelf(BlockRegistry.rubyWool)

		lootTableData.register(BlockRegistry.rubyOre, oreDrops(BlockRegistry.rubyOre, ItemRegistry.ruby))

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
		lootTableData.register(EntityTypeRegistry.RUBY_SHEEP, sheepDrops(BlockRegistry.rubyWool))
	}

	private fun generateGifts(lootTableData: LootTableData) {
		lootTableData.register(LootContextTypes.GIFT, id("jeweler_gift"), LootTable.builder()
			.pool(LootPool.builder()
				.rolls(ConstantLootTableRange.create(1))
				.with(ItemEntry.builder(Items.IRON_NUGGET))
				.with(ItemEntry.builder(Items.IRON_INGOT))
				.with(ItemEntry.builder(Items.GOLD_NUGGET))
				.with(ItemEntry.builder(Items.GOLD_INGOT))
			)
		)
	}
}

fun oreDrops(ore: Block, drop: ItemConvertible): LootTable.Builder = LootTable.builder()
	.pool(LootPool.builder()
		.rolls(ConstantLootTableRange.create(1))
		.with(AlternativeEntry.builder(
			ItemEntry.builder(ore).conditionally(withSilkTouchLootCondition),
			ItemEntry.builder(drop).apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))
		)))

private fun sheepDrops(itemConvertible: ItemConvertible): LootTable.Builder = LootTable.builder()
	.pool(LootPool.builder()
		.rolls(ConstantLootTableRange.create(1))
		.with(ItemEntry.builder(itemConvertible)))

	.pool(LootPool.builder()
		.rolls(ConstantLootTableRange.create(1))
		.with(LootTableEntry.builder(EntityType.SHEEP.lootTableId)
		))

val withSilkTouchLootCondition: LootCondition.Builder = MatchToolLootCondition.builder(ItemPredicate.Builder.create()
	.enchantment(EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.atLeast(1))))