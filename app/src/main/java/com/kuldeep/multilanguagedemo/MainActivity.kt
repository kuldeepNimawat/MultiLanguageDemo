package com.kuldeep.multilanguagedemo

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.kuldeep.multilanguagedemo.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocate()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar=supportActionBar
        actionBar!!.title= resources.getString(R.string.app_name)
        binding.langBtn.setOnClickListener{
            showChooseLanguage()
        }
    }

    private fun showChooseLanguage(){

        var listItems= arrayOf("عربي","اردو","русский","हिंदी","English")

        val mBuilder= AlertDialog.Builder(this)
        mBuilder.setTitle("Choose Language")
        mBuilder.setSingleChoiceItems(listItems,-1){ dialog, which->
            if(which==0){
                setLocate("ar")
                recreate()
            }else if(which==1){
                setLocate("ur")
                recreate()
            }else if(which==2){
                setLocate("ru")
                recreate()
            }else if(which==3){
                setLocate("hi")
                recreate()
            }else if(which==4){
                setLocate("en")
                recreate()
            }
            dialog.dismiss()
        }

        val mDialog=mBuilder.create()
        mDialog.show()
    }

    private fun setLocate(lang: String){
        val loc= Locale(lang)
        Locale.setDefault(loc)
        val config= Configuration()
        config.locale=loc
        baseContext.resources.updateConfiguration(config,baseContext.resources.displayMetrics)
        val editor=getSharedPreferences("Setting",Context.MODE_PRIVATE).edit()
        editor.putString("my_lang",lang)
        editor.apply()
    }

    private fun loadLocate(){
        val sharedPreferences=  getSharedPreferences("Setting",Activity.MODE_PRIVATE)
        val language=sharedPreferences.getString("my_lang","")
        setLocate(language!!)
    }
}