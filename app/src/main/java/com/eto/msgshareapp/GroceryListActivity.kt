package com.eto.msgshareapp

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GroceryListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<TileDataClass>
    private var isListView = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_groccery_list)

        recyclerView = findViewById(R.id.recycleView)

        isListView = getViewType() // Automatically detect screen size
        prepareDataList()
        setUpAdapter(isListView)
        setUpBackPress()
        setUpChangeView()  // ✅ Don't pass view type
    }

    private fun getViewType(): Boolean {
        val screenWidthDp = resources.displayMetrics.widthPixels / resources.displayMetrics.density
        val isList = screenWidthDp < 700

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = if (isList) {
            LinearLayoutManager(this)
        } else {
            GridLayoutManager(this, 2)
        }

        return isList
    }

    private fun setUpBackPress() {
        val backArrow = findViewById<ImageView>(R.id.backArrow)
        backArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setUpChangeView() {
        val btChangeView = findViewById<ImageView>(R.id.btChangeView)


        btChangeView.setImageResource(
            if (isListView) R.drawable.ic_grid_view else R.drawable.ic_list_view
        )

        btChangeView.setOnClickListener {
            isListView = !isListView


            val newIconRes = if (isListView) {
                R.drawable.ic_grid_view
            } else {
                R.drawable.ic_list_view
            }
            btChangeView.setImageResource(newIconRes)


            recyclerView.layoutManager = if (isListView) {
                LinearLayoutManager(this)
            } else {
                GridLayoutManager(this, 2)
            }


            setUpAdapter(isListView)
        }
    }

    private fun setUpAdapter(isListView: Boolean) {
        val adapter = AdapterClass(isListView)
        recyclerView.adapter = adapter
        adapter.submitList(dataList)
    }

    private fun prepareDataList() {
        dataList = arrayListOf(
            TileDataClass(1, R.drawable.img_cucumber, "Cucumber", "Freshly harvested", 60, 10),
            TileDataClass(2, R.drawable.img_tomato, "Tomato", "Juicy red tomatoes", 50, 15),
            TileDataClass(3, R.drawable.img_watermelon, "Watermelon", "Crunchy and sweet", 40, 5),
            TileDataClass(
                4,
                R.drawable.img_red_chilli,
                "Red Chilli",
                "Spicy, red colored Chilli",
                50,
                15
            ),
            TileDataClass(5, R.drawable.img_carrot, "Carrot", "Crunchy and sweet", 40, 5),
            TileDataClass(6, R.drawable.img_green_chilli, "Green Chilli", "Spicy and hot", 70, 20),
            TileDataClass(7, R.drawable.img_red_capsicum, "Capsicum", "Rich in Vitamin C", 90, 25)
        )
    }
}
