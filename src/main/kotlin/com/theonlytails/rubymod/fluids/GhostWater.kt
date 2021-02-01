package com.theonlytails.rubymod.fluids

import com.theonlytails.rubymod.registries.BlockRegistry
import com.theonlytails.rubymod.registries.FluidRegistry
import com.theonlytails.rubymod.registries.ItemRegistry
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.fluid.FlowableFluid
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.particle.DefaultParticleType
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateManager
import net.minecraft.state.property.Properties
import net.minecraft.tag.FluidTags
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.*
import java.awt.Color
import java.util.Random

abstract class GhostWater : FlowableFluid(), FluidInfo {
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

	@Environment(EnvType.CLIENT)
	override fun randomDisplayTick(world: World, pos: BlockPos, state: FluidState, random: Random) {
		if (!state.isStill && !state.get(FALLING)) {
			if (random.nextInt(64) == 0) {
				world.playSound(pos.x.toDouble() + 0.5,
					pos.y.toDouble() + 0.5,
					pos.z.toDouble() + 0.5,
					SoundEvents.BLOCK_WATER_AMBIENT,
					SoundCategory.BLOCKS,
					random.nextFloat() * 0.25f + 0.75f,
					random.nextFloat() + 0.5f,
					false)
			}
		} else if (random.nextInt(10) == 0) {
			world.addParticle(ParticleTypes.UNDERWATER,
				pos.x.toDouble() + random.nextDouble(),
				pos.y.toDouble() + random.nextDouble(),
				pos.z
					.toDouble() + random.nextDouble(),
				0.0,
				0.0,
				0.0)
		}
	}

	override fun getFlowing() = FluidRegistry.flowingGhostWater

	override fun getStill() = FluidRegistry.stillGhostWater

	override fun isInfinite() = true

	override fun beforeBreakingBlock(world: WorldAccess, pos: BlockPos, state: BlockState) {
		val blockEntity = if (state.block.hasBlockEntity()) world.getBlockEntity(pos) else null
		Block.dropStacks(state, world, pos, blockEntity)
	}

	override fun getFlowSpeed(world: WorldView) = 4

	override fun getLevelDecreasePerBlock(world: WorldView) = 1

	override fun matchesType(fluid: Fluid) =
		fluid == FluidRegistry.stillGhostWater || fluid == FluidRegistry.flowingGhostWater

	override val fogColor = Color(228, 80, 63, 255).rgb

	override fun getParticle(): DefaultParticleType = ParticleTypes.DRIPPING_WATER

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