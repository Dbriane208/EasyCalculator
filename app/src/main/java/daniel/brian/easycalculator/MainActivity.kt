package daniel.brian.easycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        }
    fun OnDigit(view : View){

        val result_tv = findViewById<TextView>(R.id.result_tv)
        result_tv.append((view as Button).text)
        lastNumeric = true

    }
    fun OnAllClear(view : View){

        val input_tv = findViewById<TextView>(R.id.input_tv)
        input_tv.text = ""
         lastNumeric = false
         lastDot = false


        val result_tv = findViewById<TextView>(R.id.result_tv)
        result_tv.text = ""
        lastNumeric = false
        lastDot = false

    }
    fun OnClear(view : View){

        val result_tv = findViewById<TextView>(R.id.result_tv)
        result_tv.text = result_tv.text.dropLast(1)
        lastNumeric = false
        lastDot = false

    }

    fun OnDecimal(view: View) {

        val result_tv = findViewById<TextView>(R.id.result_tv)
        if (lastNumeric && !lastDot ) {

            result_tv.append(".")

            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator(view: View){
        val result_tv = findViewById<TextView>(R.id.result_tv)
        if(lastNumeric && !isOperatorAdded(result_tv.text.toString())) {
            result_tv.append((view as Button).text)

            lastNumeric = false
            lastDot = false
        }
    }

    fun onEqual(view : View){
        if(lastNumeric) {

            val result_tv = findViewById<TextView>(R.id.result_tv)
            var resultTv = result_tv.text.toString()
            var prefix = ""

            try {

                if(resultTv.startsWith("-")){
                    prefix = "-"
                    resultTv = resultTv.substring(1)
                }

                if(resultTv.contains("-")){
                    val split_value = resultTv.split("-")

                    var one = split_value[0]
                    var two = split_value[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    val result_tv = findViewById<TextView>(R.id.result_tv)
                    result_tv.text = (one.toDouble() - two.toDouble()).toString()
                }

            }catch (e : ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun isOperatorAdded(value : String) : Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("÷") || value.contains("×") ||
            value.contains("-") || value.contains("+")
        }
    }
}



