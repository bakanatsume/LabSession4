package com.bipash.labsession4

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var txtName: EditText
    private lateinit var txtAddress : EditText
    private lateinit var txtCalendar : EditText
    private lateinit var txtView : TextView
    private lateinit var spinner : Spinner
    private lateinit var btnSubmit : Button

    private val department = arrayOf("Select your Department","Software", "Hardware", "Mechanical", "Engineer")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtName = findViewById(R.id.txtName)
        txtAddress = findViewById(R.id.txtAddress)
        txtCalendar = findViewById(R.id.txtCalendar)
        spinner = findViewById(R.id.spinner)
        btnSubmit = findViewById(R.id.btnSubmit)
        txtView = findViewById(R.id.txtView)

        btnSubmit.setOnClickListener{
            txtView.text= ("${txtName.text.toString()}\n${txtAddress.text.toString()}\nServed Year is 2\nDeparment is Software")

        }


        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            department
        )

        spinner.adapter = adapter
        spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedDepartment = parent?.getItemAtPosition(position).toString()
                   // Toast.makeText(this@MainActivity, "Selected option is $selectedDepartment", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

        txtCalendar.setOnClickListener{
                loadCalender();
        }
    }
    private fun loadCalender(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, day ->
                txtCalendar.setText("${year}/${month}/${day}")
                var servedYear = calculateTimePeriod(year, month, day)
                //Toast.makeText(this, "${servedYear}", Toast.LENGTH_SHORT).show()
            },
            year-3,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun calculateTimePeriod(year: Int, month: Int, day: Int): String {
        val userInput = Calendar.getInstance()
        val present = Calendar.getInstance()
        userInput[year,month] = day
        var timePeriod = present[Calendar.YEAR]-userInput[Calendar.YEAR]
        if(present[Calendar.DAY_OF_YEAR]<userInput[Calendar.DAY_OF_YEAR]){
            timePeriod -= 1
        }
        val timePeriodInt = timePeriod
        return timePeriodInt.toString()
    }
}