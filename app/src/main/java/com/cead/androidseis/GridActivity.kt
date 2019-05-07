package com.cead.androidseis

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_grid.*

class GridActivity : AppCompatActivity() {

    var gridView : GridView ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

        gridView = findViewById(R.id.gridView)

        var listNames: List<String> = arrayListOf(
            "JUAN",
            "PEDRO",
            "CARLOS",
            "PANCHO",
            "PANCHO2",
            "PANCHO3",
            "PANCHO4",
            "PANCHO5",
            "PANCHO6",
            "PANCHO7",
            "PANCHO8",
            "PANCHO9",
            "PANCHO0",
            "SANTIAGO")


        var myAdapter  = MyAdapter(this@GridActivity, R.layout.list_item, listNames)
        gridView!!.adapter = myAdapter

        gridView!!.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@GridActivity,"item position $position la persona es: ${listNames.get(position)} " , Toast.LENGTH_LONG).show()
            }

        }




    }


}
