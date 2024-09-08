package com.example.customdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyBottomSheetFragment : BottomSheetDialogFragment() {

    interface BottomSheetListener {
        fun onItemOneClicked()
        fun onItemTwoClicked()
    }

    private var listener: BottomSheetListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.botttom_sheet_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.sheetItemOne).setOnClickListener {
            listener?.onItemOneClicked()
            dismiss()
        }

        view.findViewById<TextView>(R.id.sheetItemTwo).setOnClickListener {
            listener?.onItemTwoClicked()
            dismiss()
        }
    }

    fun setBottomSheetListener(listener: BottomSheetListener) {
        this.listener = listener
    }

}