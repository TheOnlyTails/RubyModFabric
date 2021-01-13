package com.theonlytails.rubymod.registries

import com.theonlytails.rubymod.RubyMod
import com.theonlytails.rubymod.id
import com.theonlytails.rubymod.items.RubyArmorItem
import com.theonlytails.rubymod.util.materials.RubyArmorMaterial
import com.theonlytails.rubymod.util.materials.RubyToolMaterial
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
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

	val RUBY: Item = register("ruby", Item(DEFAULT_ITEM_PROPERTY))

	val RUBY_HELMET: Item = register("ruby_helmet", RubyArmorItem(EquipmentSlot.HEAD))

	val RUBY_CHESTPLATE: Item = register("ruby_chestplate", RubyArmorItem(EquipmentSlot.CHEST))

	val RUBY_LEGGINGS: Item = register("ruby_leggings", RubyArmorItem(EquipmentSlot.LEGS))

	val RUBY_BOOTS: Item = register("ruby_boots", RubyArmorItem(EquipmentSlot.FEET))

	val RUBY_PICKAXE: Item = register("ruby_pickaxe",
		object : PickaxeItem(rubyToolMaterial, 1, -2.8f, UNSTACKABLE_PROPERTY) {})

	val RUBY_SWORD: Item = register("ruby_sword",
		SwordItem(rubyToolMaterial, 2, -2.4f, UNSTACKABLE_PROPERTY))

	val RUBY_AXE: Item = register("ruby_axe",
		object : AxeItem(rubyToolMaterial, 5f, -3.05f, UNSTACKABLE_PROPERTY) {})

	val RUBY_SHOVEL: Item = register("ruby_shovel",
		ShovelItem(rubyToolMaterial, 1f, -3f, UNSTACKABLE_PROPERTY))

	val RUBY_HOE: Item = register("ruby_hoe",
		object : HoeItem(rubyToolMaterial, -2, -0.5f, UNSTACKABLE_PROPERTY) {})

	val POISONED_APPLE: Item = register("poisoned_apple",
		Item(Settings()
			.group(ItemGroup.FOOD)
			.food(FoodComponent.Builder()
				.hunger(7)
				.saturationModifier(14.4f)
				// Gives you Nausea 2 for 7 seconds 100% of the time;
				.statusEffect(StatusEffectInstance(StatusEffects.NAUSEA, 7 * 20, 1), 1f)

				// Gives you Nausea 2 for 7 seconds 100% of the time;
				.statusEffect(StatusEffectInstance(StatusEffects.POISON, 9 * 20, 1), 1f)

				// Gives you Glowing 1 for 10 seconds 100% of the time;
				.statusEffect(StatusEffectInstance(StatusEffects.GLOWING, 10 * 20, 0), 1f)

				// Gives you Hunger 3 for 3 seconds 10% of the time;
				.statusEffect(StatusEffectInstance(StatusEffects.HUNGER, 3 * 20, 2), 0.1f)

				// Gives you Blindness (!) 3 for 5 seconds 5% of the time;
				.statusEffect(StatusEffectInstance(StatusEffects.BLINDNESS, 5 * 20, 2), 0.05f)

				// Gives you Luck (!) 1 for 1 seconds 50% of the time;
				.statusEffect(StatusEffectInstance(StatusEffects.LUCK, 20, 0), 0.5f)
				.alwaysEdible()
				.build()
			)))

	val RUBY_SHEEP_SPAWN_EGG = register("ruby_sheep_spawn_egg",
		SpawnEggItem(
			EntityTypeRegistry.RUBY_SHEEP,
			0xE3E6E7,
			0xFD0D0D,
			DEFAULT_ITEM_PROPERTY
		))
}
