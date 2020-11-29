package org.csuf.cpsc411.simplehttpclient

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginTop

class ObjDetailScreenGenerator(val ctx : Context) {
    lateinit var layoutObj : LinearLayout
    lateinit var layoutObj2 : LinearLayout
    fun generate() : LinearLayout {
        // 1. Create a linearLayout object
        layoutObj = LinearLayout(ctx)
        val lParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        layoutObj.layoutParams = lParams
        layoutObj.orientation = LinearLayout.VERTICAL
        layoutObj.setBackgroundColor(Color.WHITE)


        //"Claim Info top text
        var lbParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        lbParams.weight = 1.0F
        var lbl = TextView(ctx)
        lbl.text = "Please Enter Claim Information"
        lbl.setTypeface(Typeface.DEFAULT_BOLD)
        lbl.setTextColor(Color.BLACK)
        lbl.textSize = 15F
        lbl.gravity = Gravity.CENTER
        lbl.setPadding(0,100,0,0)
        lbl.setBackgroundColor(Color.WHITE)
        layoutObj.addView(lbl, lbParams)


        // 2. Add ObjDetailSection
        val fldRowGenerator = ObjDetailSectionGenerator(ctx)
        val colView = fldRowGenerator.generate()
        layoutObj.addView(colView)

        // 3. Add Next Button LinearLayout
        val nLayout = LinearLayout(ctx)
        val nParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        // only applied to height now
        nLayout.layoutParams = nParams
        //nLayout.orientation = LinearLayout.HORIZONTAL
        nLayout.setBackgroundColor(Color.WHITE)
        nLayout.setPadding(0,150,150,0)
        nLayout.gravity = Gravity.RIGHT
        //
        val nButton = Button(ctx)
        nButton.text = "Add"
        nButton.isAllCaps = false
        nButton.setId(R.id.add_btn)
        nButton.setBackgroundColor(Color.LTGRAY)
        val nbParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        //nbParams.gravity = Gravity.RIGHT
        nLayout.addView(nButton, nbParams)
        //
        layoutObj.addView(nLayout, nParams)

        //status and message layout
        val sLayout = LinearLayout(ctx)
        val sParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        // only applied to height now
        sLayout.layoutParams = sParams
        sLayout.setBackgroundColor(Color.WHITE)
        sLayout.orientation = LinearLayout.HORIZONTAL
        //status and status message
        //"Status text"
        lbl = TextView(ctx)
        lbl.text = "Status:"
        lbl.setTypeface(Typeface.DEFAULT_BOLD)
        lbl.setTextColor(Color.BLACK)
        lbl.textSize = 20F
        lbl.setPadding(150,100,0,0)
        lbl.setBackgroundColor(Color.WHITE)
        val ssParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        sLayout.addView(lbl, ssParams)
        var status = TextView(ctx)
        status.text = "waiting for user..."
        status.id = R.id.refresh_status
        status.setTextSize(15F)
        status.setTextColor(Color.BLACK)
        status.setBackgroundColor(Color.WHITE)
        status.setPadding(50,0,0,0)
        sLayout.addView(status, ssParams)
        layoutObj.addView(sLayout,sParams)
        return layoutObj
    }
}