package com.example.simple_post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var incel: EditText
    lateinit var rv: RecyclerView
    var dat = arrayListOf<celep.dat>()
    lateinit var prog: ProgressBar
    lateinit var tvw: TextView
    lateinit var add: Button
    var apif = APIClient().getClient()?.create(APIInterface::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        CoroutineScope(Dispatchers.IO).launch {
            ref()
        }
        add.setOnClickListener {
            wait(true)
            if(incel.text.isNotEmpty()){
                var a=celep.dat(incel.text.toString())
                CoroutineScope(Dispatchers.IO).launch {
                    if(apif!=null){
                        apif!!.adddat(a)?.enqueue(object : Callback<celep.dat> {
                            override fun onResponse(call: Call<celep.dat>, response: Response<celep.dat>) {
                                wait(false)
                            }

                            override fun onFailure(call: Call<celep.dat>, t: Throwable) {

                                wait(false)
                                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show();
                            }

                        })
                    }
                    ref()
                }
            }
        }


    }
    fun ref(){
        if (apif != null) {
            apif!!.getdat()?.enqueue(object : Callback<List<celep.dat>> {
                override fun onResponse(call: Call<List<celep.dat>>, response: Response<List<celep.dat>>) {
                    dat.clear()
                    for (i in response.body()!!) {
                        dat.add(i)

                    }
                    rv.adapter?.notifyDataSetChanged()
                    wait(false)

                }

                override fun onFailure(call: Call<List<celep.dat>>, t: Throwable) {

                    wait(false)
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show();
                }

            })
        }
    }

    fun init() {
        incel=findViewById(R.id.ed1)
        add=findViewById(R.id.button)
        rv = findViewById(R.id.rvma)
        rv.adapter = RVAdapter(dat,this)
        rv.layoutManager = LinearLayoutManager(this)
        tvw = findViewById(R.id.wait)
        prog = findViewById(R.id.progressBar)
        wait(true)
    }

    fun wait(a: Boolean) {
        if (a) {
            prog.isVisible = true
            tvw.isVisible = true
        } else {
            prog.isVisible = false
            tvw.isVisible = false
        }

    }
}