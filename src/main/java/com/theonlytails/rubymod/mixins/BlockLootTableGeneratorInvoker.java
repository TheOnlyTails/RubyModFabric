package com.theonlytails.rubymod.mixins;

import net.minecraft.block.Block;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BlockLootTableGenerator.class)
public interface BlockLootTableGeneratorInvoker {
    @Invoker("oreDrops")
    static LootTable.Builder oreDrops(Block dropWithSilkTouch, Item drop) {
        throw new AssertionError();
    }
}
