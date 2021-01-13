package com.theonlytails.rubymod.items

import com.theonlytails.rubymod.registries.ItemRegistry
import com.theonlytails.rubymod.util.materials.RubyArmorMaterial
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.world.World
import java.util.stream.StreamSupport

class RubyArmorItem(slot: EquipmentSlot) : ArmorItem(RubyArmorMaterial(), slot, ItemRegistry.UNSTACKABLE_PROPERTY) {
	override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
		if (entity is PlayerEntity) {
			val armor = entity.armorItems

			val wearingAllRubyArmor = StreamSupport.stream(armor.spliterator(), false)
				.allMatch { it.item is RubyArmorItem }

			if (wearingAllRubyArmor && entity.mainHandStack.item === ItemRegistry.RUBY_PICKAXE) {
				entity.addStatusEffect(StatusEffectInstance(
					StatusEffects.HASTE,
					220,
					0,
					true,
					true
				))
			}
		}
	}
}