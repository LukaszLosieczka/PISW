var form = document.getElementById("inputForm");
var formTitle = document.getElementById("title");
var noteName = document.getElementById("name");
var noteContent = document.getElementById("content");
var notesList = document.getElementById("notesList");
var currentNoteId = null;

//window.localStorage.clear();

form.addEventListener('submit', saveNote);
loadNotes();

function editNote(noteId){
    var notes = JSON.parse(window.localStorage.getItem("notesList"));
    currentNoteId = noteId;
    formTitle.innerHTML = "Edit note";
    var note = getNoteById(notes, noteId);
    noteName.value = note.name;
    noteContent.value = note.content;
}

function saveNote(event){
    event.preventDefault();
    var note;
    var notes = JSON.parse(window.localStorage.getItem("notesList"));
    if(notes === null){
        notes = [];
    }
    if(currentNoteId === null){
        note = createNewNote();
        notes.push(note);
        addNoteToList(note);
    }
    else{
        note = getNoteById(notes, currentNoteId);
        note.name = noteName.value;
        note.content = noteContent.value;
        document.getElementById(note.id).innerHTML = note.name;
    }

    window.localStorage.setItem("notesList", JSON.stringify(notes));

    form.reset()
    formTitle.innerHTML = "Add new note";
    currentNoteId = null;
}

function getNoteById(notes, noteId){
    return notes.find(note => {
        return note.id == noteId
      });
}

function createNewNote(){
    var note = { 
        id: Date.now().toString(),
        name: noteName.value,
        content: noteContent.value
     }; 
    return note;
}

function loadNotes(){
    const notes = JSON.parse(window.localStorage.getItem("notesList"));
    notes.forEach(note => addNoteToList(note));
}

function addNoteToList(note){
    var button = document.createElement("button");
    button.innerHTML = note.name;
    button.setAttribute("id", note.id);
    button.setAttribute("type", "button");
    button.setAttribute("class", "list-group-item list-group-item-action");
    button.onclick = function() {editNote(note.id)};
    notesList.prepend(button);
}