package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.id
import com.theonlytails.rubymod.registries.BlockRegistry
import com.theonlytails.rubymod.registries.ItemRegistry
import me.shedaniel.cloth.api.datagen.v1.DataGeneratorHandler
import me.shedaniel.cloth.api.datagen.v1.RecipeData
import net.minecraft.advancement.criterion.InventoryChangedCriterion
import net.minecraft.block.Blocks
import net.minecraft.data.server.recipe.CookingRecipeJsonFactory
import net.minecraft.data.server.recipe.ShapedRecipeJsonFactory
import net.minecraft.data.server.recipe.ShapelessRecipeJsonFactory
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.predicate.NumberRange
import net.minecraft.predicate.entity.EntityPredicate
import net.minecraft.predicate.item.ItemPredicate
import net.minecraft.recipe.Ingredient
import net.minecraft.tag.ItemTags

object RecipeGenerator {
	fun generate(handler: DataGeneratorHandler) {
		val recipeData = handler.recipes

		shapedRecipes(recipeData)
		shapelessRecipes(recipeData)
		smeltingRecipes(recipeData)
	}

	private fun shapedRecipes(recipeData: RecipeData) {
		ShapedRecipeJsonFactory.create(BlockRegistry.logicGate)
			.pattern("t t")
			.pattern("srs")
			.input('t', Items.REDSTONE_TORCH)
			.input('s', ItemTags.STONE_CRAFTING_MATERIALS)
			.input('r', ItemRegistry.ruby)
			.criterion("hasRuby", conditionsFromItem(ItemRegistry.ruby))
			.offerTo(recipeData)

		ShapedRecipeJsonFactory.create(BlockRegistry.rubyBlock)
			.pattern("rrr")
			.pattern("rrr")
			.pattern("rrr")
			.input('r', ItemRegistry.ruby)
			.criterion("hasRuby", conditionsFromItem(ItemRegistry.ruby))
			.offerTo(recipeData)

		ShapedRecipeJsonFactory.create(BlockRegistry.rubyCarpet, 8)
			.pattern("ccc")
			.pattern("crc")
			.pattern("ccc")
			.input('c', Items.WHITE_CARPET)
			.input('r', ItemRegistry.ruby)
			.group("carpet")
			.criterion("hasRuby", conditionsFromItem(ItemRegistry.ruby))
			.offerTo(recipeData, id("ruby_carpet_from_carpet"))

		ShapedRecipeJsonFactory.create(BlockRegistry.rubyCarpet, 3)
			.pattern("ww")
			.input('w', BlockRegistry.rubyWool)
			.group("carpet")
			.criterion("hasRubyWool", conditionsFromItem(BlockRegistry.rubyWool))
			.offerTo(recipeData, id("ruby_carpet_from_wool"))
	}

	private fun shapelessRecipes(recipeData: RecipeData) {
		ShapelessRecipeJsonFactory.create(ItemRegistry.poisonedApple)
			.input(Items.APPLE)
			.input(ItemRegistry.ruby)
			.criterion("hasRuby", conditionsFromItem(ItemRegistry.ruby))
			.offerTo(recipeData)

		ShapelessRecipeJsonFactory.create(ItemRegistry.ruby, 9)
			.input(BlockRegistry.rubyBlock)
			.criterion("hasRubyBlock", conditionsFromItem(BlockRegistry.rubyBlock))
			.offerTo(recipeData, id("ruby_block_to_ruby"))

		ShapelessRecipeJsonFactory.create(BlockRegistry.rubyWool)
			.input(Items.WHITE_WOOL)
			.input(ItemRegistry.ruby)
			.group("wool")
			.criterion("hasWhiteWool", conditionsFromItem(Blocks.WHITE_WOOL))
			.offerTo(recipeData, id("ruby_wool_from_wool"))
	}

	private fun smeltingRecipes(recipeData: RecipeData) {
		CookingRecipeJsonFactory.createSmelting(Ingredient.ofItems(BlockRegistry.rubyOre), ItemRegistry.ruby, 1f, 100)
			.criterion("hasRuby", conditionsFromItem(ItemRegistry.ruby))
			.offerTo(recipeData, id("ruby_ore_smelt"))

		CookingRecipeJsonFactory.createBlasting(Ingredient.ofItems(BlockRegistry.rubyOre), ItemRegistry.ruby, 1f, 100)
			.criterion("hasRuby", conditionsFromItem(ItemRegistry.ruby))
			.offerTo(recipeData, id("ruby_ore_blast"))
	}
}

fun conditionsFromItem(itemConvertible: ItemConvertible) =
	conditionsFromItemPredicates(ItemPredicate.Builder.create().item(itemConvertible).build())

fun conditionsFromItemPredicates(vararg itemPredicates: ItemPredicate) =
	InventoryChangedCriterion.Conditions(EntityPredicate.Extended.EMPTY,
		NumberRange.IntRange.ANY,
		NumberRange.IntRange.ANY,
		NumberRange.IntRange.ANY,
		itemPredicates)
