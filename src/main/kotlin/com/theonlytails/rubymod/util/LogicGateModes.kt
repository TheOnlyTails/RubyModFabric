package com.theonlytails.rubymod.util

import net.minecraft.util.StringIdentifiable

enum class LogicGateModes(private val type: String, val function: (Boolean, Boolean) -> Int) : StringIdentifiable {
	OR("or", { isFirstInputOn, isSecondInputOn -> if (isFirstInputOn || isSecondInputOn) 15 else 0 }),
	AND("and", { isFirstInputOn, isSecondInputOn -> if (isFirstInputOn && isSecondInputOn) 15 else 0 });

	override fun asString() = this.type

	operator fun invoke(firstInput: Boolean, secondInput: Boolean) = this.function(firstInput, secondInput)
}