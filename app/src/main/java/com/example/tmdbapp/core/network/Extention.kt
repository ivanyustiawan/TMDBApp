package com.example.tmdbapp.core.network

fun Boolean?.orFalse(): Boolean = this ?: false

fun Long?.orZero(): Long = this ?: 0

fun Double?.orZero(): Double = this ?: 0.0