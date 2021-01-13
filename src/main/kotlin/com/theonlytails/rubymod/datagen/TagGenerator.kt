package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.registries.BlockRegistry
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
			.append(BlockRegistry.RUBY_BLOCK)
	}

	private fun generateItemTags(tag: TagData) {
		tag.item(Identifier("minecraft:beacon_payment_items"))
			.append(ItemRegistry.RUBY)

		tag.item(Identifier("fabric:pickaxes"))
			.append(ItemRegistry.RUBY_PICKAXE)

		tag.item(Identifier("fabric:axes"))
			.append(ItemRegistry.RUBY_AXE)

		tag.item(Identifier("fabric:swords"))
			.append(ItemRegistry.RUBY_SWORD)

		tag.item(Identifier("fabric:shovels"))
			.append(ItemRegistry.RUBY_SHOVEL)

		tag.item(Identifier("fabric:hoes"))
			.append(ItemRegistry.RUBY_HOE)
	}
}
