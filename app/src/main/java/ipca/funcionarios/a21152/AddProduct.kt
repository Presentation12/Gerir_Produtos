package ipca.funcionarios.a21152

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddProduct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        findViewById<Button>(R.id.buttonSave).setOnClickListener {
            val intent = Intent()
            intent.putExtra("brand", findViewById<EditText>(R.id.editTextBrand).text.toString())
            intent.putExtra("model", findViewById<EditText>(R.id.editTextModel).text.toString())
            intent.putExtra("qt", findViewById<EditText>(R.id.editTextQt).text.toString().toInt())
            intent.putExtra("price", findViewById<EditText>(R.id.editTextPrice).text.toString().toDouble())
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}