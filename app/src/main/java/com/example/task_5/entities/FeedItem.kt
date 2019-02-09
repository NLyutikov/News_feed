package com.example.task_5.entities

data class FeedItem (
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val type: FeedType
)