package com.theonlytails.rubymod.datagen

import com.theonlytails.rubymod.RubyMod
import net.devtech.arrp.api.RRPCallback
import net.devtech.arrp.api.RuntimeResourcePack

object DataGeneratorRunner {
	val RESOURCE_PACK: RuntimeResourcePack = RuntimeResourcePack.create("${RubyMod.MOD_ID}:${RubyMod.MOD_ID}")

	fun initGenerators() {
		LangGenerator.generate()

		RRPCallback.EVENT.register { it.add(RESOURCE_PACK) }
	}
}