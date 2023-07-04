package be.laurent.gamehistory

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import be.laurent.gamehistory.databinding.ActivityMainBinding
import be.laurent.gamehistory.fragments.BarFragment
import be.laurent.gamehistory.fragments.HomeFragment
import be.laurent.gamehistory.fragments.TitleFragment

class ViewStatsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentTitle : FrameLayout
    private lateinit var fragmentBar : FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        fragmentTitle = this.findViewById(R.id.containerFragmentTitle)
        fragmentBar = this.findViewById(R.id.containerFragmentBar)

        val transaction = supportFragmentManager.beginTransaction()
        //transaction.replace(R.id.containerFragmentTitle, TitleFragment.initFragment("Statistiques"))
        transaction.replace(R.id.containerFragmentBar, BarFragment())
        transaction.commit()

    }

}