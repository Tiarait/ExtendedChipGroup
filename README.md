# ExtendedChipGroup
ExtendedChipGroup inherits from ChipGroup which was added in google material library. I added a function to control the max number of lines, as well as buttons for while / hiding chips

## Getting Started
* implementation 'com.google.android.material:material:1.2.0'
* Declareted styleable in ur attr
```
  <declare-styleable name="ChipGroup">
        <attr name="maxRow" format="reference|integer"/>
        <attr name="additionalChipMore" format="reference|string"/>
        <attr name="additionalChipHide" format="reference|string"/>
        <attr name="additionalChipColor" format="reference|color"/>
        <attr name="additionalChipColorPressed" format="reference|color"/>
        <attr name="additionalTextColor" format="reference|color"/>
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
additionalTextColor | (color) additional buttons text color (Show | Hide)
additionalChipColor | (color) additional background colors of the buttons when rest
additionalChipColorPressed | (color) additional background colors of the button when pressed
additionalChipMore | (string) title for show|more button
additionalChipHide | (string) title for hide|less button


## Screenshots
![alt text](https://github.com/Tiarait/ExtendedChipGroup/blob/master/screen.jpg)

## License 
 
    Copyright 2014 Niek Haarman

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
    http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
