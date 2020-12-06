package com.example.appet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_registro1.*
import kotlinx.android.synthetic.main.activity_registro_mascota.*

class RegistroMascotaActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var spinner: Spinner? = null //creamos una variable para el Spinner
    private var arrayAdapter: ArrayAdapter<String>? = null //Creamos un ArrayAdapter, se utiliza a la hora de pasar el arreglo al Spinner
    private var result: TextView? = null //Esta variable debe ser eliminada, solo se utiliza para mostrar que si se esta seleccionando el departamento


    private lateinit var nombreMascotaRegistro: String //En esta variable se almacenara mas adelante, el departamento seleccionado por el usuario
    private lateinit var tipoMascotaRegistro: String //Esta variable se utiliza para almacenar el valor de la ciudad seleccionada por el usuario
    private lateinit var sexoMascotaRegistro: String //En esta variable se almacenara mas adelante, el departamento seleccionado por el usuario
    private lateinit var razaMascotaRegistro: String //Esta variable se utiliza para almacenar el valor de la ciudad seleccionada por el usuario
    private lateinit var colorPeloMascotaRegistro: String //Esta variable se utiliza para almacenar el valor de la ciudad seleccionada por el usuario
    private lateinit var pesoMascotaRegistro: String //Esta variable se utiliza para almacenar el valor de la ciudad seleccionada por el usuario


    private val itemList = arrayListOf<String>()  //Creamos el Array



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_mascota)
        textSexo.visibility= View.INVISIBLE
        spinnerSexoMascota.visibility= View.INVISIBLE
        textRaza.visibility= View.INVISIBLE
        spinnerRaza.visibility= View.INVISIBLE
        textColorPelo.visibility = View.INVISIBLE
        spinnerColorPelo.visibility = View.INVISIBLE
        textPesoMascota.visibility = View.INVISIBLE
        pesoMascota.visibility = View.INVISIBLE
        buttonNextMascota.visibility = View.INVISIBLE

        itemList.addAll(listOf("Seleccion"))
        itemList.addAll(listOf("Seleccion Colombia"))
        itemList.addAll(listOf("Seleccion España"))


        spinner = findViewById(R.id.spinnerTipoMascota) //Accedemos al Spinner que hicimos en el archivo xml con la ayuda de su id
        result = findViewById(R.id.resultText)//Esta linea debe ser eliminada, solo se utiliza para mostrar que si se esta seleccionando el departamento
        arrayAdapter =ArrayAdapter(applicationContext , android.R.layout.simple_spinner_item , arrayTipoMascota()) //se llama el array que ya creamos y se adapta
        spinner?.adapter = arrayAdapter //Se envia el array que ya hemos adatado al spinner
        spinner?.onItemSelectedListener = this //Se define el spinner


    }


    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(applicationContext,"Nothing Selected", Toast.LENGTH_LONG).show()
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if(parent==spinnerTipoMascota) { //Despues de completar el primer campo
            tipoMascotaRegistro = parent?.getItemAtPosition(position) as String //Obtenemos el tipo de mascota
            println("La seleccion es: $position")
            if(position!=0) { //Si es diferente a la seleccionada por defecto desplegamos la siguiente

                spinner =findViewById(R.id.spinnerSexoMascota) //Accedemos al Spinner que hicimos en el archivo xml con la ayuda de su id
                result =findViewById(R.id.resultText)//Esta linea debe ser eliminada, solo se utiliza para mostrar que si se esta seleccionando el departamento
                arrayAdapter = ArrayAdapter(
                    applicationContext,
                    android.R.layout.simple_spinner_item,
                    arraySexo()
                ) //se llama el array que ya creamos y se adapta
                spinner?.adapter = arrayAdapter //Se envia el array que ya hemos adatado al spinner
                spinner?.onItemSelectedListener = this //Se define el spinner

                textSexo.visibility = View.VISIBLE
                spinnerSexoMascota.visibility = View.VISIBLE
            }
            else{
                textSexo.visibility = View.INVISIBLE
                spinnerSexoMascota.visibility = View.INVISIBLE
                textRaza.visibility = View.INVISIBLE
                spinnerRaza.visibility = View.INVISIBLE
                textColorPelo.visibility = View.INVISIBLE
                spinnerColorPelo.visibility = View.INVISIBLE
                buttonNextMascota.visibility = View.INVISIBLE
            }
        }

        else if(parent==spinnerSexoMascota) {
            sexoMascotaRegistro = parent?.getItemAtPosition(position) as String //Obtenemos el tipo de mascota
            if(position!=0) { //Si es diferente a la seleccionada por defecto desplegamos la siguiente

                spinner =findViewById(R.id.spinnerRaza) //Accedemos al Spinner que hicimos en el archivo xml con la ayuda de su id
                result =findViewById(R.id.resultText)//Esta linea debe ser eliminada, solo se utiliza para mostrar que si se esta seleccionando el departamento
                arrayAdapter = ArrayAdapter(
                    applicationContext,
                    android.R.layout.simple_spinner_item,
                    arrayRaza(tipoMascotaRegistro)
                ) //se llama el array que ya creamos y se adapta
                spinner?.adapter = arrayAdapter //Se envia el array que ya hemos adatado al spinner
                spinner?.onItemSelectedListener = this //Se define el spinner

                textRaza.visibility = View.VISIBLE
                spinnerRaza.visibility = View.VISIBLE
            }
            else{
                textRaza.visibility = View.INVISIBLE
                spinnerRaza.visibility = View.INVISIBLE
                buttonNextMascota.visibility = View.INVISIBLE
                textColorPelo.visibility = View.INVISIBLE
                spinnerColorPelo.visibility = View.INVISIBLE
            }


        } else if(parent==spinnerRaza){
            razaMascotaRegistro = parent?.getItemAtPosition(position) as String //Obtenemos el tipo de mascota
            if(position!=0) { //Si es diferente a la seleccionada por defecto desplegamos la siguiente

                spinner =findViewById(R.id.spinnerColorPelo) //Accedemos al Spinner que hicimos en el archivo xml con la ayuda de su id
                result =findViewById(R.id.resultText)//Esta linea debe ser eliminada, solo se utiliza para mostrar que si se esta seleccionando el departamento
                arrayAdapter = ArrayAdapter(
                    applicationContext,
                    android.R.layout.simple_spinner_item,
                    arrayColor()
                ) //se llama el array que ya creamos y se adapta
                spinner?.adapter = arrayAdapter //Se envia el array que ya hemos adatado al spinner
                spinner?.onItemSelectedListener = this //Se define el spinner

                textColorPelo.visibility = View.VISIBLE
                spinnerColorPelo.visibility = View.VISIBLE
            }
            else{
                textColorPelo.visibility = View.INVISIBLE
                spinnerColorPelo.visibility = View.INVISIBLE
                buttonNextMascota.visibility = View.INVISIBLE
            }

        } else if(parent==spinnerColorPelo) {
            colorPeloMascotaRegistro = parent?.getItemAtPosition(position) as String //Obtenemos el color de la mascota

            if(position!=0) { //Si es diferente a la seleccionada por defecto desplegamos la siguiente

                textColorPelo.visibility = View.VISIBLE
                spinnerColorPelo.visibility = View.VISIBLE
                buttonNextMascota.visibility = View.VISIBLE
            }
            else{
                buttonNextMascota.visibility = View.INVISIBLE
            }

        }



    }



    private fun arrayTipoMascota(): ArrayList<String> {
        val tipoPet = arrayListOf<String>() //Creamos el array de tipos de mascotas
        tipoPet.addAll(listOf("Seleccionar la mascota","Gato","Perro"))
        return tipoPet
    }

    private fun arraySexo(): ArrayList<String> {
        val sexo = arrayListOf<String>() //Creamos el array de sexos de mascota
        sexo.addAll(listOf("Seleccionar el sexo de la mascota","Hembra","Macho"))
        return sexo
    }

    private fun arrayRaza(tipoPet:String?): ArrayList<String> {
        val razas = arrayListOf<String>() //Creamos el array de razas de mascota
        if(tipoPet.equals("Perro")){
            razas.addAll(listOf("Seleccionar Raza","Afador", "Affenpinscher", "Airedale Terrier", "Akita Americano", "Alaskan Husky", "Alaskan Klee Kai",
                "Alaskan Malamute", "American Bulldog", "American Foxhound", "American Staffordshire Terrier",
                "Azawakh", "Barbet", "Basenji", "Basset Hound", "Beagle", "Beagle Harrier", "Beauceron", "Bedlington terrier",
                "Berger Picard", "Bichón Boloñés", "Bichón Frisé", "Bichón Habanero", "Bichón Maltés", "Black and Tan Coonhound",
                "Bloodhound", "Boerboel", "Border Collie", "Border Terrier", "Borzoi", "Boston Terrier", "Bóxer", "Boyero de Berna",
                "Braco Alemán de Pelo Corto", "Braco Alemán de pelo Duro", "Braco Italiano", "Buhund Noruego", "Bulldog Francés",
                "Bulldog Inglés", "Bullmastiff", "Bull Terrier", "Bull Terrier Miniatura", "Canaan dog", "Caniche Mediano", "Caniche Miniatura",
                "Cairn Terrier", "Catahoula Leopard Dog", "Chihuahua", "Chin Japonés", "Chinook", "Chow Chow", "Clumber Spaniel",
                "Cocker Spaniel Americano", "Cocker Spaniel Inglés", "Collie", "Collie barbudo o bearded collie", "Corgi Galés de Cardigan",
                "Corgi Galés de Pembroke", "Cotón de Tuléar", "Criollo", "Curly-Coated Retriever", "Dachshund", "Dálmata", "Dandie Dinmont Terrier",
                "Dingo Australiano", "Dóberman", "Dogo Argentino", "Dogo de Burdeos", "Dutch Shepherd", "Elkhound Noruego", "Field Spaniel",
                "Finnish Spitz", "Flat-Coated Retriever", "Foxhound Inglés", "Fox Terrier", "Fox Terrier Toy", "Galgo Afgano", "Galgo Español",
                "Galgo Italiano", "Glen of Imaal Terrier", "Golden Retriever", "Gran Boyero Suizo", "Gran Danés", "Grifón de Bruselas",
                "Grifón vandeano basset pequeño", "Hamiltonstovare", "Harrier", "Hokkaido Inu", "Husky Siberiano", "Jack Russell Terrier",
                "Japanese Spitz", "Karelian Bear", "Keeshond", "Kerry Blue Terrier", "Komondor", "Korean Jindo", "Kuvasz", "Labradoodle",
                "Labrador Retriever", "Lagotto Romagnolo", "Lakeland Terrier", "Lancashire Heeler", "Leonberger", "Lhasa Apso", "Manchester Terrier",
                "Mastín", "Mastín Afgano", "Mastín Español", "Mastín Italiano", "Mastín Napolitano", "Mastín Tibetano", "Mucuchíes", "Mudi",
                "Münsterländer Grande", "Otterhound", "Ovejero Magallánico", "Papillon", "Parson Russell Terrier", "Pastor Alemán", "Pastor Belga",
                "Pastor Belga Groenendael", "Pastor Belga Laekenois", "Pastor Belga Malinois", "Pastor Belga Tervuerense", "Pastor Blanco Suizo",
                "Pastor Catalán", "Pastor Chiribaya o Pastor Peruano", "Pastor Croata", "Pastor de Anatolia", "Pastor de Briep", "Pastor de Karst",
                "Pastor de las islas Shetland", "Pastor de los Pirineos", "Pastor del Cáucaso", "Pastor de Rusia Meridional", "Pastor Ganadero Australiano",
                "Pastor Garafiano", "Pastor Islandés", "Pastor Lapón", "Pastor Lapón de Suecia", "Pastor Leonés", "Pastor Mallorquín", "Pastor Ovejero Australiano",
                "Pastor Polaco de las Llanuras", "Pastor Polaco de Podhale", "Pastor Portugués", "Pastor Vasco", "Pastor Yugoslavo de Charplanina", "Pequeño Brabazón",
                "Pekinés", "Perdiguero de Burgos", "Perdiguero de Dentre", "Perdiguero Portugués", "Perro de Agua Español", "Perro de Agua Frisón", "Perro de Aguas Portugués",
                "Perro de Canaan", "Perro Crestado Chino", "Perro de Agua Americano", "Perro de Groenlandia", "Perro de Montaña de los Pirineos", "Perro del Atlas",
                "Perro Esquimal Americano", "Perro Esquimal Canadiense", "Perro Hiena", "Perro Lobo Chescolovaco", "Perro Lobo de Saarlos", "Perro Lobo Herreño",
                "Perro Majorero", "Perro sin pelo Mexicano", "Phalène", "Pinscher", "Pinscher Austriaco", "Pinscher Miniatura", "Pit bull terrier americano", "Podenco Andaluz",
                "Podenco Canario", "Podenco Ibicenco", "Podenco Portugués", "Pointer", "Pointer Inglés", "Pomerania", "Porcelain", "Presa Canario", "Pudelpointer",
                "Pug o Carlino", "Puli", "Raad van Beheer", "Rastreadores de Hannover", "Rastreador Montañés de Baviera", "Rata Terrier", "Ratonero Bodeguero Andaluz",
                "Ratonero de Praga", "Ratonero Mallorquín", "Ratonero Valenciano", "Redbone Coonhound", "Retriever de la Bahía de Chesapeake", "Retriever de Pelo Liso",
                "Rottweiler", "Sabueso Alemán", "Sabueso Artesiano", "Sabueso Austriaco Negro y Fuego", "Sabueso Bosnio de Pelo Cerdoso", "Sabueso del Tirol", "Sabueso de Hygen",
                "Sabueso de las Montañas de Montenegro", "Sabueso Eslovaco", "Sabueso Español", "Sabueso Finlandés", "Sabueso Francés Tricolor", "Sabueso Halden",
                "Sabueso Italiano de Pelo Corto", "Sabueso Italiano de Pelo Duro", "Sabueso Noruego", "Sabueso Polaco", "Sabueso Schiller", "Sabueso Serbio", "Sabueso Suizo",
                "Sabueso Tinajero", "Sabueso Tricolor Serbio", "Saluki", "Samoyedo", "San Bernardo", "Schapendoes Neerlandés", "Schipperke", "Schnauzer", "Schnauzer Blanco",
                "Schnauzer Gigante", "Schanauzer Miniatura", "Sealyham Terrier", "Setter Gordon", "Setter Inglés", "Setter Irlandés", "Setter Irlandés Rojo", "Setter Irlandés Rojo y Blanco",
                "Shar Pei", "Shiba Inu", "Shikoku Inu", "Shih Tzu", "Silky Terrier Australiano", "Spaniel Bretón", "Spaniel Continental Enanos", "Spaniel de Agua Irlandés",
                "Spaniel de Pont-Audemer", "Spaniel de Sussex", "Spaniel Francés", "Spaniel japonés", "Spaniel Picardo", "Spitz Alemán", "Spitz de Norrbotten",
                "Spitz Japonés Enano", "Spitz Lobo", "Springer Spaniel Inglés", "Stabyhon (Perdiguero Frisón)", "Staffordshire Bull Terrier", "Tchuvatch Eslovaco",
                "Teckel", "Tejonero Alpino", "Terranova", "Terrier americano sin pelo", "Terrier Australiano", "Terrier Brasileño", "Terrier Cazador Alemán", "Terrier Checo",
                "Terrier de Airedale", "Terrier de Norfolk", "Terrier de Norwich", "Terrier de Skye", "Terrier Escocés", "Terrier Galés", "Terrier Irlendés", "Terrier Japonés",
                "Terrier Ruso Negro", "Terrier Tibetano", "Tosa Inu", "Toy Terrier Ruso", "Vallhund Sueco", "Vizsla", "Volpino Italiano", "West Highland White Terrier",
                "Wheaten Terrier", "Whippet", "Wolfhound Irlendés", "Yorkshire Terrier"))
        }else if (tipoPet.equals("Gato")){
            razas.addAll(listOf("Seleccionar Raza", "Abisinio", "Africano doméstico", "American Curl", "American shorthair", "American wirehair", "Angora turco",
                "Aphrodite-s Giants", "Australian Mist", "Azul ruso", "Bengala", "Bobtail japonés", "Bombay", "Bosque de Noruega",
                "Brazilian Shorthair", "British Shorthair", "Burmés", "Burmilla", "California Spangled", "Ceylon", "Chartreux",
                "Cornish rex", "Cymric", "Deutsch Langhaar", "Devon rex", "Don Sphynx", "Dorado africano", "Europeo común",
                "Gato exótico", "German Rex", "Habana brown", "Himalayo", "Khao Manee", "Korat", "Maine Coon", "Manx",
                "Mau egipcio", "Munchkin", "Ocicat", "Ojos azules", "Oriental", "Oriental de pelo largo", "Persa",
                "Peterbald", "Pixi Bob", "Ragdoll", "Sagrado de Birmania", "Scottish Fold", "Selkirk rex", "Serengeti",
                "Seychellois", "Siamés", "Siamés Moderno", "Siamés Tradicional", "Siberiano", "Snowshoe", "Sphynx",
                "Tonkinés", "Van Turco"))
        }
        return razas
    }

    private fun arrayColor(): ArrayList<String> {
        val coloresAr = arrayListOf<String>() //Creamos el array de colores de mascota
        coloresAr.addAll(listOf("Seleccionar el color del pelo de la mascota","Amarillo","Blanco", "Blanco/Cafe", "Blanco/Gris", "Blanco/Naranja", "Blanco/Negro", "Cafe","Dorada",
            "Gris", "Naranja", "Negro", "Negro/Amarillo", "Negro/Dorado"))
        return coloresAr
    }



}