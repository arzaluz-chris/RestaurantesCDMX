/*
 * RestaurantesCentroCDMX - Activity Principal
 *
 * Esta activity muestra la lista de restaurantes del centro de la Ciudad de México
 * e implementa funcionalidad de búsqueda y menú contextual.
 */
package com.example.restaurantescdmx

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantescdmx.adapters.MenuCategoryPagerAdapter
import com.example.restaurantescdmx.adapters.RestaurantAdapter
import com.example.restaurantescdmx.databinding.ActivityMainBinding
import com.example.restaurantescdmx.models.Restaurant
import com.example.restaurantescdmx.utils.DataProvider

/**
 * Activity principal que muestra la lista de restaurantes
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var restaurantAdapter: RestaurantAdapter
    private var selectedRestaurant: Restaurant? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        loadRestaurants()
    }

    /**
     * Configura el Toolbar personalizado
     */
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = getString(R.string.title_restaurants)
            setDisplayShowTitleEnabled(true)
        }
    }

    /**
     * Configura el RecyclerView con su adaptador
     */
    private fun setupRecyclerView() {
        restaurantAdapter = RestaurantAdapter(
            onItemClick = { restaurant ->
                // Navegar al detalle del restaurante
                navigateToRestaurantDetail(restaurant)
            },
            onContextMenuClick = { view, restaurant ->
                // Mostrar menú contextual
                selectedRestaurant = restaurant
                registerForContextMenu(view)
                openContextMenu(view)
                unregisterForContextMenu(view)
            }
        )

        binding.recyclerViewRestaurants.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = restaurantAdapter
        }
    }

    /**
     * Carga la lista de restaurantes desde el proveedor de datos
     */
    private fun loadRestaurants() {
        val restaurants = DataProvider.getRestaurants()

        if (restaurants.isEmpty()) {
            // Mostrar mensaje de no resultados
            binding.textViewNoResults.visibility = View.VISIBLE
            binding.recyclerViewRestaurants.visibility = View.GONE
        } else {
            // Mostrar lista de restaurantes
            binding.textViewNoResults.visibility = View.GONE
            binding.recyclerViewRestaurants.visibility = View.VISIBLE
            restaurantAdapter.updateList(restaurants)
        }
    }

    /**
     * Navega al detalle del restaurante
     */
    private fun navigateToRestaurantDetail(restaurant: Restaurant, initialTab: Int = 0) {
        val intent = Intent(this, RestaurantDetailActivity::class.java).apply {
            putExtra(RestaurantDetailActivity.EXTRA_RESTAURANT, restaurant)
            putExtra(RestaurantDetailActivity.EXTRA_INITIAL_TAB, initialTab)
        }
        startActivity(intent)
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
            queryHint = getString(R.string.search_restaurants)

            // Configurar listener para búsqueda en tiempo real
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    // No necesitamos hacer nada aquí, ya que filtramos en tiempo real
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    // Filtrar restaurantes en tiempo real
                    restaurantAdapter.filter.filter(newText ?: "")
                    return true
                }
            })
        }

        return true
    }

    /**
     * Crea el menú contextual para los restaurantes
     */
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
        menu?.setHeaderTitle(selectedRestaurant?.name ?: "Opciones")
    }

    /**
     * Maneja la selección de elementos del menú contextual
     */
    override fun onContextItemSelected(item: MenuItem): Boolean {
        selectedRestaurant?.let { restaurant ->
            when (item.itemId) {
                R.id.context_food -> {
                    navigateToRestaurantDetail(restaurant, MenuCategoryPagerAdapter.FOOD_TAB)
                    return true
                }
                R.id.context_drinks -> {
                    navigateToRestaurantDetail(restaurant, MenuCategoryPagerAdapter.DRINKS_TAB)
                    return true
                }
                R.id.context_extras -> {
                    navigateToRestaurantDetail(restaurant, MenuCategoryPagerAdapter.EXTRAS_TAB)
                    return true
                }
                else -> {
                    // ✅ RAMA ELSE AGREGADA PARA COMPLETAR EL WHEN
                    return super.onContextItemSelected(item)
                }
            }
        }
        return super.onContextItemSelected(item)
    }
}