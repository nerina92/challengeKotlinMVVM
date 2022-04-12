package com.example.challengemaxisistemaskotlin.ui.modelView.view

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challengemaxisistemaskotlin.R
import com.example.challengemaxisistemaskotlin.data.Repository
import com.example.challengemaxisistemaskotlin.databinding.ActivityMainBinding
import com.example.challengemaxisistemaskotlin.ui.modelView.BreedsViewModel
import com.example.challengemaxisistemaskotlin.ui.modelView.BreedsViewModelFactory
import com.example.challengemaxisistemaskotlin.ui.view.CustomAdapter

class MainActivity : AppCompatActivity() {
    private val adapter = CustomAdapter()
    private lateinit var binding: ActivityMainBinding
    private var recyclerView: RecyclerView? = null
    var progressDialog: ProgressDialog? = null
    var breeds: List<String>? =null
    var filterbreeds:kotlin.collections.MutableList<kotlin.String?>? = null
    lateinit var viewModel:BreedsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this@MainActivity)
        progressDialog!!.setMessage("Cargando razas...")
        progressDialog!!.show()

        recyclerView = findViewById(R.id.customRecyclerView)
        viewModel = ViewModelProvider(this, BreedsViewModelFactory(Repository())).get(BreedsViewModel::class.java)
        binding.customRecyclerView.adapter=adapter
        viewModel.breeds.observe(this, Observer {
            println("onCreate $it")
            if (it != null) {
                adapter.setBreeds(it)
            }
        })
        viewModel.getBreeds()
       // setupViewModel()
    }

     fun setupViewModel (){
        /*Con este método se pueden observar los datos en vivo para actualizar la pantalla de la
        interfaz de usuario cada vez que haya un cambio en los datos de caché.*/

        /*Con este método se pueden observar los datos en vivo para actualizar la pantalla de la
        interfaz de usuario cada vez que haya un cambio en los datos de caché.*/
        val repository = Repository()


         viewModel.getBreeds()
        viewModel.breeds.observe(this) { breeds ->

            if (breeds != null) {
                if (breeds.isEmpty()) {
                    progressDialog?.dismiss()
                    Toast.makeText(
                        this@MainActivity,
                        "Algo saió mal, seguro puedes volver a intentarlo!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    /*viewModel.getBreedPhoto(breeds)
                    viewModel.breedsPhoto.observe(context) { photos ->
                        if (photos.isEmpty()) {
                            progressDialog?.dismiss()
                            Toast.makeText(
                                this@MainActivity,
                                "Algo saió mal, seguro puedes volver a intentarlo2!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {*/
                            progressDialog?.dismiss()
                            // Toast.makeText(MainActivity.this,"Imagenes listas para mostrar",Toast.LENGTH_SHORT).show();

                            generateDataList(breeds/*, photos*/)
                       // }
                    //}
                }
            }
        }

    }



    private fun generateDataList(breedsList: List<String>?/*, photoList: List<String>*/) {
        breeds = breedsList
        //adapter = CustomAdapter()
        if (breedsList != null) {
            //adapter!!.setBreeds(breedsList)
        }
        //adapter!!.setPhotoBreed(photoList)
        adapter!!.setContext(this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.adapter = adapter
        recyclerView!!.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }
}


