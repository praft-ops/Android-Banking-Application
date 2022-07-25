package ivy.project.bankingapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_deposit.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import java.util.ArrayList

class DepositActivity : AppCompatActivity() {

    lateinit var myToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_deposit)

        // SET INITIAL ACCOUNT BALANCE
        var checkingAccount = resources.getInteger(R.integer.checking_account)
        var savingsAccount = resources.getInteger(R.integer.savings_account)
        var creditAccount = resources.getInteger(R.integer.credit_account)

        // INITIALIZE "CURRENCYNUMBERFORMAT" OBJECT
        val currencyNumberFormat = NumberFormat.getCurrencyInstance()
        currencyNumberFormat.setMaximumFractionDigits(0);

        //PASS ACCOUNT BALANCES INTO FORMATTER
        var checkingCurrencyFormat = currencyNumberFormat.format(checkingAccount)
        var savingsCurrencyFormat = currencyNumberFormat.format(savingsAccount)
        var creditCurrencyFormat = currencyNumberFormat.format(creditAccount)


        // CREATE DRAWER VIEW

        val myDepositDrawerLayout: DrawerLayout = findViewById(R.id.myDrawerLayout)
        val navView: NavigationView = findViewById(R.id.myDrawerView)

        myToggle = ActionBarDrawerToggle(this, myDepositDrawerLayout, 0, 0)

        myDepositDrawerLayout.addDrawerListener(myToggle)

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

        // Initialize Spinner
        val spinner = findViewById<Spinner>(R.id.accounts)

        // Create Array List to hold spinner dropdown data
        val arrayList = ArrayList<String>()
        arrayList.add("CHECKING")
        arrayList.add("SAVINGS")
        arrayList.add("CREDIT")

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val accountName = parent.getItemAtPosition(position).toString()

                if (position == 0) {
                    selectedAccountAmount.text = checkingCurrencyFormat
                }
                else if (position == 1) {
                    selectedAccountAmount.text = savingsCurrencyFormat
                }
                else if (position == 2) {
                    selectedAccountAmount.text = creditCurrencyFormat
                }
                Toast.makeText(parent.context, "Selected: $accountName", Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        if (myToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}