package com.example.todoapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTodoModal(
    task: String,
    onDismiss: () -> Unit,
    onTaskSave: (String) -> Unit,
    sheetState: SheetState,
) {
    var editedTitle by remember { mutableStateOf(task) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = null,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(Modifier.padding(20.dp)) {
            Text("Редактировать задачу", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = editedTitle,
                onValueChange = { editedTitle = it },
                label = { Text("Текст задачи") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(24.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Отмена")
                }
                Button(
                    onClick = {
                        if (editedTitle.isNotBlank()) onTaskSave(editedTitle)
                        onDismiss()
                    },
                    enabled = editedTitle.isNotBlank(),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Check, contentDescription = "Сохранить")
                }
            }
        }
    }
}