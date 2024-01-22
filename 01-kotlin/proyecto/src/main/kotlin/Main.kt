fun main(args: Array<String>) {
    println("Hello World!")

    // SWITCH
    //No existe switch en Kotlin como tal, se aplica When
    val estadoCivitWhen = "C"

    when (estadoCiviIWhen){
        (C) -> {
            println("casado")
        }
        s -> {
            println("soltero")
        }
        else -> {
            println("no se sabe")
        }
    }

    //If chiquito, se aplica if en una linea siempre y cuando no exista otro salto de linea

    val coqueteo = if (estadoCivitWhen == "C" ) "Si" else "No"


    //FUNCIONES
    //palabra reservada fun

    fun funcionChevere(entrada: String ): Unit { //Unit es una funcion void
        //template string: permite usar variables en un string
        println("Tiene esta entrada ${entrada}")

    }


    fun calcularSuetdo(
        sueldo: Double,// Requerido
        tasa:   Double = 12.00, // Opcionat (defecto)
        bonoEspecial: Double? = null, // Opcion null
    ) : Double{
        // Int -> Int? (nullable)
        // String -> String? (nullable)
        // Date -> Date? (nullable)
        bonoEspecial.dec() //muestra advertencia porque puede ser un valor null al que no se aplica funcion

        if (bonoEspecial == null) {
            return sueldo * (100 / tasa)
        }else{
            bonoEspecial.dec() //Como se filtra por el if que no puede llegar a ser null, no da advertencia
            return sueldo * (100/ tasa ) + bonoEspecial}
        }


        //PARAMETROS DE LAS FUNCIONES

        calcularSuetdo(15.0) //Llamo solo al requerido
        calcularSuetdo(15.0, 12.0,13.0) //Llamo a los tres
        //parametro nombrado
        calcularSuetdo(sueldo=15.0, bonoEspecial = 25.0) //Me evito de llamar a un parametro
        calcularSuetdo(sueldo=15.0, 12.0, bonoEspecial = 25.0) //lamo a los tres con dos nombramientos


    //CLASES

    abstract class Numeros(
        //protected public private es el alcance de la propiedad
        //public es por default
        public val numberOne : Int,
        private val numberTwo : Int
    )
    {
        init {
            this.numberOne; this.numberTwo //This es opcional
            numberOne; numberTwo //Sin this es lo mismo
            println("Inicializando")
        }
    }

    class Suma(
        numeroUno: Int,
        numeroDos: Int,
    ):Numeros(numeroUno, numeroDos){ //constructor de la clase padre
        init {
            numeroDos; numeroUno;
        }
    }


}