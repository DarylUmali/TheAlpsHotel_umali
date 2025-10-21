package ph.edu.comteq.thealpshotel_umali

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ph.edu.comteq.thealpshotel_umali.ui.theme.TheAlpsHotel_umaliTheme

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheAlpsHotel_umaliTheme {
                Scaffold{ innerPadding ->
                    Profile(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(modifier: Modifier = Modifier) {
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
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center, // Center content vertically
                horizontalAlignment = Alignment.CenterHorizontally // Center content horizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.umali_daryl_t), // Image resource
                    contentDescription = "Profile picture",
                    modifier = Modifier
                        .size(120.dp) // Image size
                        .clip(CircleShape) // Make image circular
                )
                Spacer(modifier = Modifier.height(16.dp)) // Space between image and name

                // Name Text
                Text(
                    text = "Daryl T Umali",
                    fontSize = 28.sp,
                    color = Color.Black
                )

                // Job Title
                Text(
                    text = "Developer",
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Daryl T Umali is an IT Student at\n" +
                            "COMTEQ Computer College. This\n" +
                            "application was developed as part of\n" +
                            "the course, Mobile Application\n" +
                            "Development and was adopted from\n" +
                            "World Skills Lyon 2024",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
        }
    }
}
        @Preview(showBackground = true)
        @Composable
        fun ProfilePreview() {
            TheAlpsHotel_umaliTheme {
                Profile()
            }
        }
