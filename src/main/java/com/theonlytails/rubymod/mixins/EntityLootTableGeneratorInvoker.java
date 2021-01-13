package com.theonlytails.rubymod.mixins;

import net.minecraft.data.server.EntityLootTableGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EntityLootTableGenerator.class)
public interface EntityLootTableGeneratorInvoker {
    @Invoker("createForSheep")
    static LootTable.Builder createForSheep(ItemConvertible itemConvertible) {
        throw new AssertionError();
    }
}
