package com.mnhyim.todoapp.ui.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mnhyim.todoapp.domain.model.Todo
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Home(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val todos by viewModel.todos.collectAsStateWithLifecycle()

    HomeScreen(
        items = todos,
        onCheckItem = { viewModel.checkItem(it) },
        onRefresh = viewModel::fetchTodos,
        modifier = modifier
    )
    if (state.isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun HomeScreen(
    items: List<Todo>,
    onCheckItem: (Todo) -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Button(
            onClick = onRefresh,
            modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 0.dp)
        ) {
            Text("REFRESH")
        }
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            repeat(7) {
                Card(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text("May", style = MaterialTheme.typography.labelSmall)
                        Text("23", style = MaterialTheme.typography.titleMedium)
                        Text("Sat", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(5) {
                Card(onClick = {}) {
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
                TodoItem(item = item, onCheckItem = onCheckItem)
            }
        }
    }
}

@Composable
fun TodoItem(
    item: Todo,
    onCheckItem: (Todo) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp, 8.dp, 8.dp, 8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Checkbox(
                checked = item.completed,
                onCheckedChange = { onCheckItem(item) },
            )
        }
    }
}