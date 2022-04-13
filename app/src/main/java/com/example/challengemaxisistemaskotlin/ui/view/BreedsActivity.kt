package com.example.challengemaxisistemaskotlin.ui.modelView.view

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challengemaxisistemaskotlin.R
import com.example.challengemaxisistemaskotlin.data.Repository
import com.example.challengemaxisistemaskotlin.databinding.ActivityBreedsBinding
import com.example.challengemaxisistemaskotlin.ui.modelView.BreedsViewModel
import com.example.challengemaxisistemaskotlin.ui.modelView.BreedsViewModelFactory
import com.example.challengemaxisistemaskotlin.ui.view.CustomAdapter

class BreedsActivity : AppCompatActivity() {
    private val adapter = CustomAdapter()
    private lateinit var binding: ActivityBreedsBinding
    private var recyclerView: RecyclerView? = null
    var progressDialog: ProgressDialog? = null
    lateinit var viewModel: BreedsViewModel
    var breeds: ArrayList<String>? =null
    var breed:String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breeds)

        binding = ActivityBreedsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this@BreedsActivity)
        progressDialog!!.setMessage("Cargando sub-razas...")
        progressDialog!!.show()

        recyclerView = findViewById(R.id.customRecyclerView)
        viewModel = ViewModelProvider(this, BreedsViewModelFactory(Repository(get()))).get(BreedsViewModel::class.java)
        binding.customRecyclerView.adapter=adapter


        breed = if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras == null) {
                ""
            } else {
                extras.getString("selectBreed")
            }
        } else {
            savedInstanceState.getSerializable("selectBreed") as String?
        }
        println("La raza seleccionada es $breed")
        if (breed != null) {
            viewModel.getSubbreeds(breed!!)
        }
        setupViewModel()
    }

    fun setupViewModel(){
        viewModel.subbreeds.observe(this) { breeds ->

            if (breeds != null) {
                if (breeds.isEmpty()) {
                    progressDialog?.dismiss()
                    var empty_breeds= ArrayList<String>()
                    var empty_photos= ArrayList<String>()
                    empty_breeds.add("No hay subrazas")
                    empty_photos.add("https://prints.ultracoloringpages.com/f905b86a41352429807917a265044dd9.png")
                    generateDataList(empty_breeds, empty_photos)
                /*Toast.makeText(
                        this@BreedsActivity,
                        "Algo saió mal, seguro puedes volver a intentarlo!",
                        Toast.LENGTH_SHORT
                    ).show()*/
                } else {
                    viewModel.getSubbreedPhoto(breed!!, breeds)
                    //breed?.let { viewModel.getSubbreedPhoto(it,breeds) }
                    viewModel.subBreedsPhoto.observe(this) { photos ->
                        if (photos.isEmpty()) {
                            progressDialog?.dismiss()
                            Toast.makeText(
                                this@BreedsActivity,
                                "Algo saió mal, seguro puedes volver a intentarlo2!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            progressDialog?.dismiss()
                            //Toast.makeText(this@BreedsActivity,"Imagenes listas para mostrar",Toast.LENGTH_SHORT).show();

                            generateDataList(breeds, photos)
                        }
                    }
                }
            }
        }
    }

    fun generateDataList(breedsList: ArrayList<String>?, photoList: ArrayList<String>){
        breeds = breedsList
        adapter.setPosition("subrazas")
        if (breedsList != null) {
            adapter!!.setBreeds(breedsList)
        }
        adapter!!.setPhotoBreed(photoList)
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