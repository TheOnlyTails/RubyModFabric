package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.blockentity.RubyBarrelBlockEntity
import com.theonlytails.rubymod.id
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.registry.Registry

object BlockEntityTypes {
	private fun register(id: String, block: BlockEntityType<*>) =
		Registry.register(Registry.BLOCK_ENTITY_TYPE, id(id), block)

	val rubyBarrel: BlockEntityType<*> = register("ruby_barrel", BlockEntityType.Builder.create(
		{ RubyBarrelBlockEntity() },
		BlockRegistry.rubyBarrel)
		.build(null))
}
