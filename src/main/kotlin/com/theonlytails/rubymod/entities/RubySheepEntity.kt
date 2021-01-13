package com.theonlytails.rubymod.entities

import com.theonlytails.rubymod.id
import com.theonlytails.rubymod.registries.BlockRegistry
import com.theonlytails.rubymod.registries.ItemRegistry
import net.minecraft.block.BlockState
import net.minecraft.entity.EntityData
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.passive.AnimalEntity
import net.minecraft.entity.passive.SheepEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Items
import net.minecraft.nbt.CompoundTag
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.DyeColor
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.LocalDifficulty
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World

class RubySheepEntity(type: EntityType<out SheepEntity?>, worldIn: World) : SheepEntity(type, worldIn) {
	private lateinit var eatGrassGoal: EatGrassGoal
	private var rubySheepTimer = 0

	override fun initGoals() {
		super.initGoals()

		eatGrassGoal = EatGrassGoal(this)

		goalSelector.add(0, SwimGoal(this))
		goalSelector.add(1, EscapeDangerGoal(this, 1.25))
		goalSelector.add(2, AnimalMateGoal(this, 1.0))
		goalSelector.add(2, AnimalMateGoal(this, 1.0, SheepEntity::class.java))
		goalSelector.add(3, TemptGoal(this, 1.1, TEMPTATION_ITEMS, false))
		goalSelector.add(4, FollowParentGoal(this, 1.1))
		goalSelector.add(5, this.eatGrassGoal)
		goalSelector.add(6, LookAtEntityGoal(this, PlayerEntity::class.java, 6.0f))
		goalSelector.add(7, LookAroundGoal(this))
	}

	override fun getAmbientSound(): SoundEvent? = SoundEvents.ENTITY_SHEEP_AMBIENT
	override fun getDeathSound(): SoundEvent? = SoundEvents.ENTITY_SHEEP_DEATH
	override fun getHurtSound(source: DamageSource?): SoundEvent? = SoundEvents.ENTITY_SHEEP_HURT
	override fun playStepSound(pos: BlockPos?, state: BlockState?) {
		playSound(SoundEvents.ENTITY_SHEEP_STEP, 0.1f, 1f)
	}

	override fun mobTick() {
		this.rubySheepTimer = eatGrassGoal.timer
		super.mobTick()
	}

	override fun tickMovement() {
		if (world.isClient) {
			rubySheepTimer = 0.coerceAtLeast(rubySheepTimer - 1)
		}

		super.tickMovement()
	}

	override fun handleStatus(id: Byte) =
		if (id.toInt() == 10) {
			rubySheepTimer = 40
		} else {
			super.handleStatus(id)
		}

	override fun sheared(category: SoundCategory) {
		world.playSoundFromEntity(null as PlayerEntity?,
			this,
			SoundEvents.ENTITY_SHEEP_SHEAR,
			category,
			1.0f,
			1.0f)
		this.isSheared = true
		val i = 1 + random.nextInt(3)

		for (j in 0 until i) {
			val itemEntity = this.dropItem(BlockRegistry.rubyWool, 1)
			if (itemEntity != null) {
				itemEntity.velocity =
					itemEntity.velocity.add(((random.nextFloat() - random.nextFloat()) * 0.1f).toDouble(),
						(random.nextFloat() * 0.05f).toDouble(),
						((random.nextFloat() - random.nextFloat()) * 0.1f).toDouble())
			}
		}
	}

	override fun initialize(
		world: ServerWorldAccess?,
		difficulty: LocalDifficulty?,
		spawnReason: SpawnReason?,
		entityData: EntityData?,
		entityTag: CompoundTag?,
	): EntityData? {
		val initialSpawnResult = super.initialize(world, difficulty, spawnReason, entityData, entityTag)
		this.color = DyeColor.WHITE
		return initialSpawnResult
	}

	override fun canBreedWith(other: AnimalEntity?): Boolean {
		return other is SheepEntity && other.isInLove()
	}

	override fun getLootTableId(): Identifier = id("entities/ruby_sheep")

	companion object {
		val TEMPTATION_ITEMS: Ingredient = Ingredient.ofItems(ItemRegistry.ruby, Items.WHEAT)

		@JvmStatic
		fun setCustomAttributes(): DefaultAttributeContainer.Builder {
			// func_233666_p_() -> registerAttributes()
			return MobEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.23)
		}
	}
}