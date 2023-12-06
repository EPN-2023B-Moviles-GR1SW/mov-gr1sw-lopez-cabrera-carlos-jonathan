import Modelo.Flora
import Modelo.Parque
import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")
    var listaParques : LinkedList<Parque> = LinkedList<Parque>()
    var listaFlora : LinkedList<Flora> = LinkedList<Flora>()

    //Lista de Flora
    listaFlora.addLast(Flora("helecho", "pteridofita", "verde", 1.2f, false))
    listaFlora.addLast(Flora("orquídea", "angiosperma", "morado", 0.3f, true))
    listaFlora.addLast(Flora("rosa", "angiosperma", "rojo", 0.5f, false))
    listaFlora.addLast(Flora("cactus", "angiosperma", "verde", 2.0f, false))
    listaFlora.addLast( Flora("margarita", "angiosperma", "blanco", 0.4f, false))


    //Lista Parque
    listaParques.addLast(Parque("Parque Nacional Yasuní", 9820.0f, "selva tropical", true, "dificil"))
    listaParques.addLast(Parque("Parque Metropolitano Guanguiltagua", 557.0f, "bosque andino", false, "facil"))
    listaParques.addLast(Parque("Parque Nacional Galápagos", 7990.0f, "islas volcánicas", true, "moderado"))
    listaParques.add(Parque("Parque Nacional Cotopaxi", 339.0f, "páramo", true, "moderado"))
    listaParques.addLast(Parque("Parque La Carolina", 64.0f, "urbano", false, "facil"))

    var menuWhen : String = ""
    var parque = Parque("",0.0F,"fals",false,"")
    var flora = Flora("","","",0.0F,false)

    do {
        println("Escoja una opcion\na.Agregar un nuevo objeto\n" +
                "b.Eliminar un objeto\n"+
                "c.Actualizar un objeto\n"+
                "d.Mostrar lista\n"+
                "e.Salir del programa\n")
        menuWhen =  readln()

        when(menuWhen){
            "a" -> {
                println("Seleccione una opcion")
                println("1. Agregar Parque\n2. Agregar Flora")
                val opcion = readln().toInt()
                if (opcion ==1) {
                    println("Ingrese los datos del Parque (nombre, extension (Float), tipo, proteccion (Boolean), accesibilidad):")
                    val datos = readLine()!!.split(',').map { it.trim() }
                        parque.nombre = datos[0]
                        parque.extension = datos[1].toFloat()
                        parque.tipo = datos[2].toString()
                        parque.proteccion = datos[3].toBoolean()
                        parque.accecibilidad = datos[4]
                        listaParques.addLast(parque)
                }else{
                    println("Ingrese los datos del Flora (nombre, tipo, color, altura(Float), Extincion (Boolean)):")
                    val datos = readLine()!!.split(',').map { it.trim() }
                    flora.nombre = datos[0]
                    flora.tipo = datos[1]
                    flora.color = datos[1]
                    flora.altura = datos[3].toFloat()
                    flora.enExtincion = datos[4].toBoolean()
                    listaFlora.addLast(flora)
                }


            }
            "b" -> {
                println("Eliminar")
                println("1. Eliminar Parque\n2. Eliminar Flora")
                val opcion = readln().toInt()
                if (opcion ==1) {
                    println("Ingrese el nombre del parque a borrar:")
                    for(parque in listaParques){
                        println(parque)
                    }

                    val nombre = readln()
                    for(parque in listaParques){
                       if (nombre == parque.nombre){
                           listaParques.remove(parque)
                           println("Parque Eliminado con exito")

                       }else{
                           println("No existe el parque")
                       }
                    }
                }else{
                    println("Ingrese el nombre de la flora a borrar:")
                    for(flora in listaFlora){
                        println(flora)
                    }

                    val nombre = readln()
                    for(flora in listaFlora){
                        if (nombre == flora.nombre){
                            listaFlora.remove(flora)
                            println("Flora Eliminado con exito")

                        }else{
                            println("No existe la flora")
                        }
                    }
                }

            }
            "c" -> {
                println("Actualizar")
                println("Seleccione una opcion")
                println("1. Actualizar accesiblidad Parques\n2. Actualizar altura Flora")
                val opcion = readln().toInt()
                if (opcion ==1) {
                    for(parque in listaParques){
                        println(parque)
                    }
                    println("Ingrese el nombre del parque a actualizar la accesbilidad")
                    val nombre = readln()
                    for(parque in listaParques) {
                        if (nombre == parque.nombre) {
                            val access = readln().toString()
                            parque.accecibilidad = access
                            println("Parque Actualizado con exito")
                        }
                    }


                }else{
                    for(flora in listaFlora){
                        println(flora)
                    }
                    println("Ingrese el nombre de la Flora a actualizar la altura")
                    val nombre = readln()
                    for(flora in listaFlora) {
                        if (nombre == flora.nombre) {
                            val altura = readln().toFloat()
                            flora.altura = altura
                            println("Flora Actualizado con exito")
                        }
                    }
                }
            }
            "d" -> {
                println("Mostrar lista")
                println("Seleccione una opcion")
                println("1. Mostrar Lista Parques\n2. Mostrar Lista Flora")
                val opcion = readln().toInt()
                if (opcion ==1) {
                    for(parque in listaParques){
                        println(parque)
                    }
                }else{
                    for(flora in listaFlora){
                        println(flora)
                    }
                }

            }
            "e" -> {
                println("Salir")
                break
            }
            else -> {
                println("Eleccion incorrecta")
            }
        }
    }while(menuWhen !== "e")

}