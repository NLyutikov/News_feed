package com.example.task_5.entities

data class FeedItem (
    val id: String,
    var isLiked: Boolean,
    val title: String,
    val content: String,
    val imageUrl: String,
    val type: FeedType
)