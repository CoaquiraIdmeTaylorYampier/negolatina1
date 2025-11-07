package com.example.negolatina

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat
import com.google.android.material.button.MaterialButton

class CheckoutLegacyActivity : AppCompatActivity() {

    private var cantidad = 1
    private val precioUnitario = 16.90

    private lateinit var toolbar: Toolbar
    private lateinit var tvProductName: TextView
    private lateinit var tvProductDescription: TextView
    private lateinit var tvPrecio: TextView
    private lateinit var tvCantidad: TextView
    private lateinit var tvTotal: TextView
    private lateinit var btnEliminar: ImageButton
    private lateinit var btnOpciones: ImageButton
    private lateinit var spinnerCantidad: LinearLayout
    private lateinit var btnFinalizarCompra: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            val layout = createCheckoutLayout()
            setContentView(layout)

            setupToolbar()
            setupProducto()
            setupButtons()
            actualizarTotal()
        } catch (e: Exception) {
            Toast.makeText(this, "Error al cargar la vista: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    private fun createCheckoutLayout(): ConstraintLayout {
        val constraintLayout = ConstraintLayout(this).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(Color.WHITE)
        }

        // ===== TOOLBAR =====
        toolbar = Toolbar(this).apply {
            id = ViewCompat.generateViewId()
            setBackgroundColor(Color.parseColor("#E31E24"))
            setTitleTextColor(Color.WHITE)
        }
        constraintLayout.addView(toolbar, ConstraintLayout.LayoutParams(
            0, getActionBarSize()
        ))

        // ===== SCROLLVIEW =====
        val scrollView = ScrollView(this).apply {
            id = ViewCompat.generateViewId()
        }

        val scrollContent = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(16))
        }

        val cardView = createProductCard()
        scrollContent.addView(cardView)
        scrollView.addView(scrollContent)

        constraintLayout.addView(scrollView, ConstraintLayout.LayoutParams(
            0, 0
        ))

        // ===== LAYOUT TOTAL =====
        val layoutTotal = createTotalLayout()
        constraintLayout.addView(layoutTotal)

        // ===== CONSTRAINTS =====
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)

        constraintSet.connect(toolbar.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        constraintSet.connect(toolbar.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        constraintSet.connect(toolbar.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

        constraintSet.connect(scrollView.id, ConstraintSet.TOP, toolbar.id, ConstraintSet.BOTTOM)
        constraintSet.connect(scrollView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        constraintSet.connect(scrollView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        constraintSet.connect(scrollView.id, ConstraintSet.BOTTOM, layoutTotal.id, ConstraintSet.TOP)

        constraintSet.connect(layoutTotal.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
        constraintSet.connect(layoutTotal.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        constraintSet.connect(layoutTotal.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

        constraintSet.applyTo(constraintLayout)
        return constraintLayout
    }

    @SuppressLint("ResourceType")
    private fun createProductCard(): CardView {
        val cardView = CardView(this).apply {
            radius = dpToPx(8).toFloat()
            cardElevation = dpToPx(2).toFloat()
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                bottomMargin = dpToPx(16)
            }
        }

        val cardContent = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(16))
        }

        val header = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        tvProductName = TextView(this).apply {
            text = "Chuletas de cerdo"
            textSize = 18f
            setTextColor(Color.parseColor("#333333"))
            setTypeface(typeface, Typeface.BOLD)
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        }

        btnOpciones = ImageButton(this).apply {
            id = ViewCompat.generateViewId()
            setImageResource(android.R.drawable.ic_menu_more)
            setBackgroundResource(android.R.drawable.btn_default)
        }

        btnEliminar = ImageButton(this).apply {
            id = ViewCompat.generateViewId()
            setImageResource(android.R.drawable.ic_menu_delete)
            setBackgroundResource(android.R.drawable.btn_default)
        }

        header.addView(tvProductName)
        header.addView(btnOpciones)
        header.addView(btnEliminar)

        tvProductDescription = TextView(this).apply {
            text = "Descripción del producto"
            textSize = 14f
            setTextColor(Color.parseColor("#666666"))
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { topMargin = dpToPx(8) }
        }

        tvPrecio = TextView(this).apply {
            text = "S/. 16.90 x Kg."
            textSize = 16f
            setTextColor(Color.parseColor("#E31E24"))
            setTypeface(typeface, Typeface.BOLD)
        }

        val cantidadLayout = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { topMargin = dpToPx(12) }
        }

        val labelCantidad = TextView(this).apply {
            text = "Cantidad:"
            textSize = 14f
        }

        spinnerCantidad = LinearLayout(this).apply {
            id = ViewCompat.generateViewId()
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dpToPx(8), dpToPx(8), dpToPx(8), dpToPx(8))
        }

        tvCantidad = TextView(this).apply {
            text = "1"
            textSize = 16f
            setTypeface(typeface, Typeface.BOLD)
        }

        val arrowDown = ImageView(this).apply {
            setImageResource(android.R.drawable.arrow_down_float)
            layoutParams = LinearLayout.LayoutParams(dpToPx(20), dpToPx(20)).apply {
                marginStart = dpToPx(4)
            }
        }

        spinnerCantidad.addView(tvCantidad)
        spinnerCantidad.addView(arrowDown)

        val labelKg = TextView(this).apply {
            text = "Kg"
            textSize = 14f
            setTextColor(Color.parseColor("#666666"))
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { marginStart = dpToPx(4) }
        }

        cantidadLayout.addView(labelCantidad)
        cantidadLayout.addView(spinnerCantidad)
        cantidadLayout.addView(labelKg)

        cardContent.addView(header)
        cardContent.addView(tvProductDescription)
        cardContent.addView(tvPrecio)
        cardContent.addView(cantidadLayout)
        cardView.addView(cardContent)

        return cardView
    }

    private fun createTotalLayout(): LinearLayout {
        val layoutTotal = LinearLayout(this).apply {
            id = ViewCompat.generateViewId()
            orientation = LinearLayout.VERTICAL
            setPadding(dpToPx(16), dpToPx(16), dpToPx(16), dpToPx(16))
        }

        val totalRow = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = dpToPx(12) }
        }

        val labelTotal = TextView(this).apply {
            text = "Total:"
            textSize = 18f
            setTypeface(typeface, Typeface.BOLD)
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        }

        tvTotal = TextView(this).apply {
            text = "S/. 16.90"
            textSize = 20f
            setTextColor(Color.parseColor("#E31E24"))
            setTypeface(typeface, Typeface.BOLD)
        }

        totalRow.addView(labelTotal)
        totalRow.addView(tvTotal)

        btnFinalizarCompra = MaterialButton(this).apply {
            id = ViewCompat.generateViewId()
            text = "Finalizar compra"
            textSize = 16f
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.parseColor("#E31E24"))
            isAllCaps = false
            cornerRadius = dpToPx(8)
        }

        layoutTotal.addView(totalRow)
        layoutTotal.addView(btnFinalizarCompra)
        return layoutTotal
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Finalizar compra"
        }
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun setupProducto() {
        tvProductName.text = "Chuletas de cerdo"
        tvProductDescription.text = "Chuletas de cerdo frescas y de gran sabor."
        tvPrecio.text = "S/. ${String.format("%.2f", precioUnitario)} x Kg."
        tvCantidad.text = cantidad.toString()
    }

    private fun setupButtons() {
        btnEliminar.setOnClickListener { mostrarDialogoEliminar() }
        btnOpciones.setOnClickListener { Toast.makeText(this, "Opciones del producto", Toast.LENGTH_SHORT).show() }
        spinnerCantidad.setOnClickListener { mostrarDialogoCantidad() }
        btnFinalizarCompra.setOnClickListener { finalizarCompra() }
    }

    private fun mostrarDialogoEliminar() {
        AlertDialog.Builder(this)
            .setTitle("Eliminar producto")
            .setMessage("¿Estás seguro de eliminar este producto?")
            .setPositiveButton("Eliminar") { d, _ ->
                Toast.makeText(this, "Producto eliminado", Toast.LENGTH_SHORT).show()
                d.dismiss(); finish()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun mostrarDialogoCantidad() {
        val cantidades = arrayOf("1","2","3","4","5","6","7","8","9","10")
        AlertDialog.Builder(this)
            .setTitle("Seleccionar cantidad (Kg)")
            .setItems(cantidades) { dialog, which ->
                cantidad = which + 1
                tvCantidad.text = cantidad.toString()
                actualizarTotal()
                dialog.dismiss()
            }.setNegativeButton("Cancelar", null).show()
    }

    private fun actualizarTotal() {
        val total = cantidad * precioUnitario
        tvTotal.text = "S/. ${String.format("%.2f", total)}"
    }

    private fun finalizarCompra() {
        val total = cantidad * precioUnitario
        AlertDialog.Builder(this)
            .setTitle("Confirmar compra")
            .setMessage("Procesando compra:\n$cantidad kg por S/. ${String.format("%.2f", total)}")
            .setPositiveButton("Confirmar") { dialog, _ ->
                dialog.dismiss()
                val intent = Intent(this, PurchaseSuccessActivity::class.java)
                startActivity(intent)
                finish()
            }.setNegativeButton("Cancelar", null).show()
    }

    private fun dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }

    private fun getActionBarSize(): Int {
        val styledAttributes = theme.obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
        val actionBarSize = styledAttributes.getDimension(0, 0f).toInt()
        styledAttributes.recycle()
        return actionBarSize
    }
}

