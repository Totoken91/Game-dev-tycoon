package com.gamedevstudio.domain.model

data class ReviewResult(
    val projectId: String,
    val scores: List<MagazineReview>,
    val averageScore: Float
    // TODO: Add word-of-mouth effect, sales impact
)

data class MagazineReview(
    val magazineName: String,
    val score: Float,
    val quote: String
    // TODO: Add magazine bias, harshness modifier
)
