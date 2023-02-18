package com.kdopenapp.quicksched.schedule.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kdopenapp.quicksched.schedule.common.Days
import com.kdopenapp.quicksched.schedule.presentation.navigation.Destinations

@Composable
fun ScheduleTabs(
    selectedTab: (Int) -> Unit,
    navigate: (String) -> Unit
) {
    var tabIndex by remember { mutableStateOf(0) } // 1.
    val tabTitles = Days.values().map { it.name }
    Column() {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(start = 5.dp, top = 3.dp, bottom = 0.dp)
        ) {
            Text(text = "QuickSched!", fontWeight = FontWeight.Bold, modifier = Modifier.padding(0.dp))
            IconButton(onClick = { navigate(Destinations.CreateSubject.route)}
                , modifier = Modifier.padding(vertical = 0.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Subject")
            }
        }
        ScrollableTabRow( selectedTabIndex = tabIndex, modifier = Modifier.padding(0.dp)) { // 3.
            tabTitles.forEachIndexed { index, title ->
                Tab(selected = tabIndex == index, // 4.
                    onClick = {
                        tabIndex = index
                        selectedTab(tabIndex)
                    },
                    text = { Text(text = title) }) // 5.
            }
        }
    }
}