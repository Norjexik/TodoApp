package com.example.todoapp.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoapp.model.TodoItem
import kotlinx.coroutines.delay

@Composable
fun SwipeToDelete(
    item: TodoItem,
    onRemove: (Long) -> Unit,
    content: @Composable () -> Unit
) {
    var isDismissing by remember { mutableStateOf(false) }

    if (isDismissing) {
        LaunchedEffect(item.id) {
            delay(300)
            onRemove(item.id)
        }
    }

    AnimatedVisibility(
        visible = !isDismissing,
        exit = slideOutHorizontally(tween(300)) + fadeOut(tween(300))
    ) {
        val state = rememberSwipeToDismissBoxState()

        LaunchedEffect(state.currentValue) {
            if (state.currentValue != SwipeToDismissBoxValue.Settled && !isDismissing) {
                isDismissing = true
            }
        }

        SwipeToDismissBox(
            state = state,
            modifier = Modifier.fillMaxWidth(),
            backgroundContent = {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.error),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(end = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Удалить задачу",
                            tint = MaterialTheme.colorScheme.onError,
                            modifier = Modifier.padding(end = 16.dp)
                        )
                    }
                }
            }
        ) {
            content()
        }
    }
}


