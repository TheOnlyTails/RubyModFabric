package com.theonlytails.rubymod.blocks

import com.theonlytails.rubymod.blockentity.RubyBarrelBlockEntity
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags
import net.minecraft.block.*
import net.minecraft.block.entity.BarrelBlockEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.stat.Stats
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.ItemScatterer
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView
import net.minecraft.world.World
import java.util.Random

@Suppress("DEPRECATION")
class RubyBarrelBlock : BlockWithEntity(FabricBlockSettings.of(
	Material.METAL, MaterialColor.RED)
	.strength(3.5f)
	.sounds(BlockSoundGroup.METAL)
	.breakByTool(FabricToolTags.PICKAXES, 2)
	.requiresTool()), BlockEntityProvider {

	init {
		this.defaultState = this.stateManager.defaultState.with(openProperty, false)
	}

	override fun onUse(
		state: BlockState,
		world: World,
		pos: BlockPos,
		player: PlayerEntity,
		hand: Hand,
		hit: BlockHitResult,
	): ActionResult {
		if (!world.isClient) {
			val blockEntity = world.getBlockEntity(pos)

			if (blockEntity is RubyBarrelBlockEntity) {
				player.openHandledScreen(blockEntity)
				player.incrementStat(Stats.OPEN_BARREL)
			}
		}

		return ActionResult.SUCCESS
	}

	override fun onStateReplaced(state: BlockState, world: World, pos: BlockPos, newState: BlockState, moved: Boolean) {
		if (!state.isOf(newState.block)) {
			val blockEntity = world.getBlockEntity(pos)

			if (blockEntity is Inventory) {
				ItemScatterer.spawn(world, pos, blockEntity)
				world.updateComparators(pos, this)
			}

			super.onStateReplaced(state, world, pos, newState, moved)
		}
	}

	override fun scheduledTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: Random) {
		val blockEntity = world.getBlockEntity(pos)

		if (blockEntity is BarrelBlockEntity) {
			blockEntity.tick()
		}
	}

	override fun createBlockEntity(world: BlockView) = RubyBarrelBlockEntity()

	override fun getRenderType(state: BlockState) = BlockRenderType.MODEL

	override fun onPlaced(world: World, pos: BlockPos, state: BlockState, placer: LivingEntity?, itemStack: ItemStack) {
		if (itemStack.hasCustomName()) {
			val blockEntity = world.getBlockEntity(pos)

			if (blockEntity is RubyBarrelBlockEntity) {
				blockEntity.customName = itemStack.name
			}
		}
	}

	override fun hasComparatorOutput(state: BlockState) = true

	override fun getComparatorOutput(state: BlockState, world: World, pos: BlockPos) =
		ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos))

	override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
		builder.add(openProperty)
	}

	companion object {
		val openProperty: BooleanProperty = Properties.OPEN
	}
}