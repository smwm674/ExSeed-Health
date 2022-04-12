package com.exseed.health.ui.main_activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.exseed.health.R
import com.exseed.health.databinding.ActivityMainBinding
import com.exseed.health.ui.main_activity.adaptor.RepositoryAdapter
import com.exseed.health.ui.main_activity.model.Items
import com.exseed.health.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RepositoryAdapter.repositoryListAction {
    private lateinit var bindingMainActivity: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMainActivity.root)
        context = this@MainActivity

        //api call to fetch the list of repository when manin activity is loaded
        fetchMatchVideosData("topic:android", 10, 1)
    }

    private fun fetchMatchVideosData(query: String, per_page: Int, page: Int) {
        viewModel.getRepositoriesList(query, per_page, page).observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    it.data!!.items?.let { it1 ->
                        if (it1.size > 0) {
                            //set list in the recyclerView once repository list received from the api call
                            setUpRecyclerView(it1 as ArrayList<Items>)
                        } else {
                            with(bindingMainActivity) {
                                //show "Empty list" message on the screen if list received is empty from the api call
                                txtErrorMessage.setText("${it.message}+/n Empty List!")
                                txtErrorMessage.visibility = View.VISIBLE
                                progessBar.visibility = View.GONE
                            }
                        }
                    }
                    bindingMainActivity.progessBar.visibility = View.GONE
                }
                Resource.Status.ERROR -> {
                    with(bindingMainActivity) {
                        //show error message on screen if any error occur in the api call
                        txtErrorMessage.setText(it.message)
                        txtErrorMessage.visibility = View.VISIBLE
                        progessBar.visibility = View.GONE
                    }
                }
                Resource.Status.LOADING -> {
                    with(bindingMainActivity) {
                        //show progress bar while api call is in the progress
                        progessBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun setUpRecyclerView(list: ArrayList<Items>) {
        val myAdapter = RepositoryAdapter(list, context, this)
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        with(bindingMainActivity) {
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = linearLayoutManager
            recyclerView.adapter = myAdapter
        }
        //enable search functionality after list is shown in the recyclerView
        searchRepository(list, myAdapter)
    }

    private fun searchRepository(data: ArrayList<Items>, myAdapter: RepositoryAdapter) {
        bindingMainActivity.txtSearchRepository.editText?.addTextChangedListener(object :
            TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //show progress bar while search filter is applied on the recyclerView
                bindingMainActivity.progessBar.visibility = View.VISIBLE
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //when search text length is less then 3 then list of all repositories will be displayed
                if (s.isNullOrEmpty() || s.length < 3)
                    filter("${s}", data, myAdapter, false)
                //only search filter will applied when search text length is greater than 3
                if (!s.isNullOrEmpty() && s.length > 3)
                    filter("${s}", data, myAdapter, true)
            }

            override fun afterTextChanged(p0: Editable?) {
                //remove progress bar after search filter is being applied on the recyclerView
                bindingMainActivity.progessBar.visibility = View.GONE
            }
        })
    }

    private fun filter(
        text: String?, //search String
        list: ArrayList<Items>,//list on which which search filter has to be applied
        myAdapter: RepositoryAdapter, // RecyclerView adaptor in which updated list can be shown
        search: Boolean // boolean will check weather to apply a search filter on RecyclerView or not
    ) {
        if (search) {
            val temp: ArrayList<Items> = ArrayList()
            for (d in list) {
                //convert to the lowercase before comparing the search string with repository's attributes(full-name, description)
                if (d.full_name.lowercase().contains(text!!.lowercase()) ||
                    d.description.lowercase().contains(text!!.lowercase())
                ) {
                    temp.add(d)
                }
            }
            //update recyclerView with the filtered list against search string
            myAdapter.updateList(temp)
            bindingMainActivity.txtResults.visibility = View.VISIBLE

            if (temp.size > 1)
                bindingMainActivity.txtResults.text = "${temp.size.toString()} results"
            else
                bindingMainActivity.txtResults.text = "${temp.size.toString()} result"

            if (temp.size == 0)   //when no record found against a search string
                bindingMainActivity.txtErrorMessage.text = getString(R.string.no_record)
        } else {
            //remove the result textview when search is not applied on the recyclerview
            bindingMainActivity.txtResults.visibility = View.GONE
            myAdapter.updateList(list)
        }
    }

    override fun onClick(data: Items) {
        Toast.makeText(context, "Not yet implemented", Toast.LENGTH_SHORT)
    }
}