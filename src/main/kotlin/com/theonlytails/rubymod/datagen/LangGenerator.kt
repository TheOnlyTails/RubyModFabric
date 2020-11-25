package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.RubyMod.id
import com.theonlytails.rubymod.datagen.DataGeneratorRunner.RESOURCE_PACK
import com.theonlytails.rubymod.registries.*
import net.devtech.arrp.json.lang.JLang
import net.devtech.arrp.json.lang.JLang.lang
import net.minecraft.util.Identifier

object LangGenerator {
	fun generate() {
		// General items
		addLang("ruby", lang().item(ItemRegistry.RUBY, "Ruby"))
		addLang("poisoned_apple", lang().item(ItemRegistry.POISONED_APPLE, "Poisoned Apple"))
		addLang("ruby_sheep_spawn_egg", lang().item(ItemRegistry.RUBY_SHEEP_SPAWN_EGG, "Ruby Sheep Spawn Egg"))

		// Armor
		addLang("ruby_helmet", lang().item(ItemRegistry.RUBY_HELMET, "Ruby Helmet"))
		addLang("ruby_chestplate", lang().item(ItemRegistry.RUBY_CHESTPLATE, "Ruby Chestplate"))
		addLang("ruby_leggings", lang().item(ItemRegistry.RUBY_LEGGINGS, "Ruby Leggings"))
		addLang("ruby_boots", lang().item(ItemRegistry.RUBY_BOOTS, "Ruby Boots"))

		// Tools
		addLang("ruby_pickaxe", lang().item(ItemRegistry.RUBY_PICKAXE, "Ruby Pickaxe"))
		addLang("ruby_axe", lang().item(ItemRegistry.RUBY_AXE, "Ruby Axe"))
		addLang("ruby_sword", lang().item(ItemRegistry.RUBY_SWORD, "Ruby Sword"))
		addLang("ruby_shovel", lang().item(ItemRegistry.RUBY_SHOVEL, "Ruby Shovel"))
		addLang("ruby_hoe", lang().item(ItemRegistry.RUBY_HOE, "Ruby Hoe"))

		// Blocks
		addLang("ruby_block", lang().block(BlockRegistry.RUBY_BLOCK, "Ruby Block"))
		addLang("ruby_ore", lang().block(BlockRegistry.RUBY_ORE, "Ruby Ore"))
		addLang("ruby_wool", lang().block(BlockRegistry.RUBY_WOOL, "Ruby Wool"))
		addLang("ruby_carpet", lang().block(BlockRegistry.RUBY_CARPET, "Ruby Carpet"))
		addLang("logic_gate", lang().block(BlockRegistry.LOGIC_GATE, "Logic Gate"))

		// Enchantments
		addLang("stinger", lang().enchantment(EnchantmentRegistry.STINGER, "Stinger"))

		// Entities
		addLang("ruby_sheep", lang().entity(EntityTypeRegistry.RUBY_SHEEP, "Ruby Sheep"))

		// Item groups (creative tabs)
		addLang("ruby_tab", lang().itemGroup(id("ruby_tab"), "RubyMod"))

		// Potions
		addLang("motivation", lang().potion(id("motivation"), "Motivation"))
		addLang("laziness", lang().potion(id("laziness"), "Laziness"))
	}

	private fun addLang(id: String, lang: JLang) {
		RESOURCE_PACK.addLang(id(id), lang)
	}

	private fun addLangCustomNamespace(id: Identifier, lang: JLang) {
		RESOURCE_PACK.addLang(id, lang)
	}
}