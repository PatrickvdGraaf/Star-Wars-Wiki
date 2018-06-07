package nl.graaf.starwarswiki.ui.characters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import nl.graaf.starwarswiki.R
import nl.graaf.starwarswiki.config.inflate

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */
class CharacterAdapter(private val mPresenter: CharacterMVP.Presenter)
    : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = parent.inflate(R.layout.list_character, false)
        view.setOnClickListener({

        })
        return CharacterViewHolder(view, mPresenter)
    }

    override fun getItemCount(): Int {
        return mPresenter.getTotalCharactersCount()
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        mPresenter.onBindViewHolder(holder, position)
    }

    class CharacterViewHolder(view: View, presenter: CharacterMVP.Presenter) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.textview_name)
        val birthTextView: TextView = view.findViewById(R.id.textview_birth)
        val favButton: ImageButton = view.findViewById(R.id.button_fav)

        init {
            view.setOnClickListener({
                presenter.onItemSelected(layoutPosition)
            })
        }
    }
}