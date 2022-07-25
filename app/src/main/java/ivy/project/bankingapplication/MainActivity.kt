package ivy.project.bankingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import java.time.chrono.JapaneseEra.values

class MainActivity : AppCompatActivity() {

    lateinit var myToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_main)

        // SET INITIAL ACCOUNT BALANCE
        var checkingAccount = resources.getInteger(R.integer.checking_account)
        var savingsAccount = resources.getInteger(R.integer.savings_account)
        var creditAccount = resources.getInteger(R.integer.credit_account)

        // INITIALIZE "CURRENCYNUMBERFORMAT" OBJECT
        val currencyNumberFormat = NumberFormat.getCurrencyInstance()
        currencyNumberFormat.setMaximumFractionDigits(0)

        //PASS ACCOUNT BALANCES INTO FORMATTER
        var checkingCurrencyFormat = currencyNumberFormat.format(checkingAccount)
        var savingsCurrencyFormat = currencyNumberFormat.format(savingsAccount)
        var creditCurrencyFormat = currencyNumberFormat.format(creditAccount)

        // DISPLAY FORMATTED CURRENCY
        checking_Balance.text = checkingCurrencyFormat
        savings_Balance.text = savingsCurrencyFormat
        credit_Balance.text = creditCurrencyFormat

        val checkActTransferArray = emptyArray<Double>()
        val savingsActTransferArray = emptyArray<Double>()
        val creditActTransferArray = emptyArray<Double>()

        // CREATE DRAWER VIEW

        val drawerLayout: DrawerLayout = findViewById(R.id.myDrawerLayout)
        val navView: NavigationView = findViewById(R.id.myDrawerView)

        myToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)

        myDrawerLayout.addDrawerListener(myToggle)

        myToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.accountsId -> {
                    startActivity(Intent(this, MainActivity::class.java))
                }
                R.id.helpId -> {
                    startActivity(Intent(this, HelpActivity::class.java))
                }
                R.id.preferencesId -> {
                    startActivity(Intent(this, PreferencesActivity::class.java))
                }
                R.id.withdrawId -> {
                    startActivity(Intent(this, WithdrawActivity::class.java))
                }
                R.id.depositId -> {
                    startActivity(Intent(this, DepositActivity::class.java))
                }
            }

            true
        }
        }
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            if (myToggle.onOptionsItemSelected(item)) {
                return true
            }
            return super.onOptionsItemSelected(item)
        }
    }
