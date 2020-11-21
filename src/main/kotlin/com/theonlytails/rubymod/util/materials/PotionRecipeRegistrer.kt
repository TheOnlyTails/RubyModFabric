package com.theonlytails.rubymod.util.materials

import com.theonlytails.rubymod.mixins.BrewingRecipeRegistryMixin
import net.minecraft.item.Items
import net.minecraft.potion.Potion

fun addTime(input: Potion, output: Potion) {
	BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(input, Items.REDSTONE, output)
}

fun addPotency(input: Potion, output: Potion) {
	BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(input, Items.GLOWSTONE_DUST, output)
}

fun invertPotion(input: Potion, output: Potion) {
	BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(input, Items.FERMENTED_SPIDER_EYE, output)
}