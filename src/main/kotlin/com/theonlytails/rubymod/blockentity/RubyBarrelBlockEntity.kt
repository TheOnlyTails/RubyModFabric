package com.theonlytails.rubymod.blockentity

import com.theonlytails.rubymod.blocks.RubyBarrelBlock
import com.theonlytails.rubymod.registries.BlockEntityTypes
import net.minecraft.block.BlockState
import net.minecraft.block.entity.LootableContainerBlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundTag
import net.minecraft.screen.GenericContainerScreenHandler
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.text.TranslatableText
import net.minecraft.util.collection.DefaultedList

class RubyBarrelBlockEntity :
	LootableContainerBlockEntity(BlockEntityTypes.rubyBarrel), NamedScreenHandlerFactory {
	private var inventory = DefaultedList.ofSize(45, ItemStack.EMPTY)
	private var viewerCount = 0

	override fun toTag(tag: CompoundTag?): CompoundTag? {
		super.toTag(tag)

		if (!serializeLootTable(tag)) {
			Inventories.toTag(tag, inventory)
		}

		return tag
	}

	override fun fromTag(state: BlockState?, tag: CompoundTag?) {
		super.fromTag(state, tag)

		inventory = DefaultedList.ofSize(size(), ItemStack.EMPTY)

		if (!deserializeLootTable(tag)) {
			Inventories.fromTag(tag, inventory)
		}
	}

	override fun size() = 45

	override fun getInvStackList(): DefaultedList<ItemStack> = inventory

	override fun setInvStackList(list: DefaultedList<ItemStack>?) {
		inventory = list
	}

	override fun getContainerName() = TranslatableText(cachedState.block.translationKey)

	override fun createScreenHandler(syncId: Int, playerInventory: PlayerInventory) =
		GenericContainerScreenHandler(ScreenHandlerType.GENERIC_9X5, syncId, playerInventory, this, 5)

	override fun onOpen(player: PlayerEntity) {
		if (!player.isSpectator) {
			if (viewerCount < 0) {
				viewerCount = 0
			}

			viewerCount++

			val blockState = cachedState

			if (!blockState.get(RubyBarrelBlock.openProperty)) {
				playSound(SoundEvents.BLOCK_BARREL_OPEN)
				setOpen(blockState, true)
			}
		}
	}

	override fun onClose(player: PlayerEntity) {
		if (!player.isSpectator) {
			viewerCount--

			if (cachedState.get(RubyBarrelBlock.openProperty)) {
				playSound(SoundEvents.BLOCK_BARREL_CLOSE)
				setOpen(cachedState, false)
			}
		}
	}

	private fun setOpen(state: BlockState, open: Boolean) {
		world?.setBlockState(getPos(), state.with(RubyBarrelBlock.openProperty, open), 3)
	}

	private fun playSound(soundEvent: SoundEvent) {
		val x = (pos.x.toDouble() + 0.5)
		val y = (pos.y.toDouble() + 0.5)
		val z = (pos.z.toDouble() + 0.5)

		world?.playSound(null,
			x,
			y,
			z,
			soundEvent,
			SoundCategory.BLOCKS,
			0.5f,
			world!!.random.nextFloat() * 0.1f + 0.9f)
	}
}
