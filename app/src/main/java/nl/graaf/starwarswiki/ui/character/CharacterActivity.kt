package nl.graaf.starwarswiki.ui.character

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import nl.graaf.starwarswiki.R

class CharacterActivity : AppCompatActivity(), CharacterMVP.View {
    private val mPresenter by lazy { CharacterPresenter(this) }
    private lateinit var mSortingButton: FloatingActionButton

    private lateinit var mAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = CharacterAdapter(mPresenter)
        with(findViewById<RecyclerView>(R.id.list_characters)) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        mSortingButton = findViewById(R.id.button_sort)
        mSortingButton.setOnClickListener({
            mPresenter.toggleSort()
        })

        mPresenter.showCharacters()
    }

    override fun setCharacterList() {
        mAdapter.notifyDataSetChanged()
    }

    override fun setSortingButtonIcon(image: Int) {
        mSortingButton.setImageResource(image)
    }


    override fun showError() {
        Toast.makeText(this, "Could not load characters", Toast.LENGTH_SHORT).show()
    }
}
