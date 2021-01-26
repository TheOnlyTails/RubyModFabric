package com.theonlytails.rubymod.fluids

import com.theonlytails.rubymod.registries.BlockRegistry
import com.theonlytails.rubymod.registries.FluidRegistry
import com.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.fluid.FlowableFluid
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.tag.FluidTags
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

abstract class GhostWater : FlowableFluid() {
	override fun getBucketItem() = ItemRegistry.ghostWaterBucket

	override fun canBeReplacedWith(
		state: FluidState,
		world: BlockView,
		pos: BlockPos,
		fluid: Fluid,
		direction: Direction,
	) = direction == Direction.DOWN && !fluid.isIn(FluidTags.WATER)

	override fun getTickRate(world: WorldView?) = 5

	override fun getBlastResistance() = 100f

	override fun toBlockState(state: FluidState?): BlockState =
		BlockRegistry.ghostWater.defaultState.with(Properties.LEVEL_15, method_15741(state))

	override fun getFlowing() = FluidRegistry.flowingGhostWater

	override fun getStill() = FluidRegistry.stillGhostWater

	override fun isInfinite() = true

	override fun beforeBreakingBlock(world: WorldAccess, pos: BlockPos, state: BlockState) {
		val blockEntity = if (state.block.hasBlockEntity()) world.getBlockEntity(pos) else null
		Block.dropStacks(state, world, pos, blockEntity)
	}

	override fun getFlowSpeed(world: WorldView) = 4

	override fun getLevelDecreasePerBlock(world: WorldView) = 1

	class GhostWaterStill : GhostWater() {
		override fun getLevel(state: FluidState) = 8

		override fun isStill(state: FluidState) = true
	}

	class GhostWaterFlowing : GhostWater() {
		override fun appendProperties(builder: StateManager.Builder<Fluid, FluidState>) {
			super.appendProperties(builder)
			builder.add(LEVEL)
		}

		override fun getLevel(state: FluidState): Int = state.get(LEVEL)

		override fun isStill(state: FluidState) = false
	}
}