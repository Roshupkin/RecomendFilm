package android.watch_movie.adapters

import android.bignerdranch.kosmos.R
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.watch_movie.model.Film
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recyclerview_film.view.*

class RandomFilmListAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val TAG = "FilmListAdapter"

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Film>() {

        override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem.filmID == newItem.filmID
        }

        override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return FilmPostViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recyclerview_film,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FilmPostViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Film>) {
        differ.submitList(list)
    }

    class FilmPostViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Film) = with(itemView) {
            itemView.setOnClickListener {
                /*click event*/
                interaction?.onItemSelected(adapterPosition, item)
            }
            itemView.text_description.text = item.description
            itemView.text_rating_kinopoisk.text = "Рейтинг КП ${item.rating}"
            itemView.text_lenhgt.text = "Длина ${item.filmLength}"
            itemView.text_year.text = item.year
            itemView.text_name_title.text = item.nameRu

            /*button share*/
            itemView.layout_button_share.setOnClickListener { }
            /*button reaction*/
            itemView.layout_button_reaction.setOnClickListener { }

            /*toggle event*/
            itemView.button_favorites.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    interaction?.onSaveFavorites(isSave = isChecked, item = item)

                    buttonView.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(context, R.color.colorRedButton)
                    )
                    buttonView.setBackgroundResource(R.drawable.ic_add_favorites_on)
                } else {
                    interaction?.onSaveFavorites(isSave = isChecked, item = item)

                    buttonView.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorWhite))
                    buttonView.setBackgroundResource(R.drawable.ic_add_favorites_off)
                }


            }

            val genres = item.genres



            for (i in 0..(genres?.size?.minus(1) ?: 0)) {

                when (i) {
                    0 -> {
                        /* Log.e(TAG, "${item.nameRu}  $i")*/
                        itemView.text_ganre1.text = genres?.get(i) ?: ""
                        itemView.text_ganre1.visibility = View.VISIBLE
                    }
                    1 -> {
                        /*  Log.e(TAG," ${item.nameRu}  $i")*/
                        itemView.text_ganre2.text = genres?.get(i) ?: ""
                        itemView.text_ganre2.visibility = View.VISIBLE
                    }
                    2 -> {
                        /* Log.e(TAG," ${item.nameRu}  $i")*/
                        itemView.text_ganre3.text = genres?.get(i) ?: ""
                        itemView.text_ganre3.visibility = View.VISIBLE
                    }

                }
            }

            Glide.with(itemView.context)
                .load(item.posterUrl)
                .into(itemView.image_view)


        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Film)
        fun onSaveFavorites(isSave: Boolean, item: Film)
        fun onSetEvaluated(item: Film)
    }
}
