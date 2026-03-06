package com.sb.NotePad.controller;

import com.sb.NotePad.dto.ApiResponseDTO;
import com.sb.NotePad.dto.NoteDTO;
import com.sb.NotePad.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping("/")
    public ResponseEntity<ApiResponseDTO<List<NoteDTO>>> getAllNotes() {

        List<NoteDTO> noteDTOList = noteService.getAllNotes();

        ApiResponseDTO<List<NoteDTO>> responseDTO =
                new ApiResponseDTO<>(true, "Notes retrieved successfully", noteDTOList);

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<ApiResponseDTO<NoteDTO>> createNote(
            @RequestBody NoteDTO noteDTO,
            @PathVariable int userId
    ) {

        NoteDTO createdNote = noteService.createNote(noteDTO, userId);

        ApiResponseDTO<NoteDTO> responseDTO =
                new ApiResponseDTO<>(true, "Note created successfully", createdNote);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<ApiResponseDTO<NoteDTO>> getNoteById(
            @PathVariable int noteId
    ) {

        NoteDTO noteDTO = noteService.getNoteById(noteId);

        ApiResponseDTO<NoteDTO> responseDTO =
                new ApiResponseDTO<>(true, "Note retrieved successfully", noteDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/update/{noteId}")
    public ResponseEntity<ApiResponseDTO<NoteDTO>> updateNote(
            @RequestBody NoteDTO noteToUpdate,
            @PathVariable int noteId
    ) {

        NoteDTO noteDTO = noteService.updateNote(noteToUpdate, noteId);

        ApiResponseDTO<NoteDTO> responseDTO =
                new ApiResponseDTO<>(true, "Note updated successfully", noteDTO);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/get-all/{userId}")
    public ResponseEntity<ApiResponseDTO<List<NoteDTO>>> getNoteForUser(
            @PathVariable int userId
    ) {

        List<NoteDTO> noteDTOList = noteService.getAllNotesForUser(userId);

        ApiResponseDTO<List<NoteDTO>> responseDTO =
                new ApiResponseDTO<>(true, "User notes retrieved successfully", noteDTOList);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{noteId}")
    public ResponseEntity<ApiResponseDTO<Boolean>> deleteNote(
            @PathVariable int noteId
    ) {

        boolean isDeleted = noteService.deleteNote(noteId);

        ApiResponseDTO<Boolean> responseDTO =
                new ApiResponseDTO<>(true, "Note deleted successfully", isDeleted);

        return ResponseEntity.ok(responseDTO);
    }

}