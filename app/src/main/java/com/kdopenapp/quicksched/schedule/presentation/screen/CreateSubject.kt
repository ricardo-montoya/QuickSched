package com.kdopenapp.quicksched.schedule.presentation.screen

import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kdopenapp.quicksched.schedule.common.AppColors
import com.kdopenapp.quicksched.schedule.common.Days
import com.kdopenapp.quicksched.schedule.domain.subject.model.SubjectLocal
import com.kdopenapp.quicksched.schedule.domain.timeblock.TimeBlock
import com.kdopenapp.quicksched.schedule.presentation.components.timeFormattedNumber
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateSubject(
    create: (SubjectLocal) -> Unit
) {
    val dayList = Days.values()
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var subjectDays by remember { mutableStateOf(emptyList<Days>()) }
    var color by remember { mutableStateOf(AppColors[1]) }
    var hourStart by remember { mutableStateOf(99) }
    var minuteStart by remember { mutableStateOf(99) }
    var hourEnd by remember { mutableStateOf(99) }
    var minuteEnd by remember { mutableStateOf(99) }
    var isDone by remember { mutableStateOf(false)}
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            modifier = Modifier.padding(20.dp),
            text = "Create a subject",
            style = MaterialTheme.typography.headlineMedium
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Name") },
                    value = name,
                    onValueChange = { name = it })
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Description") },
                    value = description,
                    onValueChange = { description = it })
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = "Days",
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    dayList.forEach { dayOfButton ->
                        DayChip(day = dayOfButton, remove = {
                            subjectDays = if (subjectDays.contains(dayOfButton)) {
                                subjectDays.filter { it != dayOfButton }
                            } else {
                                subjectDays + dayOfButton
                            }
                        })
                    }
                }
                TimeSegment(onTimeChange = {returnedHour, returnedMinute ->
                    hourStart = returnedHour
                    minuteStart = returnedMinute
                }, label =  "Start Time")
                Spacer(modifier = Modifier.height(10.dp).background(MaterialTheme.colorScheme.background))
                TimeSegment(onTimeChange = {returnedHour, returnedMinute ->
                    hourEnd= returnedHour
                    minuteEnd = returnedMinute
                }, label =  "End Time")

                color?.let { ColorSelectorText(color = it) }
                ColorSelector() { it ->
                    color = it
                }
                DoneButton(
                    name = name,
                    subjectDays = subjectDays,
                    hourStart = hourStart,
                    minuteStart = minuteStart,
                    hourEnd = hourEnd,
                    minuteEnd = minuteStart
                ){
                    isDone = true
                    val subjectCreated = createSubjectWithSelectedParameters(name, description,
                        AppColors.entries.find { color == it.value }?.key!!,
                        subjectDays, hourStart, minuteStart, hourEnd, minuteEnd
                    )
                    create(subjectCreated)
                }
            }
        }
    }
    if(isDone) {
        Box(modifier = Modifier.fillMaxSize()) {
            LinearProgressIndicator()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayChip(
    day: Days,
    remove: () -> Unit
) {
    var selected by remember { mutableStateOf(false) }
    FilterChip(
        modifier = Modifier.padding(5.dp),
        selected = selected,
        onClick = {
            selected = !selected
            remove()
        },
        label = { Text(text = day.name.substring(range = 0..2)) },
        shape = CircleShape
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorSelectorText(
    color: Color
) {
    Card(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
            .height(60.dp)
            .background(MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = color)
                .padding(10.dp)
        ) {
            Text(
                text = "Select a color",
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorSelector(
    changeColor: (Color) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(vertical = 1.dp)
            .fillMaxWidth()
            .height(60.dp)
            .background(MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            AppColors.forEach {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight((1.0f / 6.0f))
                        .background(color = it.value)
                        .clickable {
                            changeColor(it.value)
                        }
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeSegment(
    onTimeChange: (Int, Int) -> Unit,
    label : String
) {

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]
    val time = remember { mutableStateOf("") }
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hour: Int, minute: Int ->
            time.value = timeFormattedNumber(hour) + ':' + timeFormattedNumber(minute)
            onTimeChange(hour, minute)
        }, hour, minute, true
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        onClick = {
            timePickerDialog.show()
        }
    ) {
        Text(
            text = "${label}: ${time.value}",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun DoneButton(
    name : String,
    subjectDays: List<Days>,
    hourStart : Int,
    minuteStart  :Int,
    hourEnd : Int,
    minuteEnd : Int,
    done : () -> Unit
){
    var state by remember { mutableStateOf(false) }
    if(name.length>1 &&
        subjectDays.isNotEmpty() &&
        hourStart in (0..24) && minuteStart in (0..60) &&
            hourEnd in (0..24) && minuteEnd in (0..60)){
        true.also { state = it }
    } else {
        state = false
    }
    Button(
        onClick = { done()},
        enabled = state,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Done!", style = MaterialTheme.typography.bodyLarge)
    }
}

fun createSubjectWithSelectedParameters(
    name : String,
    description : String,
    color : Int,
    subjectDays : List<Days>,
    hourStart: Int,
    minuteStart: Int,
    hourEnd : Int,
    minuteEnd : Int
) : SubjectLocal{
    val timeBlock = TimeBlock(
        hourStart = hourStart,
        minuteStart = minuteStart,
        hourEnd = hourEnd,
        minuteEnd = minuteEnd,
        days = subjectDays
    )
    return SubjectLocal(
        id = null,
        name = name,
        description = description,
        color = color,
        timeBlocks = listOf(timeBlock)
    )
}