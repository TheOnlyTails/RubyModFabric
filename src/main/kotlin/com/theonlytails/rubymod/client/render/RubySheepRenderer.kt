package com.theonlytails.rubymod.client.render

import com.theonlytails.rubymod.client.model.RubySheepModel
import com.theonlytails.rubymod.entities.RubySheepEntity
import com.theonlytails.rubymod.id
import net.minecraft.client.render.entity.EntityRenderDispatcher
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.util.Identifier

class RubySheepRenderer(entityRenderDispatcher: EntityRenderDispatcher) :
	MobEntityRenderer<RubySheepEntity, RubySheepModel>(entityRenderDispatcher, RubySheepModel(), 0.5f) {

	init {
		addFeature(RubySheepWoolFeatureRenderer(this))
	}

	override fun getTexture(entity: RubySheepEntity?): Identifier = SHEARED_SHEEP_TEXTURES

	companion object {
		private val SHEARED_SHEEP_TEXTURES = id("textures/entity/ruby_sheep/ruby_sheep.png")
	}
}