package com.phisit.wikicoutrylist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CountryItem(
    country: String,
    landmass: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = country,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.semantics {
                contentDescription = country
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = landmass,
            fontSize = 12.sp,
            maxLines = 1,
            modifier = Modifier.semantics {
                contentDescription = landmass
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CountryItemPreview() {
    CountryItem(
        country = "Thailand",
        landmass = "Asia"
    )
}