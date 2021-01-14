package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.id
import com.theonlytails.rubymod.util.tradeOffersMap
import com.theonlytails.rubymod.util.trades.ItemsForItemsAndRubiesFactory
import com.theonlytails.rubymod.util.trades.ItemsForRubiesFactory
import net.fabricmc.fabric.api.`object`.builder.v1.villager.VillagerProfessionBuilder
import net.fabricmc.fabric.api.`object`.builder.v1.world.poi.PointOfInterestHelper
import net.minecraft.item.Items
import net.minecraft.sound.SoundEvents
import net.minecraft.util.registry.Registry
import net.minecraft.village.TradeOffers
import net.minecraft.village.VillagerProfession
import net.minecraft.world.poi.PointOfInterestType

object VillagerProfessionRegistry {
	private fun register(id: String, profession: VillagerProfession) =
		Registry.register(Registry.VILLAGER_PROFESSION, id(id), profession)

	private val jewelerPointOfInterest: PointOfInterestType = PointOfInterestHelper.register(
		id("jeweler"),
		1,
		1,
		BlockRegistry.rubyBarrel
	)

	private val jeweler: VillagerProfession = register("jeweler",
		VillagerProfessionBuilder.create()
			.id(id("jeweler"))
			.workstation(jewelerPointOfInterest)
			.workSound(SoundEvents.ENTITY_VILLAGER_WORK_TOOLSMITH)
			.build()
	)

	fun initTrades() {
		TradeOffers.PROFESSION_TO_LEVELED_TRADE[jeweler] = tradeOffersMap(
			mapOf(
				// Level 1 trades
				1 to arrayOf(
					ItemsForRubiesFactory(Items.COAL, 45, maxUses = 12),
					ItemsForRubiesFactory(Items.IRON_NUGGET, 16, maxUses = 9),
					ItemsForRubiesFactory(Items.IRON_INGOT, 3, maxUses = 8),
					ItemsForRubiesFactory(Items.GOLD_NUGGET, 16, maxUses = 10),
				),

				// Level 2 trades
				2 to arrayOf(
					ItemsForRubiesFactory(Items.GOLD_INGOT, 4, maxUses = 9),
					ItemsForRubiesFactory(Items.LAPIS_LAZULI, 25, maxUses = 10),
					ItemsForItemsAndRubiesFactory(
						Items.IRON_PICKAXE,
						sellingItem = ItemRegistry.rubyPickaxe,
						maxUses = 1,
						xpValue = 5,
					),
					ItemsForItemsAndRubiesFactory(
						Items.IRON_SWORD,
						sellingItem = ItemRegistry.rubySword,
						maxUses = 1,
						xpValue = 5,
					),
					ItemsForItemsAndRubiesFactory(
						Items.IRON_AXE,
						sellingItem = ItemRegistry.rubyAxe,
						maxUses = 1,
						xpValue = 5,
					),
					ItemsForItemsAndRubiesFactory(
						Items.IRON_SHOVEL,
						sellingItem = ItemRegistry.rubyShovel,
						maxUses = 1,
						xpValue = 5,
					),
					ItemsForItemsAndRubiesFactory(
						Items.IRON_HOE,
						sellingItem = ItemRegistry.rubyHoe,
						maxUses = 1,
						xpValue = 5,
					),
				),

				// Level 3 trades
				3 to arrayOf(
					ItemsForRubiesFactory(Items.EMERALD_BLOCK, sellingItemCount = 10, maxUses = 8)
				),

				// Level 4 trades
				4 to arrayOf(
					// Level 4 trades
					ItemsForRubiesFactory(Items.DIAMOND, sellingItemCount = 2, maxUses = 2)
				),

				// Level 5 trades
				5 to arrayOf(
					ItemsForItemsAndRubiesFactory(
						Items.IRON_HELMET,
						sellingItem = ItemRegistry.rubyHelmet,
						maxUses = 1,
					),
					ItemsForItemsAndRubiesFactory(
						Items.IRON_CHESTPLATE,
						sellingItem = ItemRegistry.rubyChestplate,
						maxUses = 1,
					),
					ItemsForItemsAndRubiesFactory(
						Items.IRON_LEGGINGS,
						sellingItem = ItemRegistry.rubyLeggings,
						maxUses = 1,
					),
					ItemsForItemsAndRubiesFactory(
						Items.IRON_BOOTS,
						sellingItem = ItemRegistry.rubyBoots,
						maxUses = 1,
					),
				),
			)
		)
	}
}
