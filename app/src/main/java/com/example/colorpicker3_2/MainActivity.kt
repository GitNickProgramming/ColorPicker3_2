package com.example.colorpicker3_2

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

var colorArray = arrayListOf<String>()

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        seekBar_red.setOnSeekBarChangeListener(this)
        seekBar_green.setOnSeekBarChangeListener(this)
        seekBar_blue.setOnSeekBarChangeListener(this)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setLogo(R.drawable.nickg)
        supportActionBar?.setDisplayUseLogoEnabled(true)


    }


    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        when (seekBar) {
            seekBar_red -> colorR.text = "$progress"
            seekBar_green -> colorG.text = "$progress"
            seekBar_blue -> colorB.text = "$progress"
        }

        var newColor: Int = Color.rgb(
            colorR.text.toString().toInt(),
            colorG.text.toString().toInt(),
            colorB.text.toString().toInt()
        )
        colorSwatch.setBackgroundColor(newColor)


    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val colorFileName = "colors.txt"
        val colorListFragment = supportFragmentManager.findFragmentById(R.id.color_frag) as ColorListFragment
        val colorData = findViewById<EditText>(R.id.colorEditText).text.toString()
        val id = item.itemId
        val file = File(filesDir.absolutePath + "/colors.txt")

        if (id == R.id.save_action && !colorEditText.text.isBlank()) {

            val color = "${colorData.toLowerCase()} ${colorR.text} ${colorG.text} ${colorB.text}\n"
            try {
                if (file.exists()) {
                    println("EXISTS")
                    openFileOutput(
                        colorFileName,
                        Context.MODE_APPEND
                    ).use { it.write(color.toByteArray()) }

                } else {
                    openFileOutput(
                        colorFileName,
                        Context.MODE_PRIVATE
                    ).use { it.write(color.toByteArray()) }
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
            colorEditText.text.clear()
            colorListFragment.getSavedColors()
            colorListFragment.updateColors()
            return true
        } else if (id == R.id.load_action) {
            try {
                colorListFragment.getSavedColors()
                colorListFragment.updateColors()






                Toast.makeText(this, "Loaded", Toast.LENGTH_SHORT).show()

            } catch (e: FileNotFoundException) {
                Toast.makeText(this, "No Existing Colors!", Toast.LENGTH_SHORT).show()
            }




            return true
        } else
            Toast.makeText(this, "No color name entered.", Toast.LENGTH_SHORT).show()
        return super.onOptionsItemSelected(item)

    }

    fun onColorClick(view: View){

        var tempColor = ((view.background as ColorDrawable).color)
        val red: Int = tempColor shr 16 and 0xFF
        val green: Int = tempColor shr 8 and 0xFF
        val blue: Int = tempColor shr 0 and 0xFF
        println("$red $green $blue")
        Toast.makeText(this, "Loaded", Toast.LENGTH_SHORT).show()
        colorR.text = red.toString()
        colorG.text = green.toString()
        colorB.text = blue.toString()
        seekBar_red.progress = red
        seekBar_green.progress = green
        seekBar_blue.progress = blue
        colorSwatch.setBackgroundColor(tempColor)

    }

}