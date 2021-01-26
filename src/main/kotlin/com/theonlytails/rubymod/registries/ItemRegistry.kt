package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.RubyMod
import com.theonlytails.rubymod.id
import com.theonlytails.rubymod.items.PoisonedAppleItem
import com.theonlytails.rubymod.items.RubyArmorItem
import com.theonlytails.rubymod.util.materials.RubyArmorMaterial
import com.theonlytails.rubymod.util.materials.RubyToolMaterial
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.*
import net.minecraft.item.Item.Settings
import net.minecraft.util.registry.Registry

object ItemRegistry {
	fun register(id: String, item: Item): Item = Registry.register(Registry.ITEM, id(id), item)
	val DEFAULT_ITEM_PROPERTY: Settings = Settings().group(RubyMod.RUBY_TAB).maxDamage(0).maxCount(64)
	val UNSTACKABLE_PROPERTY: Settings = Settings().group(RubyMod.RUBY_TAB)

	@JvmStatic
	val rubyArmorMaterial = RubyArmorMaterial()

	@JvmStatic
	val rubyToolMaterial = RubyToolMaterial()

	val ruby: Item = register("ruby", Item(DEFAULT_ITEM_PROPERTY))

	val rubyHelmet: Item = register("ruby_helmet", RubyArmorItem(EquipmentSlot.HEAD))

	val rubyChestplate: Item = register("ruby_chestplate", RubyArmorItem(EquipmentSlot.CHEST))

	val rubyLeggings: Item = register("ruby_leggings", RubyArmorItem(EquipmentSlot.LEGS))

	val rubyBoots: Item = register("ruby_boots", RubyArmorItem(EquipmentSlot.FEET))

	val rubyPickaxe: Item = register("ruby_pickaxe",
		object : PickaxeItem(rubyToolMaterial, 1, -2.8f, UNSTACKABLE_PROPERTY) {})

	val rubySword: Item = register("ruby_sword",
		SwordItem(rubyToolMaterial, 2, -2.4f, UNSTACKABLE_PROPERTY))

	val rubyAxe: Item = register("ruby_axe",
		object : AxeItem(rubyToolMaterial, 5f, -3.05f, UNSTACKABLE_PROPERTY) {})

	val rubyShovel: Item = register("ruby_shovel",
		ShovelItem(rubyToolMaterial, 1f, -3f, UNSTACKABLE_PROPERTY))

	val rubyHoe: Item = register("ruby_hoe",
		object : HoeItem(rubyToolMaterial, -2, -0.5f, UNSTACKABLE_PROPERTY) {})

	val poisonedApple: Item = register("poisoned_apple", PoisonedAppleItem())

	val rubySheepSpawnEgg = register("ruby_sheep_spawn_egg",
		SpawnEggItem(
			EntityTypeRegistry.RUBY_SHEEP,
			0xE3E6E7,
			0xFD0D0D,
			DEFAULT_ITEM_PROPERTY
		))

	val ghostWaterBucket = register("ghost_water_bucket", BucketItem(
		FluidRegistry.stillGhostWater,
		Settings().recipeRemainder(Items.BUCKET).maxCount(1).group(RubyMod.RUBY_TAB)
	))
}
