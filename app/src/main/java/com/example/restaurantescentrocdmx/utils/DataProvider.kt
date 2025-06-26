/*
 * RestaurantesCentroCDMX - Proveedor de datos de muestra
 *
 * Esta clase proporciona datos de ejemplo para restaurantes y sus menús.
 * En una aplicación real, estos datos vendrían de una base de datos o API.
 */
package com.example.restaurantescdmx.utils

import com.example.restaurantescdmx.models.MenuCategory
import com.example.restaurantescdmx.models.MenuItem
import com.example.restaurantescdmx.models.Restaurant

/**
 * Objeto singleton que proporciona datos de muestra para la aplicación
 */
object DataProvider {

    /**
     * Lista de restaurantes con sus menús completos
     */
    fun getRestaurants(): List<Restaurant> {
        return listOf(
            // Restaurante 1: La Casa de Toño
            Restaurant(
                id = 1,
                name = "La Casa de Toño",
                description = "Comida mexicana tradicional y casera",
                foodItems = listOf(
                    MenuItem(1, "Pozole Rojo", "Delicioso pozole tradicional con carne de cerdo, chile guajillo y todos los complementos tradicionales", 85.0, MenuCategory.FOOD, 1),
                    MenuItem(2, "Quesadillas de Flor de Calabaza", "Quesadillas hechas a mano con flor de calabaza fresca y queso Oaxaca", 65.0, MenuCategory.FOOD, 1),
                    MenuItem(3, "Mole Poblano", "Pollo bañado en auténtico mole poblano con más de 20 ingredientes", 95.0, MenuCategory.FOOD, 1),
                    MenuItem(4, "Chiles en Nogada", "Chiles poblanos rellenos de picadillo, bañados en nogada y granada", 120.0, MenuCategory.FOOD, 1),
                    MenuItem(5, "Sopa de Tortilla", "Sopa tradicional con jitomate, chile pasilla, tortilla frita y queso fresco", 55.0, MenuCategory.FOOD, 1)
                ),
                drinkItems = listOf(
                    MenuItem(6, "Agua de Horchata", "Refrescante agua de horchata preparada con arroz, canela y leche condensada", 35.0, MenuCategory.DRINK, 1),
                    MenuItem(7, "Agua de Jamaica", "Agua fresca de jamaica natural, endulzada al gusto", 30.0, MenuCategory.DRINK, 1),
                    MenuItem(8, "Atole de Chocolate", "Caliente atole de chocolate tradicional mexicano", 40.0, MenuCategory.DRINK, 1),
                    MenuItem(9, "Café de Olla", "Café aromático preparado con canela y piloncillo en olla de barro", 35.0, MenuCategory.DRINK, 1),
                    MenuItem(10, "Tepache", "Bebida fermentada de piña con especias tradicionales", 45.0, MenuCategory.DRINK, 1)
                ),
                extraItems = listOf(
                    MenuItem(11, "Frijoles Charros", "Frijoles bayos con chorizo, tocino y chile jalapeño", 45.0, MenuCategory.EXTRA, 1),
                    MenuItem(12, "Arroz Mexicano", "Arroz rojo preparado con jitomate y verduras", 35.0, MenuCategory.EXTRA, 1),
                    MenuItem(13, "Guacamole", "Guacamole fresco preparado al momento con aguacate, jitomate y cebolla", 55.0, MenuCategory.EXTRA, 1),
                    MenuItem(14, "Nopales Asados", "Nopales tiernos asados con cebolla y especias", 40.0, MenuCategory.EXTRA, 1)
                )
            ),

            // Restaurante 2: Tacos El Güero
            Restaurant(
                id = 2,
                name = "Tacos El Güero",
                description = "Los mejores tacos al pastor del centro",
                foodItems = listOf(
                    MenuItem(15, "Tacos al Pastor", "Tacos con carne de cerdo marinada, piña, cebolla y cilantro", 18.0, MenuCategory.FOOD, 2),
                    MenuItem(16, "Tacos de Carnitas", "Tacos de carnitas estilo Michoacán, doraditas y jugosas", 20.0, MenuCategory.FOOD, 2),
                    MenuItem(17, "Tacos de Suadero", "Tacos de suadero doradito con cebolla y cilantro", 22.0, MenuCategory.FOOD, 2),
                    MenuItem(18, "Gringas", "Tortillas de harina con queso, carne al pastor y piña", 35.0, MenuCategory.FOOD, 2),
                    MenuItem(19, "Volcanes", "Tortillas tostadas con frijoles, queso, carne y salsa verde", 30.0, MenuCategory.FOOD, 2),
                    MenuItem(20, "Alambres", "Carne al pastor con pimiento, cebolla, tocino y queso", 85.0, MenuCategory.FOOD, 2)
                ),
                drinkItems = listOf(
                    MenuItem(21, "Agua de Piña", "Agua fresca de piña natural", 25.0, MenuCategory.DRINK, 2),
                    MenuItem(22, "Coca Cola", "Refresco de cola en botella de vidrio", 20.0, MenuCategory.DRINK, 2),
                    MenuItem(23, "Agua Natural", "Agua purificada", 15.0, MenuCategory.DRINK, 2),
                    MenuItem(24, "Cerveza Corona", "Cerveza Corona bien fría", 35.0, MenuCategory.DRINK, 2),
                    MenuItem(25, "Agua de Limón", "Agua fresca de limón con chía", 28.0, MenuCategory.DRINK, 2)
                ),
                extraItems = listOf(
                    MenuItem(26, "Orden de Frijoles", "Frijoles refritos caseros", 25.0, MenuCategory.EXTRA, 2),
                    MenuItem(27, "Cebollitas Cambray", "Cebollitas asadas con sal y limón", 15.0, MenuCategory.EXTRA, 2),
                    MenuItem(28, "Chiles Toreados", "Chiles jalapeños asados con cebolla", 20.0, MenuCategory.EXTRA, 2),
                    MenuItem(29, "Salsa Extra", "Porción extra de salsa verde o roja", 10.0, MenuCategory.EXTRA, 2),
                    MenuItem(30, "Limones", "Limones partidos", 5.0, MenuCategory.EXTRA, 2)
                )
            ),

            // Restaurante 3: Café Central
            Restaurant(
                id = 3,
                name = "Café Central",
                description = "Cafetería y comida internacional",
                foodItems = listOf(
                    MenuItem(31, "Desayuno Americano", "Huevos al gusto, tocino, salchicha, pan tostado y hash browns", 95.0, MenuCategory.FOOD, 3),
                    MenuItem(32, "Chilaquiles Verdes", "Chilaquiles bañados en salsa verde con pollo y crema", 75.0, MenuCategory.FOOD, 3),
                    MenuItem(33, "Ensalada César", "Lechuga romana, aderezo césar, crutones y queso parmesano", 85.0, MenuCategory.FOOD, 3),
                    MenuItem(34, "Sandwich Club", "Sandwich de tres pisos con pollo, tocino, lechuga y jitomate", 90.0, MenuCategory.FOOD, 3),
                    MenuItem(35, "Pasta Alfredo", "Fettuccine en salsa alfredo con pollo y brócoli", 105.0, MenuCategory.FOOD, 3)
                ),
                drinkItems = listOf(
                    MenuItem(36, "Cappuccino", "Café espresso con leche vaporizada y espuma", 45.0, MenuCategory.DRINK, 3),
                    MenuItem(37, "Latte", "Café espresso con leche vaporizada", 42.0, MenuCategory.DRINK, 3),
                    MenuItem(38, "Frappé de Vainilla", "Bebida fría de café con helado de vainilla", 55.0, MenuCategory.DRINK, 3),
                    MenuItem(39, "Jugo de Naranja", "Jugo natural de naranja recién exprimido", 35.0, MenuCategory.DRINK, 3),
                    MenuItem(40, "Smoothie de Fresa", "Smoothie natural de fresa con yogurt", 48.0, MenuCategory.DRINK, 3)
                ),
                extraItems = listOf(
                    MenuItem(41, "Pan Dulce", "Selección de pan dulce mexicano", 15.0, MenuCategory.EXTRA, 3),
                    MenuItem(42, "Muffin de Arándanos", "Muffin casero con arándanos frescos", 35.0, MenuCategory.EXTRA, 3),
                    MenuItem(43, "Porción de Fruta", "Fruta de temporada picada", 30.0, MenuCategory.EXTRA, 3),
                    MenuItem(44, "Papas Francesas", "Papas doradas y crujientes", 40.0, MenuCategory.EXTRA, 3)
                )
            ),

            // Restaurante 4: Don Mario's
            Restaurant(
                id = 4,
                name = "Don Mario's",
                description = "Comida italiana auténtica",
                foodItems = listOf(
                    MenuItem(45, "Pizza Margherita", "Pizza tradicional con salsa de tomate, mozzarella y albahaca fresca", 165.0, MenuCategory.FOOD, 4),
                    MenuItem(46, "Spaghetti Carbonara", "Pasta con panceta, huevo, queso parmesano y pimienta negra", 125.0, MenuCategory.FOOD, 4),
                    MenuItem(47, "Lasagna Bolognesa", "Lasagna tradicional con carne molida, bechamel y queso gratinado", 145.0, MenuCategory.FOOD, 4),
                    MenuItem(48, "Risotto ai Funghi", "Risotto cremoso con hongos porcini y queso parmesano", 135.0, MenuCategory.FOOD, 4),
                    MenuItem(49, "Osso Buco", "Jarrete de ternera braseado con vegetales en salsa de vino", 195.0, MenuCategory.FOOD, 4),
                    MenuItem(50, "Antipasto Italiano", "Selección de carnes frías, quesos y vegetales en vinagreta", 110.0, MenuCategory.FOOD, 4)
                ),
                drinkItems = listOf(
                    MenuItem(51, "Chianti Classico", "Vino tinto italiano de la región de Toscana", 95.0, MenuCategory.DRINK, 4),
                    MenuItem(52, "Espresso", "Café espresso tradicional italiano", 35.0, MenuCategory.DRINK, 4),
                    MenuItem(53, "Limoncello", "Licor italiano de limón", 65.0, MenuCategory.DRINK, 4),
                    MenuItem(54, "Acqua Panna", "Agua mineral italiana natural", 25.0, MenuCategory.DRINK, 4),
                    MenuItem(55, "Prosecco", "Vino espumoso italiano", 85.0, MenuCategory.DRINK, 4)
                ),
                extraItems = listOf(
                    MenuItem(56, "Pan de Ajo", "Pan italiano tostado con mantequilla de ajo y perejil", 45.0, MenuCategory.EXTRA, 4),
                    MenuItem(57, "Bruschetta", "Pan tostado con tomate, albahaca y aceite de oliva", 55.0, MenuCategory.EXTRA, 4),
                    MenuItem(58, "Parmesano Extra", "Porción adicional de queso parmesano", 25.0, MenuCategory.EXTRA, 4),
                    MenuItem(59, "Aceitunas Mixtas", "Selección de aceitunas mediterráneas", 40.0, MenuCategory.EXTRA, 4)
                )
            ),

            // Restaurante 5: El Rincón Veracruzano
            Restaurant(
                id = 5,
                name = "El Rincón Veracruzano",
                description = "Mariscos y comida del Golfo de México",
                foodItems = listOf(
                    MenuItem(60, "Huachinango a la Veracruzana", "Pescado entero en salsa de jitomate con aceitunas y alcaparras", 185.0, MenuCategory.FOOD, 5),
                    MenuItem(61, "Arroz a la Tumbada", "Arroz caldoso con mariscos variados estilo veracruzano", 155.0, MenuCategory.FOOD, 5),
                    MenuItem(62, "Empanadas de Camarón", "Empanadas doradas rellenas de camarón y especias", 85.0, MenuCategory.FOOD, 5),
                    MenuItem(63, "Caldo de Siete Mares", "Sopa de mariscos con pescado, camarón, pulpo y vegetales", 125.0, MenuCategory.FOOD, 5),
                    MenuItem(64, "Jaibas Rellenas", "Jaibas rellenas de su propia carne con especias", 95.0, MenuCategory.FOOD, 5),
                    MenuItem(65, "Filete de Pescado al Mojo de Ajo", "Filete de pescado fresco con salsa de ajo", 115.0, MenuCategory.FOOD, 5)
                ),
                drinkItems = listOf(
                    MenuItem(66, "Agua de Coco", "Agua de coco natural servida en el coco", 40.0, MenuCategory.DRINK, 5),
                    MenuItem(67, "Michelada", "Cerveza preparada con limón, sal, chile y clamato", 45.0, MenuCategory.DRINK, 5),
                    MenuItem(68, "Torito de Cacahuate", "Bebida tradicional veracruzana con cacahuate y ron", 55.0, MenuCategory.DRINK, 5),
                    MenuItem(69, "Agua de Chía con Limón", "Agua fresca con semillas de chía y limón", 35.0, MenuCategory.DRINK, 5),
                    MenuItem(70, "Café Lechero", "Café veracruzano servido con leche caliente", 30.0, MenuCategory.DRINK, 5)
                ),
                extraItems = listOf(
                    MenuItem(71, "Plátanos Machos", "Plátanos fritos acompañamiento tradicional", 35.0, MenuCategory.EXTRA, 5),
                    MenuItem(72, "Frijoles Negros", "Frijoles negros estilo veracruzano", 40.0, MenuCategory.EXTRA, 5),
                    MenuItem(73, "Arroz Blanco", "Arroz blanco al vapor", 30.0, MenuCategory.EXTRA, 5),
                    MenuItem(74, "Tortillas de Maíz", "Tortillas hechas a mano", 15.0, MenuCategory.EXTRA, 5),
                    MenuItem(75, "Chiles Jalapeños en Escabeche", "Chiles jalapeños en vinagre con verduras", 25.0, MenuCategory.EXTRA, 5)
                )
            )
        )
    }

    /**
     * Función para obtener un restaurante por su ID
     * @param id ID del restaurante a buscar
     * @return Restaurante encontrado o null si no existe
     */
    fun getRestaurantById(id: Int): Restaurant? {
        return getRestaurants().find { it.id == id }
    }

    /**
     * Función para buscar restaurantes por nombre
     * @param query Texto a buscar
     * @return Lista filtrada de restaurantes
     */
    fun searchRestaurants(query: String): List<Restaurant> {
        return getRestaurants().filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.description.contains(query, ignoreCase = true)
        }
    }
}