/*
 * RestaurantesCentroCDMX - Modelo de datos para Elemento del Menú
 *
 * Esta clase representa un elemento individual del menú de un restaurante,
 * incluyendo su información completa y categoría.
 */
package com.example.restaurantescdmx.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Enumeración para los tipos de categorías del menú
 */
enum class MenuCategory {
    FOOD,
    DRINK,
    EXTRA
}

/**
 * Clase de datos que representa un elemento del menú
 *
 * @property id Identificador único del elemento
 * @property name Nombre del elemento
 * @property description Descripción completa del elemento
 * @property price Precio en pesos mexicanos
 * @property category Categoría del elemento (comida, bebida, complemento)
 * @property restaurantId ID del restaurante al que pertenece
 */
@Parcelize
data class MenuItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val category: MenuCategory,
    val restaurantId: Int
) : Parcelable {

    /**
     * Función para obtener el precio formateado
     * @return String con el precio en formato mexicano
     */
    fun getFormattedPrice(): String {
        return String.format("$%.2f MXN", price)
    }

    /**
     * Función para obtener una descripción breve (máximo 50 caracteres)
     * @return Descripción truncada si es necesario
     */
    fun getShortDescription(): String {
        return if (description.length > 50) {
            "${description.take(47)}..."
        } else {
            description
        }
    }
}