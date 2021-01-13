package com.theonlytails.rubymod.util.materials

import com.theonlytails.rubymod.mixins.BrewingRecipeRegistryInvoker
import net.minecraft.item.Items
import net.minecraft.potion.Potion

fun addTime(input: Potion, output: Potion) {
	BrewingRecipeRegistryInvoker.invokeRegisterPotionRecipe(input, Items.REDSTONE, output)
}

fun addPotency(input: Potion, output: Potion) {
	BrewingRecipeRegistryInvoker.invokeRegisterPotionRecipe(input, Items.GLOWSTONE_DUST, output)
}

fun invertPotion(input: Potion, output: Potion) {
	BrewingRecipeRegistryInvoker.invokeRegisterPotionRecipe(input, Items.FERMENTED_SPIDER_EYE, output)
}