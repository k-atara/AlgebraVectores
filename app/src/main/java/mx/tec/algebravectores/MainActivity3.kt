package mx.tec.algebravectores

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        setSupportActionBar(findViewById(R.id.toolbar))

        //Matriz de ejemplo de como se guarda en memoria la matriz
        val matriz = arrayOf(
            doubleArrayOf( 1.0, 2.0, -1.0, -4.0, 0.0),
            doubleArrayOf( 2.0, 3.0, -1.0, -11.0, 0.0),
            doubleArrayOf(-2.0, 0.0, -3.0,  22.0, 0.0)
        )

        var k = intent.getStringExtra("param1").toString().toInt() //variable que guarda la cantidad de vectores a ingresar.
        val r = intent.getStringExtra("param2").toString().toInt() //variable para tomar el valor de Rn
        k+=1

        val listaVectores = arrayListOf<EditText>() //lista de inputs
        val listadoble = Array(r) { DoubleArray(k) } //matriz de vectores
        val constraintLayout = findViewById(R.id.constraintLayout) as ConstraintLayout
        val cont = 120F
        var id = 0
        var vv=0F

        //Los siguientes for anidados son para generar los inputs para escribir los vectores.
        for(item in 1..r) {
            for (item1 in 1..k) {
                val bot = EditText(this@MainActivity3)
                bot.inputType = InputType.TYPE_CLASS_DATETIME+InputType.TYPE_NUMBER_FLAG_SIGNED + InputType.TYPE_NUMBER_FLAG_DECIMAL + InputType.TYPE_CLASS_NUMBER + InputType.TYPE_DATETIME_VARIATION_TIME

                bot.layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )
                bot.translationX = 40F + cont * (item1 + 1)
                bot.translationY = 400F+vv
                bot.layoutParams.width = 100
                bot.id = id
                Log.e("ID", bot.id.toString())
                constraintLayout.addView(bot)
                id+=1
                listaVectores.add(bot)
            }
            vv+=100F
        }
        var resultado = ""
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->

        if(k-1<=r) {
            var tamanoMatriz = 0
            //for para guardar en una matriz tipo de dato Double, los datos que se ingresaron en los inputs. Para posteriormente usar operaciones.
            for (item in 0..r - 1) {
                for (item1 in 0..k - 1) {

                    //Condición para aceptar las fracciones
                    if (listaVectores.get(tamanoMatriz).text.contains("/")) {

                        val opera = listaVectores.get(tamanoMatriz).text.split("/")

                        Log.e("OPERA", opera.toString())
                        Log.e("PRIMER OPERA", opera[0].toString())
                        Log.e("SEGUNDO OPERA", opera[1].toString())
                        var resultado = 0.00
                        resultado = opera[0].toString().toDouble() / opera[1].toString().toDouble()
                        resultado.toString().toDouble()
                        Log.e("Resultado", resultado.toString())
                        listadoble[item][item1] = resultado

                    } else {

                        listadoble[item][item1] = listaVectores.get(tamanoMatriz).text.toString().toDouble()

                    }
                    tamanoMatriz++

                }
            }

            for (r in 0 until listadoble.size) {
                for (c in 0 until listadoble[0].size) {
                    if (listadoble[r][c] == -0.0) listadoble[r][c] =
                        0.0  // get rid of negative zeros
                    print("${"% 6.2f".format(listadoble[r][c])}  ")
                }
                println()
            }
            //Mandamos a llamar el metodo para reducir la matriz y le enviamos la matriz original
            toReducedRowEchelonForm(listadoble)

            for (r in 0 until listadoble.size) {
                for (c in 0 until listadoble[0].size) {
                    if (listadoble[r][c] == -0.0) listadoble[r][c] =
                        0.0  // get rid of negative zeros
                    print("${"% 6.2f".format(listadoble[r][c])}  ")
                }
                println()
            }

            var suma1 = 0.0
            var suma2 = 0.0
            for (i in 0 until listadoble.size) {
                for (n in 0 until listadoble[0].size) {
                    suma1 = suma1 + listadoble[i][n]
                }
            }
            for (i in 0 until listadoble[0].size - 1) {
                suma2 = suma2 + listadoble[i][i]
            }

            Log.e("K", (k - 1).toString())
            Log.e("Suma matriz", suma1.toString())
            Log.e("Suma matriz", suma2.toString())

            //Condición para determinar si es independiente
            if (suma1 == k - 1.toDouble() && suma2 == k - 1.toDouble()) {
                resultado = "Es linealmente independiente"
            } else {
                resultado = "Es linealmente dependiente"
            }
            }else{
             resultado="Es linealmente dependiente porque el número de vectores es mayor a Rn"
            }
            //Funcionalidad para cuando le das click al botón para que te indique si tu matriz es linealmente independiente o independiente, y te regresa al inicio.
            Snackbar.make(view, resultado, Snackbar.LENGTH_LONG)
                .setAction("Back"){
                    val i = Intent(this@MainActivity3, MainActivity::class.java)
                    i.flags= Intent. FLAG_ACTIVITY_CLEAR_TASK or
                            Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(i)
                }.show()
        }
    }

    //Función que realizará las operaciones para llevar la matriz a la reducida.
   private fun toReducedRowEchelonForm(arr:Array<DoubleArray>) {
        var lead = 0
        val rowCount = arr.size //toma el tamaño de las filas
       val colCount = arr[0].size //toma el tamaño del primer vector que son las columnas

        for (r in 0 until rowCount) { //desde 0 hasta cada fila recorre la matriz
            if (colCount <= lead) return //se regresa si es menor igual a lead
            var i = r //variable que lleva el control del for

            while (arr[i][lead] == 0.0) {
                i++
                if (rowCount == i) {
                    i = r
                    lead++
                    if (colCount == lead) return
                }
            }

            val temp = arr[i]
            arr[i] = arr[r]
            arr[r] = temp

            //Multiplicación
            if (arr[r][lead] != 0.0) {
                val div = arr[r][lead]
                for (j in 0 until colCount) arr[r][j] /= div
            }
            //División
            for (k in 0 until rowCount) {
                if (k != r) {
                    val mult = arr[k][lead]
                    for (j in 0 until colCount) arr[k][j] -= arr[r][j] * mult
                }
            }

            lead++
        }
   }
}

