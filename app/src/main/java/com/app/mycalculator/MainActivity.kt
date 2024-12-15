package com.app.mycalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    lateinit var inputText:EditText
    lateinit var outputText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initializeViews()
    }

    private fun initializeViews() {
        inputText = findViewById<EditText>(R.id.etInputText)
        outputText = findViewById<EditText>(R.id.etOutputText)

    }

    fun evaluateExpression(expression: String): Double {
        try {
            val expression = ExpressionBuilder(expression)
                .build()
            return expression.evaluate()
        } catch (e: Exception) {
            println("Error evaluating expression: ${e.message}")
            return Double.NaN
        }
    }

    fun buttonClicked(view: View){
        val button: Button =view as Button
        val buttonText = button.text.toString()
        val oldText = inputText.text.toString()
        if(buttonText.equals("c", ignoreCase = true)) {
            inputText.setText("")
            outputText.setText("")

        }else if(buttonText.equals("D", ignoreCase = true)){
            inputText.setText(oldText.substring(0,oldText.length-1))


        }else if(buttonText.equals("=", ignoreCase = true)) {

            if(oldText.trim().equals("",ignoreCase = true)){
                Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show()
                inputText.setText(outputText.text.toString())
                outputText.setText("")
                return;
            }
            var result = evaluateExpression(oldText)
            outputText.setText(result.toInt().toString())
            inputText.setText("")
        }else if(buttonText.equals("X", ignoreCase = true)) {
           inputText.setText(oldText + "*")
        }else{
            inputText.setText(oldText + buttonText)
        }

    }
}