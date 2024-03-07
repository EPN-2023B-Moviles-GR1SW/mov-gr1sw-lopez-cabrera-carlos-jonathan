package com.example.flip.models

import java.time.LocalDateTime


data class Flip(
    val id: Int,
    val amountWagered: Double,
    val amountWon: Double,
    val dateTime: LocalDateTime,
    val isWin: Boolean = false,  // Set default value to false
    val userId: User,
)
