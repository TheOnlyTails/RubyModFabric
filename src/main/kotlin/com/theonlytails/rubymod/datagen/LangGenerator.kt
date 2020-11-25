package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.RubyMod.id
import com.theonlytails.rubymod.datagen.DataGeneratorRunner.RESOURCE_PACK
import com.theonlytails.rubymod.registries.*
import net.devtech.arrp.json.lang.JLang
import net.devtech.arrp.json.lang.JLang.lang

object LangGenerator {
	fun generate() {
		// General items
		addLang(lang()
			.item(ItemRegistry.RUBY, "Ruby")
			.item(ItemRegistry.POISONED_APPLE, "Poisoned Apple")
			.item(ItemRegistry.RUBY_SHEEP_SPAWN_EGG, "Ruby Sheep Spawn Egg")

			// Armor
			.item(ItemRegistry.RUBY_HELMET, "Ruby Helmet")
			.item(ItemRegistry.RUBY_CHESTPLATE, "Ruby Chestplate")
			.item(ItemRegistry.RUBY_LEGGINGS, "Ruby Leggings")
			.item(ItemRegistry.RUBY_BOOTS, "Ruby Boots")

			// Tools
			.item(ItemRegistry.RUBY_PICKAXE, "Ruby Pickaxe")
			.item(ItemRegistry.RUBY_AXE, "Ruby Axe")
			.item(ItemRegistry.RUBY_SWORD, "Ruby Sword")
			.item(ItemRegistry.RUBY_SHOVEL, "Ruby Shovel")
			.item(ItemRegistry.RUBY_HOE, "Ruby Hoe")

			// Blocks
			.block(BlockRegistry.RUBY_BLOCK, "Ruby Block")
			.block(BlockRegistry.RUBY_ORE, "Ruby Ore")
			.block(BlockRegistry.RUBY_WOOL, "Ruby Wool")
			.block(BlockRegistry.RUBY_CARPET, "Ruby Carpet")
			.block(BlockRegistry.LOGIC_GATE, "Logic Gate")

			// Enchantments
			.enchantment(EnchantmentRegistry.STINGER, "Stinger")

			// Entities
			.entity(EntityTypeRegistry.RUBY_SHEEP, "Ruby Sheep")

			// Item groups (creative tabs)
			.itemGroup(id("ruby_tab"), "RubyMod")

			// Potions
			.potion(id("motivation"), "Motivation")
			.potion(id("laziness"), "Laziness"))
	}

	private fun addLang(lang: JLang) {
		RESOURCE_PACK.addLang(id("en_us"), lang)
	}
}