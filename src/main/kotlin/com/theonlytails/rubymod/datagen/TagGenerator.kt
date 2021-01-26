package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.registries.BlockRegistry
import com.theonlytails.rubymod.registries.FluidRegistry
import com.theonlytails.rubymod.registries.ItemRegistry
import me.shedaniel.cloth.api.datagen.v1.DataGeneratorHandler
import me.shedaniel.cloth.api.datagen.v1.TagData
import net.minecraft.util.Identifier

object TagGenerator {
	fun generateTags(handler: DataGeneratorHandler) {
		val tagData = handler.tags

		generateBlockTags(tagData)
		generateItemTags(tagData)
	}

	private fun generateBlockTags(tag: TagData) {
		tag.block(Identifier("minecraft:beacon_base_blocks"))
			.append(BlockRegistry.rubyBlock)
	}

	private fun generateItemTags(tag: TagData) {
		tag.item(Identifier("minecraft:beacon_payment_items"))
			.append(ItemRegistry.ruby)

		tag.item(Identifier("fabric:pickaxes"))
			.append(ItemRegistry.rubyPickaxe)

		tag.item(Identifier("fabric:axes"))
			.append(ItemRegistry.rubyAxe)

		tag.item(Identifier("fabric:swords"))
			.append(ItemRegistry.rubySword)

		tag.item(Identifier("fabric:shovels"))
			.append(ItemRegistry.rubyShovel)

		tag.item(Identifier("fabric:hoes"))
			.append(ItemRegistry.rubyHoe)

		tag.fluid(Identifier("minecraft:water"))
			.append(FluidRegistry.stillGhostWater)
			.append(FluidRegistry.flowingGhostWater)
	}
}
