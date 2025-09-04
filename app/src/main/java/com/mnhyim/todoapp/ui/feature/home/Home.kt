package com.mnhyim.todoapp.ui.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mnhyim.todoapp.domain.model.Todo

@Composable
fun Home(
    modifier: Modifier = Modifier
) {
    val dummyItems = listOf(
        Todo(
            userId = 1,
            id = 1,
            title = "Dummy 1",
            completed = false
        ),
        Todo(
            userId = 2,
            id = 2,
            title = "Dummy 2",
            completed = true
        ),
        Todo(
            userId = 3,
            id = 3,
            title = "Dummy 3",
            completed = false
        )
    )
    HomeScreen(
        items = dummyItems,
        modifier = modifier
    )
}

@Composable
private fun HomeScreen(
    items: List<Todo>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(5) {
                Card {
                    Text(
                        text = "Category $it",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            items(items = items) { item ->
                TodoItem(item = item, onCheckItem = {})
            }
        }
    }
}

@Composable
fun TodoItem(
    item: Todo,
    onCheckItem: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    /* TODO: Change to use real data from model later on */
    Card {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp,8.dp,8.dp,8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium
            )
            Checkbox(
                checked = item.completed,
                onCheckedChange = { onCheckItem(item.id) },
            )
        }
    }
}