package hossein.gheisary.imdbfilms.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hossein.gheisary.imdbfilms.film.activity.FilmActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, FilmActivity::class.java)
        startActivity(intent)
        finish()
    }
}