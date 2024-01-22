package Modelo

class Parque constructor(_nombre : String, _extension : Float, _tipo:String, _proteccion:Boolean, _accecibilidad: String){
    var nombre : String;
    var extension : Float;
    var tipo : String;
    var proteccion : Boolean ;
    var accecibilidad : String;

    init {

        println("Init de Parque")
        nombre = _nombre
        extension = _extension
        tipo = _tipo
        proteccion = _proteccion
        accecibilidad = _accecibilidad
    }


    override fun toString(): String {
        return "Parque(nombre='$nombre', extension=$extension, tipo='$tipo', proteccion=$proteccion, acceciblidad='$accecibilidad')"
    }


}