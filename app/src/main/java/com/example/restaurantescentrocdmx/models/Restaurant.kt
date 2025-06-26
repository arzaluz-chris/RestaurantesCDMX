/*
 * RestaurantesCentroCDMX - Modelo de datos para Restaurante
 *
 * Esta clase representa un restaurante con su información básica
 * y las categorías de menú disponibles.
 */
package com.example.restaurantescdmx.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Clase de datos que representa un restaurante
 *
 * @property id Identificador único del restaurante
 * @property name Nombre del restaurante
 * @property description Descripción breve del restaurante
 * @property foodItems Lista de elementos de comida
 * @property drinkItems Lista de bebidas
 * @property extraItems Lista de complementos
 */
@Parcelize
data class Restaurant(
    val id: Int,
    val name: String,
    val description: String,
    val foodItems: List<MenuItem>,
    val drinkItems: List<MenuItem>,
    val extraItems: List<MenuItem>
) : Parcelable {

    /**
     * Función para obtener todos los elementos del menú
     * @return Lista con todos los elementos del restaurante
     */
    fun getAllMenuItems(): List<MenuItem> {
        return foodItems + drinkItems + extraItems
    }

    /**
     * Función para buscar elementos por nombre
     * @param query Texto a buscar
     * @return Lista filtrada de elementos
     */
    fun searchMenuItems(query: String): List<MenuItem> {
        return getAllMenuItems().filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.description.contains(query, ignoreCase = true)
        }
    }
}