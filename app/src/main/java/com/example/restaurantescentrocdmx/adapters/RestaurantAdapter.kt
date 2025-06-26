/*
 * RestaurantesCentroCDMX - Adaptador para la lista de restaurantes
 *
 * Esta clase maneja la visualización de restaurantes en un RecyclerView
 * e implementa funcionalidad de filtrado y menú contextual.
 */
package com.example.restaurantescdmx.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantescdmx.databinding.ItemRestaurantBinding
import com.example.restaurantescdmx.models.Restaurant

/**
 * Adaptador para mostrar la lista de restaurantes en un RecyclerView
 * Implementa Filterable para permitir búsquedas
 */
class RestaurantAdapter(
    private val onItemClick: (Restaurant) -> Unit,
    private val onContextMenuClick: (View, Restaurant) -> Unit
) : ListAdapter<Restaurant, RestaurantAdapter.RestaurantViewHolder>(RestaurantDiffCallback()), Filterable {

    // Lista completa de restaurantes para el filtrado
    private var originalList: List<Restaurant> = emptyList()
    private var filteredList: List<Restaurant> = emptyList()

    /**
     * ViewHolder para cada elemento de restaurante
     */
    inner class RestaurantViewHolder(
        private val binding: ItemRestaurantBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            // Configurar click listener para el elemento completo
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }

            // Configurar long click listener para menú contextual
            binding.root.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onContextMenuClick(binding.root, getItem(position))
                    true
                } else {
                    false
                }
            }

            // Configurar click en el botón de más opciones
            binding.imageViewMore.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onContextMenuClick(it, getItem(position))
                }
            }
        }

        /**
         * Vincula los datos del restaurante con las vistas
         */
        fun bind(restaurant: Restaurant) {
            binding.apply {
                textViewRestaurantName.text = restaurant.name
                textViewRestaurantDescription.text = restaurant.description

                // Configurar icono según el tipo de restaurante
                // En una implementación más avanzada, podrías tener iconos específicos
                imageViewRestaurant.setImageResource(
                    com.example.restaurantescdmx.R.drawable.ic_restaurant
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding = ItemRestaurantBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * Actualiza la lista de restaurantes y mantiene la referencia para filtrado
     */
    fun updateList(newList: List<Restaurant>) {
        originalList = newList.toList()
        filteredList = newList.toList()
        submitList(filteredList)
    }

    /**
     * Implementación del filtro para búsquedas
     */
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.trim() ?: ""

                filteredList = if (query.isEmpty()) {
                    originalList
                } else {
                    originalList.filter { restaurant ->
                        restaurant.name.contains(query, ignoreCase = true) ||
                                restaurant.description.contains(query, ignoreCase = true)
                    }
                }

                return FilterResults().apply {
                    values = filteredList
                    count = filteredList.size
                }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = (results?.values as? List<Restaurant>) ?: emptyList()
                submitList(filteredList)
            }
        }
    }

    /**
     * DiffCallback para optimizar las actualizaciones del RecyclerView
     */
    private class RestaurantDiffCallback : DiffUtil.ItemCallback<Restaurant>() {
        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem == newItem
        }
    }
}