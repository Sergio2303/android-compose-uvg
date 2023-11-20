import androidx.lifecycle.ViewModel
import com.zezzi.eventzezziapp.data.networking.response.MealsCategoriesResponse
import com.zezzi.eventzezziapp.data.networking.ResponseS
import com.zezzi.eventzezziapp.data.repository.MealsRepository

class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository()) : ViewModel() {
    suspend fun getMeals(): MealsCategoriesResponse? {
        return repository.getMeals()
    }
    suspend fun getFilter(category: String): ResponseS? {
        return repository.getFilter(category)
    }
}