package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.RubyMod
import com.theonlytails.rubymod.blocks.LogicGate
import com.theonlytails.rubymod.blocks.RubyCarpet
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.block.MaterialColor
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.registry.Registry

object BlockRegistry {
	val customBlocks = mutableListOf<Block>()
	private fun register(id: String, block: Block): Block {
		return Registry.register(Registry.BLOCK, RubyMod.id(id), block).also { customBlocks.add(block) }
	}

	val RUBY_BLOCK: Block = register("ruby_block", Block(
		FabricBlockSettings.of(Material.METAL)
			.hardness(5f)
			.resistance(6f)
			.sounds(BlockSoundGroup.METAL)
			.breakByTool(FabricToolTags.PICKAXES, 2)
			.requiresTool()
	))

	val RUBY_WOOL: Block = register("ruby_wool", Block(
		FabricBlockSettings.of(Material.WOOL, MaterialColor.RED)
			.strength(0.8f)
			.sounds(BlockSoundGroup.WOOL)
	))

	val RUBY_CARPET: Block = register("ruby_carpet", RubyCarpet())

	val LOGIC_GATE: Block = register("logic_gate", LogicGate(Settings.of(Material.SUPPORTED)
		.breakInstantly()
		.sounds(BlockSoundGroup.METAL)))
}
