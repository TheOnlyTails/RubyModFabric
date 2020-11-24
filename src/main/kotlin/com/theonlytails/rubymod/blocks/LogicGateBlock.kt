package com.theonlytails.rubymod.blocks

import com.theonlytails.rubymod.util.LogicGateModes
import net.minecraft.block.AbstractRedstoneGateBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.state.StateManager
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.Properties.HORIZONTAL_FACING
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World
import net.minecraft.world.WorldAccess

class LogicGateBlock(settings: Settings) : AbstractRedstoneGateBlock(settings), AdvancedRedstoneConnector {

	init {
		this.defaultState = this.stateManager.defaultState
			.with(HORIZONTAL_FACING, Direction.NORTH)
			.with(POWERED, false)
			.with(MODE, LogicGateModes.OR)
	}

	override fun getPower(world: World, pos: BlockPos, state: BlockState): Int {
		val facing = state.get(HORIZONTAL_FACING)

		val firstInput = world.getEmittedRedstonePower(pos.offset(facing.rotateYClockwise()),
			facing.rotateYClockwise()) > 0
		val secondInput = world.getEmittedRedstonePower(pos.offset(facing.rotateYCounterclockwise()),
			facing.rotateYCounterclockwise()) > 0

		return state.get(MODE)(firstInput, secondInput)
	}

	override fun onUse(
		state: BlockState,
		world: World,
		pos: BlockPos,
		player: PlayerEntity,
		hand: Hand,
		hit: BlockHitResult,
	): ActionResult {
		return if (player.abilities.allowModifyWorld) {
			world.setBlockState(pos, state.cycle(MODE), 3)
			ActionResult.success(world.isClient)
		} else {
			ActionResult.PASS
		}
	}

	override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
		builder.add(HORIZONTAL_FACING, POWERED, MODE)
	}

	override fun isValidInput(state: BlockState) = isRedstoneGate(state)

	@Suppress("DEPRECATION")
	override fun getStateForNeighborUpdate(
		stateIn: BlockState,
		facing: Direction,
		facingState: BlockState,
		worldIn: WorldAccess,
		currentPos: BlockPos,
		facingPos: BlockPos,
	): BlockState {
		return if (!worldIn.isClient && facing.axis !== stateIn.get(HORIZONTAL_FACING).axis)
			stateIn.with(MODE, stateIn.get(MODE))
		else super.getStateForNeighborUpdate(stateIn,
			facing,
			facingState,
			worldIn,
			currentPos,
			facingPos)
	}

	override fun getUpdateDelayInternal(state: BlockState) = 1
	override fun connectsToRedstoneInDirection(state: BlockState, direction: Direction) =
		direction == state[HORIZONTAL_FACING] ||
				direction == state[HORIZONTAL_FACING].rotateYClockwise() ||
				direction == state[HORIZONTAL_FACING].rotateYCounterclockwise()

	companion object {
		val MODE: EnumProperty<LogicGateModes> = EnumProperty.of(
			"logic_gate_mode",
			LogicGateModes::class.java,
			LogicGateModes.AND,
			LogicGateModes.OR,
		)
	}
}