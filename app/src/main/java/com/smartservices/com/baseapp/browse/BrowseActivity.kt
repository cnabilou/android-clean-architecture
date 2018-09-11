package com.smartservices.com.baseapp.browse

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import base.smartservices.com.presentation.browse.BrowseMinionsContract
import base.smartservices.com.presentation.model.MinionView
import com.smartservices.com.baseapp.R
import com.smartservices.com.baseapp.mapper.MinionMapper
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class BrowseActivity : AppCompatActivity(), BrowseMinionsContract.View {

    @Inject lateinit var onboardingPresenter: BrowseMinionsContract.Presenter
    @Inject lateinit var browseAdapter: BrowseAdapter
    @Inject lateinit var mapper: MinionMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        recycler_browse.layoutManager = LinearLayoutManager(this)
        recycler_browse.adapter = browseAdapter
        recycler_browse.setHasFixedSize(true)
    }

    override fun setPresenter(presenter: BrowseMinionsContract.Presenter) {
        onboardingPresenter = presenter
    }

    override fun onStart() {
        super.onStart()
        onboardingPresenter.start()
    }

    override fun showProgress() {
        progressBar.visibility = View.GONE
    }

    override fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showMinions(minions: List<MinionView>) {
        browseAdapter.minions = minions.map { mapper.mapToViewModel(it) }
        browseAdapter.notifyDataSetChanged()
        recycler_browse.visibility = View.VISIBLE
    }

    override fun hideMinions() {
       recycler_browse.visibility = View.INVISIBLE
    }

    override fun showErrorState() { }

    override fun hideErrorState() { }

    override fun showEmptyState() { }

    override fun hideEmptyState() { }
}