// ============================================================
//  PURCHASE SUCCESS ACTIVITY
// ============================================================

class PurchaseSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val layout = createPurchaseSuccessLayout()
        setContentView(layout)
    }

    private fun createPurchaseSuccessLayout(): ConstraintLayout {
        val constraintLayout = ConstraintLayout(this).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(Color.WHITE)
        }

        val topBar = View(this).apply {
            id = ViewCompat.generateViewId()
            setBackgroundColor(Color.parseColor("#E31E24"))
        }
        constraintLayout.addView(topBar, ConstraintLayout.LayoutParams(0, dpToPx(60)))

        val message = TextView(this).apply {
            id = ViewCompat.generateViewId()
            text = "¡Felicitaciones!\nSu compra se ha realizado exitosamente."
            textSize = 18f
            setTextColor(Color.parseColor("#333333"))
            gravity = Gravity.CENTER
        }
        constraintLayout.addView(message)

        val btnFinish = Button(this).apply {
            id = ViewCompat.generateViewId()
            text = "Finalizar"
            textSize = 18f
            setTextColor(Color.WHITE)
            setBackgroundColor(Color.parseColor("#E31E24"))
            isAllCaps = false
            setOnClickListener { finishAffinity() }
        }
        constraintLayout.addView(btnFinish, ConstraintLayout.LayoutParams(0, dpToPx(56)))

        val set = ConstraintSet()
        set.clone(constraintLayout)
        set.connect(topBar.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)
        set.connect(topBar.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        set.connect(topBar.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

        set.connect(message.id, ConstraintSet.TOP, topBar.id, ConstraintSet.BOTTOM, dpToPx(200))
        set.connect(message.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        set.connect(message.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)

        set.connect(btnFinish.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, dpToPx(60))
        set.connect(btnFinish.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, dpToPx(32))
        set.connect(btnFinish.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, dpToPx(32))
        set.applyTo(constraintLayout)

        return constraintLayout
    }

    private fun dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}
