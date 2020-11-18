package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.RubyMod
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.registry.Registry

object BlockRegistry {
	private fun register(id: String, block: Block) = Registry.register(Registry.BLOCK, RubyMod.id(id), block)

	val RUBY_BLOCK: Block = register("ruby_block", Block(
		FabricBlockSettings.of(Material.METAL)
			.hardness(5f)
			.resistance(6f)
			.sounds(BlockSoundGroup.METAL)
			.breakByTool(FabricToolTags.PICKAXES, 2)
			.requiresTool()
	))
}
