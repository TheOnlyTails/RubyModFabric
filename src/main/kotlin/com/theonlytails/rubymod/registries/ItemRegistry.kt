package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.RubyMod
import com.theonlytails.rubymod.util.materials.RubyArmorMaterial
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorItem
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry

object ItemRegistry {
	private fun register(id: String, item: Item) = Registry.register(Registry.ITEM, RubyMod.id(id), item)
	private val DEFAULT_ITEM_PROPERTY: Item.Settings = Item.Settings().group(RubyMod.RUBY_TAB)
	@JvmStatic val rubyArmorMaterial = RubyArmorMaterial()

	val RUBY: Item = register("ruby", Item(DEFAULT_ITEM_PROPERTY))

	val RUBY_HELMET: Item = register("ruby_helmet", ArmorItem(rubyArmorMaterial, EquipmentSlot.HEAD, DEFAULT_ITEM_PROPERTY))
	val RUBY_CHESTPLATE: Item = register("ruby_chestplate", ArmorItem(rubyArmorMaterial, EquipmentSlot.CHEST, DEFAULT_ITEM_PROPERTY))
	val RUBY_LEGGINGS: Item = register("ruby_leggings", ArmorItem(rubyArmorMaterial, EquipmentSlot.LEGS, DEFAULT_ITEM_PROPERTY))
	val RUBY_BOOTS: Item = register("ruby_boots", ArmorItem(rubyArmorMaterial, EquipmentSlot.FEET, DEFAULT_ITEM_PROPERTY))
}
