package com.theonlytails.rubymod.client

import com.theonlytails.rubymod.client.render.RubySheepRenderer
import com.theonlytails.rubymod.registries.BlockRegistry
import com.theonlytails.rubymod.registries.EntityTypeRegistry
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.entity.EntityRenderDispatcher

class RubyModClient : ClientModInitializer {
	override fun onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(EntityTypeRegistry.RUBY_SHEEP) {
				dispatcher: EntityRenderDispatcher,
				_: EntityRendererRegistry.Context?,
			->
			RubySheepRenderer(dispatcher)
		}

		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.logicGate, RenderLayer.getCutout())

		// ScreenRegistry.register(ScreenHandlerTypes.rubyBarrel) {
		// 		rubyBarrelScreenHandler: RubyBarrelScreenHandler,
		// 		playerInventory: PlayerInventory,
		// 		text: Text,
		// 	->
		// 	RubyBarrelScreen(
		// 		rubyBarrelScreenHandler,
		// 		playerInventory,
		// 		text
		// 	)
		// }
	}
}