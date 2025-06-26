/*
 * RestaurantesCentroCDMX - Fragmento para la categoría de complementos
 *
 * Este fragmento muestra los elementos de complementos de un restaurante
 * y maneja la búsqueda dentro de esta categoría.
 */
package com.example.restaurantescdmx.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantescdmx.MenuItemDetailActivity
import com.example.restaurantescdmx.adapters.MenuItemAdapter
import com.example.restaurantescdmx.databinding.FragmentMenuCategoryBinding
import com.example.restaurantescdmx.models.Restaurant

/**
 * Fragmento que muestra los elementos de complementos del restaurante
 */
class ExtrasFragment : Fragment() {

    private var _binding: FragmentMenuCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var menuItemAdapter: MenuItemAdapter
    private lateinit var restaurant: Restaurant

    companion object {
        private const val ARG_RESTAURANT = "restaurant"

        /**
         * Factory method para crear una nueva instancia del fragmento
         */
        fun newInstance(restaurant: Restaurant): ExtrasFragment {
            return ExtrasFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_RESTAURANT, restaurant)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtener el restaurante de los argumentos
        restaurant = arguments?.getParcelable(ARG_RESTAURANT)
            ?: throw IllegalArgumentException("Restaurant es requerido")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadExtraItems()
    }

    /**
     * Configura el RecyclerView con su adaptador
     */
    private fun setupRecyclerView() {
        menuItemAdapter = MenuItemAdapter { menuItem ->
            // Navegar al detalle del elemento del menú
            val intent = Intent(requireContext(), MenuItemDetailActivity::class.java).apply {
                putExtra(MenuItemDetailActivity.EXTRA_MENU_ITEM, menuItem)
                putExtra(MenuItemDetailActivity.EXTRA_RESTAURANT_NAME, restaurant.name)
            }
            startActivity(intent)
        }

        binding.recyclerViewMenuItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = menuItemAdapter
        }
    }
    /**
     * Carga los elementos de complementos del restaurante
     */
    private fun loadExtraItems() {
        val extraItems = restaurant.extraItems

        if (extraItems.isEmpty()) {
            // Mostrar mensaje de no elementos encontrados
            binding.textViewNoItems.visibility = View.VISIBLE
            binding.recyclerViewMenuItems.visibility = View.GONE
        } else {
            // Mostrar lista de elementos
            binding.textViewNoItems.visibility = View.GONE
            binding.recyclerViewMenuItems.visibility = View.VISIBLE
            menuItemAdapter.updateList(extraItems)
        }
    }

    /**
     * Método público para filtrar elementos (llamado desde la Activity padre)
     */
    fun filterItems(query: String) {
        if (::menuItemAdapter.isInitialized) {
            menuItemAdapter.filter.filter(query)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}