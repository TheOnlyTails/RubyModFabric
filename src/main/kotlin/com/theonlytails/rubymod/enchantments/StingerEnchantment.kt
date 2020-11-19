package com.theonlytails.rubymod.enchantments

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects

class StingerEnchantment : Enchantment(
	Rarity.VERY_RARE,
	EnchantmentTarget.WEAPON,
	arrayOf(EquipmentSlot.MAINHAND)
) {

	override fun onTargetDamaged(user: LivingEntity, target: Entity, level: Int) {
		if (target is LivingEntity) {
			target.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, 20 * 5 * level, level))
		}
	}

	override fun getMaxLevel() = 3

	override fun isAvailableForEnchantedBookOffer() = false

	override fun canAccept(other: Enchantment) =
		super.canAccept(other) && other !== Enchantments.SHARPNESS && other !== Enchantments.MENDING
}