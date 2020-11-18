package com.theonlytails.rubymod

import com.theonlytails.rubymod.registries.ItemRegistry
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import org.apache.logging.log4j.LogManager

object RubyMod : ModInitializer {
    const val MOD_ID = "rubymod"
    val RUBY_TAB: ItemGroup = FabricItemGroupBuilder.build(id("ruby_tab")) {
        ItemStack(ItemRegistry.RUBY)
    }

    val LOGGER = LogManager.getLogger()

    override fun onInitialize() {
        ItemRegistry

        LOGGER.info("The mod has been initialized!")
    }

    @JvmStatic
    fun id(path: String) = Identifier(MOD_ID, path)
}

