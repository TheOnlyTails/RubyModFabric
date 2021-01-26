package com.theonlytails.rubymod.client

import com.theonlytails.rubymod.client.render.RubySheepRenderer
import com.theonlytails.rubymod.registries.BlockRegistry
import com.theonlytails.rubymod.registries.EntityTypeRegistry
import com.theonlytails.rubymod.registries.FluidRegistry
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.entity.EntityRenderDispatcher
import net.minecraft.client.texture.Sprite
import net.minecraft.client.texture.SpriteAtlasTexture
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.resource.ResourceManager
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry
import net.minecraft.world.BlockRenderView
import java.awt.Color

@Suppress("DEPRECATION")
class RubyModClient : ClientModInitializer {
	override fun onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(EntityTypeRegistry.RUBY_SHEEP) {
				dispatcher: EntityRenderDispatcher,
				_: EntityRendererRegistry.Context?,
			->
			RubySheepRenderer(dispatcher)
		}

		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.logicGate, RenderLayer.getCutout())

		setupFluidRendering(FluidRegistry.stillGhostWater,
			FluidRegistry.flowingGhostWater,
			Identifier("minecraft", "water"),
			Color(228, 80, 63, 255).rgb)

		BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
			FluidRegistry.stillGhostWater,
			FluidRegistry.flowingGhostWater)
	}

	private fun setupFluidRendering(still: Fluid?, flowing: Fluid?, textureFluidId: Identifier, color: Int) {
		val stillSpriteId = Identifier(textureFluidId.namespace, "block/${textureFluidId.path}_still")
		val flowingSpriteId = Identifier(textureFluidId.namespace, "block/${textureFluidId.path}_flow")

		// If they're not already present, add the sprites to the block atlas
		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)
			.register { _: SpriteAtlasTexture, registry: ClientSpriteRegistryCallback.Registry ->
				registry.register(stillSpriteId)
				registry.register(flowingSpriteId)
			}

		val fluidId = Registry.FLUID.getId(still)
		val listenerId = Identifier(fluidId.namespace, "${fluidId.path}_reload_listener")
		val fluidSprites = arrayOf<Sprite?>(null, null)

		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES)
			.registerReloadListener(object : SimpleSynchronousResourceReloadListener {
				override fun getFabricId() = listenerId

				/**
				 * Get the sprites from the block atlas when resources are reloaded
				 */
				override fun apply(resourceManager: ResourceManager?) {
					val atlas = MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)

					fluidSprites[0] = atlas.apply(stillSpriteId)
					fluidSprites[1] = atlas.apply(flowingSpriteId)
				}
			})

		// The FluidRenderer gets the sprites and color from a FluidRenderHandler during rendering
		val renderHandler = object : FluidRenderHandler {
			override fun getFluidSprites(view: BlockRenderView?, pos: BlockPos?, state: FluidState) = fluidSprites

			override fun getFluidColor(view: BlockRenderView?, pos: BlockPos?, state: FluidState) = color
		}

		FluidRenderHandlerRegistry.INSTANCE.register(still, renderHandler)
		FluidRenderHandlerRegistry.INSTANCE.register(flowing, renderHandler)
	}
}