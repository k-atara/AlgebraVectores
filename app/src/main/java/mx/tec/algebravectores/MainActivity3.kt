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

class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        setSupportActionBar(findViewById(R.id.toolbar))

        val k = intent.getStringExtra("param1").toString().toInt()
        val r = intent.getStringExtra("param2").toString().toInt()

        //Log.e("Values", m)
        val listaVectores = arrayListOf<EditText>()

        val constraintLayout = findViewById(R.id.constraintLayout) as ConstraintLayout
        val cont = 100F
        var id = 0
        var vv=0F
        for(item in 1..r) {
            for (item1 in 1..k) {
                val bot = EditText(this@MainActivity3)
                bot.inputType = InputType.TYPE_CLASS_NUMBER

                bot.layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )
                bot.translationX = 150F + cont * (item1 + 1)
                bot.translationY = 200F+vv
                bot.id = id
                Log.e("ID", bot.id.toString())
                //Log.e("Values", item1.toString())
                constraintLayout.addView(bot)
                id+=1
                listaVectores.add(bot)
                //Log.e("Lista", listaVectores.get(0).id.toString())
            }
            vv+=100F
        }
        var resultado = ""
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            if(k>r){
                resultado="Es linealmente dependiente porque el número de vectores es mayor a Rn entonces k>n = "+k.toString()+">"+r.toString()
            }
            if(k<r){
                resultado="Es linealmente dependiente porque en el conjunto de vectores hay un vector cero"
            }
            Snackbar.make(view, resultado, Snackbar.LENGTH_LONG)
                .setAction("Action"){
                    val i = Intent(this@MainActivity3, MainActivity2::class.java)
                    i.putExtra("param1",  k.toString())
                    i.putExtra("param2",  r.toString())
                    startActivity(i)
                }.show()
        }
    }
}