package mx.tec.algebravectores

import android.content.Intent
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

        val btnReIniciar = findViewById<Button>(R.id.btnReIniciar)
        btnReIniciar.setOnClickListener{
            val i = Intent(this@MainActivity2, MainActivity::class.java)
            i.flags= Intent. FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }

    }
}