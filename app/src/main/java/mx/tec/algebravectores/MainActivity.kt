package mx.tec.algebravectores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arr= arrayOf(
            doubleArrayOf( 1.0, 2.0, -1.0, 0.0),
            doubleArrayOf( 2.0, 3.0, -1.0, 0.0),
            doubleArrayOf(-2.0, 0.0, -3.0,  0.0)
        )

        val btnIniciar = findViewById<Button>(R.id.btnIniciar)
        val spinnerM = findViewById<Spinner>(R.id.spinnerM)
        val spinnerN = findViewById<Spinner>(R.id.spinnerN)
        val spinnerR = findViewById<Spinner>(R.id.spinnerR)

        var datos1= arrayListOf("1", "2", "3", "4", "5", "6")
        var datos2= arrayListOf("1", "2", "3", "4", "5", "6", "7", "8")
        var datos3= arrayListOf("R2", "R3")

        val adaptador1 = ArrayAdapter(this@MainActivity,
            android.R.layout.simple_spinner_dropdown_item, datos1
        )
        val adaptador2 = ArrayAdapter(this@MainActivity,
            android.R.layout.simple_spinner_dropdown_item, datos2
        )
        val adaptador3 = ArrayAdapter(this@MainActivity,
            android.R.layout.simple_spinner_dropdown_item, datos3
        )

        spinnerM.adapter = adaptador1
        spinnerN.adapter = adaptador2
        spinnerR.adapter = adaptador3

        btnIniciar.setOnClickListener{
            var seleccion1 = spinnerM.selectedItem.toString()
            var seleccion2 = spinnerN.selectedItem.toString()
            var seleccion3 = spinnerR.selectedItem.toString()
            var r = ""
            if(seleccion3.equals("R2")){
                r="2"
            }
            if(seleccion3.equals("R3")){
                r="3"
            }
            val i = Intent(this@MainActivity, MainActivity2::class.java)
            i.putExtra("param1",  seleccion1)
            i.putExtra("param2",  seleccion2)
            i.putExtra("param3",  r)
            startActivity(i)
        }
    }
}