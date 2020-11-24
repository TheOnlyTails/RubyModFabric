package com.theonlytails.rubymod

import com.theonlytails.rubymod.entities.RubySheepEntity
import com.theonlytails.rubymod.mixins.BrewingRecipeRegistryMixin
import com.theonlytails.rubymod.registries.*
import com.theonlytails.rubymod.util.materials.addPotency
import com.theonlytails.rubymod.util.materials.addTime
import com.theonlytails.rubymod.util.materials.invertPotion
import com.theonlytails.rubymod.world.FeatureGen
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.potion.Potions
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object RubyMod : ModInitializer {
	private const val MOD_ID = "rubymod"
	val RUBY_TAB: ItemGroup = FabricItemGroupBuilder.build(id("ruby_tab")) {
		ItemStack(ItemRegistry.RUBY)
	}

	val LOGGER: Logger = LogManager.getLogger()

	override fun onInitialize() {
		BlockRegistry
		ItemRegistry
		EnchantmentRegistry
		EntityTypeRegistry
		registerBlockItems()

		FabricDefaultAttributeRegistry.register(EntityTypeRegistry.RUBY_SHEEP, RubySheepEntity.setCustomAttributes())

		FeatureGen.addFeaturesToBiomes()

		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(Potions.WATER,
			ItemRegistry.RUBY,
			PotionRegistry.MOTIVATION)
		addTime(PotionRegistry.MOTIVATION, PotionRegistry.LONG_MOTIVATION)
		addPotency(PotionRegistry.MOTIVATION, PotionRegistry.STRONG_MOTIVATION)

		invertPotion(PotionRegistry.MOTIVATION, PotionRegistry.LAZINESS)
		addTime(PotionRegistry.LAZINESS, PotionRegistry.LONG_LAZINESS)
		addPotency(PotionRegistry.LAZINESS, PotionRegistry.STRONG_LAZINESS)
	}

	private fun registerBlockItems() {
		BlockRegistry.customBlocks.forEach { block ->
			ItemRegistry.register(Registry.BLOCK.getId(block).path,
				BlockItem(block, ItemRegistry.DEFAULT_BLOCK_ITEM_PROPERTY))
		}
	}

	@JvmStatic
	fun id(path: String) = Identifier(MOD_ID, path)
}

