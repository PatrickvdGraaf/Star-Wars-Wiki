package nl.graaf.starwarswiki.ui.characters

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import nl.graaf.starwarswiki.R
import nl.graaf.starwarswiki.base.BaseActivity
import nl.graaf.starwarswiki.databinding.ActivityCharactersBinding
import nl.graaf.starwarswiki.model.Character

class CharacterActivity : BaseActivity<CharacterPresenter>(), CharacterView {

    private lateinit var mSortingButton: FloatingActionButton

    /**
     * DataBinding instance
     */
    private lateinit var binding: ActivityCharactersBinding

    /**
     * The adapter for the list of posts
     */
    private val characterAdapter = CharacterAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_characters)
        binding.adapter = characterAdapter
        binding.layoutManager = LinearLayoutManager(this)
        binding.dividerItemDecoration = DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL)

        setSortingButtonIcon(R.drawable.ic_birth_24dp)
        binding.buttonSort.setOnClickListener({
            binding.buttonIcon = characterAdapter.toggleSort()
        })

        presenter.onViewCreated()
    }

    override fun onDestroy() {
        presenter.onViewDestroyed()
        super.onDestroy()
    }

    /**
     * Instantiates the presenter the Activity is based on.
     */
    override fun instantiatePresenter(): CharacterPresenter {
        return CharacterPresenter(this)
    }

    /**
     * Updates the previous characters by the specified ones
     * @param characters the list of characters that will replace existing ones
     */
    override fun updateCharacters(characters: List<Character>) {
        characterAdapter.updateCharacters(characters)
    }

    /**
     * Displays an error in the view
     * @param error the error to display in the view
     */
    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    /**
     * Displays the loading indicator of the view
     */
    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }

    /**
     * Hides the loading indicator of the view
     */
    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }

    override fun setSortingButtonIcon(icon: Int) {
        binding.buttonIcon = icon
    }
}
