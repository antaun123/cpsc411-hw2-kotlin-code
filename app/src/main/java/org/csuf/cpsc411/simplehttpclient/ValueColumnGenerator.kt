package org.csuf.cpsc411.simplehttpclient

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout

class ValueColumnGenerator(val ctx : Context) {
    fun generate() : LinearLayout {
        val layoutObj = LinearLayout(ctx)
        val lParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutObj.layoutParams = lParams
        layoutObj.orientation = LinearLayout.VERTICAL
        layoutObj.setBackgroundColor(Color.BLUE)
        //
        val vParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        vParams.weight = 1.0F
        vParams.bottomMargin = 3
        var value = EditText(ctx)
        value.id = R.id.claim_title_text
        value.setHint("Enter Claim Title")
        value.setTextSize(15F)
        value.setBackgroundColor(Color.WHITE)
        value.setPadding(0,300,100,0)
        layoutObj.addView(value, vParams)
        value = EditText(ctx)
        value.id = R.id.claim_date
        value.setHint("Enter Date")
        value.setPadding(0,30,100,0)
        value.setTextSize(15F)
        value.setBackgroundColor(Color.WHITE)
        layoutObj.addView(value, vParams)
        return layoutObj
    }
}
