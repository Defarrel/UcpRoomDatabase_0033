package com.example.ucp2.ui.view.dokter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.R
import com.example.ucp2.data.entity.Dokter
import com.example.ucp2.data.entity.Jadwal
import com.example.ucp2.ui.customwidget.TopAppBarr
import com.example.ucp2.ui.viewmodel.HomeUiState
import com.example.ucp2.ui.viewmodel.HomeViewModel
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeView(
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddDokter: () -> Unit = {},
    onAddJadwal: () -> Unit = {},
    onDetailClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBarr(
                judul = "Daftar Dokter",
                showBackButton = false,
                onBack = {},
                modifier = modifier
            )
        },
        floatingActionButton = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                FloatingActionButton(
                    onClick = onAddJadwal,
                    shape = MaterialTheme.shapes.medium,
                    containerColor = colorResource(id = R.color.floatingbutton),
                    contentColor = colorResource(id = R.color.white)
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Lihat Jadwal"
                    )
                }
                FloatingActionButton(
                    onClick = onAddDokter,
                    shape = MaterialTheme.shapes.medium,
                    containerColor = colorResource(id = R.color.floatingbutton),
                    contentColor = colorResource(id = R.color.white)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Tambah Dokter"
                    )
                }
            }
        }


    ){ innerPadding ->
        val homeUiState by viewModel.homeUiState.collectAsState()

        BodyHomeMhsView(
            homeUiState = homeUiState,
            onClick = {
                onDetailClick(it)
            },
            modifier = modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeMhsView(
    homeUiState: HomeUiState,
    onClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when{
        homeUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        homeUiState.isError -> {
            LaunchedEffect(homeUiState.errorMessage) {
                homeUiState.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }
        homeUiState.listdokter.isEmpty() ->{
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Tidak ada data Dokter",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.padding(16.dp)
                )
            }
        }
        else -> {
            ListDokter(
                lisDokter = homeUiState.listdokter,
                onClick = {
                    onClick(it)
                    println(it)
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListDokter(
    lisDokter: List<Dokter>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
){
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = lisDokter,
            itemContent = {dktr ->
                CardMhs(
                    dktr = dktr,
                    onClick = {onClick(dktr.nama)}
                )
            }
        )
    }
}



@Composable
fun CardMhs(
    dktr: Dokter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = dktr.nama,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.black)
                )
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Dokter",
                    tint = colorResource(id = R.color.icon)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider(color = colorResource(id = R.color.dll2), thickness = 1.dp)

            InfoRow(
                icon = Icons.Filled.ThumbUp,
                label = "Spesialis",
                value = dktr.spesialis
            )

            InfoRow(
                icon = Icons.Filled.AddCircle,
                label = "Klinik",
                value = dktr.klinik

            )

            InfoRow(
                icon = Icons.Filled.Call,
                label = "Kontak",
                value = dktr.noHp
            )

            InfoRow(
                icon = Icons.Filled.DateRange,
                label = "Jam Kerja",
                value = dktr.jamKerja
            )
        }
    }
}

@Composable
fun InfoRow(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = colorResource(id = R.color.icon),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = label,
                fontSize = 12.sp,
                color = colorResource(id = R.color.black)
            )
            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.black)
            )
        }
    }
}

