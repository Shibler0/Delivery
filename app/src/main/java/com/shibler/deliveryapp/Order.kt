package com.shibler.deliveryapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Aliment(val name: String, val price: Double, val smiley: String, val quantity: Int)

@Composable
fun Order() {

    val font = FontFamily(Font(R.font.afacadflux))

    var orderList by remember { mutableStateOf(mutableListOf<Aliment>()) }

    orderList.add(Aliment("Cheeseburger", 9.99, "\uD83C\uDF54", 1))
    orderList.add(Aliment("Cola", 9.99, "\uD83E\uDD64", 1))
    orderList.add(Aliment("Fries", 9.99, "\uD83C\uDF5F", 1))
    orderList.add(Aliment("Cake", 9.99, "\uD83C\uDF70", 1))


    for(i in 0 until orderList.size step 2) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ){
            Column (
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
                    .fillMaxWidth()
                    .height(100.dp)
                    .shadow(10.dp, shape = RoundedCornerShape(20.dp), true)
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
                    .alpha(0.9f)
                    .clip(RoundedCornerShape(20.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(text = orderList[i].name +" "+ orderList[i].smiley, fontFamily = font, fontSize = 20.sp, modifier = Modifier.padding(start = 10.dp))

                Text(text = orderList[i].quantity.toString() + " | " + orderList[i].price.toString() + "$", fontFamily = font, fontSize = 15.sp, modifier = Modifier
                    .padding(start = 10.dp)
                    .alpha(0.5f))
            }

            Column (
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
                    .fillMaxWidth()
                    .height(100.dp)
                    .shadow(10.dp, shape = RoundedCornerShape(20.dp), true)
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
                    .alpha(0.9f)
                    .clip(RoundedCornerShape(20.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(text = orderList[i+1].name +" "+ orderList[i+1].smiley, fontFamily = font, fontSize = 20.sp, modifier = Modifier.padding(start = 10.dp))

                Text(text = orderList[i+1].quantity.toString() + " | " + orderList[i+1].price.toString() + "$", fontFamily = font, fontSize = 15.sp, modifier = Modifier
                    .padding(start = 10.dp)
                    .alpha(0.5f))
            }

        }

    }

    }

}