package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.datagen.DataGeneratorRunner.RESOURCE_PACK
import com.theonlytails.rubymod.id
import com.theonlytails.rubymod.registries.*
import net.devtech.arrp.json.lang.JLang
import net.devtech.arrp.json.lang.JLang.lang

object LangGenerator {
	fun generate() {
		addLang(lang()
			// General items
			.item(ItemRegistry.ruby, "Ruby")
			.item(ItemRegistry.poisonedApple, "Poisoned Apple")
			.item(ItemRegistry.rubySheepSpawnEgg, "Ruby Sheep Spawn Egg")

			// Armor
			.item(ItemRegistry.rubyHelmet, "Ruby Helmet")
			.item(ItemRegistry.rubyChestplate, "Ruby Chestplate")
			.item(ItemRegistry.rubyLeggings, "Ruby Leggings")
			.item(ItemRegistry.rubyBoots, "Ruby Boots")

			// Tools
			.item(ItemRegistry.rubyPickaxe, "Ruby Pickaxe")
			.item(ItemRegistry.rubyAxe, "Ruby Axe")
			.item(ItemRegistry.rubySword, "Ruby Sword")
			.item(ItemRegistry.rubyShovel, "Ruby Shovel")
			.item(ItemRegistry.rubyHoe, "Ruby Hoe")

			// Blocks
			.block(BlockRegistry.rubyBlock, "Ruby Block")
			.block(BlockRegistry.rubyOre, "Ruby Ore")
			.block(BlockRegistry.rubyWool, "Ruby Wool")
			.block(BlockRegistry.rubyCarpet, "Ruby Carpet")
			.block(BlockRegistry.rubyBarrel, "Ruby Barrel")
			.block(BlockRegistry.logicGate, "Logic Gate")

			// Enchantments
			.enchantment(EnchantmentRegistry.STINGER, "Stinger")

			// Entities
			.entity(EntityTypeRegistry.RUBY_SHEEP, "Ruby Sheep")

			// Item groups (creative tabs)
			.itemGroup(id("ruby_tab"), "RubyMod")

			// Potions
			.allPotionOf(id("motivation"), "Motivation")
			.allPotionOf(id("laziness"), "Laziness"))
	}

	private fun addLang(lang: JLang) {
		RESOURCE_PACK.addLang(id("en_us"), lang)
	}
}