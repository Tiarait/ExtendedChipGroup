package io.github.tiarait.extendedchipgroup

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.view.MarginLayoutParamsCompat
import androidx.core.view.ViewCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ExtendedChipGroup : ChipGroup {
    private val DEF_STYLE_RES = com.google.android.material.R.style.Widget_MaterialComponents_ChipGroup
    //макс колво строк - плавающая переменная взависимости скрыто или нет
    private var maxRow = Int.MAX_VALUE
    //макс колво строк - переменная из xml
    var maxRowDef = Int.MAX_VALUE
    //имя кнопки ЕЩЕ
    private var chipMoreTitle = ""
    //имя кнопки СКРЫТЬ
    private var chipHideTitle = ""
    //цвет кнопки СКРЫТЬ/ЕЩЕ
    private var chipMoreColor = -0xeee
    private var chipMoreColorPressed = -0xc5c5c5
    private var chipMoreColorText = -0x000
    private var colorStateList: ColorStateList
    //для обновления элементов
    private var lastChipsList: ArrayList<String> = ArrayList()
    private var mLineSpacing = 0
    private var mItemSpacing = 0

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, com.google.android.material.R.attr.chipGroupStyle)

    constructor(context: Context?, attrs: AttributeSet?,  defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        @SuppressLint("ChipGroup") val a = context!!.obtainStyledAttributes(
            attrs,
            R.styleable.ChipGroup,
            defStyleAttr,
            DEF_STYLE_RES)

        //получаем maxRow из xml
        maxRowDef = a.getColor(R.styleable.ChipGroup_maxRow, maxRow)
        if (maxRowDef > 0) isSingleLine = false
        //или наоборот, смотря что в приоритете
        //if (isSingleLine) maxRowDef = Int.MAX_VALUE
        maxRow = maxRowDef
        //получаем имя кнопки ЕЩЕ из xml
        chipMoreTitle = a.getString(R.styleable.ChipGroup_additionalChipMore).toString()
        //получаем имя кнопки СКРЫТЬ из xml
        chipHideTitle = a.getString(R.styleable.ChipGroup_additionalChipHide).toString()
        //получаем цвет кнопки СКРЫТЬ/ЕЩЕ из xml
        chipMoreColor = a.getColor(R.styleable.ChipGroup_additionalChipColor, -0xeee)
        chipMoreColorPressed = a.getColor(R.styleable.ChipGroup_additionalChipColorPressed, -0xc5c5c5)
        chipMoreColorText = a.getColor(R.styleable.ChipGroup_additionalTextColor, -0x000)

        colorStateList = ColorStateList(
            arrayOf(intArrayOf(android.R.attr.state_pressed), intArrayOf()),
            intArrayOf(
                chipMoreColorPressed,
                chipMoreColor
            )
        )

        val array = context.theme.obtainStyledAttributes(attrs, R.styleable.FlowLayout, 0, 0)
        mLineSpacing = array.getDimensionPixelSize(com.google.android.material.R.styleable.FlowLayout_lineSpacing, 0)
        mItemSpacing = array.getDimensionPixelSize(R.styleable.FlowLayout_itemSpacing, 0)
    }

    fun setMaxRow(max: Int) {
        maxRow = max
        maxRowDef = max
    }

    fun setShowText(title: String) {
        chipMoreTitle = title
    }

    fun setHideText(title: String) {
        chipHideTitle = title
    }

    fun update() {
        setChips(ArrayList(lastChipsList))
    }

    //устанавливаем Chip'ы из List<String>, lastChipsList должен быть такого же типа
    //List<Chip> - не работает (не обновляются view)
    fun setChips(text: List<String>) {
        removeAllViews()
        lastChipsList.clear()
        for (string in text) {
            if (string.isNotEmpty()) {
                val chip = Chip(context)
                chip.text = string
                chip.isSelected = true
                chip.isCheckable = true
                lastChipsList.add(string)
                addView(chip)
            }
        }
    }

    //в оригинале (FlowLayout) - rowCount
    var row = 0
    //копия с оригинала (FlowLayout)  + корректировки
    override fun onLayout(sizeChanged: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        if (childCount == 0) {
            row = 0
            return
        }
        row = 1

        val isRtl = ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL
        val paddingStart = if (isRtl) paddingRight else paddingLeft
        val paddingEnd = if (isRtl) paddingLeft else paddingRight
        var childStart = paddingStart
        var childTop = paddingTop
        var childBottom = childTop
        var childEnd: Int

        val maxChildEnd = right - left - paddingEnd

        for (i in 0 until childCount) {
            if (getChildAt(i) == null) {
                continue
            }
            val child = getChildAt(i) as Chip
            if (child.visibility == View.GONE) {
                child.setTag(R.id.row_index_key, -1)
                continue
            }
            val lp = child.layoutParams
            var startMargin = 0
            var endMargin = 0
            if (lp is MarginLayoutParams) {
                startMargin = MarginLayoutParamsCompat.getMarginStart(lp)
                endMargin = MarginLayoutParamsCompat.getMarginEnd(lp)
            }
            childEnd = childStart + startMargin + child.measuredWidth
            if (!isSingleLine && childEnd > maxChildEnd) {
                childStart = paddingStart
                childTop = childBottom + mLineSpacing
                row++
                //если строк больше чем maxRow - заменяем последний элемент пред строки на
                //"chipMoreTitle/ЕЩЕ (колво оставшихся)"
                if (row > maxRow && (i - 1) > 0  && chipMoreTitle.isNotEmpty()) {
                    getChildAt(i).visibility = View.GONE
                    val showChip = (getChildAt(i - 1) as Chip)
                    showChip.chipBackgroundColor = colorStateList
                    showChip.setTextColor(chipMoreColorText)
                    showChip.text = chipMoreTitle + " (${childCount - i + 1})"
                    showChip.isCheckable = false
                    showChip.isSelected = false
                    //при клике на chipMoreTitle выставляем maxRow на максимум и обновляем элементы
                    //после добавляем chipHideTitle
                    showChip.setOnClickListener {
                        maxRow = Int.MAX_VALUE
                        setChips(ArrayList(lastChipsList))

                        if (chipHideTitle.isNotEmpty()) {
                            val hideChip = Chip(context)
                            hideChip.chipBackgroundColor = colorStateList
                            hideChip.setTextColor(chipMoreColorText)
                            hideChip.text = chipHideTitle
                            hideChip.isSelected = false
                            hideChip.isCheckable = false
                            //при клике на chipHideTitle выставляем maxRow на заданный и обновляем элементы
                            hideChip.setOnClickListener {
                                maxRow = maxRowDef
                                setChips(ArrayList(lastChipsList))
                            }
                            addView(hideChip)
                        }
                    }
                }
            }
            //проверяем - если строк больше чем maxRow - скрываем элементы
            child.visibility = if (row > maxRow) View.GONE else View.VISIBLE
            child.setTag(R.id.row_index_key, row - 1)
            childEnd = childStart + startMargin + child.measuredWidth
            childBottom = childTop + child.measuredHeight
            if (isRtl) {
                child.layout(
                    maxChildEnd - childEnd,
                    childTop,
                    maxChildEnd - childStart - startMargin,
                    childBottom
                )
            } else {
                child.layout(childStart + startMargin, childTop, childEnd, childBottom)
            }
            childStart += startMargin + endMargin + child.measuredWidth + mItemSpacing
        }
    }
}
