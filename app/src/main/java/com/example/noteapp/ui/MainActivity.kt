package com.example.noteapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteapp.data.local.NoteDatabase
import com.example.noteapp.repository.NoteRepository
import com.example.noteapp.ui.screens.AddNoteScreen
import com.example.noteapp.ui.screens.NoteListScreen
import com.example.noteapp.ui.theme.NoteAppTheme
import com.example.noteapp.viewmodel.NoteViewModel
import com.example.noteapp.viewmodel.NoteViewModelFactory

class MainActivity : ComponentActivity() {

    private val noteViewModel: NoteViewModel by lazy {
        val noteDao = NoteDatabase.getDatabase(this).noteDao()
        val repository = NoteRepository(noteDao)
        val factory = NoteViewModelFactory(repository)
        ViewModelProvider(this, factory)[NoteViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NoteAppTheme {
                val navController = rememberNavController()

                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            navController.navigate("add_note")
                        }) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
                        }
                    }
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = "note_list",
                        modifier = Modifier.padding(padding)
                    ) {
                        composable("note_list") {
                            NoteListScreen(
                                navController = navController,
                                noteViewModel = noteViewModel
                            )
                        }
                        composable(
                            route = "add_note?noteId={noteId}",
                            arguments = listOf(
                                navArgument("noteId") {
                                    type = NavType.IntType
                                    defaultValue = -1 // use -1 to indicate 'new note'
                                    nullable = false
                                }
                            )
                        ) { backStackEntry ->
                            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
                            AddNoteScreen(
                                navController = navController,
                                noteViewModel = noteViewModel,
                                noteId = noteId
                            )
                        }
                    }
                }
            }

        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}