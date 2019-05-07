package com.cead.androidseis

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*

class MainActivity : AppCompatActivity() {


    // LISTVIEW
    // para hacer el menu y elk directorio menu es click derecho en res -> Android Resource File -> nombre y tipo menu
    var listView: ListView ? = null
    var count: Int = 0
    var listNames:  ArrayList<String> ?  = null
    var myAdapter: MyAdapter ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listViewOne)

        //datos a mostrar
         listNames = arrayListOf(
            "JUAN",
            "PEDRO",
            "CARLOS"
        )

        // 1 metodos
        //adaptador la forma visual a mostrar los datos
        //var adapter : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, listNames)


        //establecemos el adaptador con nuestro  List View
        /*      listView!!.adapter = adapter


        }*/
        // 2 metodos
        // enlazamos con nuestro adaptador personalizado

         myAdapter = MyAdapter(this@MainActivity, R.layout.list_item, listNames)
        listView!!.adapter = myAdapter

        listView!!.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(
                    this@MainActivity,
                    "item position $position la persona es: ${listNames!![position]} ",
                    Toast.LENGTH_LONG
                ).show()

                var intent:Intent = Intent(this@MainActivity, GridActivity::class.java)
                startActivity(intent)
            }
        }


        registerForContextMenu(listView)  //para que se escuchen las pulsaciones de los nombres y salga un popup
    }

    // inflamos el layout del menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.action_bar_menu, menu)
        return true
    }


    //manejamos eventos click en el meni de opciones
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item!!.itemId) {
            R.id.addItem ->{
                    listNames!!.add("Added n " + (++count))  // añadir nuevo nombre
                    myAdapter!!.notifyDataSetChanged()   // notifica al adapatador y lo refresque
                return true
            } else -> {
                return super.onOptionsItemSelected(item)
            }

        }


    }

    // inflamos el layout del context menu es el popup al pulsar la figura
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        var inflater : MenuInflater = menuInflater


        var info: AdapterView.AdapterContextMenuInfo = menuInfo as AdapterView.AdapterContextMenuInfo
        menu!!.setHeaderTitle(listNames!![info.position])
        inflater.inflate(R.menu.context_menu, menu)
    }


    //manejamos eventos click en el context menu
    override fun onContextItemSelected(item: MenuItem?): Boolean {

        var info: AdapterView.AdapterContextMenuInfo = item!!.menuInfo as AdapterView.AdapterContextMenuInfo

        when(item!!.itemId){
            R.id.deleteItem -> {

                listNames!!.removeAt(info.position)  // borramos nombre  var  info -> nos dara la posicion que ha sido clickeado de nuestra lista del adapter
                myAdapter!!.notifyDataSetChanged()   // notifica al adapatador y lo refresque
                return true

            } else -> {
                return super.onContextItemSelected(item)
             }

        }
    }
}


