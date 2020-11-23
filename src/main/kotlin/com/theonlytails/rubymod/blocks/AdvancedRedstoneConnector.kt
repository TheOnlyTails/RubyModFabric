package com.theonlytails.rubymod.blocks

import net.minecraft.block.BlockState
import net.minecraft.util.math.Direction

interface AdvancedRedstoneConnector {
	fun connectsToRedstoneInDirection(state: BlockState, direction: Direction) = true
}
