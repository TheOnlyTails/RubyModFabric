package com.theonlytails.rubymod.util.trades

import com.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.entity.Entity
import net.minecraft.item.ItemConvertible
import net.minecraft.village.TradeOffer
import net.minecraft.village.TradeOffers
import java.util.Random
import net.minecraft.item.ItemStack as IS

/**
 * A trade that takes rubies, and returns items.
 *
 * @author TheOnlyTails
 *
 * @property rubyCount The number of rubies taken.
 * @property sellingItem The item being returned.
 * @property sellingItemCount The number of items being returned.
 * @property maxUses The number of times this trade can be used before restocking is required.
 * @property xpValue The number of XP the villager receives when the trade is done.
 */
class ItemsForRubiesFactory(
	private val sellingItem: ItemConvertible,
	private val sellingItemCount: Int = 1,
	private val rubyCount: Int = 1,
	private val maxUses: Int,
	private val xpValue: Int = 2,
) : TradeOffers.Factory {
	private val priceMultiplier = 0.05f

	/**
	 * Creates a trade offer.
	 *
	 * @return a new trade offer, or `null` if none should be created
	 */
	override fun create(entity: Entity, random: Random) =
		TradeOffer(
			IS(ItemRegistry.ruby, rubyCount),
			IS(sellingItem, sellingItemCount),
			maxUses,
			xpValue,
			priceMultiplier
		)
}