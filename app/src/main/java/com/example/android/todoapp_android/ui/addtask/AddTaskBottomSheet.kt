package com.example.android.todoapp_android.ui.addtask

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.android.todoapp_android.database.MyDataBase
import com.example.android.todoapp_android.database.Task
import com.example.android.todoapp_android.databinding.BottomSheetDialogeBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar

class AddTaskBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: BottomSheetDialogeBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetDialogeBinding.inflate(layoutInflater)
        return binding.root

    }

    var onDismissListener: OnDismissListener? = null

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener?.onDismiss()
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submit.setOnClickListener {
            checkValidate()
            addTask()




        }


        setDate()

                binding.date.setOnClickListener {

            showDatePicker()
        }



    }

    fun validate():Boolean{
        var valid = true
        if (binding.etTitle.text.toString().isNullOrEmpty()){
            binding.etTitle.error = "This field is required"
            valid = false

        }else{
            binding.etTitle.error = null
        }
        return valid
    }

    private fun addTask() {

        if (validate() == false){
            return
        }
        var title = binding.etTitle.text.toString()
        var desc = binding.etDesc.text.toString()
        if (desc.isNullOrEmpty()){
           desc = ""
        }

        MyDataBase.getDatabase(requireContext())
            .tasksDao()
            .insertTask(
                Task(title = title ,
                    description = desc ,
                    date = currentDate.timeInMillis
                ))


        showTaskInsertedDialoge()

    }


    fun showTaskInsertedDialoge(){
        val alertDialog = AlertDialog.Builder(
            requireContext()
        ).setMessage("Task Added..")
         .setPositiveButton("ok"
         ) { dialog, btnId ->
             dialog.dismiss()
             dismiss()
         }.setCancelable(false)
            .show()
    }

    @SuppressLint("SetTextI18n")
    fun setDate(){
        binding.date.text = currentDate.get(Calendar.YEAR).toString() + "/" +
                (currentDate.get(Calendar.MONTH) + 1)  + "/" +
                currentDate.get(Calendar.DAY_OF_MONTH)

    }

    var currentDate = Calendar.getInstance()
    init {
        currentDate.set(Calendar.HOUR , 0)
        currentDate.set(Calendar.MINUTE , 0)
        currentDate.set(Calendar.SECOND , 0)
        currentDate.set(Calendar.MILLISECOND , 0)
    }
    private fun showDatePicker() {

        val datePicker = DatePickerDialog(requireActivity() ,
            DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->

                currentDate.set(Calendar.YEAR , year)
                currentDate.set(Calendar.MONTH , month)
                currentDate.set(Calendar.DAY_OF_MONTH , dayOfMonth)
                setDate()

            } ,
            currentDate.get(Calendar.YEAR) ,
            currentDate.get(Calendar.MONTH) ,
            currentDate.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()

    }

    private fun checkValidate() {




    }

}