package hossein.gheisary.imdbfilms.film.list

import android.annotation.SuppressLint
import android.os.Bundle
import javax.inject.Inject
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hossein.gheisary.data.remote.model.Outcome
import hossein.gheisary.data.remote.model.SearchResult
import hossein.gheisary.imdbfilms.R
import hossein.gheisary.imdbfilms.base.BaseFragment
import hossein.gheisary.imdbfilms.utility.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_film_list.*
import hossein.gheisary.imdbfilms.film.activity.FilmNavigator

class FilmListFragment: BaseFragment() {
    companion object {
        private const val ARG_FRAGMENT_INSTANCE = "ARG_FRAGMENT_INSTANCE"
        fun newInstance(someInt: String): FilmListFragment {
            val myFragment = FilmListFragment()
            val args = Bundle()
            args.putString(ARG_FRAGMENT_INSTANCE, someInt)
            myFragment.arguments = args
            return myFragment
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var filmListViewModel: FilmListViewModel
    private lateinit var filmListAdapter: FilmListAdapter
    var items = SearchResult(Search = ArrayList())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(context, R.layout.fragment_film_list, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filmListViewModel = ViewModelProviders.of(this, viewModelFactory).get(FilmListViewModel::class.java)

        initObservers()

        setupList()

        setupListeners()
    }

    private fun setupListeners() {
        filmListSearchIconImageView.setOnClickListener {
            items.Search.clear()
            filmListViewModel.searchFilms(filmListSearchEditText.text.toString(), 1)
        }
    }

    private fun setupList() {
        val linearLayoutManager =  LinearLayoutManager(activity)
        filmListRecyclerView.layoutManager = linearLayoutManager
        filmListAdapter = FilmListAdapter(mActivity)
        filmListAdapter.clickEvent.subscribe { FilmNavigator(mActivity as AppCompatActivity).navigateToFilmDetailFragment(it) }
        filmListRecyclerView.adapter = filmListAdapter

        val scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                filmListViewModel.searchFilms(filmListSearchEditText.text.toString(), page + 1)
            }
        }
        filmListRecyclerView.addOnScrollListener(scrollListener)
    }

    private fun initObservers() {
        filmListViewModel.loginOutcome.observe(this, Observer<Outcome<SearchResult>> { outcome ->
            when (outcome) {
                is Outcome.Progress -> setLoading(outcome.loading)

                is Outcome.Success -> { updateList(outcome.data) }

                is Outcome.Failure -> { Toast.makeText(context,outcome.exception.message, Toast.LENGTH_LONG).show() }
            }
        })
    }

    @SuppressLint("SetTextI18n", "CheckResult")
    private fun updateList(data : SearchResult) {
        items.Search.addAll(data.Search)
        filmListAdapter.setData(items)
        filmListAdapter.notifyDataSetChanged()
    }
}