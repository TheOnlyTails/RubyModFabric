package com.theonlytails.rubymod.util.materials

import com.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient

class RubyToolMaterial : ToolMaterial {
	override fun getDurability() = 850

	override fun getMiningSpeedMultiplier() = 7f

	override fun getAttackDamage() = 3f

	override fun getMiningLevel() = 3

	override fun getEnchantability() = 12

	override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(ItemRegistry.RUBY)
}