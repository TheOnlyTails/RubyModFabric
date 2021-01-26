package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.blocks.LogicGateBlock
import com.theonlytails.rubymod.blocks.RubyBarrelBlock
import com.theonlytails.rubymod.blocks.RubyCarpetBlock
import com.theonlytails.rubymod.id
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags
import net.minecraft.block.*
import net.minecraft.block.AbstractBlock.Settings
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.registry.Registry
import java.util.Random

object BlockRegistry {
	val customBlocks = mutableListOf<Block>()
	private fun register(id: String, block: Block): Block =
		Registry.register(Registry.BLOCK, id(id), block).also { customBlocks.add(block) }

	val rubyBlock = register("ruby_block", Block(
		FabricBlockSettings.of(Material.METAL)
			.hardness(5f)
			.resistance(6f)
			.sounds(BlockSoundGroup.METAL)
			.breakByTool(FabricToolTags.PICKAXES, 2)
			.requiresTool()
	))

	val rubyOre = register("ruby_ore", object : OreBlock(FabricBlockSettings.of(Material.STONE)
		.hardness(3.0f)
		.resistance(3.0f)
		.sounds(BlockSoundGroup.STONE)
		.breakByTool(FabricToolTags.PICKAXES, 2)) {
		override fun getExperienceWhenMined(random: Random) = 3
	})

	val rubyWool = register("ruby_wool", Block(
		FabricBlockSettings.of(Material.WOOL, MaterialColor.RED)
			.strength(0.8f)
			.sounds(BlockSoundGroup.WOOL)
	))

	val rubyCarpet = register("ruby_carpet", RubyCarpetBlock())

	val rubyBarrel = register("ruby_barrel", RubyBarrelBlock())

	val logicGate = register("logic_gate", LogicGateBlock(Settings.of(Material.SUPPORTED)
		.breakInstantly()
		.sounds(BlockSoundGroup.METAL)))

	val ghostWater = register("ghost_water",
		object : FluidBlock(FluidRegistry.stillGhostWater,
			FabricBlockSettings.copy(Blocks.WATER)) {}
	)
}
