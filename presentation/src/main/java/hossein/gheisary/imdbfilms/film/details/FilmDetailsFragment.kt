package hossein.gheisary.imdbfilms.film.details

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import hossein.gheisary.data.remote.model.FilmDetailsResponse
import hossein.gheisary.data.remote.model.Outcome
import hossein.gheisary.imdbfilms.R
import hossein.gheisary.imdbfilms.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_film_details.*
import javax.inject.Inject


class FilmDetailsFragment: BaseFragment(){
    companion object {
        private const val ARG_FRAGMENT_DOING = "ARG_FRAGMENT_DOING"
        fun newInstance(someInt: String): FilmDetailsFragment {
            val myFragment = FilmDetailsFragment()
            val args = Bundle()
            args.putString(ARG_FRAGMENT_DOING, someInt)
            myFragment.arguments = args
            return myFragment
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var filmDetailsViewModel: FilmDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(context, R.layout.fragment_film_details, null)
    }

    @SuppressLint("SetTextI18n", "ResourceType")
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filmId = arguments!!.getString(ARG_FRAGMENT_DOING)

        filmDetailsViewModel = ViewModelProviders.of(this, viewModelFactory).get(FilmDetailsViewModel::class.java)
        initObservers()
        filmDetailsViewModel.getFilmDetails(filmId)
    }

    @SuppressLint("NewApi")
    private fun initObservers() {
        filmDetailsViewModel.activeWorksDetailOutcome.observe(this, Observer<Outcome<FilmDetailsResponse>> { outcome ->
            when (outcome) {
                is Outcome.Progress -> setLoading(outcome.loading)

                is Outcome.Success -> { updateUI(outcome.data) }

                is Outcome.Failure -> {Toast.makeText(context,outcome.exception.message, Toast.LENGTH_LONG).show()}
            }
        })
    }

    @SuppressLint("SetTextI18n", "ResourceType")
    private fun updateUI(data : FilmDetailsResponse) {
        Glide.with(mActivity.applicationContext)
            .load(data.Poster)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(filmDetailsPosterImageView)

        filmDetailsTitleTextView.text = data.Title
    }
}





