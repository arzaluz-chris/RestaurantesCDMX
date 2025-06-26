/*
 * RestaurantesCentroCDMX - Adaptador para elementos del menú
 *
 * Esta clase maneja la visualización de elementos del menú en un RecyclerView
 * e implementa funcionalidad de filtrado.
 *
 */
package com.example.restaurantescdmx.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantescdmx.R
import com.example.restaurantescdmx.databinding.ItemMenuItemBinding
import com.example.restaurantescdmx.models.MenuCategory
import com.example.restaurantescdmx.models.MenuItem

/**
 * Adaptador para mostrar elementos del menú en un RecyclerView
 * Implementa Filterable para permitir búsquedas
 */
class MenuItemAdapter(
    private val onItemClick: (MenuItem) -> Unit
) : ListAdapter<MenuItem, MenuItemAdapter.MenuItemViewHolder>(MenuItemDiffCallback()), Filterable {

    // Lista completa de elementos para el filtrado
    private var originalList: List<MenuItem> = emptyList()
    private var filteredList: List<MenuItem> = emptyList()

    /**
     * ViewHolder para cada elemento del menú
     */
    inner class MenuItemViewHolder(
        private val binding: ItemMenuItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            // Configurar click listener
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }
        }

        /**
         * Vincula los datos del elemento del menú con las vistas
         */
        fun bind(menuItem: MenuItem) {
            binding.apply {
                textViewItemName.text = menuItem.name
                textViewItemDescription.text = menuItem.getShortDescription()
                textViewPrice.text = menuItem.getFormattedPrice()

                // Configurar icono según la categoría
                val iconRes = when (menuItem.category) {
                    MenuCategory.FOOD -> R.drawable.ic_food
                    MenuCategory.DRINK -> R.drawable.ic_food // En un proyecto real, tendrías ic_drink
                    MenuCategory.EXTRA -> R.drawable.ic_food // En un proyecto real, tendrías ic_extra
                }
                imageViewMenuItem.setImageResource(iconRes)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val binding = ItemMenuItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MenuItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    /**
     * Actualiza la lista de elementos y mantiene la referencia para filtrado
     */
    fun updateList(newList: List<MenuItem>) {
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
                    originalList.filter { menuItem ->
                        menuItem.name.contains(query, ignoreCase = true) ||
                                menuItem.description.contains(query, ignoreCase = true)
                    }
                }

                return FilterResults().apply {
                    values = filteredList
                    count = filteredList.size
                }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = (results?.values as? List<MenuItem>) ?: emptyList()
                submitList(filteredList)
            }
        }
    }

    /**
     * DiffCallback para optimizar las actualizaciones del RecyclerView
     */
    private class MenuItemDiffCallback : DiffUtil.ItemCallback<MenuItem>() {
        override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem == newItem
        }
    }
}