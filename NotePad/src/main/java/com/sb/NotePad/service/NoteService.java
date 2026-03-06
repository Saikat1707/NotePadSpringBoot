package com.sb.NotePad.service;

import com.sb.NotePad.dto.NoteDTO;

import java.util.List;

public interface NoteService {
    List<NoteDTO> getAllNotesForUser(int userID);
    NoteDTO createNote(NoteDTO noteDTO);
    NoteDTO getNoteById(int noteId);
    List<NoteDTO> getAllNotes();
}
