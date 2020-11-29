package org.csuf.cpsc411.simplehttpclient

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

open class CustomActivity : AppCompatActivity() {
    lateinit var cList : MutableList<Claim>
    lateinit var cService : ClaimService
    var refreshStatus : Int = 0

    @SuppressLint("SetTextI18n")
    fun refreshScreen(cObj : Claim) {
        Log.d("Claim Service", "Refreshing the Screen. ")
        val statusView : TextView = findViewById(R.id.refresh_status)
        Log.d("refreshScreen","${refreshStatus}")
        when (refreshStatus) {
            0 -> {
                statusView.text = "Waiting for server response..."
            }
            1 -> {
                statusView.text = "Claim ${cObj.title} has been created"
            }
            2 -> {
                statusView.text = "Claim ${cObj.title} failed to be created"
            }
        }
        Log.d("refreshScreen", "${cService.currentIndx}, ${cService.claimList.count()}")
    }

    fun refreshStatusTrue() {
        refreshStatus = 1
    }
    fun refreshStatusFalse() {
        refreshStatus = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val fldRowGenerator = ObjDetailScreenGenerator(this)
        //val colView = fldRowGenerator.generate()
        //setContentView(colView)
        setContentView(R.layout.main_activity)
        val bView: Button = findViewById(R.id.add_button)
        val titleView : EditText = findViewById(R.id.claim_title)
        val dateView : EditText = findViewById(R.id.claim_date)

        bView.setOnClickListener {
            Log.d("Detailed Activity ", "Add button clicked!")

            val claimTitle : String = titleView.text.toString()
            val claimDate : String = dateView.text.toString()
            cService.claimList.add(Claim(claimTitle,claimDate))
            val cObj = cService.next()
            cService.addClaim(cObj)
            titleView.text.clear()
            dateView.text.clear()
            refreshScreen(cObj)
        }
        cService = ClaimService.getInstance(this)
        val pos = intent.getIntExtra("ElementId", 0)
        Log.d("Detailed Activity ", "Display ${pos} Claim object")
        if (cService.claimList.count() != 0) {
            val cObj = cService.fetchAt(pos)
            refreshScreen(cObj)
        }
        else {
            cService.claimList.add(Claim("empty","empty"))
            val cObj = cService.fetchAt(0)
            refreshScreen(cObj)
        }
        //cService.getAll()
    }
}

