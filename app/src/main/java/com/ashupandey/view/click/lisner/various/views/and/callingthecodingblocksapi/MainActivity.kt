package com.ashupandey.view.click.lisner.various.views.and.callingthecodingblocksapi

import Data
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashupandey.view.click.lisner.various.views.and.callingthecodingblocksapi.custom.adapter.CustomAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        runBlocking {
         val deff =   async {
             getDatas()
            }
            deff.await()
        }
    }

    private suspend fun getDatas() {
         var retrofitBuilder:Retrofit?=null


        withContext(Dispatchers.Default){
            retrofitBuilder = Retrofit.Builder()
                    .baseUrl("https://reqres.in/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

       val api =  retrofitBuilder?.create(ApiInterface::class.java)
        val mutableList = mutableListOf<Data>()

            coroutineScope {
                  val d1 =     async {
                      getList(api,1,mutableList)
                      }

                  val d2 =    async {
                    getList(api,2,mutableList)
                    }

                d1.await()
                d2.await()
            }



        withContext(Dispatchers.Default){
            recyclerView.adapter = CustomAdapter(mutableList)
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }


    }

    private suspend fun getList(api: ApiInterface?,p:Int,list: MutableList<Data>) {
        try{
            withContext(Dispatchers.IO) {
                list.addAll(api?.getData(p)?.body()?.data!!)
            }
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }
        catch (e:Exception){
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }

    }


}
