package com.exseed.health.ui.repository_activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.exseed.health.databinding.ActivityRepositioryBinding
import com.exseed.health.ui.main_activity.model.Items
import com.exseed.health.utils.Utils.format
import com.exseed.health.utils.Utils.parseDateTime
import com.exseed.health.utils.Utils.setText
import com.exseed.health.utils.setImage
import java.util.*

class RepositoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepositioryBinding
    private lateinit var data: Items
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositioryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this@RepositoryActivity
        //use toolbar(defined in the xml file of this activity) as an action bar
        setSupportActionBar(binding.myToolbar)
        //disable the title show on the actionbar (myToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false);
        //enable back button on the actionbar (myToolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        //enable display of back button on the actionbar (myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //get parcelable list which is passed in the intent
        data = intent.getParcelableExtra<Items>("clickedRepository")!!
        //set the data of the list on the screen
        setData()
    }

    private fun setData() {

        // TreeMap to find the appropriate suffix
        val suffixes: NavigableMap<Long, String> = TreeMap()
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "G");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "P");
        suffixes.put(1_000_000_000_000_000_000L, "E");

        with(binding) {
            context.setImage(imageview, data?.owner.avatar_url)
            setText(title, data.full_name)
            setText(language, data.language)
            setText(pullRequest, format(data.forks, suffixes))
            setText(issues, format(data.open_issues, suffixes))
            setText(commits, format(data.commits, suffixes))
            setText(lastRelase, parseDateTime(data.lastRelase))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home ->
                onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}