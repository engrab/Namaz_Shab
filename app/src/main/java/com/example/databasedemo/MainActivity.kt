package com.example.databasedemo

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databasedemo.database.User
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private lateinit var tvTotalName: TextView
    private lateinit var tvIndex: TextView
    private lateinit var tvAddName: TextView
    private lateinit var cvAstgfar: CardView
    private lateinit var cvMakam: CardView
    private lateinit var cvAfwa: CardView
    private lateinit var tvMakam: TextView
    private lateinit var tvAfwa: TextView
    private var count = 0
    private var makam = 0
    private var afwa = 0

    private lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this, UserViewModelFactory(
                (application as App).db
            )
        ).get(UserViewModel::class.java)

        rv = findViewById(R.id.rv)
        tvTotalName = findViewById(R.id.tvTotalName)
        tvIndex = findViewById(R.id.tvIndex)
        cvAstgfar = findViewById(R.id.astgfar)
        cvMakam = findViewById(R.id.cvMakam)
        tvMakam = findViewById(R.id.tvMakam)
        cvAfwa = findViewById(R.id.cvAfwa)
        tvAfwa = findViewById(R.id.tvAfwa)
        tvAddName = findViewById(R.id.tvAddName)

        if (savedInstanceState != null) {
            tvIndex.text = savedInstanceState.getInt("count").toString()
            tvMakam.text = savedInstanceState.getInt("makam").toString()
            tvAfwa.text = savedInstanceState.getInt("afwa").toString()
        } else {
            tvIndex.text = count.toString()
            tvMakam.text = makam.toString()
            tvAfwa.text = afwa.toString()
        }
        cvAfwa.setOnClickListener {
            afwa++
            tvAfwa.text = afwa.toString()
            if (afwa == 30) {
                Toast.makeText(this@MainActivity, "Complete Tasbee", Toast.LENGTH_LONG).show()
                cvAfwa.visibility = View.GONE
            }

        }
        cvMakam.setOnClickListener {
            makam++
            tvMakam.text = makam.toString()
            if (makam == 7) {
                Toast.makeText(this@MainActivity, "Complete Tasbee", Toast.LENGTH_LONG).show()
                cvMakam.visibility = View.GONE
            }

        }
        cvAstgfar.setOnClickListener {
            count++
            tvIndex.text = count.toString()
            if (count == 70) {
                Toast.makeText(this@MainActivity, "Complete Tasbee", Toast.LENGTH_LONG).show()
                cvAstgfar.visibility = View.GONE
            }

        }
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            startActivity(Intent(this, AddNameActivity::class.java))
        }
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)



        viewModel.getAllUser().observe(this) {
            rv.adapter = UserAdapter(this, it)
            tvTotalName.text = it.size.toString()
            if (it.isEmpty()){
                tvAddName.visibility = View.VISIBLE
                rv.visibility = View.GONE
            }else{
                tvAddName.visibility = View.GONE
                rv.visibility = View.VISIBLE
            }
        }

    }



    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("count", count)
        outState.putInt("makam", makam)
        outState.putInt("afwa", afwa)
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Exit App")
            .setMessage("Are you want to exit from App?") // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(android.R.string.yes) { dialog, which ->
               dialog.dismiss()
                super.onBackPressed()
            }

            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
}