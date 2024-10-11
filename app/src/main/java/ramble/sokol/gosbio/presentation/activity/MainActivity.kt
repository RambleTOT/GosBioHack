package ramble.sokol.gosbio.presentation.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ramble.sokol.gosbio.R
import ramble.sokol.gosbio.presentation.fragments.SplashScreenFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_main)

        val splashScreenFragment = SplashScreenFragment()
        val fragment : Fragment? =
            supportFragmentManager.findFragmentByTag(SplashScreenFragment::class.java.simpleName)

        if (fragment !is SplashScreenFragment){
            supportFragmentManager.beginTransaction()
                .add(R.id.layout_fragment, splashScreenFragment, SplashScreenFragment::class.java.simpleName)
                .commit()
        }

    }


}