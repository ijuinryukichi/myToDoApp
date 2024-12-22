import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.MainScreen
import com.example.myapplication.ui.TaskInputScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController = navController) }
        composable("taskInput/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toInt() ?: 0
            TaskInputScreen(taskId = taskId, onSave = { navController.popBackStack() })
        }
    }
}