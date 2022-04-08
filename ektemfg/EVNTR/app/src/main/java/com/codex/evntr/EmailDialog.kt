package com.codex.evntr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class EmailDialog: DialogFragment() {
    private lateinit var items: ArrayList<String>
    lateinit var eventName: TextView
    lateinit var eventButton: Button
    lateinit var emailText: EditText
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        items = arguments?.getStringArrayList(ITEMS) ?: throw IllegalStateException("No args provided")
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.round_corner);
        return inflater.inflate(R.layout.event_signup_popup, container, false)
    }

    companion object {

        private const val ITEMS = "items"

        fun newInstance(
            items: ArrayList<String>,
        ): EmailDialog = EmailDialog().apply {
            arguments = Bundle().apply {
                putStringArrayList(ITEMS, items)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        eventName = view?.findViewById(R.id.email_event_name)!!
        eventButton = view?.findViewById(R.id.email_event_button)!!
        emailText = view?.findViewById(R.id.email_text)!!
        eventName.text = items[0]







        eventButton.setOnClickListener{
            Toast.makeText(context, "Invitasjonen sendt til ${emailText.text} .", Toast.LENGTH_SHORT).show()

            this.dismiss();
        }


    }


}
