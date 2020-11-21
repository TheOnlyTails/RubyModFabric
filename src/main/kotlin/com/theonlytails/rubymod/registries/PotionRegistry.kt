package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.RubyMod
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.potion.Potion
import net.minecraft.util.registry.Registry

object PotionRegistry {
	private fun register(id: String, potion: Potion) =
		Registry.register(Registry.POTION, RubyMod.id(id), potion)

	val MOTIVATION: Potion = register("motivation", Potion(
		"motivation",
		StatusEffectInstance(StatusEffects.SPEED, 90 * 20),
		StatusEffectInstance(StatusEffects.JUMP_BOOST, 90 * 20),
	))

	val LONG_MOTIVATION: Potion = register("long_motivation", Potion(
		"motivation",
		StatusEffectInstance(StatusEffects.SPEED, 240 * 20),
		StatusEffectInstance(StatusEffects.JUMP_BOOST, 240 * 20),
	))

	val STRONG_MOTIVATION: Potion = register("strong_motivation", Potion(
		"motivation",
		StatusEffectInstance(StatusEffects.SPEED, 90 * 20, 1),
		StatusEffectInstance(StatusEffects.JUMP_BOOST, 90 * 20, 1),
	))

	val LAZINESS: Potion = register("laziness", Potion(
		"laziness",
		StatusEffectInstance(StatusEffects.SLOWNESS, 90 * 20),
		StatusEffectInstance(StatusEffects.NAUSEA, 90 * 20),
	))

	val LONG_LAZINESS: Potion = register("long_laziness", Potion(
		"laziness",
		StatusEffectInstance(StatusEffects.SLOWNESS, 240 * 20),
		StatusEffectInstance(StatusEffects.NAUSEA, 240 * 20),
	))

	val STRONG_LAZINESS: Potion = register("strong_laziness", Potion(
		"laziness",
		StatusEffectInstance(StatusEffects.SLOWNESS, 90 * 20, 1),
		StatusEffectInstance(StatusEffects.NAUSEA, 90 * 20, 1),
	))
}