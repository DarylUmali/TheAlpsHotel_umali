package ph.edu.comteq.thealpshotel_umali

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.gson.Gson
import ph.edu.comteq.thealpshotel_umali.ui.theme.TheAlpsHotel_umaliTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheAlpsHotel_umaliTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Homepage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun HotelCard(hotel: Hotel){
    Card (
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ){
        Row (
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("file:///android_asset/${hotel.hotel_cover_image}")
                    .crossfade(true)
                    .build(),
                contentDescription = hotel.hotel_name,
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                modifier = Modifier.size(120.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Column (
                modifier = Modifier.weight(1f).padding(horizontal = 12.dp)
            ){
                Text(
                    text = hotel.hotel_name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = hotel.hotel_rating.toString(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
//                    stars
                    repeat(hotel.hotel_rating.toInt()) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Star",
                            tint = Color.Yellow,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                Text(
                    text = "${hotel.hotel_to_ski_distance} km to ski lift",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homepage(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var hotels by remember { mutableStateOf(emptyList<Hotel>()) }
    var searchQuery by remember { mutableStateOf("") }

    val filteredHotels = hotels.filter {
        it.hotel_name.contains(searchQuery, ignoreCase = true)
    }
    val hotel_rating by remember { mutableStateOf(0.0) }

//    load json data
    LaunchedEffect(Unit) {
        val json = context.assets.open("hotels.json")
            .bufferedReader()
            .use { it.readText() }
        val gson = Gson()
        val hotelArray = gson.fromJson(
            json,
            Array<Hotel>::class.java
        )
        hotels = hotelArray.toList()
    }
//    main container
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    // Combine Logo and Title
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.france_national_flag),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .width(25.dp)
                                .padding(end = 8.dp)
                        )
                        Text(
                            "The Alph's Hotel",
                            // Removed large fontSize as TopAppBar handles its own typography
                        )
                    }
                },
                // Add the user icon to the actions area (right side)
                actions = {
                    IconButton(onClick = { /* Handle user icon click */ }) {
                        Icon(
                            imageVector = Icons.Outlined.Person,
                            contentDescription = "User",
                        )
                    }
                }
            )
        }
    ) { paddingValues -> // paddingValues is crucial for content placement

        // Main container (content of the screen)
        Column(
            // Apply padding from the Scaffold to push content below the TopAppBar
            modifier = modifier.padding(paddingValues)
        ) {
//        Search box
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { newValue -> searchQuery = newValue },
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { (Text("Search....")) },
                singleLine = true,
            )
//        hotel list
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredHotels) { hotel ->
                    HotelCard(hotel)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TheAlpsHotel_umaliTheme {
        Homepage()
    }
}