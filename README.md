# ExtendedChipGroup
ExtendedChipGroup extend from ChipGroup which was added in google material library. I added a function to control the max number of lines, as well as buttons for while / hiding chips

[Youtube](https://www.youtube.com/watch?v=Wczd4a_lLx4&feature=youtu.be)

## Getting Started
* implementation 'com.google.android.material:material:1.2.0'
* Your theme must inherit from Theme.MaterialComponents
* Declareted styleable in ur attr
```
  <declare-styleable name="ChipGroup">
        <attr name="maxRow" format="reference|integer"/>
        <attr name="additionalChipMore" format="reference|string"/>
        <attr name="additionalChipHide" format="reference|string"/>
        <attr name="additionalChipColor" format="reference|color"/>
        <attr name="additionalChipColorPressed" format="reference|color"/>
        <attr name="additionalTextColor" format="reference|color"/>
        <attr name="additionalChipFixed" format="reference|boolean"/>
        <attr format="dimension" name="lineSpacing"/>
        <attr format="dimension" name="itemSpacing"/>
    </declare-styleable>
```
* Copy code [ExtendedChipGroup.kt](https://github.com/Tiarait/ExtendedChipGroup/blob/master/app/src/main/java/io/github/tiarait/extendedchipgroup/ExtendedChipGroup.kt)
* Add ExtendedChipGroup in xml
```
<io.github.tiarait.extendedchipgroup.ExtendedChipGroup
                android:id="@+id/chip_group"
                app:maxRow="2"
                app:itemSpacing="6dp"
                app:additionalTextColor="#eee"
                app:additionalChipColor="@color/colorDarkBtn"
                app:additionalChipColorPressed="@color/colorDarkBtnSecondary"
                app:additionalChipMore="@string/btn_show"
                app:additionalChipHide="@string/btn_hide"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"/>
```

First Header | Second Header
------------ | -------------
maxRow | (int) maximum number of ChipGroup lines
additionalChipFixed | (boolean) show (Hide) button in the same place as (Show) button
additionalTextColor | (color) additional buttons text color (Show | Hide)
additionalChipColor | (color) additional background colors of the buttons when rest
additionalChipColorPressed | (color) additional background colors of the button when pressed
additionalChipMore | (string) title for show|more button
additionalChipHide | (string) title for hide|less button


## Screenshots
![alt text](https://github.com/Tiarait/ExtendedChipGroup/blob/master/screen.jpg)

## License 
```
The MIT License (MIT)

Copyright (c) 2020-present Tiarait

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
