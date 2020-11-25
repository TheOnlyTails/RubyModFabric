package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.RubyMod.id
import com.theonlytails.rubymod.datagen.DataGeneratorRunner.RESOURCE_PACK
import com.theonlytails.rubymod.registries.BlockRegistry
import com.theonlytails.rubymod.registries.ItemRegistry
import net.devtech.arrp.json.recipe.JIngredient.ingredient
import net.devtech.arrp.json.recipe.JIngredients.ingredients
import net.devtech.arrp.json.recipe.JKeys.keys
import net.devtech.arrp.json.recipe.JPattern.pattern
import net.devtech.arrp.json.recipe.JRecipe
import net.devtech.arrp.json.recipe.JRecipe.*
import net.devtech.arrp.json.recipe.JResult.item
import net.devtech.arrp.json.recipe.JResult.itemStack
import net.minecraft.item.Items

object RecipeGenerator {
	fun generate() {
		shapedRecipes()
		shapelessRecipes()
		smeltingRecipes()
	}

	private fun shapedRecipes() {
		addRecipe("logic_gate", shaped(
			pattern(
				"t t",
				"srs",
			),
			keys()
				.key("t", ingredient().item(Items.REDSTONE_TORCH))
				.key("s", ingredient().tag("minecraft:stone_crafting_materials"))
				.key("r", ingredient().item(ItemRegistry.RUBY)),
			item(BlockRegistry.LOGIC_GATE.asItem())
		))

		addRecipe("ruby_block", shaped(
			pattern(
				"rrr",
				"rrr",
				"rrr",
			),
			keys().key("r", ingredient().item(ItemRegistry.RUBY)),
			item(BlockRegistry.RUBY_BLOCK.asItem())
		))

		addRecipe("ruby_carpet_from_carpet", shaped(
			pattern(
				"ccc",
				"crc",
				"ccc",
			),
			keys()
				.key("c", ingredient().item(Items.WHITE_CARPET))
				.key("r", ingredient().item(ItemRegistry.RUBY)),
			itemStack(BlockRegistry.RUBY_CARPET.asItem(), 8)
		).group("carpet"))

		addRecipe("ruby_carpet_from_wool", shaped(
			pattern(
				"ww"
			),
			keys().key("w", ingredient().item(BlockRegistry.RUBY_WOOL.asItem())),
			itemStack(BlockRegistry.RUBY_CARPET.asItem(), 3)
		).group("carpet"))
	}

	private fun shapelessRecipes() {
		addRecipe("poisoned_apple", shapeless(
			ingredients()
				.add(ingredient().item(Items.APPLE))
				.add(ingredient().item(ItemRegistry.RUBY)),
			item(ItemRegistry.POISONED_APPLE)
		))

		addRecipe("ruby_block_to_ruby", shapeless(
			ingredients().add(ingredient().item(BlockRegistry.RUBY_BLOCK.asItem())),
			itemStack(ItemRegistry.RUBY, 9)
		))

		addRecipe("ruby_wool_from_wool", shapeless(
			ingredients()
				.add(ingredient().item(ItemRegistry.RUBY))
				.add(ingredient().item(Items.WHITE_WOOL)),
			item(BlockRegistry.RUBY_WOOL.asItem())
		))
	}

	private fun smeltingRecipes() {
		addRecipe("ruby_ore_smelt", smelting(
			ingredient().item(BlockRegistry.RUBY_ORE.asItem()),
			item(ItemRegistry.RUBY)
		).experience(1f).cookingTime(100))

		addRecipe("ruby_ore_blast", blasting(
			ingredient().item(BlockRegistry.RUBY_ORE.asItem()),
			item(ItemRegistry.RUBY)
		).experience(1f).cookingTime(100))
	}

	private fun addRecipe(id: String, recipe: JRecipe) {
		RESOURCE_PACK.addRecipe(id(id), recipe)
	}
}
