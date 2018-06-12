package nl.graaf.starwarswiki.ui.characters

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.annotation.DrawableRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import nl.graaf.starwarswiki.R
import nl.graaf.starwarswiki.databinding.ListCharacterBinding
import nl.graaf.starwarswiki.model.Character

/**
 * Adapter for the list of the posts
 * @property context Context in which the application is running
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 */
class CharacterAdapter(private val context: Context,
                       private val listener: Listener? = null)
    : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {
    enum class SortingType {
        BIRTH, NAME
    }

    private var sortingType = SortingType.NAME

    private val characters = mutableListOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ListCharacterBinding = DataBindingUtil.inflate(inflater,
                R.layout.list_character, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position], listener)
    }

    /**
     * Updates the list of characters of the adapter
     * @param characters the new characters of posts of the adapter
     */
    fun updateCharacters(characters: List<Character>) {
        this.characters.addAll(characters)
        sort()
    }

    @DrawableRes
    fun toggleSort(): Int {
        return when (sortingType) {
            SortingType.NAME -> {
                sortingType = SortingType.BIRTH
                sort()
                R.drawable.ic_sort_name_24dp
            }
            SortingType.BIRTH -> {
                sortingType = CharacterAdapter.SortingType.NAME
                sort()
                R.drawable.ic_birth_24dp
            }
        }
    }

    private fun sort(){
        when (sortingType) {
            SortingType.BIRTH -> sortByBirth()
            SortingType.NAME -> sortByName()
        }
        notifyDataSetChanged()
    }

    private fun sortByName() {
        characters.sortWith(Comparator { o1, o2 ->
            o1.name.compareTo(o2.name)
        })
    }

    private fun sortByBirth() {
        characters.sortWith(Comparator { o1, o2 ->
            if (o1.birthYear == "unknown" && o2.birthYear == "unknown") {
                0
            } else if (o1.birthYear == "unknown" && o2.birthYear != "unknown") {
                1
            } else if (o1.birthYear != "unknown" && o2.birthYear == "unknown") {
                -1
            } else {
                val period1 = o1.getPeriod()
                val period2 = o2.getPeriod()
                if (period1 == "BBY" && period2 == "ABY") {
                    1
                } else if (period1 == "ABY" && period2 == "BBY") {
                    -1
                } else if (period1 == "BBY") {
                    o1.getYear().compareTo(o2.getYear())
                } else {
                    o2.getYear().compareTo(o1.getYear())
                }
            }
        })
    }

    /**
     * The [RecyclerView.ViewHolder] of the adapter.
     * @property binding the DataBinding object for [Character] item.
     */
    inner class CharacterViewHolder(private val binding: ListCharacterBinding)
        : RecyclerView.ViewHolder(binding.root) {
        /**
         * Binds a character into the view
         */
        fun bind(character: Character, listener: Listener? = null) {
            binding.character = character
            setFavouriteIcon(character.isFavourite)
            listener?.let { l ->
                binding.root.setOnClickListener { l.onCharacterSelected(character) }
                binding.buttonFav.setOnClickListener {
                    onToggleFavourite(character)
                }
            }
        }

        private fun onToggleFavourite(character: Character) {
            characters.filter { c -> c.id == character.id }
                    .map { c ->
                        c.isFavourite = !c.isFavourite
                    }
            notifyDataSetChanged()
            listener?.updateCharacterInDb(character)
        }

        private fun setFavouriteIcon(isFavourite: Boolean) {
            if (isFavourite) {
                binding.buttonIcon = R.drawable.ic_fav_24dp
            } else {
                binding.buttonIcon = R.drawable.ic_fav_border_24dp
            }
        }
    }

    /**
     * Defines an interface that lets the Presenter interact with the [CharacterAdapter]
     */
    interface Listener {
        fun onCharacterSelected(character: Character)
        fun updateCharacterInDb(character: Character)
    }
}