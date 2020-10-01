package mx.tec.algebravectores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType.TYPE_CLASS_NUMBER
import android.util.Log
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val m = intent.getStringExtra("param1").toString().toInt()
        val n = intent.getStringExtra("param2").toString().toInt()
        val r = intent.getStringExtra("param3").toString().toInt()

        //Log.e("Values", m)
        val listaVectores = arrayListOf<EditText>()

        val constraintLayout = findViewById(R.id.constraintLayout) as ConstraintLayout
        val cont = 100F
        var id = 0
        var vv=0F
        for(item in 1..m) {

            for (item1 in 1..n) {
                val bot = EditText(this)
                bot.inputType = TYPE_CLASS_NUMBER


                bot.layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )
                bot.translationX = 50F + cont * (item1 + 1)
                bot.translationY = 100F+vv
                bot.id = id
                Log.e("ID", bot.id.toString())
                //Log.e("Values", item1.toString())
                constraintLayout.addView(bot)
                id+=1
                listaVectores.add(bot)
                Log.e("Lista", listaVectores.get(0).id.toString())
            }
            vv+=100F
        }

        val btnCalcular = findViewById<Button>(R.id.btnCalcular)
        btnCalcular.setOnClickListener{
            //Regla 2
            if(m>r){
                Toast.makeText(this@MainActivity2, "Hola!!! Es dependiente", Toast.LENGTH_LONG).show()
            }
            if(m<r){
                Toast.makeText(this@MainActivity2, "Hola!!! Es dependiente", Toast.LENGTH_LONG).show()
            }
        }

    }
}