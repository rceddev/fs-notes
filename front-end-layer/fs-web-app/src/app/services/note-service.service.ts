import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  constructor() { }

  notes : {id:number, title: string, content:string}[] = [
    {id:1, title: "Note 1", content: "he Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan. A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally bred for hunting." },
    {id:2, title: "Note 2", content: "he Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan. A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally bred for hunting." },
    {id:3, title: "Note 3", content: "he Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan. A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally bred for hunting." },
    {id:4, title: "Note 4", content: "he Shiba Inu is the smallest of the six original and distinct spitz breeds of dog from Japan. A small, agile dog that copes very well with mountainous terrain, the Shiba Inu was originally bred for hunting." },    
  ];

  getNotes(): {id:number, title: string, content:string}[]{
    //TODO Fetch notes from backend
    return this.notes;
  }

  createNote(id:number, title:string, content:string){
    this.notes.push({id:id, title: title, content: content});
  }

}
