package com.example.mycalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycalculator.ui.theme.MyCalculatorTheme
import java.lang.ArithmeticException

class MainActivity : ComponentActivity() {

    private var tvInput:TextView ?= null
    var lastNumeric:Boolean = false
    var lastDot:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
        }

    fun onDigit(view: View)
    {
        //Toast.makeText(this, "S-a apasat o cifra", Toast.LENGTH_LONG).show()
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear (view: View)
    {
        tvInput?.text = ""
    }

    fun onEqual(view: View)
    {
        if (lastNumeric)
        {
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-"))
                {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-"))
                {
                    var splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }
                else if (tvValue.contains("+"))
                {
                    var splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }
                else if (tvValue.contains("*"))
                {
                    var splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }
                else if (tvValue.contains("/"))
                {
                    var splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }
                else if (tvValue.contains("%"))
                {
                    var splitValue = tvValue.split("%")
                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty())
                    {
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() % two.toDouble()).toString())
                }
            }
            catch (e : ArithmeticException)
            {
                e.printStackTrace()
            }
        }
    }

    fun onDecimalPoint(view: View)
    {
        if (lastNumeric && !lastDot)
        {
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onOperator (view: View)
    {
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString()))
            {
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun onDelete (view: View)
    {
        var value = tvInput?.text?.toString()
        value = value?.substring(0, value.length-1)
        tvInput?.text = value
    }

    private fun removeZeroAfterDot(result:String) : String{
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length - 2)

        return value
    }

    private fun isOperatorAdded(value : String) : Boolean
    {
        return if(value.startsWith("-"))
        {
            false
        }
        else
        {
            value.contains("/") ||
                    value.contains("*") ||
                    value.contains("+") ||
                    value.contains("-") ||
                    value.contains("%")
        }
    }
}