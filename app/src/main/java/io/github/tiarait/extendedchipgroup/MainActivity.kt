package io.github.tiarait.extendedchipgroup

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.NumberPicker
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = getString(R.string.app_name)

        handler.postDelayed(updTags, 500)

        textEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                handler.removeCallbacks(updTags)
                handler.postDelayed(updTags, 500)
            }
        })

        textSeparEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                handler.removeCallbacks(updTags)
                handler.postDelayed(updTags, 500)
            }
        })

        textShowEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                handler.removeCallbacks(updTags)
                handler.postDelayed(updTags, 500)
            }
        })

        textHideEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                handler.removeCallbacks(updTags)
                handler.postDelayed(updTags, 500)
            }
        })

        picker.minValue = 1
        picker.maxValue = 100
        picker.value = 2
        picker.setOnValueChangedListener { numberPicker, i, i1 ->
            handler.removeCallbacks(updTags)
            handler.postDelayed(updTags, 500)
        }
    }

    val updTags = Runnable {
        chip_group.setMaxRow(picker.value)
        chip_group.setShowText(textShowEdit.text.toString())
        chip_group.setHideText(textHideEdit.text.toString())

        var separ = textSeparEdit.text.toString().trim()
        if (separ.isEmpty()) separ = " "
        if (textEdit.text.toString().isNotEmpty() && textEdit.text.toString().contains(separ)) {
            chip_group.setChips(textEdit.text.toString().trim().split(separ))
        } else {
            chip_group.update()
        }
    }
}