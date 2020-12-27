import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Resume} from "../models/resume";

@Injectable({
  providedIn: 'root'
})
export class ResumeService {

  private resumesUrl = 'http://localhost:8080/resumes';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  getResumes(): Observable<Resume[]> {
    return this.http.get<Resume[]>(this.resumesUrl);
  }
}
