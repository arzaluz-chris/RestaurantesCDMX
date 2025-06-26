/*
 * RestaurantesCentroCDMX - Activity de Detalle del Restaurante
 *
 * Esta activity muestra las pestañas con las categorías del menú
 * (Comida, Bebidas, Complementos) usando TabLayout y ViewPager2.
 */
package com.example.restaurantescdmx

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.restaurantescdmx.adapters.MenuCategoryPagerAdapter
import com.example.restaurantescdmx.databinding.ActivityRestaurantDetailBinding
import com.example.restaurantescdmx.fragments.DrinksFragment
import com.example.restaurantescdmx.fragments.ExtrasFragment
import com.example.restaurantescdmx.fragments.FoodFragment
import com.example.restaurantescdmx.models.Restaurant
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Activity que muestra el detalle del restaurante con pestañas para las categorías del menú
 */
class RestaurantDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestaurantDetailBinding
    private lateinit var restaurant: Restaurant
    private lateinit var pagerAdapter: MenuCategoryPagerAdapter

    companion object {
        const val EXTRA_RESTAURANT = "extra_restaurant"
        const val EXTRA_INITIAL_TAB = "extra_initial_tab"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar View Binding
        binding = ActivityRestaurantDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener datos del intent
        restaurant = intent.getParcelableExtra(EXTRA_RESTAURANT)
            ?: throw IllegalArgumentException("Restaurant es requerido")
        val initialTab = intent.getIntExtra(EXTRA_INITIAL_TAB, 0)

        setupToolbar()
        setupViewPager()
        setupTabLayout()

        // Seleccionar pestaña inicial si se especificó
        if (initialTab in 0 until MenuCategoryPagerAdapter.TAB_COUNT) {
            binding.viewPager.setCurrentItem(initialTab, false)
        }
    }

    /**
     * Configura el Toolbar personalizado
     */
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = restaurant.name
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    /**
     * Configura el ViewPager2 con su adaptador
     */
    private fun setupViewPager() {
        pagerAdapter = MenuCategoryPagerAdapter(this, restaurant)
        binding.viewPager.adapter = pagerAdapter
    }

    /**
     * Configura el TabLayout y lo conecta con el ViewPager2
     */
    private fun setupTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = pagerAdapter.getTabTitle(position)
        }.attach()
    }

    /**
     * Crea el menú de opciones en el Toolbar
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        // Configurar SearchView
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.apply {
            queryHint = getString(R.string.search_menu_items)

            // Configurar listener para búsqueda en tiempo real
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    // Filtrar elementos en el fragmento actual
                    filterCurrentFragment(newText ?: "")
                    return true
                }
            })
        }

        return true
    }

    /**
     * Filtra los elementos en el fragmento actualmente visible
     */
    private fun filterCurrentFragment(query: String) {
        val currentFragment = supportFragmentManager.fragments.find { fragment ->
            fragment.isVisible && (fragment is FoodFragment ||
                    fragment is DrinksFragment ||
                    fragment is ExtrasFragment)
        }

        when (currentFragment) {
            is FoodFragment -> currentFragment.filterItems(query)
            is DrinksFragment -> currentFragment.filterItems(query)
            is ExtrasFragment -> currentFragment.filterItems(query)
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