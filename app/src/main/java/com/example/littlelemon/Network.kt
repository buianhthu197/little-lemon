package com.example.littlelemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetwork(
    @SerialName("menu")
    val menu: List<MenuItemNetwork>
) {
}

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String,
    @SerialName("price")
    val price: Double
) {
    fun toMenuItemRoom() = MenuItemRoom(id, title, description, image, category, price)
}