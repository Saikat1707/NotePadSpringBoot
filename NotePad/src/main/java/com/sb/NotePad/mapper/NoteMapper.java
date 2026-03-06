package com.sb.NotePad.mapper;

import com.sb.NotePad.dto.NoteDTO;
import com.sb.NotePad.entities.Note;

public class NoteMapper {
    public static Note toNote(NoteDTO noteDTO){
        Note note = new Note();

        note.setTitle(noteDTO.getTitle());
        note.setDesc(noteDTO.getDesc());

        return note;
    }
    public static NoteDTO toNoteDto(Note note){
        NoteDTO noteDTO = new NoteDTO();

        noteDTO.setTitle(note.getTitle());
        noteDTO.setDesc(note.getDesc());
        noteDTO.setCreatedByUserName(note.getUser().getName());
        noteDTO.setCreatedByUserEmail(note.getUser().getEmail());
        noteDTO.setUserId(note.getUser().getId());

        return noteDTO;
    }
}
