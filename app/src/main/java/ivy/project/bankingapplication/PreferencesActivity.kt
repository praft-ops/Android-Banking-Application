package ivy.project.bankingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ivy.project.bankingapplication.R

class PreferencesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_preferences)
    }
}