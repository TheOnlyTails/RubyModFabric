package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.RubyMod
import com.theonlytails.rubymod.id
import me.shedaniel.cloth.api.datagen.v1.DataGeneratorHandler
import net.devtech.arrp.api.RRPCallback
import net.devtech.arrp.api.RuntimeResourcePack
import java.nio.file.Paths

object DataGeneratorRunner {
	val RESOURCE_PACK: RuntimeResourcePack = RuntimeResourcePack.create(id(RubyMod.MOD_ID).toString())
	val handler: DataGeneratorHandler =
		DataGeneratorHandler.create(Paths.get("../src/generated/resource"))

	fun register() {
		LangGenerator.generate()
		RecipeGenerator.generate(handler)
		LootTableGenerator.generate(handler)
		TagGenerator.generateTags(handler)
		ModelGenerator.generate(handler)

		RRPCallback.EVENT.register { it.add(RESOURCE_PACK) }
	}
}