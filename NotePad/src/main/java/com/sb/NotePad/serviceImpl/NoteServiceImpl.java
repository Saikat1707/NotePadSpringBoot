package com.sb.NotePad.serviceImpl;

import com.sb.NotePad.dto.NoteDTO;
import com.sb.NotePad.entities.Note;
import com.sb.NotePad.entities.User;
import com.sb.NotePad.mapper.NoteMapper;
import com.sb.NotePad.repository.NoteRepo;
import com.sb.NotePad.repository.UserRepo;
import com.sb.NotePad.service.NoteService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    NoteRepo noteRepo;

    @Override
    public List<NoteDTO> getAllNotesForUser(int userID) {
        User user = userRepo.findById(userID)
                .orElseThrow(()->new RuntimeException("Resource Not Found"));

        List<Note> notes = user.getNotes();
        List<NoteDTO> noteDTOList = notes
                .stream().map(NoteMapper::toNoteDto)
                .toList();
        return noteDTOList;
    }

    @Override
    public NoteDTO createNote(NoteDTO noteDTO,int userId) {
        Note note = NoteMapper.toNote(noteDTO);
        User user = userRepo.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found"));
        note.setUser(user);

        Note savedNote = noteRepo.save(note);
        return NoteMapper.toNoteDto(savedNote);
    }

    @Override
    public NoteDTO getNoteById(int noteId) {
        Note note = noteRepo.findById(noteId)
                .orElseThrow(()->new RuntimeException("Resource not found"));

        return NoteMapper.toNoteDto(note);
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        List<Note> noteList = noteRepo.findAll();
        List<NoteDTO> noteDTOList =
                noteList.stream()
                        .map(NoteMapper::toNoteDto)
                        .toList();
        return noteDTOList;
    }

    @Override
    public NoteDTO updateNote(NoteDTO noteDTO, int noteId) {
        Note note = noteRepo.findById(noteId)
                .orElseThrow(()->new RuntimeException("Resource not found"));
        note.setTitle(noteDTO.getTitle());
        note.setDesc(noteDTO.getDesc());

        Note updatedNote=noteRepo.save(note);
        return NoteMapper.toNoteDto(updatedNote);
    }

    @Override
    public boolean deleteNote(int noteId) {

        Note note = noteRepo.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        noteRepo.delete(note);
        return true;
    }
}
