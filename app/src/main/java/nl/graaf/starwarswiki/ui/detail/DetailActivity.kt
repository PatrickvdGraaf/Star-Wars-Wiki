package nl.graaf.starwarswiki.ui.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import nl.graaf.starwarswiki.R
import nl.graaf.starwarswiki.model.Character

/**
 *
 * Created by Patrick van de Graaf on 6/7/2018.
 *
 */
class DetailActivity : AppCompatActivity(), DetailMVP.View {
    private val mPresenter by lazy { DetailPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        with(intent.getParcelableExtra<Character>("character")) {
            findViewById<TextView>(R.id.textview_name).text = name
            findViewById<TextView>(R.id.textview_birth).text = birthYear

            mPresenter.showMovies(films)
            mPresenter.showVehicles(vehicles)
        }
    }

    override fun setMovieText(m: String) {
        findViewById<TextView>(R.id.textview_films).text = m
    }

    override fun setVehicleText(v: String) {
        findViewById<TextView>(R.id.textView_vehicles).text = v
    }

}