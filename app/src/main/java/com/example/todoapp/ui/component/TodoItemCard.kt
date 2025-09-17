package com.example.todoapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todoapp.model.TodoItem

@Composable
fun TodoItemCard(
    item: TodoItem,
    onToggle: (Int) -> Unit,
    onRemove: (Int) -> Unit,
    onEdit: (TodoItem) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(0xFFF8F8F8), RoundedCornerShape(8.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = item.isDone,
            onCheckedChange = { onToggle(item.id) }
        )

        Text(
            text = item.title,
            modifier = Modifier
                .weight(1f)
                .clickable { onEdit(item) },
            style = if (item.isDone) MaterialTheme.typography.bodySmall.copy(
                textDecoration = TextDecoration.LineThrough,
                color = Color.Gray
            ) else MaterialTheme.typography.bodyLarge
        )

        IconButton(onClick = { onEdit(item) }) {
            Icon(Icons.Default.Edit, contentDescription = "Редактировать")
        }

        IconButton(onClick = { onRemove(item.id) }) {
            Icon(Icons.Default.Delete, contentDescription = "Удалить")
        }
    }
}