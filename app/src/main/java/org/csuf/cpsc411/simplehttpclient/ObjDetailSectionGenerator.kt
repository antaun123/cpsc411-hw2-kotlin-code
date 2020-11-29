package org.csuf.cpsc411.simplehttpclient


import android.content.Context

import android.view.ViewGroup
import android.widget.LinearLayout

class ObjDetailSectionGenerator(val ctx : Context) {
    lateinit var layoutObj : LinearLayout
    fun generate() : LinearLayout {
        layoutObj = LinearLayout(ctx)
        val lParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutObj.layoutParams = lParams
        layoutObj.orientation = LinearLayout.HORIZONTAL


        val lcParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        val colGenerator = LabelColumnGenerator(ctx)
        var lblView = colGenerator.generate()
        lcParams.weight = 3.0F
        layoutObj.addView(lblView, lcParams)

        val valGenerator = ValueColumnGenerator(ctx)
        val vcParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        lblView = valGenerator.generate()
        //between Label and Value
        vcParams.weight = 5.0F
        layoutObj.addView(lblView, vcParams)

        return layoutObj
    }
}

