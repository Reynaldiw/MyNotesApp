package com.reynaldiwijaya.mynotesapp;

import com.reynaldiwijaya.mynotesapp.entity.Note;

import java.util.ArrayList;

public interface LoadNotesCallback {
    void preExecute();
    void postExecute(ArrayList<Note> notes);
}
