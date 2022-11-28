package ipca.funcionarios.a21152

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

class ProductDetails : AppCompatActivity() {

    val product = Product(null,null,null,null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val brand = intent.getStringExtra("brand")
        val model = intent.getStringExtra("model")
        val qt = intent.getIntExtra("qt",0)
        val price = intent.getDoubleExtra("price", 0.0)

        findViewById<TextView>(R.id.textViewDetailedBrand).text = brand
        findViewById<TextView>(R.id.textViewDetailedModel).text = model
        findViewById<TextView>(R.id.textViewDetailedYear).text = qt.toString()
        findViewById<TextView>(R.id.textViewDetailedPrice).text = price.toString()

        product.brand = brand
        product.model = model
        product.qt = qt
        product.price = price
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Brand: ${product.brand}\nModel: ${product.model}\nQuantity:${product.qt}$\nPrice:${product.price}")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
        return true
    }
}