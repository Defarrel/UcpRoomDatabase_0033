package com.example.ucp2.ui.customwidget

import android.provider.ContactsContract.Profile
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.R


@Composable
fun TopAppBarr(
    onBack: () -> Unit,
    showBackButton: Boolean = true,
    showProfile: Boolean = true,
    judul: String,
    judul2: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = colorResource(id = R.color.topbar),
        shadowElevation = 4.dp
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 1.dp),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (showBackButton) {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = colorResource(id = R.color.white)
                        )
                    }
                }
                if (showProfile) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(start = 8.dp)
                    )
                }
                Column(
                    modifier = Modifier.align(Alignment.CenterVertically),
                ) {
                    Text(
                        text = judul,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.white)
                    )
                    Text(
                        text = judul2,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                        color = colorResource(id = R.color.white)
                    )
                }
            }
        }
    }
}

