package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.entities.RubySheepEntity
import com.theonlytails.rubymod.id
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.util.registry.Registry

object EntityTypeRegistry {
	private fun register(id: String, entityType: EntityType<*>) =
		Registry.register(Registry.ENTITY_TYPE, id(id), entityType)

	@Suppress("UNCHECKED_CAST")
	val RUBY_SHEEP: EntityType<RubySheepEntity> = register("ruby_sheep", FabricEntityTypeBuilder
		.create(SpawnGroup.CREATURE, ::RubySheepEntity)
		.dimensions(EntityDimensions.fixed(0.625f, 1.25f))
		.build()) as EntityType<RubySheepEntity>
}