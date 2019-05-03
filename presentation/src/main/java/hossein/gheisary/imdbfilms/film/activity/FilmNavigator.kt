package hossein.gheisary.imdbfilms.film.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import hossein.gheisary.imdbfilms.R
import hossein.gheisary.imdbfilms.film.details.FilmDetailsFragment
import hossein.gheisary.imdbfilms.film.list.FilmListFragment

class FilmNavigator (activity: AppCompatActivity) {
    private var fragmentManager: FragmentManager = activity.supportFragmentManager

    fun navigateToFilmListFragment(param: String) {
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left,R.anim.slide_out_right)
                .replace(R.id.film_fragment_container, FilmListFragment.newInstance(param))
                .addToBackStack("FilmListFragment")
                .commit()
    }

    fun navigateToFilmDetailFragment(id: String) {
        fragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left,R.anim.slide_out_right)
            .add(R.id.film_fragment_container, FilmDetailsFragment.newInstance(id))
            .addToBackStack("FilmDetailsFragment")
            .commit()
    }


}