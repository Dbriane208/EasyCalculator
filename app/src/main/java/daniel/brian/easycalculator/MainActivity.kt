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

        val resultTv = findViewById<TextView>(R.id.result_tv)
        resultTv.append((view as Button).text)
        lastNumeric = true

    }
    fun OnAllClear(view : View){

        val resultTv = findViewById<TextView>(R.id.result_tv)
        resultTv.text = ""
        lastNumeric = false
        lastDot = false

    }
    fun OnClear(view : View){

        val resultTv = findViewById<TextView>(R.id.result_tv)
        resultTv.text = resultTv.text.dropLast(1)
        lastNumeric = false
        lastDot = false

    }

    fun OnDecimal(view: View) {

        if (lastNumeric && !lastDot ) {

            val resultTv = findViewById<TextView>(R.id.result_tv)
            resultTv.append(".")

            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual(view : View) {
        if (lastNumeric) {

            val resultTv = findViewById<TextView>(R.id.result_tv)
            var resultValue = resultTv.text.toString()
            var prefix = ""

            try {

                if (resultValue.startsWith("-")) {
                    prefix = "-"
                    resultValue = resultValue.substring(1)

                }

                if (resultValue.contains("-")) {
                    val splitValue = resultValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    resultTv.text = removeZerosAfterDot((one.toDouble() - two.toDouble()).toString())

                } else if (resultValue.contains("+")) {
                    val splitValue = resultValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    resultTv.text = removeZerosAfterDot((one.toDouble() + two.toDouble()).toString())

                } else if (resultValue.contains("÷")) {
                    val splitValue = resultValue.split("÷")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }
                    resultTv.text = removeZerosAfterDot((one.toDouble() / two.toDouble()).toString())

                } else if (resultValue.contains("×")) {
                    val splitValue = resultValue.split("×")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    resultTv.text = removeZerosAfterDot((one.toDouble() * two.toDouble()).toString())
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private fun removeZerosAfterDot(result: String) : String{
        var value = result

        if(result.contains("0")){
            value = result.substring(0,result.length -2)
        }

        return value
    }

    fun onOperator(view: View){

        val resultTv = findViewById<TextView>(R.id.result_tv)
        if(lastNumeric && !isOperatorAdded(resultTv.text.toString())) {
            resultTv.append((view as Button).text)

            lastNumeric = false
            lastDot = false
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



