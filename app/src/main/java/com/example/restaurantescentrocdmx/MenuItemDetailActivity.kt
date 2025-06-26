/*
 * RestaurantesCentroCDMX - Activity de Detalle del Elemento del Menú
 *
 * Esta activity muestra la información completa de un elemento del menú
 * incluyendo descripción detallada, precio y otros datos relevantes.
 */
package com.example.restaurantescdmx

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.restaurantescdmx.databinding.ActivityMenuItemDetailBinding
import com.example.restaurantescdmx.models.MenuCategory
import com.example.restaurantescdmx.models.MenuItem as MenuItemModel

/**
 * Activity que muestra el detalle completo de un elemento del menú
 */
class MenuItemDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuItemDetailBinding
    private lateinit var menuItem: MenuItemModel

    companion object {
        const val EXTRA_MENU_ITEM = "extra_menu_item"
        const val EXTRA_RESTAURANT_NAME = "extra_restaurant_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar View Binding
        binding = ActivityMenuItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener datos del intent
        menuItem = intent.getParcelableExtra(EXTRA_MENU_ITEM)
            ?: throw IllegalArgumentException("MenuItem es requerido")
        val restaurantName = intent.getStringExtra(EXTRA_RESTAURANT_NAME) ?: ""

        setupToolbar()
        displayMenuItemDetails()
    }

    /**
     * Configura el Toolbar personalizado
     */
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.title_item_detail)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    /**
     * Muestra los detalles del elemento del menú en las vistas
     */
    private fun displayMenuItemDetails() {
        binding.apply {
            // Información básica
            textViewItemName.text = menuItem.name
            textViewPrice.text = menuItem.getFormattedPrice()
            textViewDescription.text = menuItem.description

            // Configurar icono según la categoría
            val iconRes = when (menuItem.category) {
                MenuCategory.FOOD -> R.drawable.ic_food
                MenuCategory.DRINK -> R.drawable.ic_food // En un proyecto real, tendrías ic_drink
                MenuCategory.EXTRA -> R.drawable.ic_food // En un proyecto real, tendrías ic_extra
            }
            imageViewItemDetail.setImageResource(iconRes)
        }
    }

    /**
     * Maneja las acciones del menú de opciones
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Botón de regresar
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}