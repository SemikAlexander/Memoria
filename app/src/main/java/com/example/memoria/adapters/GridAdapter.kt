package com.example.memoria.adapters

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView


internal class GridAdapter(
        private val context: Context,
        private val gameCols: Int,
        private val gameRows: Int,
        topic: String
) : BaseAdapter() {

    private val pictureArray : ArrayList<String> = ArrayList()
    private val pictureCollection : String = topic
    private val gameResources : Resources = context.resources

    private enum class Status {
        OPEN, CLOSE, DELETE
    }

    private val cardsStatus : ArrayList<Status> = ArrayList()

    private fun makePictureArray() {
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
            cardsStatus.add(Status.CLOSE)
    }

    override fun getCount(): Int = gameCols * gameRows
    override fun getItem(position: Int): Any? = null
    override fun getItemId(position: Int): Long = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: ImageView =
                if (convertView == null) ImageView(context)
                else convertView as ImageView

        when (cardsStatus[position]){
            Status.OPEN -> {
                val drawableId: Int = gameResources.getIdentifier(
                        pictureArray[position],
                        "drawable",
                        context.packageName
                )
                view.setImageResource(drawableId)
            }
            Status.CLOSE -> view.setImageResource(
                    context.resources.getIdentifier(
                            "close",
                            "drawable",
                            context.packageName
                    )
            )
            Status.DELETE -> view.visibility = View.GONE
        }

        return view
    }

    init {
        makePictureArray()
        closeCells()
    }

    /*Game functions*/
    fun checkOpenCells() {
        val first: Int = cardsStatus.indexOf(Status.OPEN)
        val second: Int = cardsStatus.lastIndexOf(Status.OPEN)
        if (first == second) return
        if (pictureArray[first] == pictureArray[second]) {
            cardsStatus[first] = Status.DELETE
            cardsStatus[second] = Status.DELETE
        } else {
            cardsStatus[first] = Status.CLOSE
            cardsStatus[second] = Status.CLOSE
        }
        return
    }

    fun openCell(position: Int) {
        if (cardsStatus[position] !== Status.DELETE)
            cardsStatus[position] = Status.OPEN
        notifyDataSetChanged()
        return
    }

    fun checkGameOver(): Boolean {
        return cardsStatus.indexOf(Status.CLOSE) < 0
    }
}