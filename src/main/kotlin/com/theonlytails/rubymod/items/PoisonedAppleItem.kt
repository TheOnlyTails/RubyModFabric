package com.theonlytails.rubymod.items

import com.theonlytails.rubymod.entities.RubySheepEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.passive.SheepEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.*
import net.minecraft.text.LiteralText
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand

class PoisonedAppleItem : Item(Settings()
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
	)) {

	override fun useOnEntity(
		stack: ItemStack,
		player: PlayerEntity,
		target: LivingEntity,
		hand: Hand,
	): ActionResult {
		if (target is SheepEntity) {
			if (target.isAlive && target is RubySheepEntity) {
				target.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, 9 * 20, 1))
				target.setGlowing(true)

				if (!player.world.isClient) {
					player.sendMessage(LiteralText("I don't think that sheep is feeling so well..."), false)

					stack.decrement(1)
				}
			}

			return ActionResult.success(player.world.isClient)
		}
		return ActionResult.PASS
	}
}
