package com.example.challengemaxisistemaskotlin.ui.view

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challengemaxisistemaskotlin.R
import com.example.challengemaxisistemaskotlin.databinding.ActivityMainBinding
import com.example.challengemaxisistemaskotlin.ui.modelView.BreedsViewModel
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import org.koin.androidx.viewmodel.ext.android.viewModel as viewModel


class MainActivity : AppCompatActivity() {
    private val adapter = CustomAdapter()
    private lateinit var binding: ActivityMainBinding
    private var recyclerView: RecyclerView? = null
    var progressDialog: ProgressDialog? = null
    var breeds: List<String>? =null
    //var filterbreeds:kotlin.collections.MutableList<kotlin.String?>? = null

    //USO DE KOIN
    private val viewModel:BreedsViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog = ProgressDialog(this@MainActivity)
        progressDialog!!.setMessage("Cargando razas...")
        progressDialog!!.show()

        recyclerView = findViewById(R.id.customRecyclerView)
       // viewModel = ViewModelProvider(this, BreedsViewModelFactory(Repository(get()))).get(BreedsViewModel::class.java)
        binding.customRecyclerView.adapter=adapter
        viewModel.getBreeds()
        setupViewModel()
    }

     fun setupViewModel (){
        /*Con este método se pueden observar los datos en vivo para actualizar la pantalla de la
        interfaz de usuario cada vez que haya un cambio en los datos de caché.*/

        /*Con este método se pueden observar los datos en vivo para actualizar la pantalla de la
        interfaz de usuario cada vez que haya un cambio en los datos de caché.*/

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
                    viewModel.getBreedPhoto(breeds)
                    viewModel.breedsPhoto.observe(this) { photos ->
                        if (photos.isEmpty()) {
                            progressDialog?.dismiss()
                            Toast.makeText(
                                this@MainActivity,
                                "Algo saió mal, seguro puedes volver a intentarlo2!",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            progressDialog?.dismiss()
                            // Toast.makeText(MainActivity.this,"Imagenes listas para mostrar",Toast.LENGTH_SHORT).show();
                            generateDataList(breeds, photos)
                       }
                    }
                }
            }
        }
    }

    private fun generateDataList(breedsList: ArrayList<String>?, photoList: ArrayList<String>) {
        breeds = breedsList
        adapter.setPosition("razas")
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

/*private operator fun <T, V> ReadWriteProperty<T, V>.getValue(t: T, property: KProperty<V?>): V {
return this.getValue(t,property)
}

private operator fun Any.setValue(mainActivity: MainActivity, property: KProperty<*>, breedsViewModel: BreedsViewModel) {

}

private operator fun Any.getValue(mainActivity: MainActivity, property: KProperty<*>): BreedsViewModel {
return this.getValue(mainActivity,property)
}*/


