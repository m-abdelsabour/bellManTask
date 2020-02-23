package com.bellman.task.entity

data class Data(
    val attractions: MutableList<Attraction>,
    val events: MutableList<Any>,
    val hot_spots: MutableList<HotSpot>
)