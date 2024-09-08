package com.example.customdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity(), MyBottomSheetFragment.BottomSheetListener {
    private lateinit var showAlertDialogButton: Button
    private lateinit var showBottomSheetButton: Button
    private lateinit var showCustomBottomSheetButton: Button
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        showAlertDialogButton = findViewById(R.id.showAlertDialogButton)
        showBottomSheetButton = findViewById(R.id.showBottomSheetButton)
        showCustomBottomSheetButton = findViewById(R.id.showCustomBottomSheetButton)
        textView = findViewById(R.id.textView)

        showAlertDialogButton.setOnClickListener {
            showCustomAlertDialog()
        }

        showBottomSheetButton.setOnClickListener {
            showBottomSheet()
        }

        showCustomBottomSheetButton.setOnClickListener {
            showCustomBottomSheet()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun showCustomAlertDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)

        val dialogBuilder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Custom Dialog")
            .create()

        val dialogEditText = dialogView.findViewById<EditText>(R.id.etDialog)
        val dialogButton = dialogView.findViewById<Button>(R.id.closeButton)

        dialogButton.setOnClickListener {
            val dialogText = dialogEditText.text.toString()
            textView.text = dialogText
            dialogBuilder.dismiss()
        }
        dialogBuilder.show()
    }

    private fun showBottomSheet() {
        val bottomSheetView = layoutInflater.inflate(R.layout.botttom_sheet_layout, null)

        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(bottomSheetView)

        val sheetItemOne = bottomSheetView.findViewById<TextView>(R.id.sheetItemOne)
        val sheetItemTwo = bottomSheetView.findViewById<TextView>(R.id.sheetItemTwo)

        sheetItemOne.setOnClickListener {
            Toast.makeText(this, "Item one selected...", Toast.LENGTH_SHORT).show()
            bottomSheetDialog.dismiss()
        }

        sheetItemTwo.setOnClickListener {
            Toast.makeText(this, "Item two selected...", Toast.LENGTH_SHORT).show()
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }

    private fun showCustomBottomSheet() {
        val customBottomSheetFragment = MyBottomSheetFragment()
        customBottomSheetFragment.setBottomSheetListener(this)
        customBottomSheetFragment.show(supportFragmentManager, customBottomSheetFragment.tag)
    }

    override fun onItemOneClicked() {
        Toast.makeText(this, "Custom one clicked...", Toast.LENGTH_SHORT).show()
    }

    override fun onItemTwoClicked() {
        Toast.makeText(this, "Custom two clicked...", Toast.LENGTH_SHORT).show()
    }
}