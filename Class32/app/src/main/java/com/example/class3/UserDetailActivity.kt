package com.example.class3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.class3.ui.theme.Class3Theme

class UserDetailActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userId = intent.getIntExtra("USER_ID", -1)
        val user = UserRepository.getUserById(userId)

        setContent {
            Class3Theme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text("User Details") },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    if (user != null) {
                        UserDetailScreen(
                            user = user,
                            modifier = Modifier.padding(innerPadding)
                        )
                    } else {
                        Text(
                            text = "User not found",
                            modifier = Modifier
                                .padding(innerPadding)
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UserDetailScreen(user: User, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        DetailItem(label = "Name", value = user.name)
        DetailItem(label = "Age", value = if (user.age > 0) "${user.age} years" else "Not specified")
        DetailItem(label = "Country", value = if (user.country.isNotBlank()) user.country else "Not specified")
        DetailItem(label = "Position", value = if (user.position.isNotBlank()) user.position else "Not specified")
        DetailItem(label = "School", value = if (user.schoolName.isNotBlank()) user.schoolName else "Not specified")
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun UserDetailScreenPreview() {
    Class3Theme {
        UserDetailScreen(
            user = User(
                name = "John Doe",
                age = 25,
                country = "USA",
                position = "Developer",
                schoolName = "Tech University"
            )
        )
    }
}
