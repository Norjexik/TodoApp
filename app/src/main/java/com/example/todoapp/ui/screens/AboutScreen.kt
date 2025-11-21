package com.example.todoapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.R
import com.example.todoapp.data.techLinks
import com.example.todoapp.util.openUrl

@Composable
fun AboutScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.outline_fact_check_24),
                contentDescription = "Логотип приложения",
                modifier = Modifier
                    .size(72.dp)
                    .padding(end = 8.dp),
                tint = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = "О приложении",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        AboutItem(R.drawable.android_name, "Название", "Todo App")
        AboutItem(R.drawable.upload_file, "Версия", "1.0.0")
        AboutItem(R.drawable.person, "Автор", "Norjex")
        AboutItem(R.drawable.calendar_month, "Год", "2025")

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Приложение для управления задачами.",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "Реализовано с использованием:",
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            techLinks.forEach { (techName, url) ->
                Text(
                    text = "• $techName",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier
                        .padding(vertical = 2.dp)
                        .clickable { (openUrl(context, url)) }
                        .fillMaxWidth(),
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}

@Composable
private fun AboutItem(icon: Int, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(end = 8.dp)
            )
            Text(
                text = label,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Text(
            text = value,
            color = MaterialTheme.colorScheme.primary
        )
    }
}