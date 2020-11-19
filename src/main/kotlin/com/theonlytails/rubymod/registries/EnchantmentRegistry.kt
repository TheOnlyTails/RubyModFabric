package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.RubyMod
import com.theonlytails.rubymod.enchantments.StingerEnchantment
import net.minecraft.enchantment.Enchantment
import net.minecraft.util.registry.Registry

object EnchantmentRegistry {
	private fun register(id: String, enchantment: Enchantment) =
		Registry.register(Registry.ENCHANTMENT, RubyMod.id(id), enchantment)

	val STINGER: Enchantment = register("stinger", StingerEnchantment())
}