package com.theonlytails.rubymod.client.render

import com.theonlytails.rubymod.client.model.RubySheepModel
import com.theonlytails.rubymod.entities.RubySheepEntity
import com.theonlytails.rubymod.id
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.feature.FeatureRenderer
import net.minecraft.client.render.entity.feature.FeatureRendererContext
import net.minecraft.client.render.entity.model.SheepWoolEntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.passive.SheepEntity
import net.minecraft.util.DyeColor

class RubySheepWoolFeatureRenderer(featureRendererContext: FeatureRendererContext<RubySheepEntity, RubySheepModel>) :
	FeatureRenderer<RubySheepEntity, RubySheepModel>(featureRendererContext) {

	private val sheepModel = SheepWoolEntityModel<RubySheepEntity>()

	override fun render(
		matrixStack: MatrixStack?,
		vertexConsumerProvider: VertexConsumerProvider?,
		i: Int,
		sheepEntity: RubySheepEntity,
		f: Float,
		g: Float,
		h: Float,
		j: Float,
		k: Float,
		l: Float,
	) {
		if (!sheepEntity.isSheared && !sheepEntity.isInvisible) {
			val v: Float
			val w: Float
			val x: Float
			if (sheepEntity.hasCustomName() && "jeb_" == sheepEntity.name.asString()) {
				val n = sheepEntity.age / 25 + sheepEntity.entityId
				val o = DyeColor.values().size
				val p = n % o
				val q = (n + 1) % o
				val r = ((sheepEntity.age % 25).toFloat() + h) / 25.0f
				val fs = SheepEntity.getRgbColor(DyeColor.byId(p))
				val gs = SheepEntity.getRgbColor(DyeColor.byId(q))
				v = fs[0] * (1.0f - r) + gs[0] * r
				w = fs[1] * (1.0f - r) + gs[1] * r
				x = fs[2] * (1.0f - r) + gs[2] * r
			} else {
				val hs = SheepEntity.getRgbColor(sheepEntity.color)
				v = hs[0]
				w = hs[1]
				x = hs[2]
			}
			render(
				this.contextModel,
				this.sheepModel,
				TEXTURE,
				matrixStack,
				vertexConsumerProvider,
				i,
				sheepEntity,
				f,
				g,
				j,
				k,
				l,
				h,
				v,
				w,
				x)
		}
	}

	companion object {
		private val TEXTURE = id("textures/entity/ruby_sheep/ruby_sheep_fur.png")
	}
}