package com.example.memoria.adapters

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.memoria.adapters.GridAdapter.Status.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class GridAdapter(
    private val context: Context,
    private val gameCols: Int,
    private val gameRows: Int,
    topic: String
) : BaseAdapter() {

    private val pictureArray: ArrayList<String> = ArrayList()
    private val pictureCollection: String = topic
    private val gameResources: Resources = context.resources

    private enum class Status {
        OPEN, CLOSE, DELETE
    }

    private val cardsStatus: ArrayList<Status> = ArrayList()

    private fun createGameField() {
        pictureArray.clear()

        for (i in 0 until gameCols * gameRows / 2) {
            pictureArray.add(pictureCollection + i.toString())
            pictureArray.add(pictureCollection + i.toString())
        }

        pictureArray.shuffle()
    }

    private fun closeCells() {
        cardsStatus.clear()
        for (i in 0 until gameCols * gameRows)
            cardsStatus.add(CLOSE)
    }

    override fun getCount(): Int = gameCols * gameRows
    override fun getItem(position: Int): Any? = null
    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: ImageView =
            if (convertView == null) ImageView(context)
            else convertView as ImageView

        when (cardsStatus[position]) {
            OPEN -> {
                val drawableId: Int = gameResources.getIdentifier(
                    pictureArray[position],
                    "drawable",
                    context.packageName
                )
                view.setImageResource(drawableId)
            }
            CLOSE -> view.setImageResource(
                context.resources.getIdentifier(
                    "close",
                    "drawable",
                    context.packageName
                )
            )
            DELETE -> view.visibility = View.GONE
        }
        return view
    }

    init {
        createGameField()
        closeCells()
    }

    /*Game logic*/
    fun checkOpenCells() {
        if (cardsStatus.indexOf(OPEN) > -1 && cardsStatus.lastIndexOf(OPEN) > -1){
            val firstImage: Int = cardsStatus.indexOf(OPEN)
            val secondImage: Int = cardsStatus.lastIndexOf(OPEN)
            if (firstImage == secondImage) return
            if (pictureArray[firstImage] == pictureArray[secondImage]) {
                cardsStatus[firstImage] = DELETE
                cardsStatus[secondImage] = DELETE
                GlobalScope.launch {
                    delay(1000)
                    launch(Dispatchers.Main) {
                        notifyDataSetChanged()
                    }
                }
            } else {
                cardsStatus[firstImage] = CLOSE
                cardsStatus[secondImage] = CLOSE
                GlobalScope.launch {
                    delay(1000)
                    launch(Dispatchers.Main) {
                        notifyDataSetChanged()
                    }
                }
            }
        }
        return
    }

    fun openCell(position: Int) {
        if (cardsStatus[position] !== DELETE)
            cardsStatus[position] = OPEN
        notifyDataSetChanged()
        return
    }

    fun isGameOver(): Boolean =
        cardsStatus.indexOf(CLOSE) < 0

}