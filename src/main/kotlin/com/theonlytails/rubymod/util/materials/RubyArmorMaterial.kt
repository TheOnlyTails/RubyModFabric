package com.theonlytails.rubymod.util.materials

import com.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents

class RubyArmorMaterial : ArmorMaterial {
	companion object {
		@JvmStatic
		private val BASE_DURABILITY = listOf(11, 16, 15, 13)

		@JvmStatic
		private val PROTECTION_VALUES = listOf(2, 5, 6, 2)
	}

	override fun getDurability(slot: EquipmentSlot) = BASE_DURABILITY[slot.entitySlotId] * 24

	override fun getProtectionAmount(slot: EquipmentSlot) = PROTECTION_VALUES[slot.entitySlotId]

	override fun getEnchantability() = 18

	override fun getEquipSound(): SoundEvent = SoundEvents.ITEM_ARMOR_EQUIP_IRON

	override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(ItemRegistry.ruby)

	override fun getName() = "ruby"

	override fun getToughness() = 0f

	override fun getKnockbackResistance() = 0.5f
}