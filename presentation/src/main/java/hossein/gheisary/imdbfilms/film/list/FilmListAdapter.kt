package hossein.gheisary.imdbfilms.film.list

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import hossein.gheisary.data.remote.model.SearchResult
import hossein.gheisary.imdbfilms.R
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_film_list.view.*

class FilmListAdapter( val context: Context) : RecyclerView.Adapter<FilmListViewHolder>() {

    private val clickSubject = PublishSubject.create<String>()
    val clickEvent : Observable<String> = clickSubject
    var items = SearchResult(Search = ArrayList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_film_list, parent, false)
        return FilmListViewHolder(view)
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onBindViewHolder(holder: FilmListViewHolder, position: Int) {
        holder.itemFilmListTitleTextView.text = items.Search[position].Title

        Glide.with(context)
            .load(items.Search[position].Poster)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.itemFilmListPosterImageView)

        holder.itemFilmListConstraintLayout.setOnClickListener {
            clickSubject.onNext(items.Search[position].imdbID)
        }
    }

    override fun getItemCount(): Int {
        return items.Search.size
    }

    fun setData(items : SearchResult) {
        this.items = items
    }
}

class FilmListViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val itemFilmListPosterImageView = view.itemFilmListPosterImageView!!
    val itemFilmListTitleTextView = view.itemFilmListTitleTextView!!
    val itemFilmListConstraintLayout = view.itemFilmListConstraintLayout!!
}



