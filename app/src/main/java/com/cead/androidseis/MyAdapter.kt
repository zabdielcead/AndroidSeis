package com.cead.androidseis

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class  MyAdapter(var context: Context ?,var layout: Int ?, var names: List<String> ?) : BaseAdapter(){



    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        // View holder pattern
        var holder: ViewHolder ? = null

        //copiamos la vista
        // convertview son los elementos que aparecen en la pantalla
        var vista :View ? = convertView

        if(convertView == null) {
            //inflammos la vista con nuestro layout
            var layoutInflater : LayoutInflater =  LayoutInflater.from(context)
            vista = layoutInflater.inflate(layout!!,null)

            holder = ViewHolder()

            //Refernciamos el elemento a modifficar y lo rellenamos
            holder.nameTextView = vista!!.findViewById(R.id.txtUser)

            vista!!.tag = holder

        }else {
            holder = convertView!!.tag as ViewHolder?
        }








        //nos traemos el valor actual dependiente de la posicion
        var currentName: String = names!!.get(position)

        holder!!.nameTextView!!.text = currentName

        //devolvemos la vista inflada y modificada con nuestros datos
        return vista
    }

    override fun getItem(position: Int): Any? {
        return names!!.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position!!.toLong()
    }

    override fun getCount(): Int {
        return names!!.size
    }


    class ViewHolder{
        var  nameTextView: TextView ? = null

    }

}