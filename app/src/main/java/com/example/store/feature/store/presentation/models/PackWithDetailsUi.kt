package com.example.store.feature.store.presentation.models

import android.content.Context
import com.example.store.R
import com.example.store.feature.common.domain.model.PackWithDetails
import java.text.NumberFormat
import java.util.Locale

data class PackWithDetailsUi(
    val id: Int,
    val name: String,
    val unit: String,
    val type: PackType,
    val originalPrice: DisplayableNumber,
    val discountedPrice: DisplayableNumber,
    val bonus: DisplayableNumber?,
    val quant: Int
)

fun PackWithDetails.toPackWithDetailsUi(): PackWithDetailsUi {
    val originalPrice = packPrice.price
    val discountedPrice = originalPrice - packPrice.bonus
    return PackWithDetailsUi(
        id = pack.id,
        name = pack.name,
        unit = unit.name,
        type = pack.type.toPackType(),
        originalPrice = packPrice.price.toFormattedPrice(),
        discountedPrice = discountedPrice.toFormattedPrice(),
        bonus = packPrice.price.toFormattedPrice(),
        quant = pack.quant
    )
}

data class DisplayableNumber(
    val value: Int,
    val formatted: String
)

enum class PackType {
    UNIT,
    WEIGHT
}

fun Int.toFormattedPrice(): DisplayableNumber {
    val rubles = this / 100.0
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(rubles) + " BYN"
    )
}

fun Int.toFormattedQuantity(packType: PackType): DisplayableNumber {
    val value = if (packType == PackType.WEIGHT) this / 1000.0 else this.toDouble()
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = if (packType == PackType.WEIGHT) 1 else 0
    }
    val unit = if (packType == PackType.WEIGHT) "кг" else "шт"
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(value) + " $unit"
    )
}

fun Int.toPackType(): PackType {
    return when (this) {
        0 -> PackType.UNIT
        else -> PackType.WEIGHT
    }
}

fun PackType.toPriceDescription(context: Context): String {
    return when (this) {
        PackType.UNIT -> context.getString(R.string.pcs)
        PackType.WEIGHT -> context.getString(R.string.kg)
    }
}
