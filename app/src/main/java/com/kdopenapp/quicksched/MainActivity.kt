package com.kdopenapp.quicksched

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kdopenapp.quicksched.schedule.common.Days
import com.kdopenapp.quicksched.schedule.presentation.ScheduleViewModel
import com.kdopenapp.quicksched.schedule.presentation.navigation.NavigationHost
import com.kdopenapp.quicksched.schedule.presentation.screen.ScheduleTabs
import com.kdopenapp.quicksched.schedule.presentation.subject.SubjectState
import com.kdopenapp.quicksched.ui.theme.QuickSchedTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuickSchedTheme {
                val vm: ScheduleViewModel = hiltViewModel()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationHost()
                }
            }
        }
    }
}
