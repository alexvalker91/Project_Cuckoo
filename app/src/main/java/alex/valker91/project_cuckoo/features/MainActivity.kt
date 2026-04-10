package alex.valker91.project_cuckoo.features

import alex.valker91.project_cuckoo.R
import alex.valker91.project_cuckoo.util.UserPrefs
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val prefs = UserPrefs(this)

            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

            val navController = navHostFragment.navController
            val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)

            if (prefs.isUserSaved()) {
                navGraph.setStartDestination(R.id.clientsFragment)
            } else {
                navGraph.setStartDestination(R.id.inputFragment)
            }

            navController.graph = navGraph
        }
    }
}