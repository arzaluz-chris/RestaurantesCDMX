
package com.example.restaurantescdmx.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.restaurantescdmx.fragments.DrinksFragment
import com.example.restaurantescdmx.fragments.ExtrasFragment
import com.example.restaurantescdmx.fragments.FoodFragment
import com.example.restaurantescdmx.models.Restaurant

/**
 * Adaptador para ViewPager2 que maneja las pestañas del menú
 * Crea fragmentos para cada categoría del menú
 */
class MenuCategoryPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val restaurant: Restaurant
) : FragmentStateAdapter(fragmentActivity) {

    companion object {
        const val TAB_COUNT = 3
        const val FOOD_TAB = 0
        const val DRINKS_TAB = 1
        const val EXTRAS_TAB = 2
    }

    /**
     * Retorna el número total de pestañas
     */
    override fun getItemCount(): Int = TAB_COUNT

    /**
     * Crea el fragmento correspondiente para cada posición
     */
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            FOOD_TAB -> FoodFragment.newInstance(restaurant)
            DRINKS_TAB -> DrinksFragment.newInstance(restaurant)
            EXTRAS_TAB -> ExtrasFragment.newInstance(restaurant)
            else -> throw IllegalArgumentException("Posición de fragmento inválida: $position")
        }
    }

    /**
     * Obtiene el título de la pestaña para una posición dada
     */
    fun getTabTitle(position: Int): String {
        return when (position) {
            FOOD_TAB -> "Comida"
            DRINKS_TAB -> "Bebidas"
            EXTRAS_TAB -> "Complementos"
            else -> "Pestaña $position"
        }
    }
}