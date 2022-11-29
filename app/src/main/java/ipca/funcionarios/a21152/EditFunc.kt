package ipca.funcionarios.a21152

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class EditFunc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)

        findViewById<Button>(R.id.buttonSave).setOnClickListener {
            val intentEdit = Intent()
            intentEdit.putExtra("model", intent.getStringExtra("model"))
            intentEdit.putExtra("qt", findViewById<EditText>(R.id.editTextQt).text.toString().toInt())
            intentEdit.putExtra("price", findViewById<EditText>(R.id.editTextPrice).text.toString().toDouble())
            setResult(RESULT_OK, intentEdit)
            finish()
        }

    }
}