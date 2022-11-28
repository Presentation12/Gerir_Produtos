package ipca.funcionarios.a21152

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.util.*
import java.util.Locale.filter

class MainActivity : AppCompatActivity() {

    val products = arrayListOf<Product>()
    val adapter = productsAdapter()
    var getResult : ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        products.add(Product("Nike","Air Force 1",200,109.99))
        products.add(Product("Nike","Air Jordan 1",300,129.99))

        val searchViewProducts = findViewById<SearchView>(R.id.searchViewProducts)

        val listViewProducts = findViewById<ListView>(R.id.listViewProducts)
        listViewProducts.adapter = adapter

        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if(it.resultCode == Activity.RESULT_OK){
                    val brand = it.data?.getStringExtra("brand")
                    val model = it.data?.getStringExtra("model")
                    val qt = it.data?.getIntExtra("qt", 0)
                    val price = it.data?.getDoubleExtra("price",0.0)
                    products.add(Product(brand,model,qt,price))
                    adapter.notifyDataSetChanged()
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_inicial, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return when(item.itemId){
            R.id.add_product -> {
                getResult?.launch(Intent(this, AddProduct::class.java))
                true
            }
            R.id.count_products -> {
                Toast.makeText(this,"Quantity of products: ${products.size}", Toast.LENGTH_LONG).show()
                true
            }
            else -> {
                false
            }
        }
    }

    inner class productsAdapter: BaseAdapter()
    {
        override fun getCount(): Int {
            return products.size
        }

        override fun getItem(position: Int): Any {
            return products[position]
        }

        override fun getItemId(position: Int): Long {
            return 0L
        }

        override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
            val rootView = layoutInflater.inflate(R.layout.row_product, p2, false)
            val textViewBrand = rootView.findViewById<TextView>(R.id.textViewBrand)
            val textViewModel = rootView.findViewById<TextView>(R.id.textViewModel)
            val deleteButton = rootView.findViewById<Button>(R.id.button_delete)
            val editButton = rootView.findViewById<Button>(R.id.button_edit)
            val plusButton = rootView.findViewById<Button>(R.id.button_plus)
            val minusButton = rootView.findViewById<Button>(R.id.button_minus)

            textViewBrand.text = products[position].brand
            textViewModel.text = products[position].model

            rootView.setOnClickListener{
                val intent = Intent(this@MainActivity, ProductDetails::class.java)
                intent.putExtra("brand", products[position].brand)
                intent.putExtra("model", products[position].model)
                intent.putExtra("qt", products[position].qt)
                intent.putExtra("price", products[position].price)
                startActivity(intent)
            }

            deleteButton.setOnClickListener{
                products.remove(products[position])
                adapter.notifyDataSetChanged()
            }

            plusButton.setOnClickListener {
                products[position].qt = products[position].qt?:0 + 1
                Toast.makeText(this@MainActivity, "Quantity:${products[position].qt}", Toast.LENGTH_LONG).show()
                adapter.notifyDataSetChanged()
            }

            minusButton.setOnClickListener {
                if (products[position].qt?:0 > 0)
                {
                    products[position].qt = products[position].qt?:0 - 1
                    Toast.makeText(this@MainActivity, "Quantity:${products[position].qt}", Toast.LENGTH_LONG).show()
                    adapter.notifyDataSetChanged()
                }
                else
                {
                    Toast.makeText(this@MainActivity, "Value equal to zero", Toast.LENGTH_LONG).show()
                }
            }

            return rootView
        }
    }
}