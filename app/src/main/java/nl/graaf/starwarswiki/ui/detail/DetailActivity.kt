package nl.graaf.starwarswiki.ui.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import nl.graaf.starwarswiki.R
import nl.graaf.starwarswiki.model.Character

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */

// TODO save details and update the character

class DetailActivity : AppCompatActivity(), DetailMVP.View {
    private lateinit var mPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        with(intent.getSerializableExtra("character") as Character) {
            mPresenter = DetailPresenter(applicationContext, this@DetailActivity, this)

            findViewById<TextView>(R.id.textview_name).text = name
            findViewById<TextView>(R.id.textview_birth).text = birthYear

            mPresenter.loadMovies()
            mPresenter.loadVehicles()
            mPresenter.loadHomeWorld()
        }
    }

    override fun setMovieText(m: String) {
        findViewById<TextView>(R.id.textview_films_title).visibility = View.VISIBLE
        with(findViewById<TextView>(R.id.textview_films)) {
            text = m
            visibility = View.VISIBLE
        }
    }

    override fun setVehicleText(v: String) {
        findViewById<TextView>(R.id.textView_vehicles_title).visibility = View.VISIBLE
        with(findViewById<TextView>(R.id.textView_vehicles)) {
            text = v
            visibility = View.VISIBLE
        }
    }

    override fun setHomeWorldText(h: String) {
        findViewById<TextView>(R.id.textView_homeworld_title).visibility = View.VISIBLE
        with(findViewById<TextView>(R.id.textView_homeworld)) {
            text = h
            visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        mPresenter.onDestroy()
        super.onDestroy()
    }
}