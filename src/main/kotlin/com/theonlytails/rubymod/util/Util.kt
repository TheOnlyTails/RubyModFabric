package com.theonlytails.rubymod.util

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
import net.minecraft.village.TradeOffers

fun tradeOffersMap(map: Map<Int, Array<TradeOffers.Factory>>) = Int2ObjectOpenHashMap(map)