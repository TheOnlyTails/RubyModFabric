package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.RubyMod
import com.theonlytails.rubymod.util.materials.RubyArmorMaterial
import com.theonlytails.rubymod.util.materials.RubyToolMaterial
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.*
import net.minecraft.util.registry.Registry

object ItemRegistry {
	private fun register(id: String, item: Item) = Registry.register(Registry.ITEM, RubyMod.id(id), item)
	private val DEFAULT_ITEM_PROPERTY: Item.Settings = Item.Settings().group(RubyMod.RUBY_TAB)

	@JvmStatic
	val rubyArmorMaterial = RubyArmorMaterial()

	@JvmStatic
	val rubyToolMaterial = RubyToolMaterial()

	val RUBY: Item = register("ruby", Item(DEFAULT_ITEM_PROPERTY))

	val RUBY_BLOCK: Item = register("ruby_block", BlockItem(BlockRegistry.RUBY_BLOCK, DEFAULT_ITEM_PROPERTY))

	val RUBY_HELMET: Item = register("ruby_helmet",
		ArmorItem(rubyArmorMaterial, EquipmentSlot.HEAD, DEFAULT_ITEM_PROPERTY))

	val RUBY_CHESTPLATE: Item = register("ruby_chestplate",
		ArmorItem(rubyArmorMaterial, EquipmentSlot.CHEST, DEFAULT_ITEM_PROPERTY))

	val RUBY_LEGGINGS: Item = register("ruby_leggings",
		ArmorItem(rubyArmorMaterial, EquipmentSlot.LEGS, DEFAULT_ITEM_PROPERTY))

	val RUBY_BOOTS: Item = register("ruby_boots",
		ArmorItem(rubyArmorMaterial, EquipmentSlot.FEET, DEFAULT_ITEM_PROPERTY))

	val RUBY_PICKAXE: Item = register("ruby_pickaxe",
		object : PickaxeItem(rubyToolMaterial, 1, -2.8f, DEFAULT_ITEM_PROPERTY) {})

	val RUBY_SWORD: Item = register("ruby_sword",
		SwordItem(rubyToolMaterial, 2, -2.4f, DEFAULT_ITEM_PROPERTY))

	val RUBY_AXE: Item = register("ruby_axe",
		object : AxeItem(rubyToolMaterial, 5f, -3.05f, DEFAULT_ITEM_PROPERTY) {})

	val RUBY_SHOVEL: Item = register("ruby_shovel",
		ShovelItem(rubyToolMaterial, 1f, -3f, DEFAULT_ITEM_PROPERTY))

	val RUBY_HOE: Item = register("ruby_hoe",
		object : HoeItem(rubyToolMaterial, -2, -0.5f, DEFAULT_ITEM_PROPERTY) {})
}
