package com.example.footbal360.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.footbal360.R
import com.example.footbal360.data.model.chips.Data
import com.example.footbal360.ui.theme.ChipsBackground
import com.example.footbal360.ui.theme.ChipsText
import com.example.footbal360.ui.theme.chipsSpacer

@Composable
fun Chip(chip: Data) {
    Row {
        OutlinedButton(
            colors = ButtonDefaults.buttonColors(containerColor = ChipsBackground),
            onClick = { /*TODO*/ }
        ){
            Text(text = chip.title,
                color = ChipsText,
                fontFamily = FontFamily(Font(R.font.iran_sansx_demi_bold)),
                fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.width(chipsSpacer))
    }
}