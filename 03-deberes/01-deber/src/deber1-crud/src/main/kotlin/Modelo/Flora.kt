package Modelo

class Flora constructor(_nombre: String, _tipo: String, _color: String, _altura: Float, _enExtincion:Boolean){

    var nombre : String;
    var tipo : String;
    var color : String;
    var altura : Float;
    var enExtincion : Boolean;

    init {
        this.nombre= _nombre
        this.tipo = _tipo
        this.color= _color
        this.altura= _altura
        this.enExtincion = _enExtincion

    }

    override fun toString(): String {
        return "Flora(nombre='$nombre', tipo='$tipo', color='$color', altura=$altura, enExtincion=$enExtincion)"
    }


}