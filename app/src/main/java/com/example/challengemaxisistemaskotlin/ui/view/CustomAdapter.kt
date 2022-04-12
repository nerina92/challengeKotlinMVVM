package com.example.challengemaxisistemaskotlin.ui.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.challengemaxisistemaskotlin.R
import com.example.challengemaxisistemaskotlin.ui.modelView.view.BreedsActivity
import com.squareup.picasso.Picasso
import java.util.*

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {
    private var photoList= mutableListOf<String>()
    private var breedList= mutableListOf<String>()
    private var context: Context? = null

    fun setBreeds(breeds:List<String>){
        this.breedList=breeds.toMutableList()
    }
    fun setPhotoBreed(photos:List<String>){
        this.photoList=photos.toMutableList()
    }
    fun setContext(context: Context){
        this.context=context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.custom_row, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.txtTitle.text = breedList!![position].toUpperCase(Locale.ROOT)
        try {
            Picasso.get().load(photoList!![position]).into(holder.imgView)
        } catch (ex: NullPointerException) {
            ex.printStackTrace()
        } catch (ex: IndexOutOfBoundsException) {
            ex.printStackTrace()
        }
        if (context!!.javaClass.toString() == "class com.example.maxisistemaschallenge.View.MainActivity") {
            holder.itemView.setOnClickListener { v: View? ->
                val miIntent = Intent(context, BreedsActivity::class.java)
                miIntent.putExtra("selectBreed", breedList!![position])
                context!!.startActivity(miIntent)
            }
        }
    }

    override fun getItemCount(): Int {
        return breedList!!.size
    }

    class CustomViewHolder(val mView: View) : RecyclerView.ViewHolder(
        mView
    ) {
        var imgView: ImageView
        var txtTitle: TextView

        init {
            imgView = mView.findViewById(R.id.image_view)
            txtTitle = mView.findViewById(R.id.tv_title)
        }
    }

   /* fun setFilter(filterBreeds: List<String>?) {
        breedList = filterBreeds
        notifyDataSetChanged()
    }*/
}