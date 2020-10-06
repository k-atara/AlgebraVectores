package mx.tec.algebravectores

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.ArrayList

class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        setSupportActionBar(findViewById(R.id.toolbar))

        val matriz = arrayOf(
            doubleArrayOf( 1.0, 2.0, -1.0, -4.0,0.0),
            doubleArrayOf( 2.0, 3.0, -1.0, -11.0,0.0),
            doubleArrayOf(-2.0, 0.0, -3.0,  22.0,0.0)
        )

        toReducedRowEchelonForm(matriz)

        var k = intent.getStringExtra("param1").toString().toInt() //variable que guarda la cantidad de vectores a ingresar.
        val r = intent.getStringExtra("param2").toString().toInt() //variable para tomar el valor de Rn
        k+=1

        val listaVectores = arrayListOf<EditText>()
        val listadoble = Array(r) { DoubleArray(k) }
        val constraintLayout = findViewById(R.id.constraintLayout) as ConstraintLayout
        val cont = 120F
        var id = 0
        var vv=0F

        //Los siguientes for anidados son para generar los inputs para escribir los vectores.
        for(item in 1..r) {
            for (item1 in 1..k) {
                val bot = EditText(this@MainActivity3)
                bot.inputType = InputType.TYPE_NUMBER_FLAG_SIGNED + InputType.TYPE_NUMBER_FLAG_DECIMAL + InputType.TYPE_CLASS_NUMBER


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


            var tamanoMatriz = 0
                //for para guardar en una matriz tipo de dato Double los datos que se ingresaron en los inputs. Para posteriormente usar operaciones.
            for(item in 0..r-1) {
                for (item1 in 0..k-1) {

                    listadoble[item][item1]= listaVectores.get(tamanoMatriz).text.toString().toDouble()
                    tamanoMatriz++
                }
            }

            for (r in 0 until listadoble.size) {
                for (c in 0 until listadoble[0].size) {
                    if (listadoble[r][c] == -0.0) listadoble[r][c] = 0.0  // get rid of negative zeros
                    print("${"% 6.2f".format(listadoble[r][c])}  ")
                }
                println()
            }

            toReducedRowEchelonForm(listadoble)

            for (r in 0 until listadoble.size) {
                for (c in 0 until listadoble[0].size) {
                    if (listadoble[r][c] == -0.0) listadoble[r][c] = 0.0  // get rid of negative zeros
                    print("${"% 6.2f".format(listadoble[r][c])}  ")
                }
                println()
            }

            var suma =0.0

            if(k-1!=r){
                //condición para inidicar si el número de vectores ingresados fue mayor al indicado en Rn
                if(k-1>r){
                    resultado="Es linealmente dependiente porque el número de vectores es mayor a Rn entonces k>n = "+(k-1).toString()+">"+r.toString()
                }
                //condición para inidicar si el número de vectores ingresados fue menor al indicado en Rn
                if(k-1<r){
                    resultado="Es linealmente dependiente porque el conjunto de vectores tiene al menos un vector cero"
                }
                if(k-1==1){
                    if(r==2){
                        if(listadoble[0][0]!=0.0 || listadoble[0][1]!=0.0){
                            resultado="Es linealmente independiente"
                        }else{
                            resultado="Es linealmente dependiente "
                        }
                    }else if(r==3){
                        if(listadoble[0][0]!=0.0 || listadoble[0][1]!=0.0 || listadoble[0][2]!=0.0 ){
                            resultado="Es linealmente independiente"
                        }else{
                            resultado="Es linealmente dependiente "
                        }
                    }

                }
            }else{
                for(i in 0 until listadoble.size){
                    suma = suma + listadoble[i][i]
                }
                if(suma == k-1.toDouble()){
                    resultado="Es linealmente independiente"
                }else{
                    resultado="Es linealmente dependiente"
                }
            }
            //Funcionalidad para cuando le das click al botón para que te indique si tu matriz es linealmente independiente o independiente.
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
        val rowCount = arr.size
       val colCount = arr[0].size
        for (r in 0 until rowCount) {
            if (colCount <= lead) return
            var i = r

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

            if (arr[r][lead] != 0.0) {
                val div = arr[r][lead]
                for (j in 0 until colCount) arr[r][j] /= div
            }

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

