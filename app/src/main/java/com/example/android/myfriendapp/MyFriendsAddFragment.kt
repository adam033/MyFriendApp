package com.example.android.myfriendapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.my_friends_add_fragment.*
import kotlinx.coroutines.GlobalScope
import  kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyFriendsAddFragment : Fragment() {
    companion object {
        fun newInstance() : MyFriendsAddFragment {
            return MyFriendsAddFragment()
        }
    }

    private var namaInput : String = " "
    private var emailInput : String = " "
    private var telpInput : String = " "
    private var alamatInput : String = " "
    private var genderInput : String = " "

    private var db: AppDatabase? = null
    private var myFriendDao: MyFriendDao? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_friends_add_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLocalDB()
        initView()
    }
    private fun initLocalDB(){
        db = AppDatabase.getAppDatabase(activity!!)
        myFriendDao = db?.myFriendDao()
    }

    private fun initView(){
        btnSave.setOnClickListener{
            validasiInput()
        }
        setDataSpinnerGender()

    }

    private fun setDataSpinnerGender(){
        val adapter = ArrayAdapter.createFromResource(activity!!,R.array.gender_list,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGender.adapter = adapter
    }
    private fun validasiInput(){
        namaInput = edtName.text.toString()
        emailInput = edtEmail.text.toString()
        telpInput = edtTelp.text.toString()
        alamatInput = edtAdress.text.toString()
        genderInput = spinnerGender.selectedItem.toString()

        when{
            namaInput.isEmpty() -> edtName.error = "Nama Tidak Boleh Kosong"
            genderInput.equals("Pilih Kelamin") -> tampilToast("Kelamin Harus Dipilih")
            emailInput.isEmpty() -> edtEmail.error = "Email tidak boleh kosong"
            telpInput.isEmpty() -> edtTelp.error = "Telp Tidak Boleh Kosong"
            alamatInput.isEmpty() -> edtAdress.error = "Alamat Tidak Boleh Kosong"

            else -> {
                val teman = MyFriend(nama = namaInput, kelamin = genderInput, email = emailInput, telp = telpInput, alamat = alamatInput)
            }
        }
    }
    private fun tambahDataTeman(teman : MyFriend) : Job {
        return GlobalScope.launch {
            myFriendDao?.tambahTeman(teman)
            (activity as MainActivity).tampilMyFriendsFragment()
        }
    }

    private fun tampilToast(message: String) {
        Toast.makeText(activity!!, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}