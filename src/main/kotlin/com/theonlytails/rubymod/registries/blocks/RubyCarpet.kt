package com.theonlytails.rubymod.registries.blocks

import net.minecraft.block.*
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

class RubyCarpet : Block(Settings.of(Material.CARPET, MaterialColor.RED)
	.strength(0.1f)
	.sounds(BlockSoundGroup.WOOL)) {
	private val shape = createCuboidShape(0.0, 0.0, 0.0, 16.0, 1.0, 16.0)

	override fun getOutlineShape(
		state: BlockState,
		world: BlockView?,
		pos: BlockPos,
		context: ShapeContext?,
	): VoxelShape = shape

	override fun getStateForNeighborUpdate(
		state: BlockState,
		direction: Direction,
		newState: BlockState,
		world: WorldAccess,
		pos: BlockPos,
		posFrom: BlockPos,
	): BlockState = if (!state.canPlaceAt(world, pos))
		Blocks.AIR.defaultState else super.getStateForNeighborUpdate(state,
		direction,
		newState,
		world,
		pos,
		posFrom)

	override fun canPlaceAt(state: BlockState?, world: WorldView, pos: BlockPos) = !world.isAir(pos.down())
}
